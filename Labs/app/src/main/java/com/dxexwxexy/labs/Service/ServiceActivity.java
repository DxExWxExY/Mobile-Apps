package com.dxexwxexy.labs.Service;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dxexwxexy.labs.R;

public class ServiceActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        requestSmsPermission();
        EditText phone = findViewById(R.id.phoneEdit);
        EditText delay = findViewById(R.id.delayEdit);
        EditText message = findViewById(R.id.msgEdit);
        Button service = findViewById(R.id.sendButton);
        service.setOnClickListener(e -> {
            Intent intent = new Intent(this, MessageService.class);
            intent.putExtra("phone", phone.getText().toString());
            intent.putExtra("delay", delay.getText().toString());
            intent.putExtra("message", message.getText().toString());
            startService(intent);
        });
    }

    private void requestSmsPermission() {
        String smsPermission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, smsPermission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[] { smsPermission };
            ActivityCompat.requestPermissions(this, permissions, REQUEST_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            showToast(grantResults[0] == PackageManager.PERMISSION_GRANTED ?
                    "Permission granted!" : "Permission not granted!");
        }
    }

    private void showToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}
