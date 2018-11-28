package charts.xyline;

import charts.Chart;
import charts.ChartParams;
import charts.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XYLineChart extends Chart {

    private JFreeChart chart;

    private XYDataset createDataSet(List<Entry> entries) {
        Map<String, List<XYLineChartEntry>> map = new LinkedHashMap<>();

        entries.forEach(
                entry -> {
                    XYLineChartEntry xyLineChartEntry = (XYLineChartEntry) entry;
                    String series = xyLineChartEntry.getSeries();
                    if(!map.containsKey(series)){
                        map.put(series, new ArrayList<>());
                    }
                    map.get(series).add(xyLineChartEntry);
                }
        );

        XYSeriesCollection collection = new XYSeriesCollection();

        map.keySet().forEach(
                seriesTitle -> {
                    XYSeries series = new XYSeries(seriesTitle);
                    map.get(seriesTitle).forEach(entry -> series.add(entry.getX(), entry.getY()));
                    collection.addSeries(series);
                }
        );

        return collection;
    }

    public void createChart(List<Entry> entries, ChartParams chartParams) {
        XYLineChartParams params = (XYLineChartParams) chartParams;
        chart = ChartFactory.createXYLineChart(
                params.getChartTitle(),
                params.getXLabel(),
                params.getYLabel(),
                createDataSet(entries),
                params.getPlotOrientation(),
                params.getLegend(),
                params.getTooltip(),
                params.getUrls());
    }

//    public void display(List<XYLineChartSeries> seriesList, String chartTitle, String xLabel, String yLabel){
//
//        ChartPanel chartPanel = new ChartPanel( xyLineChart );
//        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//        final XYPlot plot = xyLineChart.getXYPlot( );
//
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
//        renderer.setSeriesPaint( 0 , Color.RED );
//        renderer.setSeriesPaint( 1 , Color.GREEN );
//        renderer.setSeriesPaint( 2 , Color.YELLOW );
//        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
//        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
//        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
//        plot.setRenderer( renderer );
//        setContentPane( chartPanel );
//        this.pack( );
//        RefineryUtilities.centerFrameOnScreen( this );
//        this.setVisible( true );
//    }


    public JFreeChart getChart() {
        return chart;
    }
}