package cd.wangyong.tool.circuitbreaker;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 监控实现
 * todo
 * @author andy
 * @since 2020/11/10
 */
public class MyCBMonitor implements CBMonitor {

    private AtomicInteger failCount = new AtomicInteger(0);


    @Override
    public void recordFailure() {
        failCount.incrementAndGet();
    }

    @Override
    public void recordSuccess() {

    }

    @Override
    public int computeFailures() {
        return failCount.get();
    }

    @Override
    public int computeAvailability() {
        return 0;
    }

    @Override
    public int computeSuccess() {
        return 0;
    }

    @Override
    public void recordCBOpenTime(long currentTimeMillis) {

    }

    @Override
    public boolean isOutCBPeriod(long currentTimeMillis) {
        return false;
    }

    @Override
    public void resetBreakTime() {

    }

    @Override
    public boolean isOutRecoveryPeriod(long currentTimeMillis) {
        return false;
    }
}
