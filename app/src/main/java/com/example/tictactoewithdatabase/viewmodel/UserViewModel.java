package com.example.tictactoewithdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        users = userRepository.getLiveDataUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public void insert(User user) { userRepository.insert(user); }
}
