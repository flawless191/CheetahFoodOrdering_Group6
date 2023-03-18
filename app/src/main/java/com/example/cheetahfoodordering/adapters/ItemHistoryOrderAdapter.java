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
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;

import java.util.List;

public class ItemHistoryOrderAdapter extends RecyclerView.Adapter<ItemHistoryOrderAdapter.ItemHistoryOrderViewHolder>{
    public List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts ;
    private ItemHistoryOrderOnClick itemHistoryOrderOnClick;
    private Context context;

    public interface ItemHistoryOrderOnClick {
        void onClickItemDetail(ItemProduct itemProduct);

    }

    public ItemHistoryOrderAdapter(List<OrderWithOrderDetailAndProduct> orderWithOrderDetailAndProducts, ItemHistoryOrderOnClick itemHistoryOrderOnClick, Context context) {
        this.orderWithOrderDetailAndProducts = orderWithOrderDetailAndProducts;
        this.itemHistoryOrderOnClick = itemHistoryOrderOnClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent,false);
        return new ItemHistoryOrderAdapter.ItemHistoryOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHistoryOrderViewHolder holder, int position) {
        if (orderWithOrderDetailAndProducts==null) return;
        OrderWithOrderDetailAndProduct orderWithOrderDetailAndProduct = orderWithOrderDetailAndProducts.get(position);

        String url = orderWithOrderDetailAndProduct.itemProduct.getProduct_image();
        Glide.with(context).load(url).into(holder.product_image);
        holder.product_name.setText(orderWithOrderDetailAndProduct.itemProduct.getProduct_name());
        holder.product_price.setText(orderWithOrderDetailAndProduct.itemProduct.getProduct_price()+"VNĐ");
        holder.txt_In_cart_quantity.setText(orderWithOrderDetailAndProduct.orderDetail.getIn_cart_quantity()+"");
        holder.txt_description.setText(orderWithOrderDetailAndProduct.itemProduct.getProduct_description());
        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemHistoryOrderOnClick.onClickItemDetail(orderWithOrderDetailAndProduct.itemProduct);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderWithOrderDetailAndProducts!=null)
            return orderWithOrderDetailAndProducts.size();
        return 0;
    }

    public class ItemHistoryOrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;
        private TextView product_name;
        private TextView product_price;
        private TextView txt_In_cart_quantity;
        private TextView txt_description;


        public ItemHistoryOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.image_item);
            product_name = itemView.findViewById(R.id.text_item_name);
            product_price = itemView.findViewById(R.id.text_item_price);
            txt_In_cart_quantity = itemView.findViewById(R.id.txt_quantity);
            txt_description = itemView.findViewById(R.id.text_description);

        }
    }
}
