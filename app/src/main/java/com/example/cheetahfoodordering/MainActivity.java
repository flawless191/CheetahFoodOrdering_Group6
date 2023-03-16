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
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cheetahfoodordering.activities.UpdateProductActivity;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;
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
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        db = Room.databaseBuilder(this,
//                AppDatabase.class, "foodorderdb").allowMainThreadQueries().build();

        addFragment( new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_item:
                    replaceFragment( new HomeFragment());

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
}