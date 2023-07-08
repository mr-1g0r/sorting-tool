package sorting;

import sorting.analyser.DataAnalyser;
import sorting.analyser.DataAnalyserFactory;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        DataAnalyser<?> dataAnalyser = DataAnalyserFactory.createDataAnalyser(args);

        final var input = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        dataAnalyser.printStats(input);
    }
}
