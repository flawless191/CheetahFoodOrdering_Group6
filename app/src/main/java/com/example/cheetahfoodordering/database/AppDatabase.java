package com.example.cheetahfoodordering.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cheetahfoodordering.models.Category;
import com.example.cheetahfoodordering.models.ItemProduct;
import com.example.cheetahfoodordering.models.Order;
import com.example.cheetahfoodordering.models.OrderDetail;
import com.example.cheetahfoodordering.models.User;

@Database(entities = {User.class, ItemProduct.class, Category.class, Order.class, OrderDetail.class}, version = 1, exportSchema = true)

public abstract class AppDatabase extends RoomDatabase {
//    public abstract ItemProductDAO ItemProductDAO();
//    public abstract CategoryDAO categoryDAO();
//    public abstract UserDAO userDAO();
}
