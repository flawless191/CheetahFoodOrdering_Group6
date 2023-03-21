package com.example.cheetahfoodordering.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cheetahfoodordering.MainActivity;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.adapters.ItemCartAdapter;
import com.example.cheetahfoodordering.dao.ItemProductDao;
import com.example.cheetahfoodordering.dao.OrderDao;
import com.example.cheetahfoodordering.dao.UserDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.Order;
import com.example.cheetahfoodordering.entity.OrderDetail;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;
import com.example.cheetahfoodordering.entity.User;

import java.util.List;


public class CartFragment extends Fragment {

    private RecyclerView recyclerViewItem;
    private MainActivity mainActivity;
    private TextView numberInList;
    private TextView totalPrice;
    public CartFragment() {
        // Required empty public constructor
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        AppDatabase db =AppDatabase.getAppDatabase(rootView.getContext());
        OrderDao orderDao= db.orderDao();
        mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("user_account", mainActivity.MODE_PRIVATE);

        String userPhone = sharedPreferences.getString("userCurrentPhone",null);
        String pass = sharedPreferences.getString("userCurrentPassword",null);
        UserDao userDao = db.userDao();
        User user = new User();
        user= userDao.getUserByPhoneAndPassword(userPhone,pass);
        List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts = orderDao.getProductInCartWithUserId(user.getUser_id(),0);
        numberInList = rootView.findViewById(R.id.txt_number_in_list);
        numberInList.setText(orderWithOrderDetailAndProducts.size()+" item in cart");
        recyclerViewItem = rootView.findViewById(R.id.recyclerItem);
        totalPrice = rootView.findViewById(R.id.txt_total);
        String priceFormat = String.format("%.0f",getTotalPrice(orderWithOrderDetailAndProducts));
        totalPrice.setText(priceFormat+" VND");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerViewItem.setLayoutManager(linearLayoutManager);
        ItemCartAdapter itemCartAdapter = new ItemCartAdapter(orderWithOrderDetailAndProducts, new ItemCartAdapter.ItemCartOnClick() {
                    @Override
                    public void onClickItemDetail(ItemProduct itemProduct) {
                        mainActivity.onClickItemDetail(itemProduct);
                    }

                    @Override
                    public void onClickDeleteFromCart(OrderDetail orderDetail) {
                        mainActivity.deleteItemFromCartOnClick(orderDetail);
                    }

                    @Override
                    public void onClickIncreaseInCartQuantity(OrderDetail orderDetail) {
                        mainActivity.increaseItemQuantityFromCartOnClick(orderDetail);
                    }

                    @Override
                    public void onClickDecreaseInCartQuantity(OrderDetail orderDetail) {
                        mainActivity.decreaseItemQuantityFromCartOnClick(orderDetail);
                    }
                }, rootView.getContext());
        recyclerViewItem.setAdapter(itemCartAdapter);
        ((Button)rootView.findViewById(R.id.btn_check_out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDao.updateOrder(new Order(orderWithOrderDetailAndProducts.get(0).order.getOrder_id(),
                        orderWithOrderDetailAndProducts.get(0).order.getUser_id(),1));
                Toast.makeText(v.getContext(), "Checkout successfully!", Toast.LENGTH_SHORT).show();
                mainActivity.replaceFragment(new CartFragment());
            }
        });
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
        return rootView;
    }

    public float getTotalPrice(List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts){
        float total=0;
        for (OrderWithOrderDetailAndProduct orderWithOrderDetailAndProduct: orderWithOrderDetailAndProducts
        ) {
            total +=orderWithOrderDetailAndProduct.itemProduct.getProduct_price()*orderWithOrderDetailAndProduct.orderDetail.getIn_cart_quantity();
        }
        return total;
    }
}