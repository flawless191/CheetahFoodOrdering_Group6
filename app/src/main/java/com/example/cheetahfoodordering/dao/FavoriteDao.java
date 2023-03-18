package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cheetahfoodordering.entity.Favorite;
import com.example.cheetahfoodordering.entity.FavoriteWithProduct;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insertAllFavorite(Favorite ... favorites);
    @Delete
    void deleteFavorite(Favorite favorite);
    @Query("SELECT * FROM favorite")
    List<Favorite> getAll();

    @Query("SELECT * FROM favorite INNER JOIN itemproducts ON favorite.product_id = itemproducts.product_id WHERE user_id = :userId")
    List<FavoriteWithProduct> getAllProductInFavoriteWithUserId(int userId);
    @Query("SELECT * FROM favorite WHERE user_id = :userId AND product_id = :productId")
    Favorite getFavoriteWithUserIdAndProductId(int userId, int productId);

}
