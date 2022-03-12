package com.example.todolist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private ArrayList<Task> taskArrayList = new ArrayList<>();

    public TaskAdapter(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TaskHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = taskArrayList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
