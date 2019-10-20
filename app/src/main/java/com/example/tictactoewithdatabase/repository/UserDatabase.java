package com.example.tictactoewithdatabase.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tictactoewithdatabase.dao.UserDao;
import com.example.tictactoewithdatabase.model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    abstract UserDao getUserDao();

    static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, UserDatabase.class, "user_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return instance;
    }
}


