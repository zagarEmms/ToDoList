package com.example.todolist;

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

    public APIclient(){
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.service = this.retrofit.create(JsonPlaceholderAPI.class);
    }

    public void getTodo(Integer todo_id, Callback<JsonPlaceholderAPI> callback) {
        this.service.getTodo(todo_id).enqueue(callback);
    }
}
