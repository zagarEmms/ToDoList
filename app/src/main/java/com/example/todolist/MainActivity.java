package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    private TaskAdapter adapter;
    private RecyclerView recyclerView;

    private void fillTasks () {
        taskArrayList.add(new Task(getString(R.string.task1)));
        taskArrayList.add(new Task(getString(R.string.task2)));
        taskArrayList.add(new Task(getString(R.string.task3)));
        taskArrayList.add(new Task(getString(R.string.task4)));
        taskArrayList.add(new Task(getString(R.string.task5)));
    }

    private void changeActivity () {

        Intent intent = new Intent(this, CreateTask.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable) taskArrayList);
        intent.putExtra("BUNDLE",args);
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        taskArrayList = (ArrayList<Task>) args.getSerializable("ARRAYLIST");

        if (taskArrayList == null) {
            fillTasks();
        }

        updateUI();
        setButton();
    }
}