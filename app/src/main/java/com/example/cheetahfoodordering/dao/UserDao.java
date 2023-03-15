package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cheetahfoodordering.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);
    @Delete
    void delete(User user);
    @Query("SELECT * FROM users")
    List<User> getAllUser();
    @Query("SELECT * FROM users WHERE user_phone = :phone")
    User getUserByPhone(String phone);

    @Query("SELECT * FROM users WHERE user_phone = :phone AND password = :password")
    User getUserByPhoneAndPassword(String phone, String password);
}
