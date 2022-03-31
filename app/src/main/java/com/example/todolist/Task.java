package com.example.todolist;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {

    private String taskTitle;
    private boolean taskDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        this.taskDone = false;
    }

    protected Task(Parcel in) {
        taskTitle = in.readString();
        taskDone = in.readByte() != 0;
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
        this.taskTitle = newTitle;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone() {
        this.taskDone = !this.taskDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskTitle);
        parcel.writeByte((byte) (taskDone ? 1 : 0));
    }

}


