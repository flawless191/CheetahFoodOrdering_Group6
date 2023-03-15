package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edt_full_name;
    private EditText edt_email;
    private EditText edt_phone;
    private EditText edt_address;
    private EditText edt_password;
    private EditText edt_confirm_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        edt_full_name = findViewById(R.id.edt_fullname);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        edt_address = findViewById(R.id.edt_address);
        edt_password = findViewById(R.id.edt_register_passworrd);
        edt_confirm_password = findViewById(R.id.edt_confirm_register_passworrd);
        ((TextView)findViewById(R.id.txt_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        ((Button)findViewById(R.id.btn_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                if(TextUtils.isEmpty(edt_full_name.getText().toString()) ||
                        TextUtils.isEmpty(edt_email.getText().toString()) ||
                        TextUtils.isEmpty(edt_phone.getText().toString()) ||
                        TextUtils.isEmpty(edt_address.getText().toString())||
                        TextUtils.isEmpty(edt_password.getText().toString())||
                        TextUtils.isEmpty(edt_confirm_password.getText().toString())){
                    Toast.makeText(v.getContext(), "Please input all field", Toast.LENGTH_SHORT).show();
                }else{
                    if (!edt_password.getText().toString().equals(edt_confirm_password.getText().toString())){
                        Toast.makeText(v.getContext(), "Password and confirm password must be the same!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(), "Successful!", Toast.LENGTH_SHORT).show();

                        User user=new User();
                        user.setFull_name(edt_full_name.getText().toString());
                        user.setUser_email(edt_email.getText().toString());
                        user.setUser_phone(edt_phone.getText().toString());
                        user.setAddress(edt_address.getText().toString());
                        user.setPassword(edt_password.getText().toString());
                        user.setIsAdmin("false");

                        UserDao userDao = appDatabase.userDao();
                        User userCheck=null;
                        userCheck=userDao.getUserByPhone(edt_phone.getText().toString());
                        if (userCheck!=null){
                            Toast.makeText(v.getContext(), "User has been existed!", Toast.LENGTH_SHORT).show();
                        }else{
                            userDao.insertUser(user);
                            Toast.makeText(v.getContext(), "User register successfully!", Toast.LENGTH_SHORT).show();
                            userCheck= userDao.getAllUser().get(0);
                            Toast.makeText(v.getContext(), "User register successfully! "+userCheck.getFull_name(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                }
            }
        });

    }
}