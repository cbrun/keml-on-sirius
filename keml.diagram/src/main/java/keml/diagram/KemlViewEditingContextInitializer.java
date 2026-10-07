package keml.diagram;

import java.util.Objects;

import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IEditingContextProcessor;
import org.eclipse.sirius.web.application.editingcontext.EditingContext;
import org.eclipse.sirius.components.view.View;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/** Makes the diagram available for KEML conversations in every workbench. */
@Service
public class KemlViewEditingContextInitializer implements IEditingContextProcessor {
    private final View view;

    public KemlViewEditingContextInitializer(@Qualifier("kemlView") View view) {
        this.view = Objects.requireNonNull(view);
    }

    @Override
    public void preProcess(IEditingContext editingContext) {
        if (editingContext instanceof EditingContext context && !context.getViews().contains(this.view)) {
            context.getViews().add(this.view);
        }
    }
}
