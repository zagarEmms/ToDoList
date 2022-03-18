package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    // result launcher is created to indicate that it will be needed a contract that launches start activity
    // cbk is received as an intent with the the result from the activity
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

        //the result of the intent from createTask activity comes back here
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null && result.getData().getStringExtra("result") != null) {
                    taskArrayList.add(new Task (result.getData().getStringExtra("result")));
                    Log.i("llista","added "+taskArrayList.get(taskArrayList.size()-1).getTaskTitle());
                    Log.i("llista","previous "+taskArrayList.get(taskArrayList.size()-2).getTaskTitle());
                }
            }
        }
    });

    private void fillTasks () {
        taskArrayList.add(new Task(getString(R.string.task1)));
        taskArrayList.add(new Task(getString(R.string.task2)));
        taskArrayList.add(new Task(getString(R.string.task3)));
        taskArrayList.add(new Task(getString(R.string.task4)));
        taskArrayList.add(new Task(getString(R.string.task5)));
    }

    private void changeActivityCreate () {

        //Intent intent = new Intent(this, CreateTask.class);
        //startActivityForResult(intent, 1);
        //startActivity(intent);

        Intent intent = new Intent(this, CreateTask.class);
        startForResult.launch(intent);

    }


    private void changeActivityEdit () {

        Intent intent = new Intent(this, EditActivity.class);
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
                    changeActivityCreate();
                }
            }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey("ARRAYLIST")) {
            fillTasks();
            Log.i("llista","onCreate");
        } else {
            taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
            //taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
        setButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("ARRAYLIST", taskArrayList);
        Log.i("llista","savedInstanceSAVED");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        taskArrayList.clear();
        taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");

        //oncreate->onSaveInstanceState->ondestroy->oncreate->onRestoreInstanceState

        Log.i("llista","savedInstanceRESTORE");
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            if(resultCode == RESULT_OK){
                if (data != null && data.getStringExtra("result") != null){
                    taskArrayList.add(new Task (data.getStringExtra("result")));
                }
            }
        }
    }*/

}