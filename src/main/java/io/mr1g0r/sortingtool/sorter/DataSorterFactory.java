package sorting.sorter;

import org.jetbrains.annotations.NotNull;
import sorting.menu.Mode;

import java.util.Optional;

public class DataSorterFactory {

    public static final String DATA_TYPE_PARAM = "-dataType";
    enum DataType {
        LONG,
        LINE,
        WORD
    }

    public static final String SORTING_TYPE_PARAM = "-sortingType";

    public static Mode createDataSorter(final String[] args) {
        var config = getConfiguration(args);
        return switch (config.dataType()) {
            case LONG -> new LongTypeSorter(config.sortingType());
            case LINE -> new LineTypeSorter(config.sortingType());
            case WORD -> new WordTypeSorter(config.sortingType());
        };
    }

    private static SorterConfiguration getConfiguration(final String[] args) {
        DataType datatype = null;
        Optional<SortingType> sortingType = Optional.empty();
        for (int idx = 0; idx < args.length; idx++) {
            switch (args[idx]) {
                case DATA_TYPE_PARAM -> datatype = DataType.valueOf(args[idx + 1].toUpperCase());
                case SORTING_TYPE_PARAM -> sortingType =
                        Optional.of(SortingType.valueOf(args[idx + 1].toUpperCase()));
            }
        }
        assert datatype != null;
        return new SorterConfiguration(datatype, sortingType.orElse(SortingType.NATURAL));
    }

    record SorterConfiguration(@NotNull DataType dataType, @NotNull SortingType sortingType) {}
}
