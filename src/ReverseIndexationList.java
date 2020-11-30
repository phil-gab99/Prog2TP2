import java.util.Map;
import java.util.HashMap;

/**
* The class ReverseIndexationList defines the fields and methods required for a
* linked list of words resulting from an addition onto the indexation list
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class ReverseIndexationList {

    private Word headWord; //Head word of list

    public void addWords(HashMap<String, Integer> documentTokens) {

        for (Map.Entry<String, Integer> entry : documentTokens.entrySet()) {

            Word temp = new Word(entry.getKey());

            if (headWord == null) {

                headWord = temp;
            } else if (contains(temp)) {

                temp = getListWord(temp.getName());
            } else {

                //Add word at end of list and give it new DocumentStructure
            }
        }
    }

    /**
    * The method contains determines whether a word is already within the
    * current linked list or not
    *
    * @param word Word for which search is undertaken
    * @return haveWord Boolean indicating if given word is in the list or not
    ***/

    private boolean contains(Word word) {

        boolean haveWord = false;
        Word index = headWord;

        while (index != null) {

            if (word.getName().equals(index.getName())) {

                haveWord = true;
                break;
            }

            index = index.getNextWord();
        }

        return haveWord;
    }

    /**
    * The method getListWord retrieves a word's reference according to its given
    * label
    *
    * @param name String labelling word
    * @return Word corresponding with given label name
    ***/

    public Word getListWord(String name) {

        Word index = headWord;

        while(index != null) {

            if (index.getName().equals(name)) {

                break;
            }

            index = index.getNextWord();
        }

        return index;
    }

    public void addSorted(String name, String source, int frequency) {

        // WordStructure node = headStructure;
        //
        // if (headStructure == null) {
        //
        //     headStructure = new WordStructure(word, frequency);
        //     return;
        // }
        //
        // while (headWord.getName().compareToIgnoreCase(word) < 0
        // && headStructure.getNextStructure() != null) {
        //
        //     headStructure = headStructure.getNextStructure();
        // }
        //
        // headStructure.setNextStructure(
        // new WordStructure(word, frequency, headStructure.getNextStructure()));
        //
        // headStructure = node;
    }
}