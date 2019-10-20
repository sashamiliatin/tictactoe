package com.example.tictactoewithdatabase.service;
import com.example.tictactoewithdatabase.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
     @GET("/user/{id}")
    Call<List<User>> getUser(@Query("id") String id);

    @POST("/adduser")
    Call<User> postUser(@Body User user,@Query("di") String id);

    @POST("/update")
    Call<User> udateUser(@Body User user,@Query("di") String id);

    @DELETE("/delete")
    Call<User> deleteUser(@Query("id") String id);
}
