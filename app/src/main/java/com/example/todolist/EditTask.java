package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

public class EditTask extends AppCompatActivity {

    private String taskName;
    private EditText titleField;
    private Button button;


    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ARRAYLIST", taskArrayList);
        startActivity(intent);

        finish();

    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
        addButton.setOnClickListener(view -> {
            titleField = (EditText) findViewById(R.id.add_name);
                    //taskArrayList.add(new Task(titleField.getText().toString()));
                    changeActivity();
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //taskArrayList = getIntent().getParcelableArrayListExtra("ARRAYLIST");
        setButton();

    }
}