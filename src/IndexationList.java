import java.io.IOException;
import java.util.ArrayList;;

/**
* The class IndexationList defines the fields and methods required for a linked
* list of documents
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class IndexationList {

    private Document headDocument; //Head document of list
    private Document lastDocument; //Last document of list

    public Document getHeadDocument() {

        return headDocument;
    }

    /**
    * The method addDocument adds a new document at the end of the linked list
    * and then initiates the document analysis
    *
    * @param name String indicating document file path
    * @param reverseList ReverseIndexationList reference to update paired list
    ***/

    public void addDocument(String name, ReverseIndexationList reverseList) {

        Document temp = new Document(name);

        if (headDocument == null) { //Checking if first document

            headDocument = temp;
            lastDocument = temp;
        } else {                    //Adding onto end of list

            lastDocument.setNextDocument(temp);
            lastDocument = lastDocument.getNextDocument();
        }

        try {

            Tokenizer.readFile(temp.getName());
        } catch(IOException e) {

            System.out.println("Stuff");
        }

        temp.setText(Tokenizer.getText());
        Tokenizer.createTokens(); //Creating tokens

        //Acquiring unique tokens and their frequency
        ArrayList<WordFrequency> documentTokens = Tokenizer.getUniqueTokens();

        temp.setWordStructure(documentTokens); //Analyzing document

        reverseList.addWords(documentTokens, temp.getName());
    }

    public void printList(Document node) {

        while (node != null) {

            System.out.println(node.getName());
            System.out.println();
            node.printList(node.getHeadStructure());
            node = node.getNextDocument();
        }

        // System.out.println("null");
    }
}