package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cheetahfoodordering.entity.Order;
import com.example.cheetahfoodordering.entity.OrderWithOrderDetailAndProduct;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insertAllOrder(Order...orders);
    @Insert
    void insertOrder(Order order);
    @Delete
    void deleteOrder(Order order);
    @Update
    void updateOrder(Order order);
    @Query("SELECT * FROM orders")
    List<Order> getAllOrder();
    @Query("SELECT * FROM orders WHERE user_id = :userId AND isCheckOut = :isCheckOut")
    Order getOrderWithUserIdAndIsCheckOut(int userId, int isCheckOut);
    @Query("SELECT * FROM orders INNER JOIN order_details ON orders.order_id = order_details.order_id INNER JOIN itemproducts ON itemproducts.product_id = order_details.product_id WHERE orders.user_id = :userId AND orders.isCheckOut = :isCheckOut")
    List<OrderWithOrderDetailAndProduct> getProductInCartWithUserId(int userId, int isCheckOut);
}
