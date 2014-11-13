/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   ExperimentPackageBuyDownloadLinkPanel.java, 2014/13/10 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.shoppingCart;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentPackage;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.page.UnderConstructPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.order.OrderFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.ExperimentsPackageDownloadPage;

public class ExperimentPackageBuyDownloadLinkPanel extends Panel {

    private static final long serialVersionUID = 5458856518415845451L;

    @SpringBean
    private OrderFacade facade;

    public ExperimentPackageBuyDownloadLinkPanel(String id, IModel<ExperimentPackage> model) {
        super(id);
        final ExperimentPackage experimentPck = model.getObject();

        boolean isDownloadVisible = EEGDataBaseSession.get().isExperimentPackagePurchased(experimentPck.getExperimentPackageId());
        boolean inCart = EEGDataBaseSession.get().getShoppingCart().isInCart(experimentPck);
        boolean isAddCartLinkVisible = !inCart && !isDownloadVisible;

        add(new Link<Void>("addToCartLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                EEGDataBaseSession.get().getShoppingCart().addToCart(experimentPck);
            }
        }.setVisibilityAllowed(isAddCartLinkVisible));

        add(new Label("inCart", ResourceUtils.getModel("text.inCart")).setVisibilityAllowed(inCart));
        // "Add to Cart" links are rendered only for experiments that haven't been places in the cart yet.

        BookmarkablePageLink<Void> downloadLink = new BookmarkablePageLink<Void>("downloadLink",
                ExperimentsPackageDownloadPage.class,
                PageParametersUtils.getDefaultPageParameters(experimentPck.getExperimentPackageId()));
        add(downloadLink.setVisibilityAllowed(isDownloadVisible));

    }
}
