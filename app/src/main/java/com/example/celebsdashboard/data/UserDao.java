package com.example.celebsdashboard.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.celebsdashboard.model.User;

import java.util.List;

/*
This class is for CRUD operations
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    List<User> get(String username, String password);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

}