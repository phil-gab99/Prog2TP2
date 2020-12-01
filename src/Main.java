public class Main {

    public static void main(String[] args) {

        IndexationList list = new IndexationList();
        ReverseIndexationList reverseList = new ReverseIndexationList();
        list.addDocument("./res/lorem.txt", reverseList);
        list.addDocument("./res/lorem2.txt", reverseList);

        list.printList(list.getHeadDocument());
        // reverseList.printList(reverseList.getHeadWord());
    }
}