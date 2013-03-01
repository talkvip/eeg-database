package cz.zcu.kiv.eegdatabase.wui.ui.lists;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.data.pojo.Weather;
import cz.zcu.kiv.eegdatabase.data.pojo.WeatherGroupRel;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.page.UnderConstructPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.PageParametersUtils;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.core.CoreConstants;
import cz.zcu.kiv.eegdatabase.wui.core.common.WeatherFacade;
import cz.zcu.kiv.eegdatabase.wui.core.group.ResearchGroupFacade;
import cz.zcu.kiv.eegdatabase.wui.core.security.SecurityFacade;
import cz.zcu.kiv.eegdatabase.wui.ui.lists.components.ListModelWithResearchGroupCriteria;
import cz.zcu.kiv.eegdatabase.wui.ui.lists.components.ResearchGroupSelectForm;

@AuthorizeInstantiation("ROLE_USER")
public class ListWeatherDefinitiosPage extends MenuPage {

    private static final long serialVersionUID = 7423167902623052151L;

    protected Log log = LogFactory.getLog(getClass());

    @SpringBean
    WeatherFacade facade;

    @SpringBean
    ResearchGroupFacade researchGroupFacade;

    @SpringBean
    SecurityFacade security;

    public ListWeatherDefinitiosPage() {

        setupComponents();
    }

    private void setupComponents() {

        setPageTitle(ResourceUtils.getModel("pageTitle.weatherDefinitionsList"));

        add(new ButtonPageMenu("leftMenu", ListsLeftPageMenu.values()));

        final WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupPlaceholderTag(true);

        final ListModelWithResearchGroupCriteria<Weather> model = new ListModelWithResearchGroupCriteria<Weather>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected List<Weather> loadList(ResearchGroup group) {
                if (group == null)
                    return facade.getDefaultRecords();
                else {
                    return facade.getRecordsByGroup(group.getResearchGroupId());
                }
            }
        };

        List<ResearchGroup> groups;
        final boolean isAdmin = security.isAdmin();
        final boolean isExperimenter = security.userIsExperimenter();
        if (isAdmin)
            groups = researchGroupFacade.getAllRecords();
        else
            groups = researchGroupFacade.getResearchGroupsWhereMember(EEGDataBaseSession.get().getLoggedUser());

        PropertyListView<Weather> weathers = new PropertyListView<Weather>("weathers", model) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Weather> item) {
                item.add(new Label("weatherId"));
                item.add(new Label("title"));
                item.add(new Label("description"));

                PageParameters parameters = PageParametersUtils.getDefaultPageParameters(item.getModelObject().getWeatherId())
                        .add("GROUP", model.getCriteriaModel().getObject().getResearchGroupId());

                item.add(new BookmarkablePageLink<Void>("edit", UnderConstructPage.class, parameters));
                item.add(new AjaxLink<Void>("delete") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {

                        int id = item.getModelObject().getWeatherId();
                        ResearchGroup group = model.getCriteriaModel().getObject();

                        if (facade.canDelete(id)) {
                            if (group != null) {
                                int groupId = group.getResearchGroupId();

                                if (groupId == CoreConstants.DEFAULT_ITEM_ID) { // delete default weather if it's from default group
                                    if (!facade.hasGroupRel(id)) { // delete only if it doesn't have group relationship
                                        facade.delete(item.getModelObject());
                                        getFeedback().info(ResourceUtils.getString("text.itemWasDeletedFromDatabase"));
                                    } else {
                                        getFeedback().error(ResourceUtils.getString("text.itemInUse"));
                                    }
                                } else {
                                    WeatherGroupRel h = facade.getGroupRel(id, groupId);
                                    if (!facade.isDefault(id)) { // delete only non default weather
                                        facade.delete(item.getModelObject());
                                    }
                                    facade.deleteGroupRel(h);
                                    getFeedback().info(ResourceUtils.getString("text.itemWasDeletedFromDatabase"));
                                }
                            }

                        } else {
                            getFeedback().error(ResourceUtils.getString("text.itemInUse"));
                        }

                        target.add(container);
                        target.add(getFeedback());
                    }

                    @Override
                    protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
                    {
                        super.updateAjaxAttributes(attributes);

                        AjaxCallListener ajaxCallListener = new AjaxCallListener();
                        ajaxCallListener.onPrecondition("return confirm('Are you sure you want to delete item?');");
                        attributes.getAjaxCallListeners().add(ajaxCallListener);
                    }

                }.setVisibilityAllowed(isAdmin || isExperimenter));

            }
        };
        container.add(weathers);

        AjaxLink<Void> link = new AjaxLink<Void>("addWeatherLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                int researchGroupId = model.getCriteriaModel().getObject().getResearchGroupId();
                setResponsePage(UnderConstructPage.class, PageParametersUtils.getDefaultPageParameters(researchGroupId));
            }
        };
        link.setVisibilityAllowed(isAdmin || isExperimenter);

        add(new ResearchGroupSelectForm("form", model.getCriteriaModel(), new ListModel<ResearchGroup>(groups), container, isAdmin));
        add(link, container);

    }

}
