package com.example.cheetahfoodordering.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;

public class ItemManageAdapter extends RecyclerView.Adapter<ItemManageAdapter.ItemManageViewHolder> {
    public List<ItemProduct> itemList ;
    private ItemManageAdapter.ItemManageOnClick itemManageOnClick;
    private Context context;
    public interface ItemManageOnClick {
        void onClickItemDetail(ItemProduct itemProduct);
    }

    public ItemManageAdapter(List<ItemProduct> itemList, ItemManageOnClick itemManageOnClick, Context context) {
        this.itemList = itemList;
        this.itemManageOnClick = itemManageOnClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage, parent,false);
        return new ItemManageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemManageViewHolder holder, int position) {
        ItemProduct item = itemList.get(position);
        if (item==null) return;
        String url = item.getProduct_image();
        Glide.with(context).load(url).into(holder.product_image);
        holder.product_name.setText(item.getProduct_name());
        holder.product_price.setText(item.getProduct_price()+"");
        holder.product_description.setText(item.getProduct_description());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemManageOnClick.onClickItemDetail(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemList!=null)
            return itemList.size();
        return 0;
    }

    public class ItemManageViewHolder extends RecyclerView.ViewHolder{
        private TextView product_name;
        private ImageView product_image;
        private TextView product_price;
        private TextView product_description;
        private TextView quantity;
        public ItemManageViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.image_item);
            product_name = itemView.findViewById(R.id.text_item_name);
            product_price = itemView.findViewById(R.id.text_item_price);
            product_description = itemView.findViewById(R.id.text_description);
        }
    }
}
