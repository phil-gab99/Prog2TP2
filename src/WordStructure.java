/**
* The class WordStructure defines the linked list format structure for the
* documents that figure on the indexation list
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class WordStructure {

    private String word;                 //Word label for this structure
    private int frequency;               //Appearances of the word in document
    private WordStructure nextStructure; //Structure for the next distinct word

    /**
    * The constructor method WordStructure pairs with each word its frequency
    * within the caller document
    *
    * @param word Word label for this structure
    * @param frequency Integer indicating word appearances
    ***/

    public WordStructure(String word, int frequency) {

        this.word = word;
        this.frequency = frequency;
    }

    /**
    * The constructor method WordStructure pairs with each word its frequency
    * within the caller document and assigns the next word structure following
    * the current one
    *
    * @param word Word label for this structure
    * @param frequency Integer indicating word appearances
    * @param nextStructure WordStructure following the current one
    ***/

    public WordStructure(String word, int frequency,
    WordStructure nextStructure) {

        this.word = word;
        this.frequency = frequency;
        this.nextStructure = nextStructure;
    }

    /**
    * The getter method getWord grants access to the current word structure's
    * word label
    *
    * @return word String label for word of this structure
    ***/

    public String getWord() {

        return word;
    }

    /**
    * The getter method getFrequency grants access to the current word
    * structure's frequency
    *
    * @return frequency Integer indicating word appearances
    ***/

    public int getFrequency() {

        return frequency;
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