package sorting.sorter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LineTypeSorter extends GenericDataSorter<String> {

    public static final String NATURAL_ORDER_STATS_TEMPLATE = "Total lines: %d.%nSorted data:%n%s";
    public static final String BYCOUNT_ORDER_SUMMARY_TEMPLATE = "Total lines: %d.%n";
    public static final String BYCOUNT_ORDER_ENTRY_TEMPLATE = "%s: %d time(s), %d%%%n";

    public LineTypeSorter(final SortingType sortingType) {
        super(sortingType, NATURAL_ORDER_STATS_TEMPLATE,
                BYCOUNT_ORDER_SUMMARY_TEMPLATE, BYCOUNT_ORDER_ENTRY_TEMPLATE);
    }

    @Override
    protected List<String> getRawData(final List<String> userInput) {
        return userInput;
    }

    @NotNull
    @Override
    protected Collector<CharSequence, ?, String> getNaturalOrderCollector() {
        return Collectors.joining("\n");
    }

}
