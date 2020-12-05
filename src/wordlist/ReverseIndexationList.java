package wordlist;

import java.util.ArrayList;
import tools.WordFrequency;

/**
* The class ReverseIndexationList defines the fields and methods required for a
* linked list of words resulting from an addition onto the indexation list
*
* @author Philippe Gabriel
* @version 1.4.8 2020-12-07
***/

public class ReverseIndexationList {

    private Word headWord; //Head word of list

    /**
    * The method addWords adds onto the reversed indexation list any new words
    * or updates already present words
    *
    * @param documentTokens ArrayList of words and their frequencies within
    * the caller document
    * @param path Document absolute path
    * @param name Document name
    ***/

    public void addWords(ArrayList<WordFrequency> documentTokens, String path,
    String name) {

        for (WordFrequency entry : documentTokens) {

            Word temp;

            if ((temp = contains(entry.getWord())) != null) {

                //Update already existing word
                temp.addDocumentStructure(path, name, entry.getFrequency());
            } else {

                //Add new word onto list
                addSorted(entry.getWord(), path, name, entry.getFrequency());
            }
        }
    }

    /**
    * The method contains retrieves a word's reference according to its given
    * label or null if the word is not within the list
    *
    * @param word String labelling word
    * @return Word corresponding to given label name or null
    ***/

    public Word contains(String word) {

        Word index = headWord;

        while (index != null) {

            if (index.getLabel().equalsIgnoreCase(word)) {

                break;
            }

            index = index.getNextWord();
        }

        return index;
    }

    /**
    * The method addSorted adds a new word onto the current reverse indexation
    * list in a lexicographically sorted fashion
    *
    * @param word String label of word
    * @param path String for document absolute path
    * @param name String for document name
    * @param frequency Integer indicating occurences of given word within
    * document
    ***/

    public void addSorted(String word, String path, String name,
    int frequency) {

        //Checking if the list is empty
        if (headWord == null) {

            headWord = new Word(
            word, new DocumentStructure(path, name, frequency));
            return;
        }

        //Checking if first node is lexicographically after new word
        if (headWord.getLabel().compareToIgnoreCase(word) > 0) {

            headWord = new Word(
            word, headWord, new DocumentStructure(path, name, frequency));
            return;
        }

        //Saving head reference in temporary variable
        Word node = headWord;

        //Checking if subsequent nodes are lexicographically before new word
        while (headWord.getNextWord() != null
        && headWord.getNextWord().getLabel().compareToIgnoreCase(word) < 0) {

            headWord = headWord.getNextWord();
        }

        //Positioning new word at correct lexicographical order
        headWord.setNextWord(new Word(word, headWord.getNextWord(),
        new DocumentStructure(path, name, frequency)));

        //Restoring head reference from temporary variable
        headWord = node;
    }

    /**
    * The method printList retrieves the current list in a string format
    *
    * @return infoList String implementation of list
    ***/

    public String printList() {

        //Saving head reference in temporary variable
        Word node = headWord;

        String infoList = "";

        while (headWord != null) {

            infoList += headWord.printList();
            headWord = headWord.getNextWord();
        }

        //Restoring head reference from temporary variable
        headWord = node;

        return infoList;
    }
}