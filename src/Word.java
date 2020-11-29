/**
* @author Philippe Gabriel
* @version 1.0 2020-12-07
*
* The class Word defines a word node as well as its own linked list of
* documents and the word's frequency within it
***/

public class Word {

    private String name;                     //String labelling word
    private DocumentStructure headStructure; //Word head structure
    private Word nextWord;                   //Word following the current one

    /**
    * The constructor method Word creates a new word with the specified
    * associated label
    *
    * @param name String labelling word
    ***/

    public Word(String name) {

        this.name = name;
    }
}