package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.activities.AddProductActivity;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;


public class SettingFragment extends Fragment {
    TextView txtManage ;
    private MainActivity mainActivity;

    public SettingFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        txtManage = rootView.findViewById(R.id.txt_manage);
        mainActivity = (MainActivity) getActivity();
        txtManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getAppDatabase(rootView.getContext());
                ItemProductDao itemProductDao = db.ItemProductDao();
        List<ItemProduct> itemProductList = itemProductDao.getAllProduct();
                mainActivity.replaceFragment(new ManageProductFragment(itemProductList));
            }
        });
        return rootView;
    }
}