package com.example.cheetahfoodordering.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int user_id;
    @ColumnInfo(name = "user_email")
    public String user_email;
    @ColumnInfo(name = "user_phone")
    public String user_phone;
    @ColumnInfo(name = "full_name")
    public String full_name;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "password")
    public String password;

    public User(int user_id, String user_email, String user_phone, String full_name, String address, String password) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.full_name = full_name;
        this.address = address;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
