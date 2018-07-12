package dxexwxexy.activities.Timer;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import dxexwxexy.activities.R;

public class TimerActivity extends AppCompatActivity {

    TextView timerDisplay;
    static TimerModel timer;
    static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle("Timer");
        configureUI();
        configureHandler();
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        finish();
    }

    @SuppressLint("DefaultLocale")
    private void configureHandler() {
        handler = new Handler(msg -> {
                long millis = msg.arg1;
                timerDisplay.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                return true;
        });
    }

    private void configureUI() {
        timerDisplay = findViewById(R.id.text_view);
        Button start = findViewById(R.id.start_btn);
        Button stop = findViewById(R.id.stop_btn);
        start.setEnabled(true);
        stop.setEnabled(false);
        start.setOnClickListener(e -> {
            startTimer();
            start.setEnabled(false);
            stop.setEnabled(true);
        });
        stop.setOnClickListener(e -> {
            timer.stop();
            start.setEnabled(true);
            stop.setEnabled(false);
        });
        timerDisplay.setText(R.string.deafault_display);
    }

    private void startTimer() {
        timer = new TimerModel();
        new Thread(() -> {
            timer.start();
            while (timer.isRunning()) {
                Message message = Message.obtain();
                message.arg1 = (int) timer.elpsedTime();
                handler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
