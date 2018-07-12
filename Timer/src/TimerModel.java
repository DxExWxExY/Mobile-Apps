public class TimerModel {
    private long startTime = 0;

    TimerModel () {

    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        startTime = 0;
    }

    public long elpsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean isRunning() {
        return startTime != 0;
    }
}