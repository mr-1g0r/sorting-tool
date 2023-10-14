package sorting.sorter;

import sorting.menu.Mode;

import java.util.*;
import java.util.stream.Collectors;

public class WordTypeSorter extends DataSorter implements Mode {

    public WordTypeSorter(final SortingType sortingType) {
        super(sortingType);
    }

    @Override
    public void printStats(final List<String> source) {
        final List<String> data = new ArrayList<>();
        for (String line : source) {
            data.addAll(processLine(line));
        }

        if (getSortingType() == SortingType.NATURAL) {
            String sortedData = data.stream()
                    .sorted()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));

            System.out.printf("Total words: %d.%n" +
                    "Sorted data: %s", data.size(), sortedData);
        } else {
            System.out.printf("Total words: %d.%n", data.size());

            data.stream()
                    .collect(Collectors.groupingBy(
                            i -> i,
                            LinkedHashMap::new,
                            Collectors.counting()))
                    .entrySet()
                    .stream().sorted(Comparator.comparingLong(Map.Entry::getValue))
                    .forEach(e -> {
                        var percentage = 100 * e.getValue() / data.size();
                        System.out.printf("%s: %d time(s), %d%%%n", e.getKey(), e.getValue(), percentage);
                    });
        }
    }

    private List<String> processLine(final String line) {
        return Arrays.stream(line.split("\\s+"))
                .sorted()
                .collect(Collectors.toList());
    }
}
