package sorting.analyser;

public class DataAnalyserFactory {

    public static final String DATA_TYPE_PARAM = "-dataType";

    enum DataType {
        LONG,
        LINE,
        WORD
    }

    public static DataAnalyser<?> createDataAnalyser(final String[] args) {
        if (args[0].equalsIgnoreCase(DATA_TYPE_PARAM) && args[1] != null) {
            return switch (DataType.valueOf(args[1].toUpperCase())) {
                case LONG -> new LongTypeAnalyser();
                case LINE -> new LineTypeAnalyser();
                case WORD -> new WordTypeAnalyser();
            };
        }
        return new WordTypeAnalyser();
    }
}
