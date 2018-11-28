package charts.time;

import charts.Entry;
import org.jfree.data.time.RegularTimePeriod;

public class TimeSeriesEntry implements Entry {
    private RegularTimePeriod timePeriod;

    private Number value;

    private String series;

    public TimeSeriesEntry(RegularTimePeriod timePeriod, Number value, String series) {
        this.timePeriod = timePeriod;
        this.value = value;
        this.series = series;
    }

    public RegularTimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Number getValue() {
        return value;
    }

    public String getSeries() {
        return series;
    }
}
