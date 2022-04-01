package com.example.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Task implements Serializable, Parcelable {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Task(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    //int userId, int id, String title, boolean completed

    protected Task(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
        completed = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeByte((byte) (completed ? 1 : 0));
    }
}
