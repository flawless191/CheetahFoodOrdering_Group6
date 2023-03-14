package com.example.cheetahfoodordering.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders", foreignKeys = @ForeignKey(entity = User.class,parentColumns = "user_id", childColumns = "user_id"))
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int order_id;
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "isCheckOut")

    private int isCheckOut;

    public Order(int order_id, int user_id, int isCheckOut) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.isCheckOut = isCheckOut;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIsCheckOut() {
        return isCheckOut;
    }

    public void setIsCheckOut(int isCheckOut) {
        this.isCheckOut = isCheckOut;
    }
}
