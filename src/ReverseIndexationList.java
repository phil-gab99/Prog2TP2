import java.util.ArrayList;

/**
* The class ReverseIndexationList defines the fields and methods required for a
* linked list of words resulting from an addition onto the indexation list
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class ReverseIndexationList {

    private Word headWord; //Head word of list

    /**
    * The method addWords adds onto the reversed indexation list any new words
    * or updates already present words
    *
    * @param documentTokens ArrayList of words and their frequencies within
    * the caller document
    * @param source Document source path
    ***/

    public void addWords(ArrayList<WordFrequency> documentTokens,
    String source) {

        for (WordFrequency entry : documentTokens) {

            Word temp;


            if ((temp = contains(entry.getWord())) != null) {

                //Update already existing word
                temp.addDocumentStructure(source, entry.getFrequency());
            } else {

                //Add new word onto list
                addSorted(entry.getWord(), source, entry.getFrequency());
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

        while(index != null) {

            if (index.getLabel().equals(word)) {

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
    * @param source String file path of source document
    * @param frequency Integer indicating occurences of given word within
    * document
    ***/

    public void addSorted(String word, String source, int frequency) {

        //Checking if the list is empty
        if (headWord == null) {

            headWord = new Word(
            word, new DocumentStructure(source, frequency));
            return;
        }

        //Checking if first node is lexicographically after new word
        if (headWord.getLabel().compareToIgnoreCase(word) > 0) {

            headWord = new Word(
            word, headWord, new DocumentStructure(source, frequency));
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
        new DocumentStructure(source, frequency)));

        //Restoring head reference from temporary variable
        headWord = node;
    }

    /**
    * The method printList prints the current reversed list to the user
    ***/

    public void printList() {

        //Saving head reference in temporary variable
        Word node = headWord;

        while (headWord != null) {

            headWord.printList();
            headWord = headWord.getNextWord();
        }

        //Restoring head reference from temporary variable
        headWord = node;
    }
}