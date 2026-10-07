package keml.diagram;

import java.util.List;

import org.eclipse.sirius.components.view.View;
import org.eclipse.sirius.components.view.emf.IJavaServiceProvider;
import org.springframework.context.annotation.Configuration;

/** Exposes the KEML semantic operations to the view's AQL expressions. */
@Configuration
public class KemlJavaServiceProvider implements IJavaServiceProvider {
    @Override
    public List<Class<?>> getServiceClasses(View view) {
        return view.getDescriptions().stream().anyMatch(description -> KemlViews.DIAGRAM_NAME.equals(description.getName()))
                ? List.of(KemlServices.class) : List.of();
    }
}
