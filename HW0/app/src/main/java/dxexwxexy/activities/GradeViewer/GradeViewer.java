package dxexwxexy.activities.GradeViewer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dxexwxexy.activities.R;

public class GradeViewer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_viewer);
        Intent data = getIntent();
        String user = data.getStringExtra("user");
        String password = data.getStringExtra("password");
        WebClient webClient = new WebClient(new WebClient.GradeListener() {
            @Override
            public void onGrade(String date, Grade grade) {
                runOnUiThread(() -> {
                    TextView grades = findViewById(R.id.grades_data);
                    grades.setText("Grade is " + grade.grade);
                    ListView listView = findViewById(R.id.list_view);
                    ArrayAdapter<Grade.Score> arrayAdapter;
                    arrayAdapter = new ArrayAdapter<Grade.Score>(GradeViewer.this, R.layout.grade_container, R.id.grade_name, grade.scores());
                    listView.setAdapter(arrayAdapter);
                });
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onError(String msg) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        new Thread(() -> webClient.query(user,password)).start();

    }
}
