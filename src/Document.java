import java.util.ArrayList;

/**
* The class Document defines a document node as well as its own linked list of
* words and their frequency
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

class Document {

    private String name;                 //Document file path
    private String text;                 //Text within document
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

    public void setText(String text) {

        this.text = text;
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

    public WordStructure getHeadStructure() {

        return headStructure;
    }

    /**
    * The setter method setWordStructure sets the current word structure list
    * for a given document
    *
    * @param documentTokens HashMap containing each word within document and
    * its frequency
    ***/

    public void setWordStructure(ArrayList<WordFrequency> documentTokens) {

        //Building the document's word structure based on unique tokens
        for (WordFrequency entry : documentTokens) {

            addSortedStructure(entry.getWord(), entry.getFrequency());
        }
    }

    public void addSortedStructure(String word, int frequency) {

        if (headStructure == null) {

            headStructure = new WordStructure(word, frequency);
            return;
        }

        if (headStructure.getWord().compareToIgnoreCase(word) > 0) {

            headStructure = new WordStructure(word, frequency, headStructure);
            return;
        }

        WordStructure node = headStructure;

        while (headStructure.getNextStructure() != null && headStructure.
        getNextStructure().getWord().compareToIgnoreCase(word) < 0) {

            headStructure = headStructure.getNextStructure();
        }

        headStructure.setNextStructure(
        new WordStructure(word, frequency, headStructure.getNextStructure()));
        headStructure = node;
    }

    public void printList(WordStructure node) {

        System.out.println(text);

        while (node != null) {

            System.out.println(node.getWord() + " - " + node.getFrequency());
            node = node.getNextStructure();
        }
    }
}