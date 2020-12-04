package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import documentlist.Document;
import documentlist.IndexationList;
import tools.DocumentScore;
import wordlist.*;

/**
* The class SearchModel gives the details of the actions to be ensued upon
* triggering action events
*
* @author Philippe Gabriel
* @version 1.16.7 2020-12-07
***/

class SearchModel {

    //View allowing control of the user interface elements
    private SearchView view;


    private String query = ""; //String holding user word query

    //References to indexation list and reversed indexation list
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
    * only be letters and numbers as well as commas for entry separators
    *
    * @param e KeyEvent holding information on the key that was typed
    ***/

    public void formatMixed(KeyEvent e) {

        char c = e.getKeyChar();

        if ((c < 'A' || c > 'z') && (c < '0' || c > '9') && (c != ',')) {

            e.consume();
        }
    }

    /**
    * The method addFiles is triggered upon pressing the Add Files button
    * and initiates the generation of the file chooser dialog
    ***/

    public void addFiles() {

        //Prevent user from undertaking action if results frame is active
        try {

            if (view.isSearchResultVisible()) {

                SearchView.msgBox(
                "Please close the current window to undertake this action.",
                "Action Not Allowed", SearchView.ERROR);
                return;
            }
        } catch(NullPointerException e) {
            //Do nothing
        }

        view.fileChooserDialog(); //Generate file chooser dialog
    }

    /**
    * The method updateFiles is triggered upon selecting files from the file
    * chooser dialog and updates both lists to hold the correct information
    ***/

    public void updateFiles(File[] files) {

        for (File f : files) {

            list.addDocument(f, listR);
        }

        view.indexList.setText(list.printList());
        view.indexListRev.setText(listR.printList());
    }

    /**
    * The method search is triggered upon pressing the Search button and
    * initiates the generation of the search dialog
    ***/

    public void search(ActionEvent e) {

        //Prevent user from undertaking action if results frame is active
        try {

            if ((view.isSearchResultVisible()) &&
            !((JFrame)(SwingUtilities
            .getWindowAncestor((JButton)(e.getSource())))).getName()
            .equals(view.searchResult.getName())) {
            
                SearchView.msgBox(
                "Please close the results window to undertake this action.",
                "Action Not Allowed", SearchView.ERROR);
                return;
            }
        } catch(NullPointerException a) {
            //Do nothing
        }

        view.makeDialog(); //Generate search dialog
    }

    /**
    * The method updateWords is triggered upon pressing the Ok button within
    * the search dialog and issues the user query to display the correct
    * resulting list
    ***/

    public void updateWords() {

        //Retrieving query
        String data = view.wordQuery.getText().replaceAll("[^A-z0-9]", " ")
        .trim();
        
        if (data.equals("")) { //Checking for empty query
        
            SearchView.msgBox("Please enter a valid query.", "No Input",
            SearchView.ERROR);
            return;
        }
        
        //Tokenizing new combined query
        String[] words = (query + " " + data).trim().split("\\s+");
        
        if (hasDuplicates(words)) { //If query holds duplicate entries
        
            SearchView.msgBox("Please avoid duplicate entries.", "Duplicates",
            SearchView.ERROR);
            return;
        } else {
        
            ArrayList<DocumentScore> searchResult = searchList(words);
            String result = "Document - Score\n";
        
            if (searchResult != null) {
        
                for (DocumentScore d : searchResult) {
        
                    result += "     " + d + "\n";
                }
            }
        
            cancel();
            
            query = (query + " " + data).trim();       //Updating full query
            
            try {
                
                view.searchResultList.setText(result); //Updating results list
            } catch(NullPointerException e) {
                
                view.makeResultsFrame(result);
            }
        }
    }

    /**
    * The method cancel closes the dialog from which the caller button is
    * located
    ***/

    public void cancel() {

        view.dialog.dispose();
    }

    /**
    * The method hasDuplicates checks if a given String array holds any
    * duplicate entries
    *
    * @param words String array of words
    * @return duplicate Boolean indicating whether the array has duplicate
    * entries or not
    ***/

    private boolean hasDuplicates(String[] words) {

        HashSet<String> wordSet = new HashSet<String>();
        boolean duplicate = false;

        for (String w : words) {

            if (wordSet.contains(w)) { //Flag for duplicate entry

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

        //Check if list is empty or if first word is not present
        if ((words.length == 0)
        || ((temp = listR.contains(words[0])) == null)) {

            return null;
        } else {

            result = new ArrayList<DocumentScore>();

            //Acquiring head document structure of first word appearance
            DocumentStructure node = temp.getHeadStructure();

            while (node != null) {

                //Acquiring indexation list head document
                Document doc = list.getHeadDocument();

                while (doc != null) {

                    //Compare indexation list document with document structure
                    if (doc.getName().equalsIgnoreCase(node.getDocument())) {

                        //Document score calculated given list of words
                        int sum = doc.score(words);

                        //Adding document to result list if has all words
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

        Collections.sort(result); //Sorting in descending order
        return result;
    }
}