package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private MainActivity activity;
    private MainActivity.MyOnClickListener listener;

    public TaskAdapter(ArrayList<Task> taskArrayList, MainActivity activity, MainActivity.MyOnClickListener myOnClickListener) {
        this.taskArrayList = taskArrayList;
        this.activity = activity;
        this.listener = myOnClickListener;
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
                   holder.checkButton.setImageResource(R.drawable.check);
                   taskArrayList.get(i).setTaskDone();

               }
           }
        );
    }

    public void setListener(MainActivity.MyOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

}
