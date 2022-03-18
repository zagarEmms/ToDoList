package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.LogRecord;

public class CreateTask extends AppCompatActivity {

    private String taskName;
    TaskFragment tf = new TaskFragment();

    private void changeActivity () {

        /*Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TaskName", taskName);
        startActivity(intent);*/

        Log.i("llista","new Task");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",taskName);

        setResult(RESULT_OK,returnIntent);

        finish();

    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
        addButton.setOnClickListener(view -> {
            taskName = tf.returnTaskTitle();
            changeActivity();
        }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, tf, null)
                .commit();

        setButton();

    }
}