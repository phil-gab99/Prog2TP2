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

    public Word getHeadWord() {

        return headWord;
    }

    public void addWords(ArrayList<WordFrequency> documentTokens,
    String source) {

        for (WordFrequency entry : documentTokens) {

            if (contains(entry.getWord())) {

                Word temp = getListWord(entry.getWord());
                temp.addDocumentStructure(source, entry.getFrequency());
            } else {

                addSorted(entry.getWord(), source, entry.getFrequency());
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

        if (headWord == null) {

            headWord = new Word(name, new DocumentStructure(source, frequency));
            return;
        }

        if (headWord.getName().compareToIgnoreCase(name) > 0) {

            headWord = new Word(name, headWord, new DocumentStructure(source, frequency));
            return;
        }

        Word node = headWord;

        while (headWord.getNextWord() != null
        && headWord.getNextWord().getName().compareToIgnoreCase(name) < 0) {

            headWord = headWord.getNextWord();
        }

        headWord.setNextWord(new Word(name, headWord.getNextWord(), new DocumentStructure(source, frequency)));

        headWord = node;
    }

    public void printList(Word node) {

        while (node != null) {

            // System.out.print(node.getName() " - " );

            System.out.println(node.getName());
            node.printList(node.getHeadStructure());
            System.out.println();
            node = node.getNextWord();
        }
        // System.out.println("null");
    }
}