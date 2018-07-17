package dxexwxexy.activities.Timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import dxexwxexy.activities.R;

public class TimerActivity extends AppCompatActivity {

    TextView timerDisplay;
    Button start, stop;
    static TimerModel timer;
    static Handler handler;
    static long savedInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle("Timer");
        if (timer == null) {
            timer = new TimerModel();
        }
        configureUI();
        configureHandler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.stop();
        savedInstance = System.currentTimeMillis();

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        if (savedInstance != 0) {
            timer = new TimerModel(savedInstance);
            startTimer();
        }
    }*/

    /*@Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putLong("startTime", timer.getStartTime());
            outState.putBoolean("isRunning", timer.isRunning());
        }
    */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        assert timer != null;
        if (timer.isRunning()) {
            configureUI();
            start.setEnabled(false);
            stop.setEnabled(true);
        }
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
        start = findViewById(R.id.start_btn);
        stop = findViewById(R.id.stop_btn);
        start.setEnabled(true);
        stop.setEnabled(false);
        start.setOnClickListener(e -> {
            startTimer();
            start.setEnabled(false);
            stop.setEnabled(true);
            Toast.makeText(this, "Timer Started", Toast.LENGTH_SHORT).show();
        });
        stop.setOnClickListener(e -> {
            timer.stop();
            handler.removeCallbacksAndMessages(null);
            start.setEnabled(true);
            stop.setEnabled(false);
            Toast.makeText(this, "Timer Stopped", Toast.LENGTH_SHORT).show();
        });
        timerDisplay.setText(R.string.deafault_display);
    }

    private void startTimer() {
        new Thread(() -> {
            timer.start();
            while (timer.isRunning()) {
                Message message = Message.obtain();
                message.arg1 = (int) timer.elapsedTime();
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
