package keml.io;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emfcloud.jackson.resource.JsonResourceFactory;

import keml.Conversation;
import keml.KemlPackage;

public class KemlFileHandler {
	
	private ResourceSetImpl resS;	
	private ResourceSetImpl resSJSON;
	
	public KemlFileHandler() {
		resS = initializeKemlXMIResourceSet();
		resSJSON = initializeKEMLJSONResourceSet();
	}

	private ResourceSetImpl initializeKemlXMIResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		ResourceSetImpl resSet = new ResourceSetImpl();
		resSet.getPackageRegistry().put(KemlPackage.eNS_URI, KemlPackage.eINSTANCE);
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("keml", new XMIResourceFactoryImpl());
		return resSet;
	}
	
	private ResourceSetImpl initializeKEMLJSONResourceSet() {
		ResourceSetImpl resSetJSON = new ResourceSetImpl();
		resSetJSON.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new JsonResourceFactory());
		// register ecore package for standalone (outside eclipse) execution
		//
		resSetJSON.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		return resSetJSON;
	}
	
	public void saveKeml(Conversation conv, String path) {
		Resource resource = resS.createResource(URI.createFileURI(path));
		resource.getContents().add(conv);

		try {
			resource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveKemlJSON(Conversation conv, String path) {
		Resource resource = resSJSON.createResource(URI.createFileURI(path));
		resource.getContents().add(conv);

		try {
			resource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Conversation loadKeml(String path) {
		Resource resource = resS.getResource(URI.createFileURI(path), true);
		return (Conversation) resource.getContents().get(0);		
	}
	
	public Conversation loadKemlJSON(String path) {
		Resource resource = resSJSON.getResource(URI.createFileURI(path), true);
		return (Conversation) resource.getContents().get(0);
	}

}
