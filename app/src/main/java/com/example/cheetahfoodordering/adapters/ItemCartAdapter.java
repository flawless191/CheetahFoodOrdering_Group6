package com.example.cheetahfoodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.OrderDetail;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;

import java.util.List;

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ItemCartViewHolder>{
    public List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts ;
    private ItemCartOnClick itemCartOnClick;
    private Context context;

    public interface ItemCartOnClick {
        void onClickItemDetail(ItemProduct itemProduct);
        void onClickDeleteFromCart(OrderDetail orderDetail);
        void onClickIncreaseInCartQuantity(OrderDetail orderDetail);
        void onClickDecreaseInCartQuantity(OrderDetail orderDetail);
    }

    public ItemCartAdapter(List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts, ItemCartOnClick itemCartOnClick, Context context) {
        this.orderWithOrderDetailAndProducts = orderWithOrderDetailAndProducts;
        this.itemCartOnClick = itemCartOnClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent,false);
        return new ItemCartAdapter.ItemCartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCartViewHolder holder, int position) {
        if (orderWithOrderDetailAndProducts==null) return;
        OrderWithOrderDetailAndProduct orderWithOrderDetailAndProduct = orderWithOrderDetailAndProducts.get(position);

        String url = orderWithOrderDetailAndProduct.itemProduct.getProduct_image();
        Glide.with(context).load(url).into(holder.product_image);
        holder.product_name.setText(orderWithOrderDetailAndProduct.itemProduct.getProduct_name());
        holder.product_price.setText(orderWithOrderDetailAndProduct.itemProduct.getProduct_price()+"VNĐ");
        holder.txt_In_cart_quantity.setText(orderWithOrderDetailAndProduct.orderDetail.getIn_cart_quantity()+"");
        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCartOnClick.onClickItemDetail(orderWithOrderDetailAndProduct.itemProduct);
            }
        });
        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCartOnClick.onClickIncreaseInCartQuantity(orderWithOrderDetailAndProduct.orderDetail);
            }
        });
        holder.img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCartOnClick.onClickDecreaseInCartQuantity(orderWithOrderDetailAndProduct.orderDetail);
            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCartOnClick.onClickDeleteFromCart(orderWithOrderDetailAndProduct.orderDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderWithOrderDetailAndProducts!=null)
            return orderWithOrderDetailAndProducts.size();
        return 0;
    }

    public class ItemCartViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;
        private TextView product_name;
        private TextView product_price;
        private TextView txt_In_cart_quantity;
        private ImageView img_delete;
        private ImageView img_add;
        private ImageView img_minus;
        public ItemCartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.image_item);
            product_name = itemView.findViewById(R.id.text_item_name);
            product_price = itemView.findViewById(R.id.text_item_price);
            txt_In_cart_quantity = itemView.findViewById(R.id.txt_in_cart_quantity);
            img_add = itemView.findViewById(R.id.img_increase);
            img_minus = itemView.findViewById(R.id.img_minus);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }


}
