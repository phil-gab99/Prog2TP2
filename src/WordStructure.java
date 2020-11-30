/**
* The class WordStructure defines the linked list format structure for the
* documents that figure on the indexation list
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class WordStructure {

    private String word;                 //Word within the document
    private int frequency;               //Appearances of word in document
    private WordStructure nextStructure; //Structure for the next distinct word

    /**
    * The constructor method WordStructure pairs with each word its frequency
    * within the caller document
    *
    * @param word Word within the document
    * @param frequency Integer indicating word appearance
    ***/

    public WordStructure(String word, int frequency) {

        this.word = word;
        this.frequency = frequency;
    }

    public WordStructure(String word, int frequency, WordStructure nextStructure) {

        this.word = word;
        this.frequency = frequency;
        this.nextStructure = nextStructure;
    }

    public String getWord() {

        return word;
    }

    /**
    * The getter method getNextStructure grants access to the next word
    * structure of the current word structure
    *
    * @return nextStructure Structure for the next distinct word
    ***/

    public WordStructure getNextStructure() {

        return nextStructure;
    }

    /**
    * The setter method setNextStructure set the word structure for the word
    * that follows the current one
    *
    * @param next Structure for the next distinct word
    ***/

    public void setNextStructure(WordStructure next) {

        nextStructure = next;
    }
}