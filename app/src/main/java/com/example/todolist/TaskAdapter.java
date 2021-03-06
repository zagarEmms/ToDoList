package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private MainActivity activity;
    private MyOnClickListener listener;

    public interface MyOnClickListener {
        void myOnClick(View view, int position);
    }

    public TaskAdapter(ArrayList<Task> taskArrayList, MainActivity activity) {
        this.taskArrayList = taskArrayList;
        this.activity = activity;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TaskHolder(layoutInflater, parent, listener);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = taskArrayList.get(position);
        final int i = position;

        holder.bind(task, activity, position);

        holder.checkButton = holder.checkButton.findViewById(R.id.checkTask);
        holder.checkButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (task.isCompleted()) {
                       holder.checkButton.setImageResource(0);
                   } else {
                       holder.checkButton.setImageResource(R.drawable.check);
                   }
                   task.setTaskDone();
               }
           }
        );
    }

    public void setListener(MyOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

}
