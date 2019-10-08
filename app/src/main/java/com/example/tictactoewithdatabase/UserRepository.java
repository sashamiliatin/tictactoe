package com.example.tictactoewithdatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class UserRepository  {
    private UserDao userDao;
    private List<User> users;


    public UserRepository(Application application){

        UserDatabase db = UserDatabase.getInstance(application);
        userDao = db.UserDao();
        users = userDao.getAll();
    }


    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);

    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);

    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).equals(user);
    }

    public void deleteAll(){
        new DeleteAllUsersAsyncTask(userDao);
    }

    public List<User> getAllUsers(){
        return users;
    }


    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
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

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{
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

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
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

    private static class DeleteAllUsersAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;
        private DeleteAllUsersAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User ... users) {
            userDao.deleteAll();
            return null;
        }
    }


}
