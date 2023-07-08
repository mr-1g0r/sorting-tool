package sorting.analyser;

import java.util.Comparator;

public class LineTypeAnalyser extends DataAnalyser<String> {

    @Override
    protected void processLine(final String line) {
        if (statsMap.containsKey(line)) {
            var count = statsMap.get(line);
            statsMap.put(line, ++count);
        } else {
            statsMap.put(line, 1);
        }
    }

    @Override
    protected Stats<String> getStats() {
        final var all_count = Integer.valueOf(statsMap.size());
        final var max = statsMap.keySet().stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        final var max_count = statsMap.get(max);
        final var max_count_percent = Math.round(max_count / all_count.doubleValue() * 100);

        return new Stats<>(all_count, max, max_count, max_count_percent);
    }

    @Override
    protected void doPrint(final Stats<String> stats) {
        System.out.printf("Total lines: %d.%n" +
                        "The longest line:%n%s%n" +
                        "(%d time(s), %d%%).",
                stats.allCount(), stats.max(), stats.maxCount(), stats.maxCountPercentage());
    }
}
