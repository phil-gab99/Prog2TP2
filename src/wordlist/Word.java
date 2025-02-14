package wordlist;

/**
* The class Word defines a word node as well as its own linked list of
* documents and the word's frequency within it
*
* @author Philippe Gabriel
* @version 1.2.12 2020-12-07
***/

public class Word {

    private String label;                    //String labelling word
    private Word nextWord;                   //Word following the current one
    private DocumentStructure headStructure; //Word head structure
    private DocumentStructure lastStructure; //Word last structure

    /**
    * The constructor method Word creates a new word with the specified
    * associated label
    *
    * @param label String labelling word
    ***/

    public Word(String label) {

        this.label = label;
    }

    /**
    * The constructor method Word creates a new word with the specified
    * associated label and indicates the word following it
    *
    * @param label String labelling word
    * @param nextWord Word following the current one
    ***/

    public Word(String label, Word nextWord) {

        this.label = label;
        this.nextWord = nextWord;
    }

    /**
    * The constructor method Word creates a new word with the specified
    * associated label and describes its first document structure
    *
    * @param label String labelling word
    * @param structure First DocumentStructure of Word
    ***/

    public Word(String label, DocumentStructure structure) {

        this.label = label;
        this.headStructure = structure;
        this.lastStructure = structure;
    }

    /**
    * The constructor method Word creates a new word with the specified
    * associated label, indicates the word following it and describes its first
    * document structure
    *
    * @param label String labelling word
    * @param nextWord Word following the current one
    * @param structure First DocumentStructure of Word
    ***/

    public Word(String label, Word nextWord, DocumentStructure structure) {

        this.label = label;
        this.nextWord = nextWord;
        this.headStructure = structure;
        this.lastStructure = structure;
    }

    /**
    * The getter method getLabel grants access to the current word's String
    * label
    *
    * @return label String labelling word
    ***/

    public String getLabel() {

        return label;
    }

    /**
    * The getter method getNextWord grants access to the current word's next
    * word following it
    *
    * @return nextWord Word following the current one
    ***/

    public Word getNextWord() {

        return nextWord;
    }

    /**
    * The setter method setNextWord sets the word that follows the current one
    *
    * @param nextWord Word following the current one
    ***/

    public void setNextWord(Word nextWord) {

        this.nextWord = nextWord;
    }

    /**
    * The getter method getHeadStructure grants access to the current word's
    * head document structure
    *
    * @return headStructure Word head structure
    ***/

    public DocumentStructure getHeadStructure() {

        return headStructure;
    }

    /**
    * The method addDocumentStructure adds an additional document structure to
    * the current word's list of document structures
    *
    * @param path String for absolute file path of document
    * @param name String for document name
    * @param frequency Integer indicating occurences of word within document
    ***/

    public void addDocumentStructure(String path, String name, int frequency) {

        DocumentStructure temp = new DocumentStructure(path, name, frequency);

        if (headStructure == null) { //Checking if first document

            headStructure = temp;
            lastStructure = temp;
        } else { //Appends onto list

            lastStructure.setNextStructure(temp);
            lastStructure = lastStructure.getNextStructure();
        }
    }

    /**
    * The method printList retrieves the current word's list of document
    * structures in their String format
    *
    * @return info String implemenation of list
    ***/

    public String printList() {

        //Saving head reference in temporary variable
        DocumentStructure node = headStructure;

        String info = "" +
        "-----------------------------------------------------------------\n" +
        "Word: " + label + "\n\n" +
        "     document - frequency\n";

        while (headStructure != null) {

            info += "     " + headStructure + "\n";
            headStructure = headStructure.getNextStructure();
        }

        //Restoring head reference from temporary variable
        headStructure = node;

        return info;
    }
}