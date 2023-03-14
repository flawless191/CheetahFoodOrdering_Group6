package com.example.cheetahfoodordering.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cheetahfoodordering.entity.Category;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.Order;
import com.example.cheetahfoodordering.entity.OrderDetail;
import com.example.cheetahfoodordering.entity.User;

@Database(entities = {User.class, ItemProduct.class, Category.class, Order.class, OrderDetail.class}, version = 1, exportSchema = true)

public abstract class AppDatabase extends RoomDatabase {
//    public abstract ItemProductDAO ItemProductDAO();
//    public abstract CategoryDAO categoryDAO();
//    public abstract UserDAO userDAO();
}
