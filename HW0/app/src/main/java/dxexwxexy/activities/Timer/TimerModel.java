package dxexwxexy.activities.Timer;

public class TimerModel {
    private long startTime;



    TimerModel () {
        this.startTime = 0;
    }

    TimerModel(long startTime) {
        this.startTime = startTime;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        startTime = 0;
    }

    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean isRunning() {
        return startTime != 0;
    }

    public long getStartTime() {
        return startTime;
    }
}