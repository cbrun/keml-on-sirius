/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package keml.diagram;

import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IEditingContextPersistenceService;
import org.eclipse.sirius.components.emf.ResourceMetadataAdapter;
import org.eclipse.sirius.components.emf.services.JSONResourceFactory;
import org.eclipse.sirius.components.emf.services.api.IEMFEditingContext;
import org.eclipse.sirius.components.events.ICause;
import org.eclipse.sirius.web.application.project.services.api.ISemanticDataInitializer;
import org.springframework.stereotype.Service;

/**
 * Initializes new KEML projects with an example conversation.
 */
@Service
public class KemlProjectTemplateInitializer implements ISemanticDataInitializer {

    private final IEditingContextPersistenceService editingContextPersistenceService;

    public KemlProjectTemplateInitializer(IEditingContextPersistenceService editingContextPersistenceService) {
        this.editingContextPersistenceService = Objects.requireNonNull(editingContextPersistenceService);
    }

    @Override
    public boolean canHandle(String projectTemplateId) {
        return KemlProjectTemplateProvider.TEMPLATE_ID.equals(projectTemplateId);
    }

    @Override
    public void handle(ICause cause, IEditingContext editingContext, String projectTemplateId) {
        if (this.canHandle(projectTemplateId) && editingContext instanceof IEMFEditingContext emfEditingContext) {
            var resource = new JSONResourceFactory().createResourceFromPath(UUID.randomUUID().toString());
            resource.eAdapters().add(new ResourceMetadataAdapter("KEML conversation"));
            resource.getContents().add(new KemlServices().kemlExampleConversation());
            emfEditingContext.getDomain().getResourceSet().getResources().add(resource);
            this.editingContextPersistenceService.persist(cause, editingContext);
        }
    }
}
