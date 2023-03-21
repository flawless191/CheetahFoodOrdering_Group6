package com.example.cheetahfoodordering.ui;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.activities.AddProductActivity;
import com.example.cheetahfoodordering.adapters.ItemManageAdapter;
import com.example.cheetahfoodordering.adapters.ItemProductAdapter;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;


public class ManageProductFragment extends Fragment  {
    private MainActivity mainActivity;
    AppDatabase db ;
    private RecyclerView recyclerViewItem;
    private ItemManageAdapter itemManageAdapter;
    List<ItemProduct> itemProductList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ManageProductFragment() {
        // Required empty public constructor
    }

    public ManageProductFragment( List<ItemProduct> itemProductList) {
        this.itemProductList = itemProductList;
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_product, container, false);
        // Inflate the layout for this fragment
        db = AppDatabase.getAppDatabase(rootView.getContext());
        ItemProductDao itemProductDao = db.ItemProductDao();

        mainActivity = (MainActivity) getActivity();
        recyclerViewItem = rootView.findViewById(R.id.recyclerItem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerViewItem.setLayoutManager(linearLayoutManager);

         itemManageAdapter = new ItemManageAdapter(itemProductList, new ItemManageAdapter.ItemManageOnClick() {
            @Override
            public void onClickItemDetail(ItemProduct itemProduct) {
                mainActivity.itemManageOnClick(itemProduct);
            }
        },rootView.getContext());
        ((ImageView)rootView.findViewById(R.id.img_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), AddProductActivity.class));
            }
        });
        recyclerViewItem.setAdapter(itemManageAdapter);
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
                    mainActivity.replaceFragment(new ManageProductFragment(listSearch));
                }
            }
        });
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }

            private void refreshItems() {
                // Simulate a delay of 2 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mainActivity.replaceFragment(new ManageProductFragment(AppDatabase.getAppDatabase(mainActivity).ItemProductDao().getAllProduct()));
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Reload", Toast.LENGTH_SHORT).show();

                    }
                }, 1000);
            }
        });
        return rootView;
    }
}