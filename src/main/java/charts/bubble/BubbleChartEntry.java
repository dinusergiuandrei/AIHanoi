package charts.bubble;

import charts.Entry;

public class BubbleChartEntry implements Entry {
    private double x;

    private double y;

    private double radius;

    private String seriesKey;

    public BubbleChartEntry(double x, double y, double radius, String seriesKey) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.seriesKey = seriesKey;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getRadius() {
        return radius;
    }

    String getSeriesKey() {
        return seriesKey;
    }
}
