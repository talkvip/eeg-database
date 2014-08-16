package cz.zcu.kiv.eegdatabase.wui.ui.licenses.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import cz.zcu.kiv.eegdatabase.data.pojo.License;

public class ViewLicensePanel extends Panel {

    private static final long serialVersionUID = 1794869820176582709L;

    public ViewLicensePanel(String id, IModel<License> model) {
        super(id, new CompoundPropertyModel<License>(model));

        add(new Label("title"));
        add(new Label("price"));
        add(new Label("licenseType"));
        add(new MultiLineLabel("description"));

    }
}
