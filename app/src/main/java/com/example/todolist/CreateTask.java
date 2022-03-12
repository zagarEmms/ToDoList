package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CreateTask extends AppCompatActivity {

    /*private void changeActivity () {

        Intent intent = new Intent(this, CreateTask.class);

        intent.putExtra("activityNumber", infoArrayList);
        startActivity(intent);
        infoArrayList.clear(); //IMPORTANT PER A NETEJAR L'ARRAY I AGAFAR UNA ALTRA ASSIGNATURA

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }
}