package com.example.celebsdashboard.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.celebsdashboard.model.User;

/*
This class defines the entities and version of the DB
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
