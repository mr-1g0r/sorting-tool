package sorting.sorter;

import java.util.List;

@FunctionalInterface
public interface DataSorter {
    void printStats(List<String> source);
}
