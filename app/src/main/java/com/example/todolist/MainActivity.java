package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    private FloatingActionButton addButton;
    private ListView list;
    private TaskAdapter adapter;
    private RecyclerView recyclerView;

    private void fillTasks () {

        taskArrayList.add(new Task(R.string.task1));
        taskArrayList.add(new Task(R.string.task2));
        taskArrayList.add(new Task(R.string.task3));
        taskArrayList.add(new Task(R.string.task4));
        taskArrayList.add(new Task(R.string.task5));

    }

    private void changeActivity () {

        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);

    }

    private void updateUI() {
        adapter = new TaskAdapter(taskArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void setButton () {

        addButton.setOnClickListener(new View.OnClickListener()
             {
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
        fillTasks();
        updateUI();
        setButton();
    }
}