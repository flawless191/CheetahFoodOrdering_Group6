package com.example.cheetahfoodordering.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.entity.Category;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.Order;
import com.example.cheetahfoodordering.entity.OrderDetail;
import com.example.cheetahfoodordering.entity.User;

@Database(entities = {User.class, ItemProduct.class, Category.class, Order.class, OrderDetail.class}, version = 1, exportSchema = true)

public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName="foodorderdb";
    private static AppDatabase appDatabase;
//    public abstract ItemProductDAO ItemProductDAO();
//    public abstract CategoryDAO categoryDAO();
    public abstract UserDao userDao();

    public static synchronized AppDatabase getAppDatabase(Context context){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "foodorderdb").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
