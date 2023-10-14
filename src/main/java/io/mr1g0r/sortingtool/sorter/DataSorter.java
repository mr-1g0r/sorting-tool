package sorting.sorter;

public class DataSorter {

    private final SortingType sortingType;

    protected DataSorter(final SortingType sortingType) {
        this.sortingType = sortingType;
    }

    public SortingType getSortingType() {
        return sortingType;
    }
}
