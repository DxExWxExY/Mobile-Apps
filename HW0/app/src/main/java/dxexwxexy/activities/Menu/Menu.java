package dxexwxexy.activities.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import dxexwxexy.activities.Intro.HelloWorld;
import dxexwxexy.activities.R;
import dxexwxexy.activities.Timer.TimerActivity;

public class Menu extends AppCompatActivity {

    /**
     * Button XXXXXX = findViewById(R.id.XXXXXX);
     * XXXXXX.setOnClickListener(e -> startActivity(new Intent(Menu.this, XXXXXX.class)));
     * @param savedInstanceState Yeet
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Class Activities");
        //Intro
        Button a = findViewById(R.id.a);
        a.setOnClickListener(e -> startActivity(new Intent(Menu.this, HelloWorld.class)));
        //Timer
        Button b = findViewById(R.id.b);
        b.setOnClickListener(e -> startActivity(new Intent(Menu.this, TimerActivity.class)));
        //Act 3
    }
}
