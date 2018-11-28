package charts.pie;

import charts.ChartParams;

public class PieChartParams implements ChartParams {
    private String chartTitle;
    private Boolean legend;
    private Boolean tooltip;
    private Boolean urls;

    public PieChartParams() {
        this("Chart");
    }

    public PieChartParams(String chartTitle) {
        this(chartTitle, true, true, true);
    }

    public PieChartParams(String chartTitle, Boolean legend, Boolean tooltip, Boolean urls) {
        this.chartTitle = chartTitle;
        this.legend = legend;
        this.tooltip = tooltip;
        this.urls = urls;
    }

    public String getChartTitle() {
        return chartTitle;
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
