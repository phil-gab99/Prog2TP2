/**
* @author Philippe Gabriel
* @version 1.0 2020-12-07
*
* The class IndexationList defines the fields and methods required for a linked
* list of documents
***/

public class IndexationList {

    private Document headDocument; //Head document of list
    private Document lastDocument; //Last document of list

    /**
    * The method addDocument adds a new document at the end of the linked list
    * and then initiates the document analysis
    *
    * @param name String indicating document file path
    ***/

    void addDocument(String name) {

        Document temp = new Document(name);

        if (headDocument == null) { //Checking if first document

            headDocument = temp;
            lastDocument = temp;
        } else {                    //Adding onto end of list

            lastDocument.setNextDocument(temp);
            lastDocument = lastDocument.getNextDocument();
        }

        temp.setWordStructure();    //Analyzing document
    }
}