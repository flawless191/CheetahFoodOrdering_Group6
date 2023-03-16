package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cheetahfoodordering.entity.ItemProduct;

import java.util.List;
@Dao
public interface ItemProductDao {
    @Insert
    void insertProduct(ItemProduct itemProduct);
    @Update
    void updateProduct(ItemProduct itemProduct);
    @Delete
    void deleteProduct(ItemProduct itemProduct);
    @Query("SELECT * FROM itemproducts WHERE product_id = :productId")
    ItemProduct getItemProduct(int productId);
    @Query("SELECT * FROM itemproducts")
    List<ItemProduct> getAllProduct();

    @Query("SELECT * FROM itemproducts WHERE category_id = :categoryId")
    List<ItemProduct> getProductWithCategory(int categoryId);

}
