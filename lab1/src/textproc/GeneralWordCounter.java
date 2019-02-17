package textproc;

import java.util.*;
import java.util.function.Consumer;

import static textproc.WordCountComparator.compareByPriority;

public class GeneralWordCounter implements TextProcessor {

    Map<String, Integer> words = new TreeMap<>();
    Set<String> stopwords;

    public GeneralWordCounter(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    @Override
    public void process(String w) {
        if (stopwords.contains(w)) {
            return;
        }

        int currentCount = words.getOrDefault(w, 0);
        words.put(w, currentCount + 1);
    }


    @Override
    public void report() {
        System.out.println("-- Här kommer toporden! --");

        Consumer<Map.Entry<String, Integer>> printEntry = entry -> {
            String word = entry.getKey();
            int n = entry.getValue();
            System.out.println(word + ": " + n);
        };

        /*
        words.entrySet().stream()
            .filter(entry -> entry.getValue() >= 200)
            .forEach(printEntry);
        */
        Comparator<Map.Entry<String, Integer>> comparator = compareByPriority(
                (o1, o2) -> o2.getValue() - o1.getValue(),
                (o1, o2) -> o1.getKey().compareTo(o2.getKey())
        );

        Comparator<Map.Entry<String, Integer>> compareCount = Comparator.comparing(Map.Entry::getValue);
        words.entrySet().stream()
            //.sorted(new WordCountComparator())
            .sorted(compareByPriority(
                compareCount.reversed(),
                Comparator.comparing(Map.Entry::getKey)
            ))
            .limit(5)
            .forEach(printEntry);

    }
}
