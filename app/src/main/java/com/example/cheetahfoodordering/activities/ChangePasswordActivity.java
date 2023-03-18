package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.User;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText edt_old_password;
    private EditText edt_new_password;

    private EditText edt_confirm_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edt_old_password = findViewById(R.id.edt_old_password);
        edt_new_password = findViewById(R.id.edt_new_password);
        edt_confirm_password = findViewById(R.id.edt_confirm_password);
        ((ImageView)findViewById(R.id.img_back_setting_from_change_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ((Button)findViewById(R.id.btn_change)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);

                String userPhone = sharedPreferences.getString("userCurrentPhone",null);
                String pass = sharedPreferences.getString("userCurrentPassword",null);
                String oldPassword =edt_old_password.getText().toString();
                String newPassword =edt_new_password.getText().toString();
                String confirmPassword =edt_confirm_password.getText().toString();

                if(TextUtils.isEmpty(userPhone)){
                    Toast.makeText(v.getContext(), "Please login to change password!", Toast.LENGTH_SHORT).show();

                }else{
                    if (TextUtils.isEmpty(oldPassword)||
                            TextUtils.isEmpty(newPassword)||
                            TextUtils.isEmpty(confirmPassword)){
                        Toast.makeText(v.getContext(), "Please input all field", Toast.LENGTH_SHORT).show();
                    }else{
                        if (pass.equals(oldPassword)){
                            if (newPassword.equals(confirmPassword)){
                                AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                                UserDao userDao = appDatabase.userDao();
                                User user = userDao.getUserByPhoneAndPassword(userPhone,pass);
                                user.setPassword(newPassword);
                                userDao.updateUser(user);
                                Toast.makeText(v.getContext(), "Change password successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(v.getContext(),LoginActivity.class));
                            }else{
                                Toast.makeText(v.getContext(), "New password and confirm password must be the same!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(v.getContext(), "Old password is not correct!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }



}