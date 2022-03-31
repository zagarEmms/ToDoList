package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Task taskItem;
    private TextView task_tile;
    public ImageButton checkButton;
    private ImageView editIcon;

    private TaskAdapter.MyOnClickListener listener;

    public TaskHolder(LayoutInflater inflater, ViewGroup parent,TaskAdapter.MyOnClickListener listener) {

        super(inflater.inflate(R.layout.list_item_task, parent, false));

        this.listener = listener;
        itemView.setOnClickListener(this);

        task_tile = itemView.findViewById(R.id.task_title);
        checkButton = itemView.findViewById(R.id.checkTask);
        editIcon = itemView.findViewById(R.id.editIcon);
    }

    public void bind (Task task, MainActivity activity, int position) {
        taskItem = task;
        task_tile.setText(taskItem.getTitle());

        if (taskItem.isCompleted()) {
            checkButton.setImageResource(R.drawable.check);
        }
    }

    /*@Override
    public void myOnClick(View view, int position) {
        if (listener != null) {
            listener.myOnClick(view, getAdapterPosition());
        }
    }*/

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.myOnClick(view, getAdapterPosition());
        }
    }

}


