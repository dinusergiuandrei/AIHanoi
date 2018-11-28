package charts.line;

import charts.ChartParams;
import org.jfree.chart.plot.PlotOrientation;

public class LineChartParams implements ChartParams {
    private String chartTitle;
    private String categoryAxisLabel;
    private String valueAxisLabel;
    private PlotOrientation plotOrientation;
    private Boolean legend;
    private Boolean tooltip;
    private Boolean urls;

    public LineChartParams() {
        this("Chart", "Category", "Value");
    }

    public LineChartParams(String chartTitle, String categoryAxisLabel, String valueAxisLabel) {
        this(chartTitle, categoryAxisLabel, valueAxisLabel, PlotOrientation.VERTICAL, true, true, false);
    }

    public LineChartParams(String chartTitle, String categoryAxisLabel, String valueAxisLabel,
                            PlotOrientation plotOrientation, Boolean legend, Boolean tooltip, Boolean urls) {
        this.chartTitle = chartTitle;
        this.categoryAxisLabel = categoryAxisLabel;
        this.valueAxisLabel = valueAxisLabel;
        this.plotOrientation = plotOrientation;
        this.legend = legend;
        this.tooltip = tooltip;
        this.urls = urls;
    }

    String getChartTitle() {
        return chartTitle;
    }

    String getCategoryAxisLabel() {
        return categoryAxisLabel;
    }

    String getValueAxisLabel() {
        return valueAxisLabel;
    }

    PlotOrientation getPlotOrientation() {
        return plotOrientation;
    }

    Boolean getLegend() {
        return legend;
    }

    Boolean getTooltip() {
        return tooltip;
    }

    Boolean getUrls() {
        return urls;
    }
}
