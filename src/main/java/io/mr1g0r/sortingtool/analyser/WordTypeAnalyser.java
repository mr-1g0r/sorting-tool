package sorting.analyser;

import java.util.Arrays;
import java.util.Comparator;

public class WordTypeAnalyser extends DataAnalyser<String> {

    @Override
    protected void processLine(final String line) {
        Arrays.stream(line.split("\\s+"))
                .forEach(word -> {
                    if (statsMap.containsKey(word)) {
                        var count = statsMap.get(word);
                        statsMap.put(word, ++count);
                    } else {
                        statsMap.put(word, 1);
                    }
                });
    }

    @Override
    protected Stats<String> getStats() {
        final var all_count = statsMap.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
        final var max = statsMap.keySet().stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        final var max_count = statsMap.get(max);
        final var max_count_percent = Math.round(max_count / all_count.doubleValue() * 100);

        return new Stats<>(all_count, max, max_count, max_count_percent);
    }

    @Override
    protected void doPrint(final Stats<String> stats) {
        System.out.printf("Total words: %d.%n" +
                        "The longest word: %s (%d time(s), %d%%).",
                stats.allCount(), stats.max(), stats.maxCount(), stats.maxCountPercentage());
    }
}
