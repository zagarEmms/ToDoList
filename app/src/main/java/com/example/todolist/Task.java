package com.example.todolist;
import java.io.Serializable;

public class Task implements Serializable {


    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Task(Task body) {
        this.userId = 1;
        this.id = 2;
        this.title = body.getTitle();
        this.completed = body.isCompleted();
    }

    //int userId, int id, String title, boolean completed

    public void editTaskTitle(String newTitle){
        this.title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTaskDone() {
        this.completed = !this.completed;
    }

}


