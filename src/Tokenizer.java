import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Tokenizer {

    private static String text;
    private static String[] textTokens;
    private static HashMap<String, Integer> uniqueTokens;

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

        uniqueTokens = new HashMap<String, Integer>();

        for (String w : textTokens) {

            if (uniqueTokens.containsKey(w)) {

                uniqueTokens.put(w, uniqueTokens.get(w) + 1);
            } else {

                uniqueTokens.put(w, 1);
            }
        }
    }

    public static String getText() {

        return text;
    }

    public static String[] getTextTokens() {

        return textTokens;
    }

    public static HashMap<String, Integer> getUniqueTokens() {

        return uniqueTokens;
    }
}