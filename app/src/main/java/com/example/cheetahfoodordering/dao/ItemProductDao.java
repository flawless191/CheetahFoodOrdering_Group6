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

    @Query("SELECT * FROM itemproducts WHERE product_name LIKE :productName")
    List<ItemProduct> getListSearchProduct(String productName);

    @Query("SELECT  *, count(*) AS Total FROM favorite INNER JOIN itemproducts ON favorite.product_id = itemproducts.product_id GROUP BY favorite.product_id ORDER BY Total DESC LIMIT 2")
    List<ItemProduct> getTop2FavoriteProduct();

    @Query("SELECT  *, count(*) AS Total FROM favorite INNER JOIN itemproducts ON favorite.product_id = itemproducts.product_id GROUP BY favorite.product_id ORDER BY Total DESC LIMIT :top")
    List<ItemProduct> getTopFavoriteProduct(int top);

}
