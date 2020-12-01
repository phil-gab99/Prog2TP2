package tools;

/**
* The class WordFrequency pairs with each word a frequency counting its
* appearances within some block of text
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class WordFrequency {

    private String word;   //String labelling word
    private int frequency; //Integer indicating occurrences of word

    /**
    * The constructor method WordFrequency pairs a word with its initial
    * frequency count
    *
    * @param word String labelling word
    * @param frequency Integer indicating occurences of word
    ***/

    public WordFrequency(String word, int frequency) {

        this.word = word;
        this.frequency = frequency;
    }

    /**
    * The getter method getWord grants access to the current word label
    *
    * @return word String labelling word
    ***/

    public String getWord() {

        return word;
    }

    /**
    * The getter method getFrequency grants access to the current word's
    * frequency count
    *
    * @return frequency Integer indicating occurrences of word
    ***/

    public int getFrequency() {

        return frequency;
    }

    /**
    * The method incrementFrequency increments a given word's frequency
    ***/

    public void incrementFrequency() {

        this.frequency++;
    }
}