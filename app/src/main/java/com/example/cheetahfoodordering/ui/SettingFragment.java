package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.activities.ChangePasswordActivity;
import com.example.cheetahfoodordering.activities.LoginActivity;
import com.example.cheetahfoodordering.activities.UserProfileActivity;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.User;

import java.util.List;


public class SettingFragment extends Fragment {
    TextView txtManage ;
    TextView txtChangePassword ;
    private MainActivity mainActivity;
    TextView txtLogOut ;
    TextView txtUserProfile ;
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

                SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("user_account", mainActivity.MODE_PRIVATE);

                String userPhone = sharedPreferences.getString("userCurrentPhone",null);
                String pass = sharedPreferences.getString("userCurrentPassword",null);
                UserDao userDao = db.userDao();
                User user = new User();
                user= userDao.getUserByPhoneAndPassword(userPhone,pass);
                if (user.getIsAdmin().equals("1") ){
                    ItemProductDao itemProductDao = db.ItemProductDao();
                    List<ItemProduct> itemProductList = itemProductDao.getAllProduct();
                    mainActivity.replaceFragment(new ManageProductFragment(itemProductList));
                }else{
                    Toast.makeText(v.getContext(), "You can't manage product", Toast.LENGTH_SHORT).show();
                }

            }
        });
        txtChangePassword = rootView.findViewById(R.id.txt_change_password);
        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), ChangePasswordActivity.class));
            }
        });
        txtLogOut = rootView.findViewById(R.id.txt_log_out);
        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("user_account", mainActivity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("userCurrentPhone");
                editor.remove("userCurrentPassword");
                startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });
        txtUserProfile = rootView.findViewById(R.id.txt_user_profile);
        txtUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), UserProfileActivity.class));
            }
        });
        return rootView;
    }
}