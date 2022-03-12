package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class CreateTask extends AppCompatActivity {

    private ExtendedFloatingActionButton addButton;
    private String taskTitle;

    private void changeActivity () {

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("taskTitle", taskTitle);
        startActivity(intent);

    }

    private void setButton () {

        addButton = (ExtendedFloatingActionButton) addButton.findViewById(R.id.add_task);
        addButton.setOnClickListener(new View.OnClickListener()
             {
                 @Override
                 public void onClick(View view) {
                     changeActivity();
                 }
             }
        );
    }

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
        }

        setButton();

    }
}