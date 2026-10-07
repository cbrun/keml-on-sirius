package keml.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import keml.analysis.AnalysisProvider;

/**
 * Embeds analysis in the Sirius server. The application controller injects this bean
 * for browser results and downloads from snapshots of the editing context.
 * Import the library, not its separate Spring Boot application or REST controller.
 */
@Configuration(proxyBeanMethods = false)
public class KemlAnalysisConfiguration {
    @Bean
    AnalysisProvider kemlAnalysisProvider() {
        return new AnalysisProvider();
    }
}
