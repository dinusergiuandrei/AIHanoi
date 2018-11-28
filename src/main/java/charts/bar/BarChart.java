package charts.bar;

import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class BarChart extends Chart {

    private JFreeChart barChart;

    private CategoryDataset createDataSet(List<Entry> source) {
        final DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        source.forEach(
                entry -> {
                    BarChartEntry barChartEntry = (BarChartEntry) entry;
                    dataSet.addValue(barChartEntry.getValue(), barChartEntry.getRowKey(), barChartEntry.getColumnKey());
                }
        );
        return dataSet;
    }

    public void createChart(List<Entry> entries, ChartParams chartParams) {
        BarChartParams params = (BarChartParams) chartParams;
        this.barChart = ChartFactory.createBarChart(
                params.getChartTitle(),
                params.getXLabel(),
                params.getYLabel(),
                createDataSet(entries),
                params.getPlotOrientation(),
                params.getLegend(),
                params.getTooltip(),
                params.getUrls());
    }

    public JFreeChart getChart() {
        return this.barChart;
    }

}