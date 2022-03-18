package com.example.todolist;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class  MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    //the result of the intent from createTask activity comes back here
    // result launcher is created to indicate that it will be needed a contract that launches start activity
    // cbk is received as an intent with the the result from the activity
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result != null && result.getResultCode() == RESULT_OK) {
            if (result.getData() != null && result.getData().getStringExtra("result") != null) {
                taskArrayList.add(new Task (result.getData().getStringExtra("result")));
                updateUI();
            }
        }
    });

    private void saveTasks () {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String tasksString = gson.toJson(taskArrayList);
        editor.putString("TASKS", tasksString);
        editor.apply();
        Log.i("save","Saving: " + tasksString);
    }

    private void fillTasks () {

        SharedPreferences sharedPreferences = getSharedPreferences("SHARE", MODE_PRIVATE);
        Gson gson = new Gson();
        String tasksString = sharedPreferences.getString("TASKS", null);
        Log.i("save","Getting: " + tasksString);

        if (tasksString == null) {

            taskArrayList.add(new Task(getString(R.string.task1)));
            taskArrayList.add(new Task(getString(R.string.task2)));
            taskArrayList.add(new Task(getString(R.string.task3)));
            taskArrayList.add(new Task(getString(R.string.task4)));
            taskArrayList.add(new Task(getString(R.string.task5)));

        } else {

            Type type = new TypeToken<ArrayList<Task>>() {}.getType();
            this.taskArrayList = gson.fromJson(tasksString, type);

        }
    }

    private void changeActivityCreate () {

        Intent intent = new Intent(this, CreateTask.class);
        startForResult.launch(intent);

    }


    public void changeEditActivity(int position) {

        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);

    }

    public interface MyOnClickListener {
        void myOnClick(View view, int position);
    }

    private void updateUI() {
        adapter = new TaskAdapter(taskArrayList, this, );
        recyclerView.setAdapter(adapter);
        adapter.setListener(MyOnClickListener);

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
            this.taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
            Log.i("llista","ELSE");

            //taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
        setButton();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("ARRAYLIST", taskArrayList);
        Log.i("llista","last " + taskArrayList.get(taskArrayList.size()-1).getTaskTitle());
        Log.i("llista","savedInstanceSAVED");

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        taskArrayList.clear();
        taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");

        //oncreate->onSaveInstanceState->ondestroy->oncreate->onRestoreInstanceState

        Log.i("llista","savedInstanceRESTORE");
    }

    @Override
    protected void onPause() {

        super.onPause();
        saveTasks();

    }


}