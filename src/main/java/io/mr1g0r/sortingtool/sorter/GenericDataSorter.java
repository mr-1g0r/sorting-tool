package sorting.sorter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class GenericDataSorter<T extends Comparable<T>> implements DataSorter {

    private final SortingType sortingType;
    private final String naturalOrderStatsTemplate;
    private final String byCountOrderSummaryTemplate;
    private final String byCountOrderEntryTemplate;

    protected GenericDataSorter(final SortingType sortingType,
                                final String naturalOrderStatsTemplate,
                                final String byCountOrderSummaryTemplate,
                                final String byCountOrderEntryTemplate) {
        this.sortingType = sortingType;
        this.naturalOrderStatsTemplate = naturalOrderStatsTemplate;
        this.byCountOrderSummaryTemplate = byCountOrderSummaryTemplate;
        this.byCountOrderEntryTemplate = byCountOrderEntryTemplate;
    }

    @Override
    public void printStats(final List<String> userInput) {
        final List<T> unorderedData = getRawData(userInput);
        if (getSortingType() == SortingType.NATURAL) {
            String sortedData = unorderedData.stream()
                    .sorted()
                    .map(Object::toString)
                    .collect(getNaturalOrderCollector());

            System.out.printf(naturalOrderStatsTemplate, unorderedData.size(), sortedData);
        } else {
            System.out.printf(byCountOrderSummaryTemplate, unorderedData.size());

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
                        System.out.printf(byCountOrderEntryTemplate, e.getKey(), e.getValue(), percentage);
                    });
        }
    }

    protected abstract List<T> getRawData(final List<String> userInput);

    @NotNull
    protected abstract Collector<CharSequence, ?, String> getNaturalOrderCollector();

    protected SortingType getSortingType() {
        return sortingType;
    }
}
