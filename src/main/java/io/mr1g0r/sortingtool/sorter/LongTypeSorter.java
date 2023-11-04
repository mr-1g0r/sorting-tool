package io.mr1g0r.sortingtool.sorter;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LongTypeSorter extends GenericDataSorter<Long> {

    public static final String NATURAL_ORDER_STATS_TEMPLATE = "Total numbers: %d.%nSorted data: %s";
    public static final String BYCOUNT_ORDER_SUMMARY_TEMPLATE = "Total numbers: %d.%n";
    public static final String BYCOUNT_ORDER_ENTRY_TEMPLATE = "%d: %d time(s), %d%%%n";

    public static final String VALUE_PARSING_ERROR_TEMPLATE = "\"%s\" is not a long. It will be skipped.%n";

    public LongTypeSorter(final SortingType sortingType,
                          final InputStream inputStream,
                          final OutputStream outputStream) {
        super(sortingType, inputStream, outputStream, NATURAL_ORDER_STATS_TEMPLATE,
                BYCOUNT_ORDER_SUMMARY_TEMPLATE, BYCOUNT_ORDER_ENTRY_TEMPLATE);
    }

    @Override
    protected List<Long> getRawData(final List<String> userInput) {
        final var rawData = new ArrayList<Long>();
        for (String line : userInput) {
            rawData.addAll(Arrays.stream(line.split("\\s+"))
                    .map(s -> {
                        try {
                            return OptionalLong.of(Long.parseLong(s));
                        } catch (NumberFormatException e) {
                            System.out.printf(VALUE_PARSING_ERROR_TEMPLATE, s);
                        }
                        return OptionalLong.empty();
                    })
                    .filter(OptionalLong::isPresent)
                    .mapToLong(OptionalLong::getAsLong)
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

}
