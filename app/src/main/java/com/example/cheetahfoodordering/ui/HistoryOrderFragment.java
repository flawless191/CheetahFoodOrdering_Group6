package com.example.cheetahfoodordering.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.adapters.ItemHistoryOrderAdapter;
import com.example.cheetahfoodordering.adapters.ItemProductAdapter;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.OrderDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;
import com.example.cheetahfoodordering.entity.User;

import java.util.List;


public class HistoryOrderFragment extends Fragment {
    private RecyclerView recyclerViewItemOrder;
    private RecyclerView recyclerViewItemPopular;
    private MainActivity mainActivity;
    public HistoryOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_order, container, false);
        AppDatabase db =AppDatabase.getAppDatabase(rootView.getContext());
        OrderDao orderDao= db.orderDao();
        mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("user_account", mainActivity.MODE_PRIVATE);

        String userPhone = sharedPreferences.getString("userCurrentPhone",null);
        String pass = sharedPreferences.getString("userCurrentPassword",null);
        UserDao userDao = db.userDao();
        User user = new User();
        user= userDao.getUserByPhoneAndPassword(userPhone,pass);
        List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts = orderDao.getProductInCartWithUserId(user.getUser_id(),1);
        recyclerViewItemOrder = rootView.findViewById(R.id.recyclerItemOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerViewItemOrder.setLayoutManager(linearLayoutManager);
        ItemHistoryOrderAdapter itemHistoryOrderAdapter = new ItemHistoryOrderAdapter(orderWithOrderDetailAndProducts, new ItemHistoryOrderAdapter.ItemHistoryOrderOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }
        },rootView.getContext());
        recyclerViewItemOrder.setAdapter(itemHistoryOrderAdapter);
        recyclerViewItemPopular = rootView.findViewById(R.id.recyclerItemPopular);
        GridLayoutManager gridLayoutManagerPopular = new GridLayoutManager(rootView.getContext(),2);
        recyclerViewItemPopular.setLayoutManager(gridLayoutManagerPopular);
        ItemProductDao itemProductDao = db.ItemProductDao();
        List<ItemProduct> itemListPopular = itemProductDao.getTop2FavoriteProduct();
        ItemProductAdapter itemProductAdapter = new ItemProductAdapter(itemListPopular, new ItemProductAdapter.ItemDetailOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.onClickItemDetail(itemProduct);
            }

            @Override
            public void onClickFavorite(ItemProduct itemProduct) {
                mainActivity.itemAddToFavoriteOnClick(itemProduct);
            }

            @Override
            public void onClickAddToCart(ItemProduct itemProduct) {
                mainActivity.itemAddToCartOnClick(itemProduct);
            }
        },rootView.getContext());
        recyclerViewItemPopular.setAdapter(itemProductAdapter);
        return rootView;

    }
}