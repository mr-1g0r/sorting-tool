package sorting.sorter;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LongTypeSorter extends GenericDataSorter<Integer> {

    public static final String NATURAL_ORDER_STATS_TEMPLATE = "Total numbers: %d.%nSorted data: %s";
    public static final String BYCOUNT_ORDER_SUMMARY_TEMPLATE = "Total numbers: %d.%n";
    public static final String BYCOUNT_ORDER_ENTRY_TEMPLATE = "%d: %d time(s), %d%%%n";

    public LongTypeSorter(final SortingType sortingType) {
        super(sortingType, NATURAL_ORDER_STATS_TEMPLATE,
                BYCOUNT_ORDER_SUMMARY_TEMPLATE, BYCOUNT_ORDER_ENTRY_TEMPLATE);
    }

    @Override
    protected List<Integer> getRawData(final List<String> userInput) {
        final var rawData = new ArrayList<Integer>();
        for (String line : userInput) {
            rawData.addAll(Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .boxed().toList());
        }
        return rawData;
    }

    @NotNull
    @Override
    protected Collector<CharSequence, ?, String> getNaturalOrderCollector() {
        return Collectors.joining(" ");
    }

    @NotNull
    @Override
    protected Comparator<Map.Entry<Integer, Long>> getPreOrderingComparator() {
        return Map.Entry.comparingByKey();
    }
}
