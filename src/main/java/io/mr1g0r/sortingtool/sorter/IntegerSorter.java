package sorting.sorter;

import sorting.menu.Mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerSorter implements Mode {

    public static final String SORT_INTEGER_PARAM = "-sortIntegers";

    @Override
    public void printStats(final List<String> source) {
        final List<Integer> data = new ArrayList<>();
        for (String line : source) {
            data.addAll(processLine(line));
        }

        String sortedData = data.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        System.out.printf("Total numbers: %d.%n" +
                "Sorted data: %s", data.size(), sortedData);
    }

    private List<Integer> processLine(final String line) {
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }
}
