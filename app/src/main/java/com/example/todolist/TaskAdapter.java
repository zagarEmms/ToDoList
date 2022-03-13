package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private Context context;

    public TaskAdapter(ArrayList<Task> taskArrayList, Context context) {
        this.taskArrayList = taskArrayList;
        this.context = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TaskHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = taskArrayList.get(position);
        final int i = position;

        holder.bind(task);

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

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

}
