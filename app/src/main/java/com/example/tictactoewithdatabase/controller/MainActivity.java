package com.example.tictactoewithdatabase.controller;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoewithdatabase.R;
import com.example.tictactoewithdatabase.listener.OnOkClickListener;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;
import com.example.tictactoewithdatabase.service.JsonPlaceHolderApi;
import com.example.tictactoewithdatabase.service.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private String androidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUniqueId();
        androidId = getAndroidId();
        init();
    }

    private void init() {
        final UserRepository userRepository = new UserRepository(this);
        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<User>> call = jsonPlaceHolderApi.getUser(androidId);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    List<User> userFromDb = response.body();
                    if (userFromDb == null || userFromDb.isEmpty()) {
                        List<User> users = userRepository.getUsers();
                        if (users == null || users.isEmpty()) {
                            initButton();
                        } else {
                            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                            intent.putExtra("id", androidId);
                            startActivity(intent);
                        }
                    } else {
                        User user = new User(userFromDb.get(0).getName(), userFromDb.get(0).getWins(), userFromDb.get(0).getWins());
                        userRepository.insert(user);
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        intent.putExtra("id", androidId);
                        startActivity(intent);
                    }
                } else {
                    System.out.println(response.errorBody());
                    List<User> users = userRepository.getUsers();
                    if (users == null || users.isEmpty()) {
                        initButton();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        intent.putExtra("id", androidId);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private void setUniqueId() {
        this.androidId = Settings.Secure.getString(getContentResolver(),
                     Settings.Secure.ANDROID_ID);
    }

    private void initButton() {
        TextView userTextView = findViewById(R.id.userNameText);
        final Button btn = findViewById(R.id.okButton);
        btn.setOnClickListener(new OnOkClickListener(this, userTextView, androidId));
    }

    public String getAndroidId() {
        return androidId;
    }


}
