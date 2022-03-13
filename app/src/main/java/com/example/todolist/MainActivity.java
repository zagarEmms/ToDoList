package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    private void fillTasks () {
        taskArrayList.add(new Task(getString(R.string.task1)));
        taskArrayList.add(new Task(getString(R.string.task2)));
        taskArrayList.add(new Task(getString(R.string.task3)));
        taskArrayList.add(new Task(getString(R.string.task4)));
        taskArrayList.add(new Task(getString(R.string.task5)));
    }

    private void changeActivity () {

        Intent intent = new Intent(this, CreateTask.class);

        intent.putExtra("ARRAYLIST", taskArrayList);

        startActivity(intent);

    }

    private void updateUI() {
        adapter = new TaskAdapter(taskArrayList, this);
        recyclerView.setAdapter(adapter);
    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task);
        addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeActivity();
                }
            }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            taskArrayList = getIntent().getParcelableArrayListExtra("ARRAYLIST");
        } else {
            fillTasks();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
        setButton();
    }
}