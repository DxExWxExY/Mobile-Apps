package dxexwxexy.activities.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import dxexwxexy.activities.GradeViewer.GradeLogin;
import dxexwxexy.activities.Intro.HelloWorld;
import dxexwxexy.activities.R;
import dxexwxexy.activities.Timer.TimerActivity;
import dxexwxexy.activities.Tuition.TuitionCalculatorActivity;

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
        Button c = findViewById(R.id.c);
        c.setOnClickListener(e -> startActivity(new Intent(Menu.this, GradeLogin.class)));
        //
        Button d = findViewById(R.id.d);
        d.setOnClickListener(e -> startActivity(new Intent(Menu.this, TuitionCalculatorActivity.class)));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "YEET", Toast.LENGTH_LONG).show();
    }
}
