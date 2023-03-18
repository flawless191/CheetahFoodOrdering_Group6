package com.example.cheetahfoodordering.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class FavoriteWithProduct {
    @Embedded
    public Favorite favorite;
    @Relation(parentColumn = "product_id",entityColumn = "product_id")
    public ItemProduct itemProduct;


}
