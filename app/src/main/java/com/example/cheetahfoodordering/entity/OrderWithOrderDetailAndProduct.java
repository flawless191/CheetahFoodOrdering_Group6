package com.example.cheetahfoodordering.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class OrderWithOrderDetailAndProduct {
    @Embedded
    public OrderDetail orderDetail;
    @Relation(parentColumn = "order_id", entityColumn = "order_id")
    public Order order;
    @Relation(parentColumn = "product_id", entityColumn = "product_id")
    public ItemProduct itemProduct;
}
