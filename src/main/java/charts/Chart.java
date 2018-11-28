package charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Chart {

    public abstract void createChart(List<Entry> entries, ChartParams params);

    public abstract JFreeChart getChart();

    public void display(String frameTitle, Integer width, Integer height) {
        ApplicationFrame frame = new ApplicationFrame(frameTitle);
        ChartPanel chartPanel = new ChartPanel(this.getChart());
        chartPanel.setPreferredSize(new Dimension(width, height));
        frame.setContentPane(chartPanel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    public void save(String path, Integer width, Integer height, ChartImageType type) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        switch (type) {
            case PNG:
                ChartUtilities.saveChartAsPNG(file, this.getChart(), width, height);
                break;
            case JPEG:
                ChartUtilities.saveChartAsJPEG(file, this.getChart(), width, height);
                break;
        }
    }

    public void display() {
        this.display("Chart", 640, 480);
    }

    public void save(String path) throws IOException {
        this.save(path, 640, 480, ChartImageType.JPEG);
    }

    public enum ChartImageType {
        JPEG,
        PNG
    }
}
