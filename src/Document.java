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

    /**
    * The setter method getText grants access to the text within the current
    * document
    *
    * @return text String representing text within document
    ***/

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
    * @param nextDocument Document following the current one
    ***/

    public void setNextDocument(Document nextDocument) {

        this.nextDocument = nextDocument;
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

    /**
    * The method addSortedStructure adds a new word structure to the document's
    * list of word structures in a lexicographically sorted fashion
    *
    * @param word String representing the word label
    * @param frequency Integer indicating the word's number of appearances
    ***/

    public void addSortedStructure(String word, int frequency) {

        //Checking if the list is empty
        if (headStructure == null) {

            headStructure = new WordStructure(word, frequency);
            return;
        }

        //Checking if first node is lexicographically after new word
        if (headStructure.getWord().compareToIgnoreCase(word) > 0) {

            headStructure = new WordStructure(word, frequency, headStructure);
            return;
        }

        //Saving head reference in temporary variable
        WordStructure node = headStructure;

        //Checking if subsequent nodes are lexicographically before new word
        while (headStructure.getNextStructure() != null && headStructure.
        getNextStructure().getWord().compareToIgnoreCase(word) < 0) {

            headStructure = headStructure.getNextStructure();
        }

        //Positioning new word at correct lexicographical order
        headStructure.setNextStructure(
        new WordStructure(word, frequency, headStructure.getNextStructure()));

        //Restoring head reference from temporary variable
        headStructure = node;
    }

    /**
    * The method printList prints the current document's list of word
    * structures
    ***/

    public void printList() {

        //Saving head reference in temporary variable
        WordStructure node = headStructure;

        System.out.println("\n----------------------------------");
        System.out.println("File path: " + name + "\n");
        System.out.println("Text:\n" + text);
        System.out.println("----------------------------------");

        while (headStructure != null) {

            System.out.println(
            headStructure.getWord() + " - " + headStructure.getFrequency());

            headStructure = headStructure.getNextStructure();
        }

        //Restoring head reference from temporary variable
        headStructure = node;
    }
}