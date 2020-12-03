package mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import documentlist.Document;
import documentlist.IndexationList;
import wordlist.*;
import tools.DocumentScore;

class SearchModel {

    // View allowing control of the user interface elements
    private SearchView view;

    private IndexationList list = new IndexationList();
    private ReverseIndexationList listR = new ReverseIndexationList();

    /**
    * The constructor method SearchModel allows the link between the model and view
    * classes for graphical interface interactions
    *
    * @param view SearchView granting access to methods configuring graphical
    *             elements
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

        if ((c < 'A' || c > 'z') && (c < '0' || c > '9') && (c != ',')) {

            e.consume();
        }
    }

    public void addFiles() {

        view.fileChooserDialog();
    }

    public void search() {

        view.searchWordsDialog();
    }

    public void updateFiles(File[] files) {

        for (File f : files) {

            list.addDocument(f, listR);
        }

        view.indexList.setText(list.printList());
        view.indexListRev.setText(listR.printList());
    }

    public void OkSearch() {

        String query = view.wordQuery.getText().replaceAll("[^A-z0-9]", " ").
        trim();

        if (query.equals("")) { //Checking for empty string

            query = " ";
        }

        String[] words = query.split("\\s+");

        if (words.length == 0) {

            SearchView.msgBox("Please enter a valid query.", "No Input",
            SearchView.ERROR);
            return;
        } else if (hasDuplicates(words)) {

            SearchView.msgBox("Please avoid duplicate entries.", "Duplicates",
            SearchView.ERROR);
            return;
        } else {

            searchList(words);
        }
    }

    /**
    * The method cancel closes the dialog from which the caller button is
    * located
    ***/

    public void cancel() {

        view.dialog.dispose();
    }

    private boolean hasDuplicates(String[] words) {

        HashSet<String> wordSet = new HashSet<String>();
        boolean duplicate = false;

        for (String w : words) {

            if (wordSet.contains(w)) {

                duplicate = true;
                break;
            }

            wordSet.add(w);
        }

        return duplicate;
    }

    /**
    * The method searchList searches within the list instances for the
    * documents that hold the words of interest
    *
    * @param words Array of words of interest
    * @return result ArrayList of documents paired with their score
    ***/

    private ArrayList<DocumentScore> searchList(String[] words) {

        Word temp;
        ArrayList<DocumentScore> result;

        if ((words.length == 0)
        || ((temp = listR.contains(words[0])) == null)) {

            return null;
        } else {

            result = new ArrayList<DocumentScore>();
            DocumentStructure node = temp.getHeadStructure();

            while (node != null) {

                Document doc = list.getHeadDocument();

                while (doc != null) {

                    if (doc.getName().equalsIgnoreCase(node.getDocument())) {

                        int sum = doc.score(words);

                        if (sum != 0) {

                            result.add(new DocumentScore(doc.getName(), sum));
                        }

                        break;
                    }

                    doc = doc.getNextDocument();
                }

                node = node.getNextStructure();
            }
        }

        Collections.sort(result);
        return result;
    }
}