package cd.wangyong.util.time;

/**
 * @author andy
 * @since 2020/11/9
 */
public class MilliTimeRange {

    private long startTime;
    private long endTime;

    public MilliTimeRange() {
    }

    public MilliTimeRange(long duration) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + duration;
    }

    public double ratio(long time) {
        if (time >= endTime || time < startTime)
            return 1;
        return ((double) (time - startTime)) / (endTime - startTime);
    }

    public long duration() {
        return endTime - startTime;
    }

    public boolean isIn(long time) {
        return time >= startTime && time <= endTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void clear() {
        this.startTime = 0;
        this.endTime = 0;
    }
}
