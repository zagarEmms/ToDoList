package com.example.todolist;

public class Task {

    private String taskTitle;
    private boolean taskDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.taskDone = false;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone() {
        this.taskDone = true;
    }
}


