import java.util.ArrayList;
import java.util.Collections;
import documentlist.IndexationList;
import documentlist.Document;
import wordlist.*;
import tools.DocumentScore;

public class Main {

    static IndexationList list = new IndexationList();
    static ReverseIndexationList reverseList = new ReverseIndexationList();

    public static void main(String[] args) {

        list.addDocument("./res/lorem.txt", reverseList);
        list.addDocument("./res/lorem2.txt", reverseList);

        // list.printList();
        // reverseList.printList();

        String[] words = {"ad", "in", "lorem", "c", "holder", "s"};

        ArrayList<DocumentScore> scores = search(words);

        for (DocumentScore d : scores) {

            System.out.println(d);
        }
    }

    public static ArrayList<DocumentScore> search(String[] query) {

        Word temp;
        ArrayList<DocumentScore> result;

        if ((query.length == 0)
        || ((temp = reverseList.contains(query[0])) == null)) {

            return null;
        } else {

            result = new ArrayList<DocumentScore>();
            DocumentStructure node = temp.getHeadStructure();

            while (node != null) {

                Document doc = list.getHeadDocument();

                while (doc != null) {

                    if (doc.getName().equalsIgnoreCase(node.getDocument())) {

                        int sum = doc.score(query);

                        if (sum != 0) {

                            result.add(new DocumentScore(doc.getName(), sum));
                        }

                        break;
                    }

                    doc = doc.getNextDocument();
                }

                node = node.getNextStructure();
            }
        }

        Collections.sort(result);
        return result;
    }
}