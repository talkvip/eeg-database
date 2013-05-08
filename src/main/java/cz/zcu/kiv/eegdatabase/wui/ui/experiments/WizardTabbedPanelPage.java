package cz.zcu.kiv.eegdatabase.wui.ui.experiments;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import javax.xml.stream.events.Attribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 3.4.13
 * Time: 15:45
 */
public class WizardTabbedPanelPage extends MenuPage {
    public WizardTabbedPanelPage() {
        final AddExperimentScenarioForm scenarioForm = new AddExperimentScenarioForm("scenarioTab");
        final AddExperimentEnvironmentForm environmentForm = new AddExperimentEnvironmentForm("environmentTab");
        final AddExperimentResultsForm resultForm = new AddExperimentResultsForm("resultTab");
        final int TAB_COUNT = 2;
        final int[] formsVisited = {0, 0, 0};

        // create a list of ITab objects used to feed the tabbed panel
        final List<ITab> tabs = new ArrayList<ITab>();
        tabs.add(new AbstractTab(new Model<String>("Scenario"))
        {
            @Override
            public Panel getPanel(String panelId)
            {
                return new TabPanel1(panelId, scenarioForm);
            }
        });

        final Model environmentModel = new Model("Environment");
        AbstractTab environmentTab = new AbstractTab(environmentModel)
        {
            @Override
            public Panel getPanel(String panelId)
            {
                return new TabPanel2(panelId, environmentForm);
            }
        };
        tabs.add(environmentTab);



        tabs.add(new AbstractTab(new Model<String>("Result"))
        {
            @Override
            public Panel getPanel(String panelId)
            {
                return new TabPanel3(panelId, resultForm);
            }
        });

        setPageTitle(ResourceUtils.getModel("pageTitle.experimentDetail"));

        add(new ButtonPageMenu("leftMenu", ExperimentsPageLeftMenu.values()));

        ExperimentTabbedPanel etp = new ExperimentTabbedPanel("steps", tabs){
            @Override
            protected WebMarkupContainer newLink(String linkId, final int index) {
                final int selectedTab = getSelectedTab();

                if (formsVisited[selectedTab]<TAB_COUNT) {
                    setTabClassWhite(selectedTab);
                    formsVisited[selectedTab]++;
                }

                return new Link(linkId)
                {
                  private static final long serialVersionUID = 1L;

                  public void onClick()
                  {
                    if (selectedTab == 0) {
                         if (scenarioForm.isValid()){
                            System.out.println("SCE NA GREEN");
                            setTabClassGreen(selectedTab);
                        }
                        else {
                            System.out.println("SCE NA RED");
                            setTabClassRed(selectedTab);
                        }
                    }
                    if (selectedTab == 1) {
                        if (environmentForm.isValid()){
                            System.out.println("ENVI NA GREEN");
                            setTabClassGreen(selectedTab);
                        }
                        else {
                            System.out.println("ENVI NA RED");
                            setTabClassRed(selectedTab);
                        }
                    }
                    if (selectedTab == 2) {
                        if (resultForm.isValid()){
                            System.out.println("RES NA GREEN");
                            setTabClassGreen(selectedTab);
                        }
                        else {
                            System.out.println("RES NA RED");
                            setTabClassRed(selectedTab);
                        }
                    }
                    setSelectedTab(index);
                  }
                };
            }
        };
        add(etp);

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forUrl("/files/wizard-style.css"));
        super.renderHead(response);
    }

    /**
     * Panel representing the content panel for the first tab.
     */
    private class TabPanel1 extends Panel
    {
        /**
         * Constructor
         *
         * @param id
         *            component id
         */
        public TabPanel1(String id, AddExperimentScenarioForm scenarioForm)
        {
            super(id);
            add(scenarioForm);
        }
    };

    /**
     * Panel representing the content panel for the second tab.
     */
    private static class TabPanel2 extends Panel
    {
        /**
         * Constructor
         *
         * @param id
         *            component id
         */
        public TabPanel2(String id, AddExperimentEnvironmentForm environmentForm)
        {
            super(id);
            add(environmentForm);
        }

    };

    /**
     * Panel representing the content panel for the third tab.
     */
    private static class TabPanel3 extends Panel
    {
        /**
         * Constructor
         *
         * @param id
         *            component id
         */
        public TabPanel3(String id, AddExperimentResultsForm resultForm)
        {
            super(id);
            add(resultForm);
        }
    };
}