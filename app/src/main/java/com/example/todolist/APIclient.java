package com.example.todolist;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {
    private static APIclient shared;

    private Retrofit retrofit;
    private JsonPlaceholderAPI service;

    public static APIclient getInstance(){
        if (shared == null) {
            shared = new APIclient();
        }
        return shared;
    }

    public APIclient() {
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.service = this.retrofit.create(JsonPlaceholderAPI.class);
    }

    public void getTodo(Callback<ArrayList<Task>> callback) {
        this.service.getTodo().enqueue(callback);
    }
}
