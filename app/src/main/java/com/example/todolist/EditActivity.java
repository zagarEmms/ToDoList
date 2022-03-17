package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    private EditText titleField;


    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ARRAYLIST", taskArrayList);
        startActivity(intent);

    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
        addButton.setOnClickListener(view -> {
            titleField = (EditText) findViewById(R.id.add_name);
            taskArrayList.add(new Task(titleField.getText().toString()));
                    changeActivity();
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        taskArrayList = getIntent().getParcelableArrayListExtra("ARRAYLIST");
        setButton();

    }
}