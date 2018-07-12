import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TimerDialog extends JDialog {

    private static TimerModel timer;
    private static Thread timerThread;
    private static JTextArea timerDisplay;

    private TimerDialog() {
        super((JFrame) null, "Timer");
        setSize(new Dimension(200, 100));
        configureUI();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void configureUI() {
        setLayout(new FlowLayout());
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        timerDisplay = new JTextArea("00:00:00");
        start.setEnabled(true);
        stop.setEnabled(false);

        start.addActionListener(e -> {
            startTimer();
            start.setEnabled(false);
            stop.setEnabled(true);
        });
        stop.addActionListener(e -> {
            stopTimer();
            start.setEnabled(true);
            stop.setEnabled(false);
        });
        add(timerDisplay);
        add(start);
        add(stop);

    }

    private void startTimer() {
        timer = new TimerModel();
        timerThread = new Thread(() -> {
            timer.start();
            while (timer.isRunning()) {
                long millis = timer.elpsedTime();
                timerDisplay.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
            }
        });
        timerThread.start();
    }

    @SuppressWarnings("CallToThreadStopSuspendOrResumeManager")
    private void stopTimer() {
        timerThread.stop();
        timer.stop();
    }

    public static void main(String[] args) {
        new TimerDialog();
    }
}
