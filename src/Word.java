/**
* The class Word defines a word node as well as its own linked list of
* documents and the word's frequency within it
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class Word {

    private String name;                     //String labelling word
    private Word nextWord;                   //Word following the current one
    private DocumentStructure headStructure; //Word head structure
    private DocumentStructure lastStructure; //Word last structure

    /**
    * The constructor method Word creates a new word with the specified
    * associated label
    *
    * @param name String labelling word
    ***/

    public Word(String name) {

        this.name = name;
    }

    public Word(String name, Word nextWord) {

        this.name = name;
        this.nextWord = nextWord;
    }

    public Word(String name, DocumentStructure structure) {

        this.name = name;
        this.headStructure = structure;
        this.lastStructure = structure;
    }

    public Word(String name, Word nextWord , DocumentStructure structure) {

        this.name = name;
        this.nextWord = nextWord;
        this.headStructure = structure;
        this.lastStructure = structure;
    }

    public String getName() {

        return name;
    }

    public Word getNextWord() {

        return nextWord;
    }

    public DocumentStructure getHeadStructure() {

        return headStructure;
    }

    public void setNextWord(Word next) {

        nextWord = next;
    }

    public void addDocumentStructure(String source, int frequency) {

        DocumentStructure temp = new DocumentStructure(source, frequency);

        if (headStructure == null) { //Checking if first document

            headStructure = temp;
            lastStructure = temp;
        } else {                     //Adding onto end of list

            lastStructure.setNextStructure(temp);
            lastStructure = lastStructure.getNextStructure();
        }
    }

    public void printList(DocumentStructure node) {

        while (node != null) {

            System.out.println(node.getDocument() + " - " + node.getFrequency());
            node = node.getNextStructure();
        }
    }
}