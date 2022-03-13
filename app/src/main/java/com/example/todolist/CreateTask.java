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

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    TaskFragment tf = new TaskFragment();

    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("ARRAYLIST", taskArrayList);

        startActivity(intent);

    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
        addButton.setOnClickListener(view -> {
            taskArrayList.add(new Task(tf.returnTaskTitle()));
            changeActivity();
        }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskArrayList = getIntent().getParcelableArrayListExtra("ARRAYLIST");

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, tf, null)
                .commit();

        setButton();

    }
}