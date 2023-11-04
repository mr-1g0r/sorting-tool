package io.mr1g0r.sortingtool.sorter;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class GenericDataSorter<T extends Comparable<T>> implements DataSorter {

    private final SortingType sortingType;
    private final InputStream inputStream;
    private final String naturalOrderStatsTemplate;
    private final String byCountOrderSummaryTemplate;
    private final String byCountOrderEntryTemplate;

    private final PrintWriter printWriter;

    protected GenericDataSorter(final SortingType sortingType,
                                final InputStream inputStream,
                                final OutputStream outputStream,
                                final String naturalOrderStatsTemplate,
                                final String byCountOrderSummaryTemplate,
                                final String byCountOrderEntryTemplate) {
        this.sortingType = sortingType;
        this.inputStream = inputStream;
        this.naturalOrderStatsTemplate = naturalOrderStatsTemplate;
        this.byCountOrderSummaryTemplate = byCountOrderSummaryTemplate;
        this.byCountOrderEntryTemplate = byCountOrderEntryTemplate;

        this.printWriter = outputStream == System.out ? null :
                new PrintWriter(outputStream, true);
    }

    @Override
    public void printStats(final List<String> userInput) {
        final List<T> unorderedData = getRawData(userInput);
        if (getSortingType() == SortingType.NATURAL) {
            String sortedData = unorderedData.stream()
                    .sorted()
                    .map(Object::toString)
                    .collect(getNaturalOrderCollector());

            printChunk(String.format(naturalOrderStatsTemplate, unorderedData.size(), sortedData));
        } else {
            printChunk(String.format(byCountOrderSummaryTemplate, unorderedData.size()));

            unorderedData.stream()
                    .collect(Collectors.groupingBy(
                            i -> i,
                            LinkedHashMap::new,
                            Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .sorted(Comparator.comparingLong(Map.Entry::getValue))
                    .forEach(e -> {
                        var percentage = 100 * e.getValue() / unorderedData.size();
                        printChunk(String.format(byCountOrderEntryTemplate,
                                                    e.getKey(), e.getValue(), percentage));
                    });
        }
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void close() {
        if (printWriter != null) {
            printWriter.close();
        }
    }

    private void printChunk(@NotNull final String chunk) {
        if (printWriter != null) {
            printWriter.print(chunk);
        } else {
            System.out.print(chunk);
        }
    }

    protected abstract List<T> getRawData(final List<String> userInput);

    @NotNull
    protected abstract Collector<CharSequence, ?, String> getNaturalOrderCollector();

    protected SortingType getSortingType() {
        return sortingType;
    }

}
