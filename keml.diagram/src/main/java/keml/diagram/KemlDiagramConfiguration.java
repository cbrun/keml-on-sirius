package keml.diagram;

import java.util.List;

import org.eclipse.sirius.components.core.api.IImagePathService;
import org.eclipse.sirius.components.view.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** Installs the KEML diagram and its tools in a Sirius Web application. */
@Configuration(proxyBeanMethods = false)
@Import({KemlJavaServiceProvider.class, KemlViewEditingContextInitializer.class,
        KemlDiagramPostProcessor.class, KemlProjectTemplateProvider.class, KemlProjectTemplateInitializer.class})
public class KemlDiagramConfiguration {

    @Bean
    View kemlView() {
        return new KemlViews().create();
    }

    /** Sirius serves diagram images through /api/images; allow access to our classpath folder. */
    @Bean
    IImagePathService kemlImagePaths() {
        return () -> List.of("/images/keml/");
    }
}
