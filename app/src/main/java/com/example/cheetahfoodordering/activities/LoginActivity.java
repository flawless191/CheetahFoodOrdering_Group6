package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.User;

public class LoginActivity extends AppCompatActivity {
    private TextView signUp;
    private EditText edt_phone;
    private EditText edt_password;
    private CheckBox cb_remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ((TextView)findViewById(R.id.txt_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        edt_phone= findViewById(R.id.edt_login_phone);
        edt_password = findViewById(R.id.edt_login_password);
        cb_remember = findViewById(R.id.che_remember);
        SharedPreferences sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);
        if (sharedPreferences.getString("userRememberPhone",null)!=null){
            String userPhone = sharedPreferences.getString("userRememberPhone",null);
            edt_phone.setText(userPhone);
            String pass = sharedPreferences.getString("userRememberPassword",null);
            edt_password.setText(pass);
            boolean isSave = sharedPreferences.getBoolean("isSave",false);
            cb_remember.setChecked(isSave);
        }
        ((Button)findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase appDatabase = AppDatabase.getAppDatabase(v.getContext());
                String userPhone = edt_phone.getText().toString();
                String userPassword = edt_password.getText().toString();
                if(TextUtils.isEmpty(userPhone)||TextUtils.isEmpty(userPassword)){
                    Toast.makeText(v.getContext(), "Please input all field", Toast.LENGTH_SHORT).show();
                }else {
                    UserDao userDao = appDatabase.userDao();
                    User userCheck = new User();
                    userCheck = userDao.getUserByPhone(edt_phone.getText().toString());
                    if (userCheck!=null){
                        User user = userDao.getUserByPhoneAndPassword(userPhone,userPassword);
                        if (user!=null){
                            SharedPreferences sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (cb_remember.isChecked()) {

                                editor.putString("userRememberPhone", userPhone);
                                editor.putString("userRememberPassword", userPassword);
                                editor.putBoolean("isSave", cb_remember.isChecked());
                                editor.commit();
                            }else {
                                editor.remove("userRememberPhone");
                                editor.remove("userRememberPassword");
                                editor.putBoolean("isSave",false);
                                editor.commit();
                            }
                            editor.putString("userCurrentPhone", userPhone);
                            editor.putString("userCurrentPassword", userPassword);
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(v.getContext(), "Wrong phone number or password!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(v.getContext(), "User is not existed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}