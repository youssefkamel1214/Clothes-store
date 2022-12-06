package com.youssef.cloath_store.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.youssef.cloath_store.models.User;

import java.util.List;

@Dao
public interface UserDao{
    @Insert
    void insert(User user);

    @Insert
    void insertll(User... user);

    @Delete
    void delete(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user WHERE uid LIKE :uid LIMIT 1")
    User findById(int uid);

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    User findById(String name);

    @Query("SELECT uid FROM user WHERE email like :email AND password like :password")
    int Login(String  email, String password);

    @Query("SELECT * FROM user")
    List<User> getAll();

}