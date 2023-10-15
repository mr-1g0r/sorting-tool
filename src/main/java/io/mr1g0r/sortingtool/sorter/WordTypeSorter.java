package sorting.sorter;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordTypeSorter extends GenericDataSorter<String> {

    public static final String NATURAL_ORDER_STATS_TEMPLATE = "Total words: %d.%nSorted data: %s";
    public static final String BYCOUNT_ORDER_SUMMARY_TEMPLATE = "Total words: %d.%n";
    public static final String BYCOUNT_ORDER_ENTRY_TEMPLATE = "%s: %d time(s), %d%%%n";

    public WordTypeSorter(final SortingType sortingType) {
        super(sortingType, NATURAL_ORDER_STATS_TEMPLATE,
                BYCOUNT_ORDER_SUMMARY_TEMPLATE, BYCOUNT_ORDER_ENTRY_TEMPLATE);
    }

    @Override
    protected List<String> getRawData(final List<String> userInput) {
        final var rawData = new ArrayList<String>();
        for (String line : userInput) {
            rawData.addAll(Arrays.stream(line.split("\\s+"))
                    .sorted()
                    .toList());
        }
        return rawData;
    }

    @NotNull
    @Override
    protected Collector<CharSequence, ?, String> getNaturalOrderCollector() {
        return Collectors.joining(" ");
    }

}
