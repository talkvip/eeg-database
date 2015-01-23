/**
 * *****************************************************************************
 * This file is part of the EEG-database project
 *
 * ==========================================
 *
 * Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 *  ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 *  ***********************************************************************************************************************
 *
 * DownloadRequestAttachmentPanel.java, 2013/10/02 00:01 Jakub Rinkes
 * ****************************************************************************
 */
package cz.zcu.kiv.eegdatabase.wui.ui.licenses.components;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.ByteArrayResource;

import cz.zcu.kiv.eegdatabase.data.pojo.PersonalLicense;

/**
 * Panel with confirm link for the table (or any repeater) of PersonalLicense objects.
 * 
 * The link confirms the license request.
 * 
 * @author Jakub Danek
 */
public class DownloadRequestAttachmentPanel extends Panel {

    private static final long serialVersionUID = -3929422353082732773L;

    public DownloadRequestAttachmentPanel(String id, IModel<PersonalLicense> request) {
        super(id);

        boolean isContent = request.getObject() != null && request.getObject().getAttachmentContent() != null;
        ByteArrayResource res;
        if (isContent) {
            try {
                res = new ByteArrayResource("", request.getObject().getAttachmentContent().getBytes(0, (int) request.getObject().getAttachmentContent().length()), request.getObject()
                        .getAttachmentFileName());
            } catch (SQLException ex) {
                Logger.getLogger(ViewLicensePanel.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        } else {
            res = new ByteArrayResource("");
        }
        add(new ResourceLink<Void>("link", res).setVisibilityAllowed(isContent));

    }
}
