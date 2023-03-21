package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;


public class ItemDetailFragment extends Fragment {
    ImageView imageItem;
    TextView txtName;
    TextView txtPrice;
    TextView txtRate;
    TextView txtDescription;
    TextView addCartQuantity;
    ImageView imageBack;
    int itemQuantity;
    private MainActivity mainActivity;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public ItemDetailFragment( int itemQuantity) {
        this.itemQuantity = itemQuantity;
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
        addCartQuantity = rootView.findViewById(R.id.txt_add_cart_quantity);
        Bundle receiverBundle = getArguments();
        ItemProduct itemProduct = (ItemProduct) receiverBundle.get("obj_item");
//        Toast.makeText(rootView.getContext(),itemProduct.getProduct_description()+"",Toast.LENGTH_SHORT).show();

        txtName.setText(itemProduct.getProduct_name());
        txtPrice.setText(itemProduct.getProduct_price()+"VNĐ");
        Glide.with(rootView.getContext()).load(itemProduct.getProduct_image()).into(imageItem);
        txtRate.setText(itemProduct.getRate()+"");
        txtDescription.setText(itemProduct.getProduct_description()+"");
        addCartQuantity.setText(itemQuantity+"");
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().popBackStack();
                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentManager supportFragmentManager = null;
//                fragmentManager.beginTransaction().sa
                fragmentManager.popBackStack();
//                super.onBackPressed();
            }
        });
        mainActivity = (MainActivity) getActivity();
        ((Button)rootView.findViewById(R.id.btn_add_to_cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(addCartQuantity.getText().toString());
                mainActivity.itemAddToCartOnClickWithQuantity(itemProduct,quantity);


            }
        });
        ((ImageView)rootView.findViewById(R.id.img_minus_quantity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(addCartQuantity.getText().toString());
                if (quantity-1==0){
                    Toast.makeText(v.getContext(), "Quantity can not equals 0!", Toast.LENGTH_SHORT).show();
                }else {
                    ItemDetailFragment itemDetailFragment = new ItemDetailFragment(quantity-1);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("obj_item",itemProduct);
                    itemDetailFragment.setArguments(bundle);
                    mainActivity.replaceFragment(itemDetailFragment);

                    Toast.makeText(v.getContext(), "Minus !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        ((ImageView)rootView.findViewById(R.id.img_add_quantity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(addCartQuantity.getText().toString());
                ItemDetailFragment itemDetailFragment = new ItemDetailFragment(quantity+1);

                Bundle bundle = new Bundle();
                bundle.putSerializable("obj_item",itemProduct);
                itemDetailFragment.setArguments(bundle);
                itemDetailFragment.setArguments(bundle);
                mainActivity.replaceFragment(itemDetailFragment);
                Toast.makeText(v.getContext(), "ADD !", Toast.LENGTH_SHORT).show();
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
                    ItemProductDao itemProductDao = AppDatabase.getAppDatabase(v.getContext()).ItemProductDao();
                    List<ItemProduct> listSearch = itemProductDao.getListSearchProduct(productNameSearch);
                    mainActivity.replaceFragment(new HomeFragment(listSearch));
                }
            }
        });
        return rootView;
    }
}