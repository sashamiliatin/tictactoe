package com.example.tictactoewithdatabase;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {


        private static UserDatabase instance;
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


