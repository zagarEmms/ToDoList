package com.example.todolist;

import com.example.todolist.model.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface JsonPlaceholderAPI {
    @GET("todos")
    Call<ArrayList<Task>> getTodo();

    @POST("todos")
    Call<Task> createPost(@Body Task task);
}