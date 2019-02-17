package textproc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map.Entry<String,Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        int countOrder = Integer.compare(o1.getValue(), o2.getValue());

        if (countOrder != 0) return countOrder;

        return o1.getKey().compareTo(o2.getKey());
    }


    public static Comparator<Map.Entry<String,Integer>> compareByPriority(Comparator<Map.Entry<String,Integer>>... comparators) {
        /*return (o1, o2) -> {
            return Arrays.stream(comparators)
                .reduce(0, (val, comparator) ->
                    val != 0 ? val : comparator.compare(o1, o2)
                , Integer::sum);
        };*/
        return (o1, o2) -> {
            for (Comparator<Map.Entry<String,Integer>> comparator : comparators) {
                int comparison = comparator.compare(o1, o2);
                if (comparison != 0) {
                    return  comparison;
                }
            }
            return 0;
        };
    }
}
