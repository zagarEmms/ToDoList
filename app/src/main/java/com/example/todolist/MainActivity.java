package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}