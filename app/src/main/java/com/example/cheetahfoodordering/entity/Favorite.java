package com.example.cheetahfoodordering.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite",
        foreignKeys = { @ForeignKey(entity = User.class,parentColumns = "user_id",childColumns = "user_id"),@ForeignKey(entity = ItemProduct.class,parentColumns = "product_id",childColumns = "product_id")})
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    private  int favorite_id;
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "product_id")

    private int product_id;

    public Favorite(int favorite_id, int user_id, int product_id) {
        this.favorite_id = favorite_id;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Favorite() {
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
