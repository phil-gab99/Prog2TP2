package mvc;

public class SearchModel {

    //View allowing control of the user interface elements
    private SearchView view;

    /**
    * The constructor method SearchModel allows the link between the model
    * and view classes for graphical interface interactions
    *
    * @param view SearchView granting access to methods configuring graphical
    * elements
    ***/

    public SearchModel(SearchView view) {

        this.view = view;
    }
}