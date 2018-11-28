package charts.pie;

import charts.Entry;

public class PieChartEntry implements Entry {
    private Comparable key;

    private Number value;

    public PieChartEntry(Comparable key, Number value) {
        this.key = key;
        this.value = value;
    }

    Comparable getKey() {
        return key;
    }

    Number getValue() {
        return value;
    }
}
