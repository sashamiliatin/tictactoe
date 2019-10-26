package com.example.tictactoewithdatabase.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.tictactoewithdatabase.dao.UserDao;
import com.example.tictactoewithdatabase.model.User;

import java.util.List;

public class UserRepository  {
    private UserDao userDao;
    private LiveData<List<User>> users;

    public UserRepository(Context context){
        UserDatabase db = UserDatabase.getInstance(context);
        this.userDao = db.getUserDao();
        this.users = userDao.getAll();
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAll(){
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public LiveData<List<User>> getLiveDataUsers(){
        return users;
    }

    public List<User> getUsers(){
        return userDao.getAllUsers();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao userDao;
        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao userDao;
        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao userDao;
        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteAll();
            return null;
        }
    }
}
