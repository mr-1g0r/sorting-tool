package sorting.analyser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataAnalyser<T> {
    protected final Map<T, Integer> statsMap = new HashMap<>();

    public void printStats(List<String> source) {
        statsMap.clear();

        for (String line : source) {
            processLine(line);
        }

        final var stats = getStats();
        doPrint(stats);
    }

    protected abstract void processLine(final String line);

    protected abstract Stats<T> getStats();

    protected abstract void doPrint(final Stats<T> stats);

    record Stats<T> (Integer allCount, T max, Integer maxCount, Long maxCountPercentage) {}

}
