package charts.bar;

import charts.Entry;

public class BarChartEntry implements Entry {
    private Number value;

    private Comparable rowKey;

    private Comparable columnKey;

    public BarChartEntry(Number value, Comparable rowKey, Comparable columnKey) {
        this.value = value;
        this.rowKey = rowKey;
        this.columnKey = columnKey;
    }

    Number getValue() {
        return value;
    }

    Comparable getRowKey() {
        return rowKey;
    }

    Comparable getColumnKey() {
        return columnKey;
    }
}
