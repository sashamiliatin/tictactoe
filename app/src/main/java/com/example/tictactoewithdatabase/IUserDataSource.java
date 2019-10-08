package com.example.tictactoewithdatabase;

import java.util.List;

public interface IUserDataSource {
    List<User> getAll();
    User findByName(String name);
    void insertAll(User... users);
    void insert(User user);
    void delete(User user);
    void  update(User user);
}
