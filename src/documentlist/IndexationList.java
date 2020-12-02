package documentlist;

import java.io.IOException;
import java.util.ArrayList;
import wordlist.ReverseIndexationList;
import tools.*;

/**
* The class IndexationList defines the fields and methods required for a linked
* list of documents
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class IndexationList {

    private Document headDocument; //Head document of list
    private Document lastDocument; //Last document of list

    /**
    * The method addDocument adds a new document at the end of the linked list
    * and then initiates the document analysis
    *
    * @param name String indicating document file path
    * @param reverseList ReverseIndexationList reference to update paired list
    ***/

    public void addDocument(String name, ReverseIndexationList reverseList) {

        Document temp;

        if (headDocument == null) { //Checking if first document

            temp = new Document(name);

            headDocument = temp;
            lastDocument = temp;
        } else if ((temp = contains(name)) != null) {

            System.out.println("The document path " + name
            + " has already been added");
            return;
        } else { //Appends onto list

            temp = new Document(name);

            lastDocument.setNextDocument(temp);
            lastDocument = lastDocument.getNextDocument();
        }

        Tokenizer tokens; //Tokenizer object for analyzing document contents

        try {

            tokens = new Tokenizer(temp.getName());
        } catch(IOException e) {

            System.out.println("Please specify a valid file path");
            return;
        }

        temp.setText(tokens.getText());

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

            if (index.getName().equals(name)) {

                break;
            }

            index = index.getNextDocument();
        }

        return index;
    }

    /**
    * The method printList prints the current list to the user
    ***/

    public void printList() {

        Document node = headDocument;

        while (headDocument != null) {

            headDocument.printList();
            headDocument = headDocument.getNextDocument();
        }

        headDocument = node;
    }
}