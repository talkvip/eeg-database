package cz.zcu.kiv.eegdatabase.wui.ui.people;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;

@AuthorizeInstantiation("ROLE_USER")
public class PeoplePage extends MenuPage {
    
    private static final long serialVersionUID = -1967810037377960414L;

    public PeoplePage() {
        
        
    }
}
