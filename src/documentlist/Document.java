package documentlist;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import tools.WordFrequency;

/**
* The class Document defines a document node as well as its own linked list of
* words and their frequency
*
* @author Philippe Gabriel
* @version 1.5.9 2020-12-07
***/

public class Document {

    private String fullPath;             //Document file absolute path
    private String name;                 //Document file name
    private String text;                 //Text within document
    private Document nextDocument;       //Document following the current one
    private WordStructure headStructure; //Document head structure

    /**
    * The constructor method Document creates a new document with the specified
    * file name
    *
    * @param fullPath String indicating document absolute file path
    * @param name String indicating document file name
    ***/

    public Document(String fullPath, String name) {

        this.fullPath = fullPath;
        this.name = name;
    }

    /**
    * The getter method getFullPath grants access to the absolute file path of
    * the current document
    *
    * @return fullPath String indicating document absolute file path
    ***/

    public String getFullPath() {
        
        return fullPath;
    }

    /**
    * The getter method getName grants access to the name of the current
    * document
    *
    * @return name String indicating document file name
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
    * The method score calculates a document's score with respect to the given
    * list of words or returns 0 if the document does not contain each word
    *
    * @param words String array of words of interest
    ***/

    public int score(String[] words) {

        //Sorting the array for simpler comparison
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);

        int sum = 0;        //Document score
        int wordIndex = 0;  //Index of provided string array

        WordStructure node = headStructure; //Acquiring head node reference

        while((wordIndex < words.length) && (node != null)) {

            if (node.getWord().equalsIgnoreCase(words[wordIndex])) {

                sum += node.getFrequency();
                wordIndex ++;
            }

            node = node.getNextStructure();
        }

        //Checking if all words are within document
        sum = (wordIndex == words.length ? sum : 0);

        return sum;
    }

    /**
    * The method printList retrieves the current document's list of word
    * structures in their String format
    *
    * @return info String implemenation of list
    ***/

    public String printList() {

        //Saving head reference in temporary variable
        WordStructure node = headStructure;

        String info = "" +
        "-----------------------------------------------------------------\n" +
        "File name: " + name + "\n" +
        "File path: " + fullPath + "\n\n" +
        "Text:\n\n" + text + "\n" +
        "word - frequency\n";

        while (headStructure != null) {

            info += "     " + headStructure + "\n";
            headStructure = headStructure.getNextStructure();
        }

        //Restoring head reference from temporary variable
        headStructure = node;

        return info;
    }
}