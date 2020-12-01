import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* The class Tokenizer reads a given file and tokenizes it while keeping count
* of each word within the text
*
* @author Philippe Gabriel
* @version 1.0 2020-12-07
***/

public class Tokenizer {

    private String text;                           //Text within file
    private ArrayList<WordFrequency> uniqueTokens; //Text unique tokens

    /**
    * The constructor method Tokenizer tokenizes a given file
    *
    * @param path String indicating file path to tokenize
    ***/

    public Tokenizer(String path) throws IOException {

        text = readFile(path);
        uniqueTokens = createTokens();
    }

    /**
    * The getter method getText grants access to the file's text contents
    *
    * @return text String holding text within file
    ***/

    public String getText() {

        return text;
    }

    /**
    * The getter method getUniqueTokens grants access to the file's unique
    * tokens
    *
    * @return uniqueTokens ArrayList of text's unique tokens
    ***/

    public ArrayList<WordFrequency> getUniqueTokens() {

        return uniqueTokens;
    }

    /**
    * The method readFile reads a file given its path and transcribes its
    * contents in a String variable
    *
    * @param path File path to read
    * @return readText Contents of file saved in a String variable
    ***/

    private String readFile(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;
        String readText = "";

        while ((line = reader.readLine()) != null) {

            readText += line + " ";
        }

        reader.close();
        return readText;
    }

    /**
    * The method createTokens creates the file's unique tokens with respect
    * to its read contents
    *
    * @return textUniqueTokens ArrayList of the unique tokens and their
    * frequency within text
    ***/

    private ArrayList<WordFrequency> createTokens() {

        String[] textTokens;

        text = text.replaceAll("[^A-z0-9]", " ");

        if (text.equals("")) { //Checking for empty string

            text = " ";
            textTokens = text.split("\\s+");
        } else { //Trimming for leading or trailing whitespaces before split

            textTokens = text.trim().split("\\s+");
        }

        ArrayList<WordFrequency> textUniqueTokens =
        new ArrayList<WordFrequency>();

        for (String w : textTokens) {

            int wordIndex = contains(w, textUniqueTokens);

            if (wordIndex != -1) {

                //Updating already present word by incrementing frequency
                textUniqueTokens.get(wordIndex).incrementFrequency();
            } else {

                //Appends new word onto list
                textUniqueTokens.add(new WordFrequency(w, 1));
            }
        }

        return textUniqueTokens;
    }

    /**
    * The method contains verifies whether a given word is present within a
    * given array list of words paired with their frequencies
    *
    * @param word String word to verify
    * @param tokens ArrayList of words paired with their frequencies
    * @return index Index location of word or -1 if word is not within list
    ***/

    private int contains(String word, ArrayList<WordFrequency> tokens) {

        int index = -1;

        for (WordFrequency entry : tokens) {

            if (entry.getWord().equalsIgnoreCase(word)) {

                index = tokens.indexOf(entry);
                break;
            }
        }

        return index;
    }
}