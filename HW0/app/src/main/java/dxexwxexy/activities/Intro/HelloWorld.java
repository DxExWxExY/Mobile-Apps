package dxexwxexy.activities.Intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dxexwxexy.activities.R;

public class HelloWorld extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        setTitle("My App");
        TextView textView = findViewById(R.id.hello_view);
        textView.setText("Hello Diego!");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
