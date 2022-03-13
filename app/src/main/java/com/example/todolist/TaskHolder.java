package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Task taskItem;
    private TextView task_tile;
    private ImageButton checkButton;

    public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.list_item_task, parent, false));
        itemView.setOnClickListener(this);

        task_tile = (TextView) itemView.findViewById(R.id.task_title);
        checkButton = (ImageButton) itemView.findViewById(R.id.checkTask);

    }

    public void bind (Task task) {
        taskItem = task;
        task_tile.setText(taskItem.getTaskTitle());
        if (taskItem.isTaskDone()) {
            checkButton.setImageResource(R.drawable.check);
        }


        checkButton = checkButton.findViewById(R.id.checkTask);
        checkButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     checkButton.setImageResource(R.drawable.check);
                     taskItem.setTaskDone();
                 }
             }
        );
    }


    @Override
    public void onClick(View view) {
        taskItem.setTaskDone();
    }


}
