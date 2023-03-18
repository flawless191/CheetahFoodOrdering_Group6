package com.example.cheetahfoodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cheetahfoodordering.activities.UpdateProductActivity;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.User;
import com.example.cheetahfoodordering.ui.CartFragment;
import com.example.cheetahfoodordering.ui.FavoriteFragment;
import com.example.cheetahfoodordering.ui.HistoryOrderFragment;
import com.example.cheetahfoodordering.ui.HomeFragment;
import com.example.cheetahfoodordering.ui.ItemDetailFragment;
import com.example.cheetahfoodordering.ui.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewItem;
    private ImageView foodType;
    FragmentManager fragmentManage;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        db = Room.databaseBuilder(this,
//                AppDatabase.class, "foodorderdb").allowMainThreadQueries().build();
        AppDatabase appDatabase= AppDatabase.getAppDatabase(this);
        ItemProductDao itemProductDao = appDatabase.ItemProductDao();
        List<ItemProduct> listBegin= itemProductDao.getAllProduct();
        addFragment( new HomeFragment(listBegin));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_item:

                    List<ItemProduct> listWithCategory= itemProductDao.getAllProduct();
                    replaceFragment( new HomeFragment(listWithCategory));

                    break;
                case R.id.favorite:
                    replaceFragment( new FavoriteFragment());


                    break;
                case R.id.cart:
                    replaceFragment( new CartFragment());
                    break;
                case R.id.history:
                    replaceFragment( new HistoryOrderFragment());

                    break;
                case R.id.setting:
                    replaceFragment( new SettingFragment());
                    break;
            }
            return true;

        });

    }

    public void replaceFragment(Fragment fragment){
        fragmentManage = getSupportFragmentManager();
        fragmentTransaction = fragmentManage.beginTransaction();
        fragmentTransaction.replace( R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    public void addFragment(Fragment fragment){
        fragmentManage = getSupportFragmentManager();
        fragmentTransaction = fragmentManage.beginTransaction();
        fragmentTransaction.add( R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    public void  onClickItemDetail(ItemProduct itemProduct){
        fragmentManage = getSupportFragmentManager();
        fragmentTransaction = fragmentManage.beginTransaction();
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_item",itemProduct);
        itemDetailFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, itemDetailFragment);

        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack("replacement");
        fragmentTransaction.commit();

    }

    public void  itemManageOnClick(ItemProduct itemProduct){
        fragmentManage = getSupportFragmentManager();
        fragmentTransaction = fragmentManage.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_item_manage",itemProduct);
        Intent intent = new Intent(MainActivity.this, UpdateProductActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }




    public void  itemAddToFavoriteOnClick(ItemProduct itemProduct){
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        FavoriteDao favoriteDao = appDatabase.favoriteDao();
        Favorite favorite = new Favorite();
        SharedPreferences sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);

        String userPhone = sharedPreferences.getString("userCurrentPhone",null);
        String pass = sharedPreferences.getString("userCurrentPassword",null);
        UserDao userDao = appDatabase.userDao();
        User user = new User();
        user= userDao.getUserByPhoneAndPassword(userPhone,pass);
        favorite.setUser_id(user.getUser_id());
        favorite.setProduct_id(itemProduct.getProduct_id());
        Favorite favoriteCheck = favoriteDao.getFavoriteWithUserIdAndProductId(user.getUser_id(),itemProduct.getProduct_id());
        if(favoriteCheck!=null){
            Toast.makeText(this, "This product already exist in favorite list!", Toast.LENGTH_SHORT).show();
        }else {
            favoriteDao.insertAllFavorite(favorite);
            Toast.makeText(this, "Add to favorite list successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void  deleteItemFromFavoriteOnClick(Favorite favorite){
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        FavoriteDao favoriteDao = appDatabase.favoriteDao();
        favoriteDao.deleteFavorite(favorite);
        Toast.makeText(this, "Delete successfully!", Toast.LENGTH_SHORT).show();
        replaceFragment(new FavoriteFragment());
    }
}