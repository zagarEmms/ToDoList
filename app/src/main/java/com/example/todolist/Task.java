package com.example.todolist;

public class Task {
    private int taskTitle;
    private boolean taskDone;

    public Task(int taskTitle) {
        this.taskTitle = taskTitle;
        this.taskDone = false;
    }

    public int getTaskTitle() {
        return taskTitle;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskTitle(int taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDone() {
        this.taskDone = true;
    }
}


