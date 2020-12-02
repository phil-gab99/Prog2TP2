import documentlist.IndexationList;
import wordlist.ReverseIndexationList;

public class Main {

    public static void main(String[] args) {

        IndexationList list = new IndexationList();
        ReverseIndexationList reverseList = new ReverseIndexationList();
        list.addDocument("./res/lorem.txt", reverseList);
        list.addDocument("./res/lorem.txt", reverseList);
        // list.addDocument("./res/lorem2.txt", reverseList);

        list.printList();
        reverseList.printList();

        // String[] words = {"w1", "w2"};
        //
        // search(words);
    }
}