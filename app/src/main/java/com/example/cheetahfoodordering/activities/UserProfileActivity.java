package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.User;

public class UserProfileActivity extends AppCompatActivity {
    private EditText edt_full_name;
    private TextView edt_phone;
    private EditText edt_email;
    private EditText edt_address;
    User user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        edt_full_name = findViewById(R.id.edt_user_name);
        edt_phone = findViewById(R.id.edt_user_phone);
        edt_email = findViewById(R.id.edt_user_email);
        edt_address = findViewById(R.id.edt_user_address);

        SharedPreferences sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);

        String userPhone = sharedPreferences.getString("userCurrentPhone",null);
        String pass = sharedPreferences.getString("userCurrentPassword",null);
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        UserDao userDao = appDatabase.userDao();

        user= userDao.getUserByPhoneAndPassword(userPhone,pass);
        edt_full_name.setText(user.getFull_name());
        edt_phone.setText(user.getUser_phone());
        edt_email.setText(user.getUser_email());
        edt_address.setText(user.getAddress());

        ((Button)findViewById(R.id.btn_update_profile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFullName = edt_full_name.getText().toString();
                String userEmail = edt_email.getText().toString();
                String userAddress = edt_address.getText().toString();
                user.setFull_name(userFullName);
                user.setUser_email(userEmail);
                user.setAddress(userAddress);
                userDao.updateUser(user);

                Toast.makeText(v.getContext(), "Update user profile successfully!", Toast.LENGTH_SHORT).show();

            }
        });

        ((ImageView)findViewById(R.id.img_setting_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}