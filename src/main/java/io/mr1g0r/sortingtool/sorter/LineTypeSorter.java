package sorting.sorter;

import sorting.menu.Mode;

import java.util.*;
import java.util.stream.Collectors;

public class LineTypeSorter extends DataSorter implements Mode {

    public LineTypeSorter(final SortingType sortingType) {
        super(sortingType);
    }

    @Override
    public void printStats(final List<String> source) {
        if (getSortingType() == SortingType.NATURAL) {
            String sortedData = source.stream()
                    .sorted()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));

            System.out.printf("Total lines: %d.%n" +
                    "Sorted data:%n%s", source.size(), sortedData);
        } else {
            System.out.printf("Total lines: %d.%n", source.size());

            source.stream()
                    .collect(Collectors.groupingBy(
                            i -> i,
                            LinkedHashMap::new,
                            Collectors.counting()))
                    .entrySet()
                    .stream().sorted(Comparator.comparingLong(Map.Entry::getValue))
                    .forEach(e -> {
                        var percentage = 100 * e.getValue() / source.size();
                        System.out.printf("%s: %d time(s), %d%%%n", e.getKey(), e.getValue(), percentage);
                    });
        }
    }

}
