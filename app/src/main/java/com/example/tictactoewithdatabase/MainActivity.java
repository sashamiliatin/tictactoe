package com.example.tictactoewithdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoewithdatabase.Controller.UserDatabase;
import com.example.tictactoewithdatabase.Repository.UserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {

    TelephonyManager telephonyManager;
    TextView userTextView;
    public static UserDatabase userDatabase;
    public static UserRepository userRepository;
    public DatabaseReference userDatabaseFirebase;
    public String ID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = UserDatabase.getInstance(getApplicationContext());
        userRepository = new UserRepository(getApplication());
        userTextView = findViewById(R.id.userNameText);
        final Button btn = findViewById(R.id.okButton);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //get unique Android ID for create/find user in firebase database
        ID = Settings.Secure.getString(getContentResolver(),
                     Settings.Secure.ANDROID_ID);
        userDatabaseFirebase = FirebaseDatabase.getInstance().getReference("users");
        System.out.println(userDatabaseFirebase.child("id").orderByKey());
        Toast.makeText(this, "aaa"+""+ userDatabaseFirebase.child(ID).getDatabase(), Toast.LENGTH_SHORT).show();
        //User user = new User("sasha",0,0);

        //user.setId(ID);
        //userDatabaseFirebase.setValue(user);


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
