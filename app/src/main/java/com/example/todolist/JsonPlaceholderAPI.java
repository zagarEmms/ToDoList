package com.example.todolist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface JsonPlaceholderAPI {
    @GET("todos")
    Call<ArrayList<Task>> getTodo();
}
