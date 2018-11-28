package charts.pie;


import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.List;

public class PieChart extends Chart {

    private JFreeChart chart;

    private PieDataset createDataSet(List<Entry> entries) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        entries.forEach(entry -> {
            PieChartEntry pieEntry = (PieChartEntry) entry;
            dataset.setValue(pieEntry.getKey(), pieEntry.getValue());
        });
        return dataset;
    }

    public void createChart(List<Entry> entries, ChartParams chartParams){
        PieChartParams params = (PieChartParams) chartParams;
        chart = ChartFactory.createPieChart(
                params.getChartTitle(),
                createDataSet(entries),
                params.getLegend(),
                params.getTooltip(),
                params.getUrls());
    }

    public JFreeChart getChart() {
        return chart;
    }
}