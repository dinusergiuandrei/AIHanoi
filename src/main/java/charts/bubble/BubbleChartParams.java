package charts.bubble;

import charts.ChartParams;
import org.jfree.chart.plot.PlotOrientation;

public class BubbleChartParams implements ChartParams {
    private String chartTitle;
    private String xLabel;
    private String yLabel;
    private PlotOrientation plotOrientation;
    private Boolean legend;
    private Boolean tooltips;
    private Boolean urls;

    public BubbleChartParams() {
        this("title", "x", "y");
    }

    public BubbleChartParams(String chartTitle, String xLabel, String yLabel) {
        this(chartTitle, xLabel, yLabel, PlotOrientation.HORIZONTAL, true, true, false);
    }

    public BubbleChartParams(String chartTitle, String xLabel, String yLabel,
                             PlotOrientation plotOrientation, Boolean legend,
                             Boolean tooltips, Boolean urls) {
        this.chartTitle = chartTitle;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.plotOrientation = plotOrientation;
        this.legend = legend;
        this.tooltips = tooltips;
        this.urls = urls;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    String getxLabel() {
        return xLabel;
    }

    String getyLabel() {
        return yLabel;
    }

    PlotOrientation getPlotOrientation() {
        return plotOrientation;
    }

    Boolean getLegend() {
        return legend;
    }

    Boolean getTooltips() {
        return tooltips;
    }

    Boolean getUrls() {
        return urls;
    }
}
