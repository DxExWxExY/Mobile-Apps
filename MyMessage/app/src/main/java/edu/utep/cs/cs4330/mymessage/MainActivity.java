package edu.utep.cs.cs4330.mymessage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();
        TextView sender = findViewById(R.id.senderLabel);
        TextView body = findViewById(R.id.msgLabel);
        Intent intent = getIntent();
        sender.setText(intent.getStringExtra("msgSender"));
        body.setText(intent.getStringExtra("msgBody"));
    }

    private void requestSmsPermission() {
        String smsPermission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, smsPermission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[] { smsPermission };
            ActivityCompat.requestPermissions(this, permissions, REQUEST_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            showToast(grantResults[0] == PackageManager.PERMISSION_GRANTED ?
                "Permission granted!" : "Permission not granted!");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
