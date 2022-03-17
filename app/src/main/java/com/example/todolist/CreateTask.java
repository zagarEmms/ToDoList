package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateTask extends AppCompatActivity {

    private String taskName;
    TaskFragment tf = new TaskFragment();

    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TaskName", taskName);
        startActivity(intent);

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