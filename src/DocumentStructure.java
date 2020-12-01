/**
* The class DocumentStructure defines the linked list format structure for the
* words that figure on the reversed indexation list
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class DocumentStructure {

    private String document;                 //Document source path of word
    private int frequency;                   //Appearances of word in document

    //Structure for the next distinct document
    private DocumentStructure nextStructure;

    /**
    * The constructor method DocumentStructure pairs with each document the
    * the caller word's frequency within it
    *
    * @param document String representing current document source path of word
    * @param frequency Integer indicating appearances of word in document
    ***/

    public DocumentStructure(String document, int frequency) {

        this.document = document;
        this.frequency = frequency;
    }

    /**
    * The getter method getDocument grants access to the current document
    * source path from which the word figures
    *
    * @return document String representing current document source path of word
    ***/

    public String getDocument() {

        return document;
    }

    /**
    * The getter method getFrequency grants access to the frequency of the
    * caller word within the current document
    *
    * @return frequency Integer indicating appearances of word in document
    ***/

    public int getFrequency() {

        return frequency;
    }

    /**
    * The getter method getNextStructure grants access to the document
    * structure following the current one
    *
    * @return nextStructure Document structure following the current one
    ***/

    public DocumentStructure getNextStructure() {

        return nextStructure;
    }

    /**
    * The setter method setNextStructure sets the document structure following
    * the current one
    *
    * @param nextStructure Document structure following the current one
    ***/

    public void setNextStructure(DocumentStructure nextStructure) {

        this.nextStructure = nextStructure;
    }
}