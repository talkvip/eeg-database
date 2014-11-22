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
 *   ShoppingCartPage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.shoppingCart;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.data.pojo.OrderItem;
import cz.zcu.kiv.eegdatabase.logic.eshop.ShoppingCart;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.order.OrderFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.order.OrderDetailPage;
import cz.zcu.kiv.eegdatabase.wui.ui.order.OrderItemPanel;

@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class ShoppingCartPage extends MenuPage {

    private static final long serialVersionUID = 1L;
    
    @SpringBean
    private OrderFacade orderFacade;
    
    public ShoppingCartPage() {
        setupComponents();
    }

    private void setupComponents() {

        IModel<String> title = ResourceUtils.getModel("pageTitle.myCart");
        add(new Label("title", title));
        setPageTitle(title);
        add(new ButtonPageMenu("leftMenu", ShoppingCartPageLeftMenu.values()));

        final ShoppingCart shoppingCart = EEGDataBaseSession.get().getShoppingCart();

        // empty cart
        add(new Label("emptyCart", ResourceUtils.getModel("text.emptyCart")) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return shoppingCart.isEmpty();
            }
        });

        PropertyListView<OrderItem> items = new PropertyListView<OrderItem>("items", shoppingCart.getOrder().getItems()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<OrderItem> item) {

                item.add(new Label("id", item.getModel().getObject().getId()));
                item.add(new OrderItemPanel("item", item.getModel()));
                item.add(new Label("price", item.getModel().getObject().getPrice()));
                item.add(new RemoveLinkPanel("removeItemLink", item.getModel()));

            }

            @Override
            public boolean isVisible() {
                return !shoppingCart.isEmpty();
            }
        };
        add(items);

        add(new Label("totalPriceMessage", ResourceUtils.getString("label.totalPrice") + " "));
        add(new Label("totalPriceAmount", new Model<Serializable>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Serializable getObject() {

                String totalPrice = "" + shoppingCart.getTotalPrice();
                return totalPrice;
            }

        }));
        add(new Label("totalPriceCurrency", " " + ResourceUtils.getString("currency.euro")));

        // XXX PAYPAL payment disabled - not necessary now.
        /*
        add(new Link<Void>("PayPalExpressCheckoutLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {

                if (!shoppingCart.isEmpty()) {
                    setResponsePage(new RedirectPage(PayPalTools.setExpressCheckout()));
                } else {
                    // Partially fixes trouble with browser caching and back button
                    setResponsePage(ShoppingCartPage.class);
                }

            }
        }.setVisibilityAllowed(!shoppingCart.isEmpty()));
        */
        // For now just create order and show it.
        
        add(new Link<Void>("createOrder"){

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {

                Order order = shoppingCart.getOrder();
                order.setDate(new Timestamp(new Date().getTime()));
                order.setPerson(EEGDataBaseSession.get().getLoggedUser());
                order.setOrderPrice(shoppingCart.getTotalPrice());
                
                Integer orderId = orderFacade.create(order);
                
                EEGDataBaseSession.get().getShoppingCart().clear();
                setResponsePage(OrderDetailPage.class, PageParametersUtils.getDefaultPageParameters(orderId));
            }
            
        }.setVisibilityAllowed(!shoppingCart.isEmpty()));
        
    }

}
