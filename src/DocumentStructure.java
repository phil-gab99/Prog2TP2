public class DocumentStructure {

    private String document;
    private int frequency;
    private DocumentStructure nextStructure;

    public DocumentStructure(String document, int frequency) {

        this.document = document;
        this.frequency = frequency;
    }

    public DocumentStructure getNextStructure() {

        return nextStructure;
    }

    public void setNextStructure(DocumentStructure next) {

        nextStructure = next;
    }
}