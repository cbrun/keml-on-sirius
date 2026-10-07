package keml.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import keml.diagram.KemlDiagramConfiguration;

/**
 * Entry point for a Sirius Web application with a compiled EMF domain and a Java-defined view.
 * The starter's auto-configuration supplies the standard Sirius Web services. Spring scans
 * this package; the explicit import adds the reusable diagram module outside that package.
 * To create another domain-specific app, replace these KEML contributions while keeping
 * the Sirius Web starter and frontend dependencies.
 */
@SpringBootApplication
@Import(KemlDiagramConfiguration.class)
public class KemlApplication {

    public static void main(String[] args) {
        SpringApplication.run(KemlApplication.class, args);
    }
}
