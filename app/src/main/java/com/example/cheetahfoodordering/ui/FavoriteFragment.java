package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.adapters.ItemFavoriteAdapter;
import com.example.cheetahfoodordering.adapters.ItemProductAdapter;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.FavoriteWithProduct;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.User;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerViewItem;
    private RecyclerView recyclerViewItemPopular;
    AppDatabase db ;
    private MainActivity mainActivity;
    private TextView numberInList;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        // Inflate the layout for this fragment
        db = AppDatabase.getAppDatabase(rootView.getContext());
        FavoriteDao favoriteDao = db.favoriteDao();
        mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("user_account", mainActivity.MODE_PRIVATE);

        String userPhone = sharedPreferences.getString("userCurrentPhone",null);
        String pass = sharedPreferences.getString("userCurrentPassword",null);
        UserDao userDao = db.userDao();
        User user = new User();
        user= userDao.getUserByPhoneAndPassword(userPhone,pass);
        List<FavoriteWithProduct> favoriteWithProductList = favoriteDao.getAllProductInFavoriteWithUserId(user.getUser_id());
        numberInList = rootView.findViewById(R.id.txt_number_in_list);
        numberInList.setText(favoriteWithProductList.size()+" item in favorite list");
        mainActivity = (MainActivity) getActivity();
        recyclerViewItem = rootView.findViewById(R.id.recyclerItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerViewItem.setLayoutManager(linearLayoutManager);
        ItemFavoriteAdapter itemFavoriteAdapter = new ItemFavoriteAdapter(favoriteWithProductList, new ItemFavoriteAdapter.ItemFavoriteOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }

            @Override
            public void onClickDeleteFavorite(Favorite favorite) {
                mainActivity.deleteItemFromFavoriteOnClick(favorite);
            }

            @Override
            public void onClickAddToCart(ItemProduct itemProduct) {

            }
        },rootView.getContext());
        recyclerViewItem.setAdapter(itemFavoriteAdapter);
        EditText edt_search = rootView.findViewById(R.id.edt_search);
        ((ImageView)rootView.findViewById(R.id.img_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_search.getText().toString())){
                    Toast.makeText(v.getContext(), "Please enter search value", Toast.LENGTH_SHORT).show();
                }else {
                    mainActivity = (MainActivity) getActivity();
                    String productNameSearch = "%" + edt_search.getText().toString()+"%";
                    ItemProductDao itemProductDao = db.ItemProductDao();
                    List<ItemProduct> listSearch = itemProductDao.getListSearchProduct(productNameSearch);
                    mainActivity.replaceFragment(new HomeFragment(listSearch));
                }
            }
        });

        recyclerViewItemPopular = rootView.findViewById(R.id.recyclerItemPopular);
        GridLayoutManager gridLayoutManagerPopular = new GridLayoutManager(rootView.getContext(),2);
        recyclerViewItemPopular.setLayoutManager(gridLayoutManagerPopular);
        ItemProductDao itemProductDao = db.ItemProductDao();
        List<ItemProduct> itemListPopular = itemProductDao.getTop2FavoriteProduct() ;
        ItemProductAdapter itemProductAdapter = new ItemProductAdapter(itemListPopular, new ItemProductAdapter.ItemDetailOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }

            @Override
            public void onClickFavorite(ItemProduct itemProduct) {

            }
        },rootView.getContext());
        recyclerViewItemPopular.setAdapter(itemProductAdapter);
        return rootView;
    }
}