package sorting.analyser;

import java.util.Arrays;
import java.util.Comparator;

public class LongTypeAnalyser extends DataAnalyser<Long> {

    @Override
    protected void processLine(final String line) {
        Arrays.stream(line.split("\\s+"))
                .mapToLong(Long::parseLong)
                .forEach(number -> {
                    if (statsMap.containsKey(number)) {
                        var count = statsMap.get(number);
                        statsMap.put(number, ++count);
                    } else {
                        statsMap.put(number, 1);
                    }
                });
    }

    @Override
    protected Stats<Long> getStats() {
        final var all_count = statsMap.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
        final var max = statsMap.keySet().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
        final var max_count = statsMap.get(max);
        final var max_count_percent = Math.round(max_count / all_count.doubleValue() * 100);

        return new Stats<>(all_count, max, max_count, max_count_percent);
    }

    @Override
    protected void doPrint(final Stats<Long> stats) {
        System.out.printf("Total numbers: %d.%n" +
                        "The greatest number: %d (%d time(s), %d%%).",
                stats.allCount(), stats.max(), stats.maxCount(), stats.maxCountPercentage());
    }
}
