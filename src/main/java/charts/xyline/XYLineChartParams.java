package charts.xyline;

import charts.ChartParams;
import org.jfree.chart.plot.PlotOrientation;

public class XYLineChartParams implements ChartParams {
    private String chartTitle;
    private String xLabel;
    private String yLabel;
    private PlotOrientation plotOrientation;
    private Boolean legend;
    private Boolean tooltip;
    private Boolean urls;

    public XYLineChartParams(String chartTitle, String xLabel, String yLabel,
                             PlotOrientation plotOrientation, Boolean legend,
                             Boolean tooltip, Boolean urls) {
        this.chartTitle = chartTitle;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.plotOrientation = plotOrientation;
        this.legend = legend;
        this.tooltip = tooltip;
        this.urls = urls;
    }

    public XYLineChartParams(String chartTitle, String xLabel, String yLabel) {
        this(chartTitle, xLabel, yLabel, PlotOrientation.VERTICAL, true, true, false);
    }

    public XYLineChartParams() {
        this("Chart", "x", "y");
    }

    public String getChartTitle() {
        return chartTitle;
    }

    String getXLabel() {
        return xLabel;
    }

    String getYLabel() {
        return yLabel;
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
