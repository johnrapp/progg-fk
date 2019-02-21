package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

    Map<String, Integer> words = new TreeMap<>();

    public MultiWordCounter(String[] words) {
        for (String w : words) {
            this.words.put(w, 0);
        }
    }

    @Override
    public void process(String w) {
        if (words.containsKey(w)) {
            int val = words.get(w);
            words.put(w, val + 1);
        }

        /*
        words.entrySet().stream()
            .filter(entry -> entry.getKey().equals(w))
            .forEach(entry -> {
                words.put(entry.getKey(), entry.getValue() + 1);
            });
        */
    }

    @Override
    public void report() {
        System.out.println("-- Här kommer nåra ord! --");
        words.entrySet().forEach(entry -> {
            String word = entry.getKey();
            int n = entry.getValue();
            System.out.println(word + ": " + n);
        });

    }
}
