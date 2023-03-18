package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.entity.ItemProduct;


public class ItemDetailFragment extends Fragment {
    ImageView imageItem;
    TextView txtName;
    TextView txtPrice;
    TextView txtRate;
    TextView txtDescription;

    ImageView imageBack;


    public ItemDetailFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        txtName = rootView.findViewById(R.id.txt_name);
        txtPrice = rootView.findViewById(R.id.txt_price);
        imageItem = rootView.findViewById(R.id.img_item);
        txtRate = rootView.findViewById(R.id.txt_rate);
        txtDescription = rootView.findViewById(R.id.txt_user_profile);
        imageBack = rootView.findViewById(R.id.img_back);
        Bundle receiverBundle = getArguments();
        ItemProduct itemProduct = (ItemProduct) receiverBundle.get("obj_item");
        Toast.makeText(rootView.getContext(),itemProduct.getProduct_description()+"",Toast.LENGTH_SHORT).show();

        txtName.setText(itemProduct.getProduct_name());
        txtPrice.setText(itemProduct.getProduct_price()+"VNĐ");
        Glide.with(rootView.getContext()).load(itemProduct.getProduct_image()).into(imageItem);
        txtRate.setText("4.5");
        txtDescription.setText(itemProduct.getProduct_description()+"");
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Thisss",Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStack();
                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentManager supportFragmentManager = null;
//                fragmentManager.beginTransaction().sa
                fragmentManager.popBackStack();
//                super.onBackPressed();
            }
        });

        return rootView;
    }
}