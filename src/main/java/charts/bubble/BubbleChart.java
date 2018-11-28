package charts.bubble;

import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BubbleChart extends Chart {

    private JFreeChart chart;

    private XYZDataset createDataSet(List<Entry> allEntries) {
        Map<String, List<BubbleChartEntry>> map = new LinkedHashMap<>();

        allEntries.forEach(
                entry -> {
                    BubbleChartEntry bubbleEntry = (BubbleChartEntry) entry;
                    String series = bubbleEntry.getSeriesKey();
                    if (!map.containsKey(series)) {
                        map.put(series, new ArrayList<>());
                    }
                    map.get(series).add(bubbleEntry);
                }
        );

        DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();

        map.keySet().forEach(series -> {
            List<BubbleChartEntry> entries = map.get(series);
            double ys[] = new double[entries.size()];
            double xs[] = new double[entries.size()];
            double radius[] = new double[entries.size()];

            for (int index = 0; index < entries.size(); index++) {
                ys[index] = entries.get(index).getY();
                xs[index] = entries.get(index).getX();
                radius[index] = entries.get(index).getRadius();
            }

            double source[][] = {ys, xs, radius};
            defaultxyzdataset.addSeries(series, source);
        });

        return defaultxyzdataset;
    }

    public void createChart(List<Entry> source, ChartParams chartParams) {
        BubbleChartParams params = (BubbleChartParams) chartParams;
        this.chart = ChartFactory.createBubbleChart(
                params.getChartTitle(),
                params.getxLabel(),
                params.getyLabel(),
                createDataSet(source),
                params.getPlotOrientation(),
                params.getLegend(),
                params.getTooltips(),
                params.getUrls());
    }

    public JFreeChart getChart() {
        return this.chart;
    }
}