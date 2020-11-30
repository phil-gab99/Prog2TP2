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

    public void addWords(HashMap<String, Integer> documentTokens, String source) {

        for (Map.Entry<String, Integer> entry : documentTokens.entrySet()) {

            if (contains(entry.getKey())) {

                Word temp = getListWord(entry.getKey());
                temp.addDocumentStructure(source, entry.getValue());
            } else {

                addSorted(entry.getKey(), source, entry.getValue());
            }
        }
    }

    /**
    * The method contains determines whether a word is already within the
    * current linked list or not
    *
    * @param word String for which search is undertaken
    * @return haveWord Boolean indicating if given word is in the list or not
    ***/

    private boolean contains(String word) {

        boolean haveWord = false;
        Word index = headWord;

        while (index != null) {

            if (word.equalsIgnoreCase(index.getName())) {

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

        Word node = headWord;

        if (headWord == null) {

            headWord = new Word(name, new DocumentStructure(source, frequency));
            return;
        }

        while (headWord.getNextWord().getName().compareToIgnoreCase(name) < 0
        && headWord.getNextWord() != null) {

            headWord = headWord.getNextWord();
        }

        headWord.setNextWord(new Word(name, headWord.getNextWord()));
        headWord.getNextWord().addDocumentStructure(source, frequency);

        headWord = node;
    }
}