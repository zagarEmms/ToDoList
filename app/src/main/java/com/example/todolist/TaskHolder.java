package com.example.todolist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Task taskItem;
    private TextView task_tile;
    public ImageButton checkButton;
    private ImageButton editButton;

    private MainActivity.MyOnClickListener listener;

    public TaskHolder(LayoutInflater inflater, ViewGroup parent, MainActivity.MyOnClickListener listener) {

        super(inflater.inflate(R.layout.list_item_task, parent, false));

        itemView.setOnClickListener(this);

        task_tile = (TextView) itemView.findViewById(R.id.task_title);
        checkButton = (ImageButton) itemView.findViewById(R.id.checkTask);
        editButton = (ImageButton) itemView.findViewById(R.id.editTask);
        this.listener = listener;
    }

    public void bind (Task task, MainActivity activity, int position) {
        taskItem = task;
        task_tile.setText(taskItem.getTaskTitle());

        if (taskItem.isTaskDone()) {
            checkButton.setImageResource(R.drawable.check);
        }
    }

    @Override
    public void onClick(View view) {
        //taskItem.setTaskDone();
        if (listener != null) {
            listener.myOnClick(view, getAdapterPosition());
        }
    }

}
