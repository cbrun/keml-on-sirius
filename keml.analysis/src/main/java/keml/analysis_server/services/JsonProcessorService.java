package keml.analysis_server.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import keml.analysis.AnalysisProvider;
import keml.analysis_server.utils.ExecutionMode;

@Service
public class JsonProcessorService {
	private final ObjectMapper om = new ObjectMapper();
	private final String DEFAULT_PATH = "../keml.sample/introductoryExamples";

	public byte[] processJsonAndReturn(JsonNode inputJson, String timestamp, boolean runFurtherAnalysis, String basePath, ExecutionMode executionMode)
			throws IOException {
		if (basePath == null) {
			basePath = DEFAULT_PATH;
			if (executionMode == ExecutionMode.JAR) {
				basePath = "../" + basePath;
			}
		} 		
		Path filePath = saveJsonToFile(inputJson, timestamp, basePath);
		String analysisFiles = AnalysisProvider.runAnalysis(filePath, runFurtherAnalysis, basePath, executionMode);
		deleteFile(filePath.toString());
		Path zipPath = zipAnalysisFiles(analysisFiles, basePath);
		byte[] zipBytes = Files.readAllBytes(zipPath);
		deleteFile(zipPath.toString());
		FileUtils.deleteDirectory(new File(analysisFiles));
		return zipBytes;
	}

	private Path saveJsonToFile(JsonNode json, String timestamp, String basePath) throws IOException {
		Path dir = Paths.get(basePath + "/keml");
		Files.createDirectories(dir);
		Path filePath = dir.resolve("input_" + timestamp + ".json");
		om.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), json);
		return filePath;
	}

	private Path zipAnalysisFiles(String analysisFiles, String basePath) throws IOException {
		Path dir = Paths.get(basePath + "/zipped");
		Files.createDirectories(dir);
		Path analysisFilesPath = Paths.get(analysisFiles);
		String target = basePath + "/zipped/" + analysisFilesPath.getFileName() + ".zip";
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
			Files.walk(analysisFilesPath).filter(p -> !Files.isDirectory(p)).forEach(p -> {
				ZipEntry zipEntry = new ZipEntry(analysisFilesPath.relativize(p).toString());
				try {
					zos.putNextEntry(zipEntry);
					Files.copy(p, zos);
					zos.closeEntry();
				} catch (IOException e) {
					System.err.println("Fehler beim Hinzufügen von Datei: " + p + " – " + e);
				}
			});
		}
		return Paths.get(target);
	}

	private boolean deleteFile(String filePath) {
		File f = new File(filePath);
		return f.delete();
	}
}
