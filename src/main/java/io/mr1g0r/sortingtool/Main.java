package sorting;

import sorting.sorter.DataSorter;
import sorting.sorter.DataSorterFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {

        final var input = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        DataSorter dataSorter = Menu.parseArgs(args);
        dataSorter.printStats(input);
    }

    static class Menu {
        public static DataSorter parseArgs(final String[] args) {
            return DataSorterFactory.createDataSorter(args);
        }
    }
}
