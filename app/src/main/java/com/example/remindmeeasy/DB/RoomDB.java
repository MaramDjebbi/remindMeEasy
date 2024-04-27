package com.example.remindmeeasy.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.remindmeeasy.DAO.UserDao;
import com.example.remindmeeasy.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    // Define a method that returns an instance of the UserDao interface
    public abstract UserDao userDao();

    // Implement the Singleton pattern
    private static RoomDB instance;

    public static synchronized RoomDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

