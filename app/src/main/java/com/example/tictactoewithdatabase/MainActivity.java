package com.example.tictactoewithdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    TextView userTextView;
    public static UserDatabase userDatabase;
    public static UserRepository userRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = UserDatabase.getInstance(getApplicationContext());
        userRepository = new UserRepository(getApplication());
        userTextView = findViewById(R.id.userNameText);
        final Button btn = findViewById(R.id.okButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userTextView.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please insert your name !!",Toast.LENGTH_SHORT).show();
                }
                else {
                    String name = userTextView.getText().toString();
                    User user = new User(""+name,0,0);
                    userDatabase.UserDao().deleteAll();
                    userRepository.insert(user);
                    startActivity(new Intent(getApplicationContext(),GameActivity.class));

                }

            }
        });
    }
}
