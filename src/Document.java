import java.util.Map;
import java.util.HashMap;

/**
* The class Document defines a document node as well as its own linked list of
* words and their frequency
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

class Document {

    private String name;                 //Document file path
    private Document nextDocument;       //Document following the current one
    private WordStructure headStructure; //Document head structure

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
    * The getter method getName grants access to the name path of the current
    * document
    *
    * @return name String indicating document file path
    ***/

    public String getName() {

        return name;
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
    * The setter method setWordStructure sets the current word structure list
    * for a given document
    *
    * @param documentTokens HashMap containing each word within document and
    * its frequency
    ***/

    public void setWordStructure(HashMap<String, Integer> documentTokens) {

        //Building the document's word structure based on unique tokens
        for (Map.Entry<String, Integer> entry : documentTokens.entrySet()) {

            addSortedStructure(entry.getKey(), entry.getValue());
        }
    }

    public void addSortedStructure(String word, int frequency) {

        WordStructure node = headStructure;

        if (headStructure == null) {

            headStructure = new WordStructure(word, frequency);
            return;
        }

        while (headStructure.
        getNextStructure().getWord().compareToIgnoreCase(word) < 0
        && headStructure.getNextStructure() != null) {

            headStructure = headStructure.getNextStructure();
        }

        headStructure.setNextStructure(
        new WordStructure(word, frequency, headStructure.getNextStructure()));

        headStructure = node;
    }
}