package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        final var stats = new HashMap<Long, Integer>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            // write your code here
            if (stats.containsKey(number)) {
                var count = stats.get(number);
                stats.put(number, ++count);
            } else {
                stats.put(number, 1);
            }
        }
        printStats(stats);
    }

    private static void printStats(final Map<Long, Integer> stats) {
        final var all_count = stats.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
        final var max = stats.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
        final var max_count = stats.get(max);
        System.out.printf("Total numbers: %d%n" +
                        "The greatest number: %d (%d time(s)).",
                all_count, max, max_count);
    }
}
