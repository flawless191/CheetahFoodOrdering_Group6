package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;

public class WelcomeActivity extends AppCompatActivity {
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        String url = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg";
//        imageView = findViewById(R.id.image_logo);
//        Glide.with(this).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg").into(imageView);

        AppDatabase db =AppDatabase.getAppDatabase(this);
        ItemProductDao itemProductDao = db.ItemProductDao();
        ItemProduct itemProduct = new ItemProduct();
        itemProduct.setProduct_image("productImage");
        itemProduct.setProduct_name("https://assets.epicurious.com/photos/5c745a108918ee7ab68daf79/5:4/w_3129,h_2503,c_limit/Smashburger-recipe-120219.jpg");
        itemProduct.setProduct_price(45000);
        itemProduct.setProduct_description("It good");
        itemProduct.setQuantity(10);
        itemProduct.setCategory_id(1);
        ItemProduct itemProduct1 = new ItemProduct();
        itemProduct1.setProduct_image("https://www.theflavorbender.com/wp-content/uploads/2020/06/Chocolate-Cupcakes-SM-5629.jpg");
        itemProduct1.setProduct_name("Cup Cake");
        itemProduct1.setProduct_price(45000);
        itemProduct1.setProduct_description("It good");
        itemProduct1.setQuantity(15);
        itemProduct1.setCategory_id(1);
        ItemProduct itemProduct2 = new ItemProduct();
        itemProduct2.setProduct_image("https://img.taste.com.au/R_dRdL7V/taste/2022/09/healthy-tacos-recipe-181113-1.jpg");
        itemProduct2.setProduct_name("Taco");
        itemProduct2.setProduct_price(45000);
        itemProduct2.setProduct_description("It good");
        itemProduct2.setQuantity(10);
        itemProduct2.setCategory_id(1);
        ItemProduct itemProduct3 = new ItemProduct();
        itemProduct3.setProduct_image("https://product.hstatic.net/200000370095/product/nuoc_coca_classic_my_d543acf834e54a0ba2389ad466251501_master.jpg");
        itemProduct3.setProduct_name("Coca");
        itemProduct3.setProduct_price(15000);
        itemProduct3.setProduct_description("It good");
        itemProduct3.setQuantity(10);
        itemProduct3.setCategory_id(2);
        ItemProduct itemProduct4 = new ItemProduct();
        itemProduct4.setProduct_image("https://product.hstatic.net/1000400508/product/2112_d99536f1cdd041b08a2cca6fcd4eb819_c8f8c249024b49a194def01c60769d63_fbe9d218afc64cc4ac750afc84385e91_master.jpg");
        itemProduct4.setProduct_name("Pepsi");
        itemProduct4.setProduct_price(15000);
        itemProduct4.setProduct_description("It good");
        itemProduct4.setQuantity(10);
        itemProduct4.setCategory_id(2);
        ItemProduct itemProduct5 = new ItemProduct();
        itemProduct5.setProduct_image("https://www.twospoons.ca/wp-content/uploads/2019/09/how-to-make-almond-milk-step-by-step-instruction-vegan-gluten-free-AMAZING-creamy-healthy-plantbased-milks-easy-recipe-twospoons-3.jpg");
        itemProduct5.setProduct_name("Milk");
        itemProduct5.setProduct_price(25000);
        itemProduct5.setProduct_description("It good");
        itemProduct5.setQuantity(10);
        itemProduct5.setCategory_id(2);
        itemProductDao.insertProduct(itemProduct);
        itemProductDao.insertProduct(itemProduct1);
        itemProductDao.insertProduct(itemProduct2);
        itemProductDao.insertProduct(itemProduct3);
        itemProductDao.insertProduct(itemProduct4);
        itemProductDao.insertProduct(itemProduct5);


    }

    public void getStarted(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));

    }
}