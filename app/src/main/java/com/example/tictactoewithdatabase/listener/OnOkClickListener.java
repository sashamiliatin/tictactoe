package com.example.tictactoewithdatabase.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoewithdatabase.controller.GameActivity;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;

public class OnOkClickListener implements View.OnClickListener {

    private Context context;
    private TextView userTextView;
    private UserRepository userRepository;

    public OnOkClickListener(Context context, TextView userTextView) {
        this.context = context;
        this.userTextView = userTextView;
        this.userRepository = new UserRepository(context);
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
        context.startActivity(new Intent(context, GameActivity.class));
    }
}