package cz.zcu.kiv.eegdatabase.wui.ui.search;

import cz.zcu.kiv.eegdatabase.logic.indexing.IndexingService;
import cz.zcu.kiv.eegdatabase.logic.search.FullTextSearchUtils;
import cz.zcu.kiv.eegdatabase.logic.search.FulltextSearchService;
import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.Strings;

import java.util.*;

/**
 * Abstract search panel. Contains all necessary logic.
 * User: Jan Koren
 * Date: 26.3.13
 * Time: 1:49
 */
public abstract class SearchPanel extends Panel {

    @SpringBean
    private FulltextSearchService searchService;

    @SpringBean
    private IndexingService indexingService;

    private Log log = LogFactory.getLog(getClass());

    public SearchPanel(String id, StringValue searchString) {
        super(id);
        add(new SearchForm("searchForm", searchString));
    }

    /**
     * Search form component.
     */
    private class SearchForm extends Form {

        private String searchString;

        public SearchForm(String id, StringValue searchString) {
            super(id);

            if(!searchString.isNull() && !searchString.isEmpty()) {
                this.searchString = searchString.toString();
            }
            final AutoCompleteTextField<String> searchField = createTextField("autocomplete");
            add(searchField);
            final Button searchButton = createSearchButton("searchButton");
            add(searchButton);
        }

        /**
         * Creates the text field with autocomplete support.
         * @param id
         * @return
         */
        private AutoCompleteTextField<String> createTextField(String id) {
            return new AutoCompleteTextField<String>(
                    id, new PropertyModel<String>(this, "searchString")) {
                @Override
                protected Iterator<String> getChoices(String input) {
                    return getAutoCompleteChoices(input);
                }
            };
        }

        /**
         * Creates the search button.
         * @param id
         * @return
         */
        private Button createSearchButton(String id) {
            Button ajaxButton =  new AjaxFallbackButton(id, this) {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                    indexingService.addToAutocomplete(getSearchString());

                    PageParameters pageParameters = new PageParameters();
                    if(getSearchString() != null) {
                        pageParameters.add("searchString", getSearchString());
                        // the search string is kept in a session due to the need to keep it displayed
                        // in the menu search text field independently on the performed requests.
                        EEGDataBaseSession.get().setSearchString(StringValue.valueOf(getSearchString()));
                        setResponsePage(SearchPage.class, pageParameters);
                    }
                }
            };
            IModel<String> labelModel = new Model<String>();
            labelModel.setObject("Search");
            ajaxButton.setLabel(labelModel);
            return ajaxButton;
        }

        /**
         * Given the input string, all indexed "autocomplete" phrases, whose first
         * characters match the input string, are returned
         * @param input The query string (or its part).
         * @return The list of found phrases for autocomplete suggestion.
         */
        private Iterator<String> getAutoCompleteChoices(String input) {
            if(Strings.isEmpty(input)) {
                List<String> emptyList = Collections.emptyList();
                return emptyList.iterator();
            }

            Set<String> autocompletePhrases = new HashSet<String>(FullTextSearchUtils.AUTOCOMPLETE_ROWS);
            try {
                autocompletePhrases = searchService.getTextToAutocomplete(input);
            } catch (SolrServerException e) {
                log.error(e);
            }
            return autocompletePhrases.iterator();
        }

        public String getSearchString() {
            return searchString;
        }

        public void setSearchString(String searchString) {
            this.searchString = searchString;
        }
    }

    public FulltextSearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(FulltextSearchService searchService) {
        this.searchService = searchService;
    }
}
