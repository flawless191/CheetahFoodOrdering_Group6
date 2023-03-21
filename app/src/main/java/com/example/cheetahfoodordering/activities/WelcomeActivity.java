package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.CategoryDao;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.OrderDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Category;
import com.example.cheetahfoodordering.entity.FavoriteWithProduct;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;
import com.example.cheetahfoodordering.entity.User;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        this.deleteDatabase("foodorderdb");

    }

    public void getStarted(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));

    }
}