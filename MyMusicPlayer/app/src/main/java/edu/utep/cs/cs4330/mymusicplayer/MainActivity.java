package edu.utep.cs.cs4330.mymusicplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private EditText urlEdit;
    private ProgressBar progressBar;
    private Handler handler;
    private Thread updater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlEdit = findViewById(R.id.urlEdit);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    /** Called when the play button is clicked. */
    public void playClicked(View view) {
        if (player == null || !player.isPlaying()) {
            player = MediaPlayer.create(this, Uri.parse(urlEdit.getText().toString()));
            if (player != null) {
                player.start();
                toast("Playing.");
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(player.getDuration());
                if (updater == null) {
                    startHandlers();
                } else {
                    updater.start();
                }
            } else {
                toast("Failed!");
            }
        }
    }

    /** Called when the stop button is clicked. */
    public void stopClicked(View view) {
        if (player != null && player.isPlaying()) {
            player.stop();
            toast("Stopped.");
        }
    }

    /** Shows a toast message. */
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startHandlers() {
        handler = new Handler(msg -> {
            progressBar.setProgress(msg.arg1);
            return true;
        });
        updater = new Thread(() -> {
            while (player.isPlaying()) {
                Message message = new Message();
                message.arg1 = player.getCurrentPosition();
                handler.sendMessage(message);
            }
        });
        updater.start();
    }

}
