package com.example.tictactoewithdatabase.Service;

import androidx.annotation.NonNull;

import com.example.tictactoewithdatabase.Repository.UserRepository;
import com.example.tictactoewithdatabase.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDbService {
    private User user;
    private UserRepository userRepository;
    String userId;
    String name;
    int games;
    int wins;
    private DatabaseReference databaseReference;

    public UserDbService(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(postListener);
    }

    public UserDbService(String userId, String name , int games,int wins){
        this.userId =userId;
        this.name =name;
        this.wins = wins;
        this.games =games;
    }

    ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            UserDbService firebaseUser =  dataSnapshot.getValue(UserDbService.class);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private void writeNewUser(String userId ,String name, int games, int wins){
        UserDbService firebaseUser = new UserDbService(userId,name,games,wins);
        databaseReference.child("users").child(userId).setValue(firebaseUser);
    }
}
