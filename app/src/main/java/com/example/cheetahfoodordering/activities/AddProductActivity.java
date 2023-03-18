package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.CategoryDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Category;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private EditText edt_product_name;
    private EditText edt_product_image;
    private EditText edt_product_price;
    private EditText edt_product_description;
    private EditText edt_quantity;
    private EditText edt_rate;
    private RadioButton radioButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_item);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        ((ImageView)findViewById(R.id.img_click_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edt_product_name = findViewById(R.id.edt_name);

        edt_product_image = findViewById(R.id.edt_image);
        edt_product_price = findViewById(R.id.edt_price);
        edt_product_description = findViewById(R.id.edt_description);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_rate = findViewById(R.id.edt_rate);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        int categoryId;
        switch(radioButton.getText().toString()){
            case "Food":
                categoryId =1;
                break;
            case "Drink":
                categoryId =2;
                break;
            default:
                categoryId =3;
                break;
        }

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_product_image.getText().toString())||
                        TextUtils.isEmpty(edt_product_name.getText().toString())||
                        TextUtils.isEmpty(edt_product_price.getText().toString())||
                        TextUtils.isEmpty(edt_product_description.getText().toString())||
                        TextUtils.isEmpty(edt_quantity.getText().toString())||
                        TextUtils.isEmpty(edt_rate.getText().toString())){
                         Toast.makeText(v.getContext(), "Please input all field", Toast.LENGTH_SHORT).show();

                }else{
                    String productImage = edt_product_image.getText().toString();
                    String productName = edt_product_name.getText().toString();
                    float productPrice = Float.parseFloat(edt_product_price.getText().toString());
                    String productDescription = edt_product_description.getText().toString();
                    int productQuantity = Integer.parseInt(edt_quantity.getText().toString());
                    float productRate = Float.parseFloat(edt_rate.getText().toString());
                    ItemProduct itemProduct = new ItemProduct();
                    itemProduct.setProduct_image(productImage);
                    itemProduct.setProduct_name(productName);
                    itemProduct.setProduct_price(productPrice);
                    itemProduct.setProduct_description(productDescription);
                    itemProduct.setQuantity(productQuantity);
                    itemProduct.setRate(productRate);
                    itemProduct.setCategory_id(categoryId);
                    AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                    CategoryDao categoryDao = appDatabase.categoryDao();
                    List<Category> categoryList = categoryDao.getAll();
                    if (categoryList.isEmpty()){
                        categoryDao.insertCategory(new Category("Food"),new Category("Drink"),new Category("Other"));
                    }
                     ItemProductDao itemProductDao = appDatabase.ItemProductDao();
                    itemProductDao.insertProduct(itemProduct);
                    Toast.makeText(v.getContext(), "Add product successful!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}