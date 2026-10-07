package keml.app;

import java.util.UUID;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.components.collaborative.api.ChangeDescription;
import org.eclipse.sirius.components.collaborative.api.IEditingContextEventHandler;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IInput;
import org.eclipse.sirius.components.core.api.IObjectSearchService;
import org.eclipse.sirius.components.core.api.IPayload;
import org.springframework.stereotype.Service;

import keml.Conversation;
import reactor.core.publisher.Sinks;

/**
 * Read the live model on Sirius's editing-context thread, just like an editing tool.
 * Only copying runs here: expensive analysis and file generation run on the HTTP thread.
 */
@Service
public class KemlAnalysisSnapshotHandler implements IEditingContextEventHandler {
    public record Input(UUID id, String conversationId) implements IInput { }
    public record Payload(UUID id, Conversation conversation) implements IPayload { }

    private final IObjectSearchService objects;

    public KemlAnalysisSnapshotHandler(IObjectSearchService objects) {
        this.objects = objects;
    }

    @Override
    public boolean canHandle(IEditingContext editingContext, IInput input) {
        return input instanceof Input;
    }

    @Override
    public void handle(Sinks.One<IPayload> payloadSink, Sinks.Many<ChangeDescription> changeDescriptionSink,
            IEditingContext editingContext, IInput input) {
        try {
            var request = (Input) input;
            var copy = objects.getObject(editingContext, request.conversationId())
                    .filter(Conversation.class::isInstance).map(Conversation.class::cast)
                    .map(EcoreUtil::copy).orElse(null);
            payloadSink.tryEmitValue(new Payload(input.id(), copy));
            // This is a read-only operation: do not emit a change or save the user's model.
        } catch (RuntimeException exception) {
            payloadSink.tryEmitError(exception);
        }
    }
}
