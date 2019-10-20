package com.example.tictactoewithdatabase.Controller;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tictactoewithdatabase.User;
import com.example.tictactoewithdatabase.UserDao;


@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {


        public static UserDatabase instance;
        public abstract UserDao UserDao();

        public static synchronized UserDatabase getInstance(Context context) {
                if (instance == null) {
                        synchronized (UserDatabase.class) {
                                if (instance == null) {
                                        instance = Room.databaseBuilder(context,
                                                UserDatabase.class, "user_database").allowMainThreadQueries()
                                                .build();
                                }
                        }
                }

                return instance;
        }

}


