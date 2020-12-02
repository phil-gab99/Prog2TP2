package tools;

/**
* The class DocumentScore pairs a document with its score based on a document
* containing a list of specfied queries
*
* @author Philippe Gabriel
* @version 1.0 2020-mm-dd
***/

public class DocumentScore implements Comparable<DocumentScore> {

    private String document; //String path of document
    private int score;       //Integer representing score of document

    /**
    * The constructor method DocumentScore pairs a document with its
    * corresponding score
    *
    * @param document String path of document
    * @param score Integer representing score of document
    ***/

    public DocumentScore(String document, int score) {

        this.document = document;
        this.score = score;
    }

    public String toString() {

        return "document: " + document + "\nscore: " + score;
    }

    public int compareTo(DocumentScore docScore) {

        return docScore.score - score;
    }
}