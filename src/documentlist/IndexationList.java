package documentlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import mvc.SearchView;
import tools.Tokenizer;
import tools.WordFrequency;
import wordlist.ReverseIndexationList;

/**
* The class IndexationList defines the fields and methods required for a linked
* list of documents
*
* @author Philippe Gabriel
* @version 1.3.5 2020-12-07
***/

public class IndexationList {

    private Document headDocument; //Head document of list
    private Document lastDocument; //Last document of list

    /**
    * The getter method getHeadDocument grants access to the indexation list's
    * head document
    *
    * @return headDocument Head document of list
    ***/

    public Document getHeadDocument() {

        return headDocument;
    }

    /**
    * The method addDocument adds a new document at the end of the linked list
    * and then initiates the document analysis
    *
    * @param document File object of document
    * @param reverseList ReverseIndexationList reference to update paired list
    ***/

    public void addDocument(File document, ReverseIndexationList reverseList) {

        Document temp;

        if (headDocument == null) { //Checking if first document

            temp = new Document(document.getName());

            headDocument = temp;
            lastDocument = temp;
        } else if (contains(document.getName()) != null) {

            SearchView.msgBox("The file " + document.getName()
            + " has already been previously added.",
            "Error Selection", SearchView.ERROR);
            return;
        } else { //Appends onto list

            temp = new Document(document.getName());

            lastDocument.setNextDocument(temp);
            lastDocument = lastDocument.getNextDocument();
        }

        Tokenizer tokens; //Tokenizer object for analyzing document contents

        try {

            tokens = new Tokenizer(document.getPath());
        } catch(IOException e) {

            System.out.println("Please specify a valid file path");
            return;
        }

        temp.setText(tokens.getActualText());

        //Acquiring unique tokens and their frequency
        ArrayList<WordFrequency> documentTokens = tokens.getUniqueTokens();

        temp.setWordStructure(documentTokens); //Analyzing document

        //Updating reversed indexation list
        reverseList.addWords(documentTokens, temp.getName());
    }

    /**
    * The method contains retrieves a document's reference according to the
    * given name or null if the document is not within the list
    *
    * @param name String path of the document
    ***/

    public Document contains(String name) {

        Document index = headDocument;

        while (index != null) {

            if (index.getName().equalsIgnoreCase(name)) {

                break;
            }

            index = index.getNextDocument();
        }

        return index;
    }

    /**
    * The method printList retrieves the current list in a string format
    *
    * @return infoList String implementation of list
    ***/

    public String printList() {

        //Saving head reference in temporary variable
        Document node = headDocument;

        String infoList = "";

        while (headDocument != null) {

            infoList += headDocument.printList();
            headDocument = headDocument.getNextDocument();
        }

        headDocument = node; //Restoring head reference from temporary variable

        return infoList;
    }
}