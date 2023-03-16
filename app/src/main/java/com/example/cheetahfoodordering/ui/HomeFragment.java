package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.adapters.ItemProductAdapter;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;


import java.util.List;


public class HomeFragment extends Fragment {

    private MainActivity mainActivity;
    AppDatabase db ;
    private RecyclerView recyclerViewItem;
    List<ItemProduct> itemProductList;
    int categoryId;
    public HomeFragment() {
        // Required empty public constructor
    }

    public HomeFragment( int categoryId) {

        this.categoryId = categoryId;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        db= AppDatabase.getAppDatabase(rootView.getContext());
        ItemProductDao itemProductDao = db.ItemProductDao();
        itemProductList= itemProductDao.getAllProduct();

        mainActivity = (MainActivity) getActivity();
        recyclerViewItem = rootView.findViewById(R.id.recyclerItem);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(),2);
        recyclerViewItem.setLayoutManager(gridLayoutManager);
        recyclerViewItem.setNestedScrollingEnabled(false);
        if(categoryId ==1) {
            itemProductList = itemProductDao.getProductWithCategory(1);

        }
        switch (categoryId){
            case 1:
                itemProductList = itemProductDao.getProductWithCategory(1);
                break;
            case 2:
                itemProductList = itemProductDao.getProductWithCategory(2);
                break;
            case 3:
                itemProductList = itemProductDao.getProductWithCategory(3);
                break;
            default:
                itemProductList= itemProductDao.getAllProduct();
                break;
        }
        ItemProductAdapter itemProductAdapter = new ItemProductAdapter(itemProductList, new ItemProductAdapter.ItemDetailOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }
        },rootView.getContext());
        recyclerViewItem.setAdapter(itemProductAdapter);
        ((ImageView)rootView.findViewById(R.id.img_all_category)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProductList = itemProductDao.getAllProduct();
                mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new HomeFragment(0));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_food)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProductList = itemProductDao.getAllProduct();
                mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new HomeFragment(1));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_drink)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProductList = itemProductDao.getAllProduct();
                mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new HomeFragment(2));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_other)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemProductList = itemProductDao.getAllProduct();
                mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new HomeFragment(3 ));
            }
        });


        return rootView;

    }
}