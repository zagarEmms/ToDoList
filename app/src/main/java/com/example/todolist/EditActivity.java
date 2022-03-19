package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private String taskName;
    private EditText titleField;
    private ArrayList <String> infoArray = new ArrayList<>();


    private void changeActivity () {

        Intent returnIntent = new Intent();
        returnIntent.putStringArrayListExtra("newName",infoArray);

        setResult(RESULT_OK,returnIntent);

        finish();
    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
        addButton.setOnClickListener(view -> {
             titleField = (EditText) findViewById(R.id.add_name);
             taskName = titleField.getText().toString();
             infoArray.add(1, taskName);

            //taskArrayList.add(new Task(titleField.getText().toString()));
                changeActivity();
            }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        infoArray = getIntent().getStringArrayListExtra("newName");
        //taskArrayList = getIntent().getParcelableArrayListExtra("ARRAYLIST");
        setButton();

    }
}
