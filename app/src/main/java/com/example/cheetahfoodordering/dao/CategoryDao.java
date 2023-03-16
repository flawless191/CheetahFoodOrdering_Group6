package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cheetahfoodordering.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insertCategory(Category... categories);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Query("SELECT * FROM category")
    List<Category> getAll();
}
