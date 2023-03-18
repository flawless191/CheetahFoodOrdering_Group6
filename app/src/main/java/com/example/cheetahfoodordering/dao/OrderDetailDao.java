package com.example.cheetahfoodordering.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cheetahfoodordering.entity.OrderDetail;

@Dao
public interface OrderDetailDao {
    @Insert
    void insertAllOrderDetails(OrderDetail...orderDetails);
    @Insert
    void insertOrderDetail(OrderDetail orderDetail);
    @Update
    void updateOrderDetail(OrderDetail orderDetail);
    @Delete
    void deleteOrderDetail(OrderDetail orderDetail);
    @Query("SELECT * FROM order_details WHERE order_id = :orderId  AND product_id = :productId")
    OrderDetail getOrderDetailWithOrderIdAndProductId(int orderId, int productId);
}
