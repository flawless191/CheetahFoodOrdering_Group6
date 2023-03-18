package com.example.cheetahfoodordering.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cheetahfoodordering.dao.CategoryDao;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.OrderDao;
import com.example.cheetahfoodordering.dao.OrderDetailDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.entity.Category;
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.Order;
import com.example.cheetahfoodordering.entity.OrderDetail;
import com.example.cheetahfoodordering.entity.User;

@Database(entities = {User.class, ItemProduct.class, Category.class, Order.class, OrderDetail.class, Favorite.class}, version = 1, exportSchema = true)

public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName="foodorderdb";
    private static AppDatabase appDatabase;
    public abstract ItemProductDao ItemProductDao();
    public abstract CategoryDao categoryDao();
    public abstract UserDao userDao();
    public abstract FavoriteDao favoriteDao();
    public abstract OrderDao orderDao();
    public abstract OrderDetailDao orderDetailDao();
    public static synchronized AppDatabase getAppDatabase(Context context){
        if(appDatabase==null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "foodorderdb").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
