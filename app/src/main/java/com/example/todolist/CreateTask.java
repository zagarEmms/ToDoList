package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class CreateTask extends AppCompatActivity {

    private String taskTitle;

    //protected Fragment createFragment();

    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("taskTitle", taskTitle);
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

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);*/


        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, TaskFragment.class, null)
                .commit();


        setButton();

    }
}