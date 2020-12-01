import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tokenizer {

    private static String text;
    private static String[] textTokens;
    private static ArrayList<WordFrequency> uniqueTokens;

    public static void readFile(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;
        String readText = "";

        while ((line = reader.readLine()) != null) {

            readText += line + " ";
        }

        text = readText;
    }

    public static void createTokens() {

        text = text.replaceAll("[^A-z0-9]", " ");
        textTokens = text.split(" +");

        uniqueTokens = new ArrayList<WordFrequency>();

        for (String w : textTokens) {

            int wordIndex = contains(w, uniqueTokens);

            if (wordIndex != -1) {

                uniqueTokens.set(wordIndex, new WordFrequency(w, uniqueTokens.get(wordIndex).getFrequency() + 1));
            } else {

                uniqueTokens.add(new WordFrequency(w, 1));
            }
        }
    }

    public static int contains(String word,
    ArrayList<WordFrequency> tokens) {

        int index = -1;

        for (WordFrequency entry : tokens) {

            if (entry.getWord().equalsIgnoreCase(word)) {

                index = tokens.indexOf(entry);
                break;
            }
        }

        return index;
    }

    public static String getText() {

        return text;
    }

    public static ArrayList<WordFrequency> getUniqueTokens() {

        return uniqueTokens;
    }
}