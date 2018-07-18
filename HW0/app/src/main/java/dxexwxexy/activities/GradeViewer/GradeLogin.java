package dxexwxexy.activities.GradeViewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import dxexwxexy.activities.R;

public class GradeLogin extends AppCompatActivity {

    EditText user, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_login);
        user = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.log_in);
        login.setOnClickListener(e -> {
            Intent grades = new Intent(GradeLogin.this, GradeViewer.class);
            grades.putExtra("user", String.valueOf(user.getText()));
            grades.putExtra("password", String.valueOf(password.getText()));
            startActivity(grades);
        });

    }
}
