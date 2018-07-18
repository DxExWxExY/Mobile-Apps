package dxexwxexy.activities.GradeViewer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dxexwxexy.activities.R;

public class GradeViewer extends AppCompatActivity {

    TextView grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_viewer);
        grades = findViewById(R.id.grade_view);
        Intent data = getIntent();
        String user = data.getStringExtra("user");
        String password = data.getStringExtra("password");
        WebClient webClient = new WebClient(new WebClient.GradeListener() {
            @Override
            public void onGrade(String date, Grade grade) {
                runOnUiThread(() -> {
                    grades.append(date+" "+grade.grade+"\n");
                    for (Grade.Score score : grade.scores()) {
                        grades.append(score.name + " " + score.earned + " " + score.max + "\n");
                    }
                });
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onError(String msg) {
                grades.setText("User Not Found");
                grades.setTextColor(getColor(R.color.red));
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
