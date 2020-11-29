import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map.Entry;
import java.util.HashMap;

/**
* @author Philippe Gabriel
* @version 1.0 2020-12-07
*
* The class Document defines a document node as well as its own linked list of
* words and their frequency
***/

class Document {

    private String name;                 //Document file path
    private WordStructure headStructure; //Document head structure
    private WordStructure lastStructure; //Document last structure
    private Document nextDocument;       //Document following the current one

    /**
    * The constructor method Document creates a new document with the specified
    * file path
    *
    * @param name String indicating document file path
    ***/

    public Document(String name) {

        this.name = name;
    }

    /**
    * The getter method getNextDocument grants access to the next document of
    * the current document
    *
    * @return nextDocument Document following the current one
    ***/

    public Document getNextDocument() {

        return nextDocument;
    }

    /**
    * The setter method setNextDocument sets the document that follows the
    * current one
    *
    * @param next Document following the current one
    ***/

    public void setNextDocument(Document next) {

        nextDocument = next;
    }

    /**
    * The setter method setWordStructure analyzes the text within the current
    * document and arranges its word structure coupled with its frequency
    ***/

    public void setWordStructure() {

        Tokenizer.readFile(name); //Reading file
        Tokenizer.createTokens(); //Creating tokens

        //Acquiring unique tokens and their frequency
        HashMap<String, Integer> documentTokens = Tokenizer.getUniqueTokens();

        //Building the document's word structure based on unique tokens
        for (Map.Entry<String, Integer> entry : documentTokens.entrySet()) {

            WordStructure temp = new WordStructure(new Word(entry.getKey()), entry.getValue());

            if (headStructure == null) { //Checking if first word

                headStructure = temp;
                lastStructure = temp;
            } else {                     //Adding onto end of list

                lastStructure.setNextStructure(temp);
                lastStructure = lastStructure.getNextStructure();
            }
        }
    }
}