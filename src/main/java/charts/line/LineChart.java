package charts.line;

import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class LineChart extends Chart {

    private JFreeChart chart;

    private DefaultCategoryDataset createDataSet(List<Entry> entries) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        entries.forEach(entry -> {
            LineChartEntry lineEntry = (LineChartEntry) entry;
            dataset.addValue(lineEntry.getNumber(), lineEntry.getRowKey(), lineEntry.getColumnKey());
            }
        );
        return dataset;
    }

    public void createChart(List<Entry> entries, ChartParams chartParams) {
        LineChartParams params = (LineChartParams) chartParams;
        chart = ChartFactory.createLineChart(
                params.getChartTitle(),
                params.getCategoryAxisLabel(),
                params.getValueAxisLabel(),
                createDataSet(entries),
                params.getPlotOrientation(),
                params.getLegend(), params.getTooltip(), params.getUrls());

    }

    public JFreeChart getChart(){
        return this.chart;
    }

}
