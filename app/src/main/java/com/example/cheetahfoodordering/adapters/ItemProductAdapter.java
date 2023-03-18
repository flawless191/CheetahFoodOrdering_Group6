package com.example.cheetahfoodordering.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cheetahfoodordering.R;
import com.example.cheetahfoodordering.dao.FavoriteDao;
import com.example.cheetahfoodordering.database.AppDatabase;
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;

public class ItemProductAdapter extends RecyclerView.Adapter<ItemProductAdapter.ItemProductViewHolder>{
    public List<ItemProduct> itemList ;
    private ItemDetailOnClick itemDetailOnClick;
    private Context context;
    public interface ItemDetailOnClick {
        void onClickItemDetail(ItemProduct itemProduct);
        void onClickFavorite(ItemProduct itemProduct);
    }

    public ItemProductAdapter(List<ItemProduct> itemList, ItemDetailOnClick itemDetailOnClick, Context context) {
        this.itemList = itemList;
        this.itemDetailOnClick = itemDetailOnClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new ItemProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductViewHolder holder, int position) {
        ItemProduct item = itemList.get(position);
        if (item==null) return;
        String url = item.getProduct_image();
        Glide.with(context).load(url).into(holder.product_image);
        holder.product_name.setText(item.getProduct_name());
        holder.product_price.setText(item.getProduct_price()+"VNĐ");

        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDetailOnClick.onClickItemDetail(item);
            }
        });
        holder.img_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDetailOnClick.onClickFavorite(item);
//                Toast.makeText(v.getContext(), "Add to favorite list successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemList!=null)
            return itemList.size();
        return 0;
    }

    public class ItemProductViewHolder extends RecyclerView.ViewHolder{
        private TextView product_name;
        private ImageView product_image;
        private TextView product_price;
        private TextView product_description;
        private TextView quantity;

        private ImageView img_favorite;
        private ImageView img_cart;
        public ItemProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.image_item);
            product_name = itemView.findViewById(R.id.text_item_name);
            product_price = itemView.findViewById(R.id.text_item_price);
            img_favorite = itemView.findViewById(R.id.img_favorite);
            img_cart = itemView.findViewById(R.id.img_add_to_cart);
        }
    }

}
