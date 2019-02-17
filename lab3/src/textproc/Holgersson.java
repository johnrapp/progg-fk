package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
        long t0 = System.nanoTime();

        ArrayList<TextProcessor> processors = new ArrayList<>();

		processors.add(new SingleWordCounter("nils"));
		processors.add(new SingleWordCounter("norge"));

        processors.add(new MultiWordCounter(REGIONS));
        processors.add(getGeneralWordCounter());

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

        while (s.hasNext()) {
			String word = s.next().toLowerCase();

			processors.forEach(p -> p.process(word));
		}

		s.close();

		processors.forEach(TextProcessor::report);

        long t1 = System.nanoTime();
        System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
        // Median(HashMap): 1385 ms
        // Median(TreeMap): 1471 ms
        // HashMap lite snabbare, men stor variation

        // Map är Interface, HashMap är implementering
        // TreeMap är sorterad, vilket gör sort snabbare
        // Dock är TreeMap O(log(n)) för operationer, medan HashMap är O(1)
    }

	public static TextProcessor getGeneralWordCounter() throws FileNotFoundException {
        Scanner s = new Scanner(new File("undantagsord.txt"));

        s.findWithinHorizon("\uFEFF", 1);
        s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

        Set<String> stopwords = new HashSet<>();

        while (s.hasNext()) {
            String word = s.next().toLowerCase();
            stopwords.add(word);
        }

        return new GeneralWordCounter(stopwords);
    }
}