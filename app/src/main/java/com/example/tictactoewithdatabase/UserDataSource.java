package com.example.tictactoewithdatabase;

import java.util.List;

public class UserDataSource implements IUserDataSource {

    private UserDao userDao;
    private static UserDataSource mInstance;

    public UserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserDataSource getInstance(UserDao userDao){
        if (mInstance == null) {
            mInstance = new UserDataSource(userDao);
        }
        return mInstance;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void insertAll(User... users) {
    userDao.insertAll(users);
    }

    @Override
    public void insert(User user) {
    userDao.insert(user);
    }

    @Override
    public void delete(User user) {
    userDao.delete(user);
    }

    @Override
    public void update(User user) {
    userDao.update(user);
    }
}
