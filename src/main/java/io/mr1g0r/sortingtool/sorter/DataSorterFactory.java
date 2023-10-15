package sorting.sorter;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class DataSorterFactory {

    public static final String DATA_TYPE_PARAM = "-dataType";

    enum DataType {
        LONG,
        LINE,
        WORD
    }
    public static final String DATA_VALUE_MISSING_ERROR = "No data type defined!";

    public static final String SORTING_TYPE_PARAM = "-sortingType";
    public static final String SORTING_VALUE_MISSING_ERROR = "No sorting type defined!";

    public static final String INVALID_ARGUMENT_ERROR_TEMPLATE = "\"%s\" is not a valid parameter. It will be skipped.";
    public static final String UNKNOWN_ERROR = "Unknown error";

    public static Optional<DataSorter> createDataSorter(final String[] args) {
        var sorterConfiguration = getSorterConfiguration(args);
        return sorterConfiguration.map(config -> switch (config.dataType()) {
            case LONG -> new LongTypeSorter(config.sortingType());
            case LINE -> new LineTypeSorter(config.sortingType());
            case WORD -> new WordTypeSorter(config.sortingType());
        });
    }

    private static Optional<SorterConfiguration> getSorterConfiguration(final String[] args) {
        Optional<DataType> datatype = Optional.empty();
        Optional<SortingType> sortingType = Optional.empty();
        for (int idx = 0; idx < args.length; idx++) {
            try {
                switch (args[idx]) {
                    case DATA_TYPE_PARAM -> datatype =
                            Optional.of(DataType.valueOf(args[idx + 1].toUpperCase()));
                    case SORTING_TYPE_PARAM -> sortingType =
                            Optional.of(SortingType.valueOf(args[idx + 1].toUpperCase()));
                    default -> {
                        if (args[idx].startsWith("-")) {
                            printError(String.format(INVALID_ARGUMENT_ERROR_TEMPLATE, args[idx]));
                        }
                    }
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                printError(switch (args[idx]) {
                    case DATA_TYPE_PARAM -> DATA_VALUE_MISSING_ERROR;
                    case SORTING_TYPE_PARAM -> SORTING_VALUE_MISSING_ERROR;
                    default -> UNKNOWN_ERROR;
                });
            }
        }
        if (datatype.isPresent()) {
            return Optional.of(new SorterConfiguration(datatype.get(), sortingType.orElse(SortingType.NATURAL)));
        } else {
            printError(DATA_VALUE_MISSING_ERROR);
        }
        return Optional.empty();
    }

    private static void printError(final String errorMessage) {
        System.out.println(errorMessage);
    }


    record SorterConfiguration(@NotNull DataType dataType, @NotNull SortingType sortingType) {}
}
