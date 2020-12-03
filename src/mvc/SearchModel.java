package mvc;

import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import documentlist.IndexationList;
import wordlist.ReverseIndexationList;

class SearchModel {

    // View allowing control of the user interface elements
    private SearchView view;

    private IndexationList list = new IndexationList();
    private ReverseIndexationList listR = new ReverseIndexationList();

    /**
    * The constructor method SearchModel allows the link between the model and
    * view classes for graphical interface interactions
    *
    * @param view SearchView granting access to methods configuring graphical
    * elements
    ***/

    public SearchModel(SearchView view) {

        this.view = view;
    }

    /**
    * The method formatMixed restricts the input within the caller textfield to
    * only be letters and numbers
    *
    * @param e KeyEvent holding information on the key that was typed
    ***/

    public void formatMixed(KeyEvent e) {

        char c = e.getKeyChar();

        if ((c < 'A' || c > 'z') && (c < '0' || c > '9')) {

            e.consume();
        }
    }

    public void addFiles() {

        view.fileChooserDialog();
    }

    public void updateFiles(File[] files) {

        for (File f : files) {

            list.addDocument(f, listR);
            view.indexList.setText(list.printList());
            view.indexListRev.setText(listR.printList());
        }
    }
}