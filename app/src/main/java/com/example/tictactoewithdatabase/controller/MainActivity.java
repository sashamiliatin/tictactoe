package com.example.tictactoewithdatabase.controller;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoewithdatabase.R;
import com.example.tictactoewithdatabase.listener.OnOkClickListener;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private String androidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        UserRepository userRepository = new UserRepository(this);
        List<User> users = userRepository.getUsers();

        if (users == null || users.isEmpty()) {
            initButton();
        } else {
            startActivity(new Intent(this, GameActivity.class));
        }
    }

    private void setUniqueId() {
        this.androidId = Settings.Secure.getString(getContentResolver(),
                     Settings.Secure.ANDROID_ID);
    }

    private void initButton() {
        TextView userTextView = findViewById(R.id.userNameText);
        final Button btn = findViewById(R.id.okButton);
        btn.setOnClickListener(new OnOkClickListener(this, userTextView));
    }

    public String getAndroidId() {
        return androidId;
    }
}
