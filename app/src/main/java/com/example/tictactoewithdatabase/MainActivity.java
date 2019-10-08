package com.example.tictactoewithdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity  {

    TextView userTextView;
    List<User> users ;
    DatabaseReference databaseReference;
    public static UserDatabase userDatabase;
    public static UserRepository userRepository;
    private CompositeDisposable compositeDisposable;
    public Application application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        setContentView(R.layout.activity_main);
        compositeDisposable =new CompositeDisposable();
        userDatabase = UserDatabase.getInstance(getApplicationContext());
        userRepository = new UserRepository(getApplication());
        /*userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "user_database").allowMainThreadQueries().build();*/
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
                    //userDatabase.UserDao().insert(user);
                    Intent ss = new Intent();
                    startActivity(new Intent(getApplicationContext(),GameActivity.class));

                }

            }
        });
    }
}
