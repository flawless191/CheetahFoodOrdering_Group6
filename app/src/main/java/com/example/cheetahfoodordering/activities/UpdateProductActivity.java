package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.OrderDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;
import com.example.cheetahfoodordering.entity.User;
import com.example.cheetahfoodordering.ui.CartFragment;
import com.example.cheetahfoodordering.ui.ManageProductFragment;

import java.util.List;

public class UpdateProductActivity extends AppCompatActivity {
    private EditText edt_product_name;
    private EditText edt_product_image;
    private EditText edt_product_price;
    private EditText edt_product_description;
    private EditText edt_quantity;
    private EditText edt_rate;
    private RadioButton radioButton;
    RadioGroup radioGroup ;
    private ImageView img_item_manage;
    private MainActivity mainActivity ;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_item);

        ((ImageView)findViewById(R.id.img_click_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();

            }
        });
        ItemProduct itemProduct = (ItemProduct)getIntent().getExtras().get("obj_item_manage");

        edt_product_name = findViewById(R.id.edt_name);
        edt_product_image = findViewById(R.id.edt_imge_url);
        edt_product_price = findViewById(R.id.edt_price);
        edt_product_description = findViewById(R.id.edt_description);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_rate = findViewById(R.id.edt_rate);

        img_item_manage = findViewById(R.id.img_item_manage);
        Glide.with(this).load(itemProduct.getProduct_image()).into(img_item_manage);

        switch(itemProduct.getCategory_id()){
            case 1:
                radioButton = findViewById(R.id.check_food);
                radioButton.setChecked(true);

                break;
            case 2:
                radioButton = findViewById(R.id.check_drink);
                radioButton.setChecked(true);
                break;
            default:
                radioButton = findViewById(R.id.check_other);
                radioButton.setChecked(true);
                break;
        }
        edt_product_image.setText(itemProduct.getProduct_image());
        edt_product_name.setText(itemProduct.getProduct_name());
        edt_product_price.setText(itemProduct.getProduct_price()+"");
        edt_product_description.setText(itemProduct.getProduct_description());
        edt_quantity.setText(itemProduct.getQuantity()+"");
        edt_rate.setText(itemProduct.getRate()+"");


        ((Button)findViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_product_image.getText().toString()) ||
                        TextUtils.isEmpty(edt_product_name.getText().toString()) ||
                        TextUtils.isEmpty(edt_product_price.getText().toString()) ||
                        TextUtils.isEmpty(edt_product_description.getText().toString()) ||
                        TextUtils.isEmpty(edt_quantity.getText().toString())||
                        TextUtils.isEmpty(edt_rate.getText().toString())) {
                    Toast.makeText(v.getContext(), "Please input all field", Toast.LENGTH_SHORT).show();

                } else {
                    radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    int categoryId;

                    switch (radioButton.getText().toString()) {
                        case "Food":
                            categoryId = 1;
                            break;
                        case "Drink":
                            categoryId = 2;
                            break;
                        default:
                            categoryId = 3;
                            break;
                    }
                    String productImage = edt_product_image.getText().toString();
                    String productName = edt_product_name.getText().toString();
                    float productPrice = Float.parseFloat(edt_product_price.getText().toString());
                    String productDescription = edt_product_description.getText().toString();
                    int productQuantity = Integer.parseInt(edt_quantity.getText().toString());
                    float productRate = Float.parseFloat(edt_rate.getText().toString());
                    ItemProduct itemUpdateProduct = new ItemProduct();
                    itemUpdateProduct.setProduct_id(itemProduct.getProduct_id());
                    itemUpdateProduct.setProduct_image(productImage);
                    itemUpdateProduct.setProduct_name(productName);
                    itemUpdateProduct.setProduct_price(productPrice);
                    itemUpdateProduct.setProduct_description(productDescription);
                    itemUpdateProduct.setQuantity(productQuantity);
                    itemUpdateProduct.setRate(productRate);
                    itemUpdateProduct.setCategory_id(categoryId);
                    AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                    ItemProductDao itemProductDao = appDatabase.ItemProductDao();
                    itemProductDao.updateProduct(itemUpdateProduct);
                    Toast.makeText(v.getContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ((Button)findViewById(R.id.btn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Are you sure you want to delete this product?");

                // Set up the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                        ItemProductDao itemProductDao = appDatabase.ItemProductDao();
                        FavoriteDao favoriteDao = appDatabase.favoriteDao();
                        List<Favorite> favoriteWithProductList = favoriteDao.getFavoriteWithProductId(itemProduct.getProduct_id());
                        OrderDao orderDao = appDatabase.orderDao();
                        List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts = orderDao.getAllProductInOrderWithProductId(itemProduct.getProduct_id());
                        if(orderWithOrderDetailAndProducts.isEmpty()&&favoriteWithProductList.isEmpty())
                        itemProductDao.deleteProduct(itemProduct);
                        else Toast.makeText(v.getContext(), "Can not delete this product!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                // Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }
}