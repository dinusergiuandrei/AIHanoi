package charts.time;

import charts.ChartParams;

public class TimeSeriesParams implements ChartParams {
    private String chartTitle;
    private String timeLabel;
    private String valueLabel;
    private Boolean legend;
    private Boolean tooltip;
    private Boolean urls;

    public TimeSeriesParams(String chartTitle, String timeLabel, String valueLabel, Boolean legend, Boolean tooltip, Boolean urls) {
        this.chartTitle = chartTitle;
        this.timeLabel = timeLabel;
        this.valueLabel = valueLabel;
        this.legend = legend;
        this.tooltip = tooltip;
        this.urls = urls;
    }

    public TimeSeriesParams(String chartTitle, String timeLabel, String valueLabel) {
        this(chartTitle, timeLabel, valueLabel, true, true, false);
    }

    public TimeSeriesParams() {
        this("Chart", "Time", "Value");
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public String getValueLabel() {
        return valueLabel;
    }

    public Boolean getLegend() {
        return legend;
    }

    public Boolean getTooltip() {
        return tooltip;
    }

    public Boolean getUrls() {
        return urls;
    }
}
