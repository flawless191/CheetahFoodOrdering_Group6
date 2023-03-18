package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

//    public HomeFragment( List<ItemProduct> itemProductListQuery) {
//
//        this.itemProductList = itemProductListQuery;
//    }


    public HomeFragment( List<ItemProduct> itemProductList) {
        this.itemProductList = itemProductList;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        db= AppDatabase.getAppDatabase(rootView.getContext());
        ItemProductDao itemProductDao = db.ItemProductDao();

        mainActivity = (MainActivity) getActivity();
        recyclerViewItem = rootView.findViewById(R.id.recyclerItem);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(),2);
        recyclerViewItem.setLayoutManager(gridLayoutManager);
        recyclerViewItem.setNestedScrollingEnabled(false);

        ItemProductAdapter itemProductAdapter = new ItemProductAdapter(this.itemProductList, new ItemProductAdapter.ItemDetailOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }

            @Override
            public void onClickFavorite(ItemProduct itemProduct) {
                mainActivity.itemAddToFavoriteOnClick(itemProduct);
            }

            @Override
            public void onClickAddToCart(ItemProduct itemProduct) {
                mainActivity.itemAddToCartOnClick(itemProduct);
            }
        },rootView.getContext());
        recyclerViewItem.setAdapter(itemProductAdapter);
        ((ImageView)rootView.findViewById(R.id.img_all_category)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                List<ItemProduct> listWithCategory= itemProductDao.getAllProduct();
                mainActivity.replaceFragment(new HomeFragment(listWithCategory));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_food)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                List<ItemProduct> listWithCategory= itemProductDao.getProductWithCategory(1);
                mainActivity.replaceFragment(new HomeFragment(listWithCategory));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_drink)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                List<ItemProduct> listWithCategory= itemProductDao.getProductWithCategory(2);
                mainActivity.replaceFragment(new HomeFragment(listWithCategory));
            }
        });
        ((ImageView)rootView.findViewById(R.id.img_other)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                List<ItemProduct> listWithCategory= itemProductDao.getProductWithCategory(3);
                mainActivity.replaceFragment(new HomeFragment(listWithCategory));
            }
        });
        EditText edt_search = rootView.findViewById(R.id.edt_search);

        ((ImageView)rootView.findViewById(R.id.img_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_search.getText().toString())){
                    Toast.makeText(v.getContext(), "Please enter search value", Toast.LENGTH_SHORT).show();
                }else {
                    mainActivity = (MainActivity) getActivity();
                    String productNameSearch = "%" + edt_search.getText().toString()+"%";
                    List<ItemProduct> listSearch = itemProductDao.getListSearchProduct(productNameSearch);
                    mainActivity.replaceFragment(new HomeFragment(listSearch));
                }
            }
        });

        return rootView;

    }
}