package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateTask extends AppCompatActivity {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    private void changeActivity () {

        Intent intent = new Intent(this, CreateTask.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable) taskArrayList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);

    }

    private void setButton () {

        ExtendedFloatingActionButton addButton = findViewById(R.id.add_task_symbol);
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
        setContentView(R.layout.activity_create_task);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        taskArrayList = (ArrayList<Task>) args.getSerializable("ARRAYLIST");

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, TaskFragment.class, null)
                .commit();

        setButton();

    }
}