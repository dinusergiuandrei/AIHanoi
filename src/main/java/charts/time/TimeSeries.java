package charts.time;

import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TimeSeries extends Chart {

    private JFreeChart chart;

    private XYDataset createDataSet(List<Entry> entries){
        Map<String, List<TimeSeriesEntry>> map = new LinkedHashMap<>();

        entries.forEach(
                entry -> {
                    TimeSeriesEntry timeSeriesEntry = (TimeSeriesEntry) entry;
                    String series = timeSeriesEntry.getSeries();
                    if(!map.containsKey(series)){
                        map.put(series, new ArrayList<>());
                    }
                    map.get(series).add(timeSeriesEntry);
                }
        );

        TimeSeriesCollection collection = new TimeSeriesCollection();
        map.keySet().forEach(
                seriesTitle -> {
                    org.jfree.data.time.TimeSeries series = new org.jfree.data.time.TimeSeries( seriesTitle, org.jfree.data.time.Second.class );
                    map.get(seriesTitle).forEach(entry -> series.add(entry.getTimePeriod(), entry.getValue()));
                    collection.addSeries(series);
                }
        );
        return collection;
    }

    public void createChart(List<Entry> entries, ChartParams chartParams){
        TimeSeriesParams params = (TimeSeriesParams) chartParams;
        chart = ChartFactory.createTimeSeriesChart(
                params.getChartTitle(),
                params.getTimeLabel(),
                params.getValueLabel(),
                createDataSet(entries),
                params.getLegend(),
                params.getTooltip(),
                params.getUrls());
    }

    @Override
    public JFreeChart getChart() {
        return chart;
    }
}
