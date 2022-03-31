package com.example.todolist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface JsonPlaceholderAPI {
    @GET("todos/{todoID}")
    Call<Task> getTodo(@Path("todoID") Integer todoID);
}
