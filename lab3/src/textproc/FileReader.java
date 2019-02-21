package textproc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> words = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(path));
            s.findWithinHorizon("\uFEFF", 1);
            s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

            while (s.hasNext()) {
                String word = s.next().toLowerCase();
                words.add(word);
            }

            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

}
