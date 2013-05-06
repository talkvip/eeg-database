package cz.zcu.kiv.eegdatabase.wui.ui.shoppingCart;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import cz.zcu.kiv.eegdatabase.wui.components.menu.button.ButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;
import cz.zcu.kiv.eegdatabase.wui.components.table.ViewLinkPanel;
import cz.zcu.kiv.eegdatabase.wui.components.utils.ResourceUtils;
import cz.zcu.kiv.eegdatabase.wui.ui.experiments.ExperimentsDetailPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * My downloads page. Currently serves only to show confirmation upon successful purchase. In the future should also
 * show user's purchase history and allow him to re-download purchased experiments.
 * User: jfronek
 * Date: 21.3.2013
 */
@AuthorizeInstantiation(value = { "ROLE_READER", "ROLE_USER", "ROLE_EXPERIMENTER", "ROLE_ADMIN" })
public class MyDownloadsPage  extends MenuPage {
    private static final int ITEMS_PER_PAGE = 20;
    /* List of purchased items. */
    private List<Experiment> order;

    public MyDownloadsPage(){
        order = (List<Experiment>) EEGDataBaseSession.get().getAttribute("order");
        if(order == null){
            setupComponents(false);
        } else {
            setupComponents(true);
            EEGDataBaseSession.get().removeAttribute("order");
        }

    }

    private void setupComponents(boolean visible) {
        IModel<String> title = ResourceUtils.getModel("pageTitle.myDownloads");
        add(new Label("title", title));
        setPageTitle(title);

        add(new Label("youHaveJustBought", ResourceUtils.getModel("label.youHaveJustBought")).setVisibilityAllowed(visible));

        add(new ButtonPageMenu("leftMenu", ShoppingCartPageLeftMenu.values()));

         DefaultDataTable<Experiment, String> list = new DefaultDataTable<Experiment, String>("list", createListColumns(),
                new OrderDataProvider(order), ITEMS_PER_PAGE);
         add(list.setVisibilityAllowed(visible));

    }

    private List<? extends IColumn<Experiment, String>> createListColumns() {
        List<IColumn<Experiment, String>> columns = new ArrayList<IColumn<Experiment, String>>();

        columns.add(new PropertyColumn<Experiment, String>(ResourceUtils.getModel("dataTable.heading.number"), "experimentId", "experimentId"));
        columns.add(new PropertyColumn<Experiment, String>(ResourceUtils.getModel("dataTable.heading.scenarioTitle"), "scenario.title", "scenario.title"));
        columns.add(new PropertyColumn<Experiment, String>(ResourceUtils.getModel("link.download"), null, null) {
            @Override
            public void populateItem(Item<ICellPopulator<Experiment>> item, String componentId, IModel<Experiment> rowModel) {
                item.add(new ViewLinkPanel(componentId, ExperimentsDetailPage.class, "experimentId", rowModel, ResourceUtils.getModel("link.detail")));
            }
        });
        return columns;
    }
}
