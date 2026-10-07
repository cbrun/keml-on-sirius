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

import java.util.List;

import org.eclipse.sirius.web.application.project.services.api.IProjectTemplateProvider;
import org.eclipse.sirius.web.application.project.services.api.ProjectTemplate;
import org.springframework.stereotype.Service;

/**
 * Makes a KEML example conversation available when creating a project.
 */
@Service
public class KemlProjectTemplateProvider implements IProjectTemplateProvider {

    public static final String TEMPLATE_ID = "KEML conversation";

    @Override
    public List<ProjectTemplate> getProjectTemplates() {
        // Sirius serves the standard project-templates classpath folder through /api/images.
        return List.of(new ProjectTemplate(TEMPLATE_ID, "KEML Conversation", "/project-templates/KEML-Conversation.png", List.of()));
    }
}
