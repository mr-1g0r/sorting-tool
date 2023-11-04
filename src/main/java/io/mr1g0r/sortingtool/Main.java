package io.mr1g0r.sortingtool;

import io.mr1g0r.sortingtool.sorter.DataSorter;
import io.mr1g0r.sortingtool.sorter.DataSorterFactory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Optional<DataSorter> dataSorter = DataSorterFactory.createDataSorter(args);
        dataSorter.ifPresent(sorter -> {
            final var input = new ArrayList<String>();
            Scanner scanner = new Scanner(sorter.getInputStream());
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            sorter.printStats(input);
            sorter.close();
        });
    }
}
