package io.mr1g0r.sortingtool.sorter;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LineTypeSorter extends GenericDataSorter<String> {

    public static final String NATURAL_ORDER_STATS_TEMPLATE = "Total lines: %d.%nSorted data:%n%s";
    public static final String BYCOUNT_ORDER_SUMMARY_TEMPLATE = "Total lines: %d.%n";
    public static final String BYCOUNT_ORDER_ENTRY_TEMPLATE = "%s: %d time(s), %d%%%n";

    public LineTypeSorter(final SortingType sortingType,
                          final InputStream inputStream,
                          final OutputStream outputStream) {
        super(sortingType, inputStream, outputStream, NATURAL_ORDER_STATS_TEMPLATE,
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
