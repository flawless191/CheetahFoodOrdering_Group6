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
import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.FavoriteWithProduct;
import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;

public class ItemFavoriteAdapter extends RecyclerView.Adapter<ItemFavoriteAdapter.ItemFavoriteViewHolder> {
    public List<FavoriteWithProduct> favoriteWithProductList ;
    private ItemFavoriteOnClick itemFavoriteOnClick;
    private Context context;
    public interface ItemFavoriteOnClick {
        void onClickItemDetail(ItemProduct itemProduct);
        void onClickDeleteFavorite(Favorite favorite);
        void onClickAddToCart(ItemProduct itemProduct);
    }

    public ItemFavoriteAdapter(List<FavoriteWithProduct> favoriteWithProductList, ItemFavoriteOnClick itemFavoriteOnClick, Context context) {
        this.favoriteWithProductList = favoriteWithProductList;
        this.itemFavoriteOnClick = itemFavoriteOnClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent,false);
        return new ItemFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemFavoriteViewHolder holder, int position) {
        FavoriteWithProduct favoriteWithProduct = favoriteWithProductList.get(position);
        if (favoriteWithProductList==null) return;
        String url = favoriteWithProduct.itemProduct.getProduct_image();
        Glide.with(context).load(url).into(holder.product_image);
        holder.product_name.setText(favoriteWithProduct.itemProduct.getProduct_name());
        holder.product_price.setText(favoriteWithProduct.itemProduct.getProduct_price()+"VNĐ");
        holder.product_description.setText(favoriteWithProduct.itemProduct.getProduct_description());
        holder.product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFavoriteOnClick.onClickItemDetail(favoriteWithProduct.itemProduct);
            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFavoriteOnClick.onClickDeleteFavorite(favoriteWithProduct.favorite);
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
        if (favoriteWithProductList!=null)
            return favoriteWithProductList.size();
        return 0;
    }

    public class ItemFavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView product_name;
        private ImageView product_image;
        private TextView product_price;
        private TextView product_description;
        private TextView quantity;

        private ImageView img_delete;
        private ImageView img_cart;

        public ItemFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.image_item);
            product_name = itemView.findViewById(R.id.text_item_name);
            product_price = itemView.findViewById(R.id.text_item_price);
            product_description = itemView.findViewById(R.id.text_description);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_cart = itemView.findViewById(R.id.img_cart);

        }
    }
}
