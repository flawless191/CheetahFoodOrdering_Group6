package com.example.cheetahfoodordering.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.cheetahfoodordering.entity.ItemProduct;
import com.example.cheetahfoodordering.entity.Order;

@Entity(tableName = "order_details",primaryKeys = {"order_id", "product_id"}, foreignKeys = {@ForeignKey(entity = Order.class,parentColumns = "order_id",childColumns = "order_id"),@ForeignKey(entity = ItemProduct.class,parentColumns = "product_id",childColumns = "product_id")})
public class OrderDetail {

    private int order_id;

    private int product_id;
    @ColumnInfo(name = "in_cart_quantity")

    private int in_cart_quantity;
    @ColumnInfo(name = "unit_price")

    private float unit_price;

    public OrderDetail(int order_id, int product_id, int in_cart_quantity, float unit_price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.in_cart_quantity = in_cart_quantity;
        this.unit_price = unit_price;
    }

    public OrderDetail() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getIn_cart_quantity() {
        return in_cart_quantity;
    }

    public void setIn_cart_quantity(int in_cart_quantity) {
        this.in_cart_quantity = in_cart_quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }
}
