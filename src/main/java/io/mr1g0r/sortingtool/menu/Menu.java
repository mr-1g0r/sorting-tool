package sorting.menu;

import sorting.sorter.DataSorterFactory;

public class Menu {

    public static Mode parseArgs(final String[] args) {
        return DataSorterFactory.createDataSorter(args);
    }
}
