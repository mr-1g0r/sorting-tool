package io.mr1g0r.sortingtool.sorter;

import java.io.InputStream;
import java.util.List;

public interface DataSorter {
    void printStats(List<String> source);

    InputStream getInputStream();

    void close();
}
