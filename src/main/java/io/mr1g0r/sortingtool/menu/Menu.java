package sorting.menu;

import sorting.analyser.DataAnalyserFactory;
import sorting.sorter.IntegerSorter;

import java.util.Arrays;

import static sorting.sorter.IntegerSorter.SORT_INTEGER_PARAM;

public class Menu {

    public static Mode parseArgs(final String[] args) {
        if (Arrays.stream(args)
                .anyMatch(arg -> arg.equalsIgnoreCase(SORT_INTEGER_PARAM))) {
            return new IntegerSorter();
        } else {
            return DataAnalyserFactory.createDataAnalyser(args);
        }
    }
}
