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
 *   ChangePasswordPage.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.ui.account;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.person.PersonFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.account.obj.ChangePasswordObj;

/**
 * Change password page.
 * 
 * @author Jakub Rinkes
 *
 */
@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class ChangePasswordPage extends MenuPage {

    private static final long serialVersionUID = -2098428058200654117L;
    private static final String PARAM_ID = "changeDone";
    
    // this constructor is for page with password change action
    public ChangePasswordPage() {

        setPageTitle(ResourceUtils.getModel("pageTitle.changePassword"));

        add(new Label("headTitle", ResourceUtils.getModel("pageTitle.changePassword")));
        setupComponents(true);
    }
    
    // this constructor is for page with message after password change
    public ChangePasswordPage(PageParameters parameters) {

        StringValue value = parameters.get(PARAM_ID);
        if (!value.isNull() && !value.isEmpty() && value.toString().equals(PARAM_ID)) {
            add(new Label("headTitle", ResourceUtils.getModel("pageTitle.changesWereMade")));
            setupComponents(false);
        } else
            throw new RestartResponseAtInterceptPageException(AccountOverViewPage.class);

    }

    private void setupComponents(boolean visible) {
        add(new ButtonPageMenu("leftMenu", MyAccountPageLeftMenu.values()));

        add(new ChangePasswordForm("form", getFeedback()).setVisibilityAllowed(visible));
    }
    
    // inner form on page used for password change.
    class ChangePasswordForm extends Form<ChangePasswordObj> {

        private static final long serialVersionUID = 1L;

        @SpringBean
        PersonFacade personFacade;

        public ChangePasswordForm(String id, final FeedbackPanel feedback) {
            super(id, new CompoundPropertyModel<ChangePasswordObj>(new ChangePasswordObj()));
            
            PasswordTextField oldPassword = new PasswordTextField("oldPassword");
            oldPassword.setLabel(ResourceUtils.getModel("page.myAccount.currentPassword"));
            oldPassword.setRequired(true);
            oldPassword.add(StringValidator.minimumLength(6));
            FormComponentLabel oldPassLabel = new FormComponentLabel("oldPassLb", oldPassword);
            add(oldPassword, oldPassLabel);

            PasswordTextField newPassword = new PasswordTextField("newPassword");
            newPassword.setLabel(ResourceUtils.getModel("page.myAccount.newPassword"));
            newPassword.setRequired(true);
            newPassword.add(StringValidator.minimumLength(6));
            FormComponentLabel newPassLabel = new FormComponentLabel("newPassLb", newPassword);
            add(newPassword, newPassLabel);

            PasswordTextField verifyPassword = new PasswordTextField("verPassword");
            verifyPassword.setLabel(ResourceUtils.getModel("page.myAccount.newPasswordAgain"));
            verifyPassword.setRequired(true);
            verifyPassword.add(StringValidator.minimumLength(6));
            FormComponentLabel verPassLabel = new FormComponentLabel("verPassLb", verifyPassword);
            add(verifyPassword, verPassLabel);

            AjaxButton submit = new AjaxButton("submit", ResourceUtils.getModel("page.myAccount.changePasswordButton"), this) {

                private static final long serialVersionUID = 1L;

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    target.add(feedback);
                }

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    ChangePasswordObj obj = ChangePasswordForm.this.getModelObject();
                    String userName = EEGDataBaseSession.get().getLoggedUser().getUsername();
                    // check if old pass is actual password in db
                    if (personFacade.isPasswordEquals(userName, obj.getOldPassword())) {
                        // check if new password is equals with verify
                        if (obj.isNewPasswordEqualsWithVerify()) {
                            // check if new password is same like old password
                            if (obj.getOldPassword().equals(obj.getNewPassword()))
                                error(ResourceUtils.getString("invalid.newAndOldPasswordsAreTheSame"));
                            else {
                                personFacade.changeUserPassword(userName, obj.getNewPassword());
                                setResponsePage(ChangePasswordPage.class, PageParametersUtils.getPageParameters(PARAM_ID, PARAM_ID));
                            }
                        } else {
                            error(ResourceUtils.getString("invalid.passwordMatch"));
                        }
                    } else {
                        error(ResourceUtils.getString("invalid.oldPassword"));

                    }
                    target.add(feedback);
                }
            };
            add(submit);
        }

    }
}
