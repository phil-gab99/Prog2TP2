import java.io.IOException;
import java.util.HashMap;

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

    /**
    * The method addDocument adds a new document at the end of the linked list
    * and then initiates the document analysis
    *
    * @param name String indicating document file path
    * @param reverseList ReverseIndexationList reference to update paired list
    ***/

    void addDocument(String name, ReverseIndexationList reverseList) {

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

        Tokenizer.createTokens(); //Creating tokens

        //Acquiring unique tokens and their frequency
        HashMap<String, Integer> documentTokens = Tokenizer.getUniqueTokens();

        temp.setWordStructure(documentTokens); //Analyzing document

        reverseList.addWords(documentTokens, temp.getName());
    }
}