package com.example.tictactoewithdatabase.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoewithdatabase.controller.GameActivity;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;
import com.example.tictactoewithdatabase.service.JsonPlaceHolderApi;
import com.example.tictactoewithdatabase.service.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnOkClickListener implements View.OnClickListener {

    private Context context;
    private TextView userTextView;
    private UserRepository userRepository;
    private String androidId;

    public OnOkClickListener(Context context, TextView userTextView, String id) {
        this.context = context;
        this.userTextView = userTextView;
        this.userRepository = new UserRepository(context);
        this.androidId = id;
    }

    @Override
    public void onClick(View v) {
        if (userTextView.getText().toString().equals("")){
            Toast
                .makeText(context, "Please insert your name !!", Toast.LENGTH_SHORT)
                .show();
        }
        else {
            onClick();
        }
    }

    private void onClick() {
        String name = userTextView.getText().toString();
        User user = new User(name,0,0);
        userRepository.insert(user);

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<User>> call = jsonPlaceHolderApi.postUser(user, androidId);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        context.startActivity(new Intent(context, GameActivity.class));
    }
}