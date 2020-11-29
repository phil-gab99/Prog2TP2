public class DocumentStructure {

    private Document document;
    private int frequency;
    private DocumentStructure nextStructure;

    public DocumentStructure(Document document, int frequency,
    DocumentStructure nextStructure) {

        this.document = document;
        this.frequency = frequency;
        this.nextStructure = nextStructure;
    }
}