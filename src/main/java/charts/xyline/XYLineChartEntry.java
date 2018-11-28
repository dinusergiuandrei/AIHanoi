package charts.xyline;

import charts.Entry;

public class XYLineChartEntry implements Entry {
    private Number x;

    private Number y;

    private String series;

    public XYLineChartEntry(Number x, Number y, String series) {
        this.x = x;
        this.y = y;
        this.series = series;
    }

    Number getX() {
        return x;
    }

    Number getY() {
        return y;
    }

    String getSeries() {
        return series;
    }
}
