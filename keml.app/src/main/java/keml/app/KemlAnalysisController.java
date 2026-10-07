package keml.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.sirius.components.collaborative.api.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.components.core.api.IEditingContextSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.TypeMismatchException;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import keml.Conversation;
import keml.analysis.AnalysisProvider;

/** Application endpoints reuse the library rather than launching another analysis server. */
@RestController
@RequestMapping(KemlAnalysisController.BASE_PATH)
public class KemlAnalysisController {
    public static final String BASE_PATH = "/api/editingcontexts/{editingContextId}/keml/conversations/{conversationId}";

    private static final Duration SNAPSHOT_LIFETIME = Duration.ofMinutes(30);
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IEditingContextEventProcessorRegistry processors;
    private final IEditingContextSearchService editingContexts;
    private final AnalysisProvider analysis;
    // ponytail: 32 in-memory snapshots suit this sample; use a shared store for clustered servers.
    private final LinkedHashMap<UUID, Snapshot> snapshots = new LinkedHashMap<>();

    record Snapshot(String editingContextId, String conversationId, Instant createdAt, Conversation conversation) { }
    public record AnalysisResponse(UUID snapshotId, Instant createdAt, AnalysisProvider.Results results) { }

    public KemlAnalysisController(IEditingContextEventProcessorRegistry processors,
            IEditingContextSearchService editingContexts, AnalysisProvider analysis) {
        this.processors = processors;
        this.editingContexts = editingContexts;
        this.analysis = analysis;
    }

    @PostMapping("/analysis")
    public ResponseEntity<AnalysisResponse> analyse(@PathVariable String editingContextId, @PathVariable String conversationId) {
        var conversation = capture(editingContextId, conversationId);
        var createdAt = Instant.now();
        var results = analysis.analyse(conversation);
        var id = UUID.randomUUID();
        remember(id, new Snapshot(editingContextId, conversationId, createdAt, conversation));
        return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(new AnalysisResponse(id, createdAt, results));
    }

    /** Native Details links capture a fresh snapshot for each download. */
    @GetMapping("/reports")
    public ResponseEntity<byte[]> reports(@PathVariable String editingContextId, @PathVariable String conversationId) throws IOException {
        return zip(capture(editingContextId, conversationId));
    }

    /** The dialog downloads exactly the snapshot displayed, even if editing has continued. */
    @GetMapping("/analysis/{snapshotId}/reports")
    public ResponseEntity<byte[]> snapshotReports(@PathVariable String editingContextId,
            @PathVariable String conversationId, @PathVariable UUID snapshotId) throws IOException {
        if (!editingContexts.existsById(editingContextId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found.");
        }
        return zip(retrieve(snapshotId, editingContextId, conversationId).conversation());
    }

    private Conversation capture(String editingContextId, String conversationId) {
        if (!editingContexts.existsById(editingContextId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found.");
        }
        var payload = processors.dispatchEvent(editingContextId,
                new KemlAnalysisSnapshotHandler.Input(UUID.randomUUID(), conversationId)).block(Duration.ofSeconds(30));
        if (payload instanceof KemlAnalysisSnapshotHandler.Payload snapshot && snapshot.conversation() != null) {
            return snapshot.conversation();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found.");
    }

    private synchronized void remember(UUID id, Snapshot snapshot) {
        snapshots.values().removeIf(value -> value.createdAt().plus(SNAPSHOT_LIFETIME).isBefore(Instant.now()));
        snapshots.put(id, snapshot);
        while (snapshots.size() > 32) {
            snapshots.pollFirstEntry();
        }
    }

    private synchronized Snapshot retrieve(UUID id, String editingContextId, String conversationId) {
        var snapshot = snapshots.get(id);
        if (snapshot == null || snapshot.createdAt().plus(SNAPSHOT_LIFETIME).isBefore(Instant.now())) {
            snapshots.remove(id);
            throw new ResponseStatusException(HttpStatus.GONE, "These analysis results expired. Recalculate to download the reports.");
        }
        if (!snapshot.editingContextId().equals(editingContextId) || !snapshot.conversationId().equals(conversationId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Analysis not found.");
        }
        return snapshot;
    }

    private ResponseEntity<byte[]> zip(Conversation conversation) throws IOException {
        Path directory = Files.createTempDirectory("keml-reports-");
        try {
            analysis.writeReports(conversation, directory);
            // Build the complete archive before sending headers, so errors never produce a partial ZIP.
            var bytes = new ByteArrayOutputStream();
            try (var zip = new ZipOutputStream(bytes); var files = Files.list(directory)) {
                for (var file : files.sorted().toList()) {
                    zip.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    Files.copy(file, zip);
                    zip.closeEntry();
                }
            }
            return ResponseEntity.ok().cacheControl(CacheControl.noStore())
                    .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                    .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("keml-analysis.zip").build().toString())
                    .body(bytes.toByteArray());
        } finally {
            FileSystemUtils.deleteRecursively(directory);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail invalidConversation(IllegalArgumentException exception) {
        String detail = exception.getMessage();
        if (detail != null && detail.contains("Endless loop")) {
            detail = "Trust cannot be calculated because the argumentation graph contains a cycle. Remove the circular links and recalculate.";
        }
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, detail);
    }

    /** Return API errors directly: Sirius's SPA error page must not turn them into HTML downloads. */
    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail unavailableAnalysis(ResponseStatusException exception) {
        return ProblemDetail.forStatusAndDetail(exception.getStatusCode(), exception.getReason());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail analysisFailed(Exception exception) {
        if (exception instanceof TypeMismatchException) {
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid analysis identifier.");
        }
        logger.error("KEML analysis failed", exception);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Analysis could not be completed. Please try again.");
    }
}
