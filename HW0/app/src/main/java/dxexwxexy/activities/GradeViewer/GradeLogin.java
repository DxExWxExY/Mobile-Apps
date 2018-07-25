package dxexwxexy.activities.GradeViewer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Map;
import java.util.Set;

import dxexwxexy.activities.R;

public class GradeLogin extends AppCompatActivity {

    AutoCompleteTextView user, password;
    Button login;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_login);
        user = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.log_in);
        remember = findViewById(R.id.remember_me);
        login.setOnClickListener(e -> {
            Intent grades = new Intent(GradeLogin.this, GradeViewer.class);
            grades.putExtra("user", String.valueOf(user.getText()));
            grades.putExtra("password", String.valueOf(password.getText()));
            if (remember.isChecked()) {
                // TODO: 7/24/2018 Remember credentials
            }
            startActivity(grades);
        });

    }
}
