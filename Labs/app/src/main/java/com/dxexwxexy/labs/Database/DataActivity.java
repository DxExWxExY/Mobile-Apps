package com.dxexwxexy.labs.Database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dxexwxexy.labs.R;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToDoDatabaseHelper databaseHelper = new ToDoDatabaseHelper(this);
        setContentView(R.layout.activity_data);
        Button add = findViewById(R.id.add_task);
        EditText task = findViewById(R.id.task_field);
        Button clear = findViewById(R.id.clear_task);
        ListView listView = findViewById(R.id.task_list_view);
//        ToDoAdapter toDoAdapter = new ToDoAdapter(this, R.layout.todo_item, databaseHelper.allItems());
        add.setOnClickListener(e -> {
            databaseHelper.addItem(new ToDoItem(task.getText().toString(), false));
//            toDoAdapter.notifyDataSetChanged();
            listView.setAdapter(new ToDoAdapter(this, R.layout.todo_item, databaseHelper.allItems()));
        });
        clear.setOnClickListener(e -> {
            databaseHelper.deleteAll();
            listView.setAdapter(new ToDoAdapter(this, R.layout.todo_item, databaseHelper.allItems()));
        });
    }
}
