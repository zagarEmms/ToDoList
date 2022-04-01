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

import com.example.todolist.api.APIclient;
import com.example.todolist.model.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  MainActivity extends AppCompatActivity implements TaskAdapter.MyOnClickListener {

    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    //the result of the intent from createTask activity comes back here
    // result launcher is created to indicate that it will be needed a contract that launches start activity
    // cbk is received as an intent with the the result from the activity
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result != null && result.getResultCode() == RESULT_OK) {
            //start activity ror result to create a NEW task and on the API
            if (result.getData() != null && result.getData().getStringExtra("result") != null) {

                Task task = new Task(0, 0, result.getData().getStringExtra("result"), false);
                taskArrayList.add(task);

                APIclient.getInstance().createPost(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.i("POST","ALL WENT WELL!");
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.i("POST","KO!");
                    }
                } , task);

                updateUI();

            } else if (result.getData() != null && result.getData().getStringArrayListExtra("newName") != null) {
                //start activity ror result to EDIT task
                //an arrayList from the editActivity with the position and new name is received and updated to the taskArrayList
                taskArrayList.get(Integer.parseInt(String.valueOf(result.getData().getStringArrayListExtra("newName").get(0)))).editTaskTitle (result.getData().getStringArrayListExtra("newName").get(1));
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
        Log.i("SAVE","Saving: " + tasksString);
    }

    private void fillTasks () {

        SharedPreferences sharedPreferences = getSharedPreferences("SHARE", MODE_PRIVATE);
        Gson gson = new Gson();
        String tasksString = sharedPreferences.getString("TASKS", null);

        if (tasksString == null) {

            APIclient.getInstance().getTodo(new Callback<ArrayList<Task>>() {
                @Override
                public void onResponse(Call<ArrayList<Task>> call, Response<ArrayList<Task>> response) {
                    Log.i("GET","GET WENT WELL!" + response.body());
                    taskArrayList =  response.body();
                    updateUI();
                }

                @Override
                public void onFailure(Call<ArrayList<Task>> call, Throwable t) {
                    Log.i("GET","KO!");
                }
            });

        } else {

            Type type = new TypeToken<ArrayList<Task>>() {}.getType();
            this.taskArrayList = gson.fromJson(tasksString, type);

        }
    }

    private void changeActivityCreate () {

        Intent intent = new Intent(this, CreateTask.class);
        startForResult.launch(intent);

    }

    @Override
    public void myOnClick(View view, int position) {
        ArrayList<String> changingTaskInfo = new ArrayList<>();
        changingTaskInfo.add(String.valueOf(position));
        changingTaskInfo.add(taskArrayList.get(position).getTitle());

        Intent intent = new Intent(this, EditActivity.class);

        intent.putStringArrayListExtra("newName",changingTaskInfo);

        startForResult.launch(intent);
    }

    private void updateUI() {
        adapter = new TaskAdapter(taskArrayList, this);
        recyclerView.setAdapter(adapter);
        Log.i("adapter","adapter created");
        adapter.setListener(this);

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
            Log.i("LIST","onCreate");
        } else {
            this.taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
            Log.i("LIST","ELSE");

            taskArrayList = savedInstanceState.getParcelableArrayList("ARRAYLIST");
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
        Log.i("LIST","last " + taskArrayList.get(taskArrayList.size()-1).getTitle());
        Log.i("LIST","savedInstanceSAVED");

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

        Log.i("LIST","savedInstanceRESTORE");
    }

    @Override
    protected void onPause() {

        super.onPause();
        saveTasks();

    }

}