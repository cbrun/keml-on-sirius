package keml.app;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import keml.KemlPackage;
import keml.provider.KemlItemProviderAdapterFactory;

/**
 * Bridges the generated EMF plugins to Sirius Web's Spring configuration.
 * Declaring these beans replaces Eclipse extension-registry discovery in a standalone server.
 */
@Configuration(proxyBeanMethods = false)
public class KemlEMFConfiguration {

    /** Sirius collects EPackage beans for resource loading, AQL and model creation. */
    @Bean
    EPackage kemlPackage() {
        return KemlPackage.eINSTANCE;
    }

    /** Sirius uses EMF Edit for explorer labels, icons and editable property descriptions. */
    @Bean
    ComposedAdapterFactory.Descriptor kemlAdapterFactoryDescriptor() {
        return KemlItemProviderAdapterFactory::new;
    }
}
