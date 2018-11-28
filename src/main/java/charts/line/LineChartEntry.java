package charts.line;

import charts.Entry;

public class LineChartEntry implements Entry {
    private Number number;
    private Comparable rowKey;
    private Comparable columnKey;

    public LineChartEntry(Number number, Comparable rowKey, Comparable columnKey) {
        this.number = number;
        this.rowKey = rowKey;
        this.columnKey = columnKey;
    }

    Number getNumber() {
        return number;
    }

    Comparable getRowKey() {
        return rowKey;
    }

    Comparable getColumnKey() {
        return columnKey;
    }
}
