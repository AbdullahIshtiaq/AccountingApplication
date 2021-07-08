package com.example.accountingapplication.network;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.accountingapplication.dataModel.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);
    @Update
    void update(Order order);
    @Delete
    void delete(Order order);

    @Query("DELETE FROM order_table")
    void deleteAll();

    @Query("SELECT * FROM order_table ORDER BY order_number DESC")
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * FROM order_table ORDER BY order_number DESC")
    LiveData<List<Order>> getSortedByOrderNumberDESC();

    @Query("SELECT * FROM order_table ORDER BY order_number ASC")
    LiveData<List<Order>> getSortedByOrderNumberASC();

    @Query("SELECT * FROM order_table ORDER BY date_time DESC")
    LiveData<List<Order>> getSortedByDateDESC();

    @Query("SELECT * FROM order_table ORDER BY date_time ASC")
    LiveData<List<Order>> getSortedByDateASC();

    @Query("SELECT * FROM order_table ORDER BY price DESC")
    LiveData<List<Order>> getSortedByPriceDESC();

    @Query("SELECT * FROM order_table ORDER BY price ASC")
    LiveData<List<Order>> getSortedByPriceASC();

    @Query("SELECT * FROM order_table WHERE order_through = :order_through")
    LiveData<List<Order>> getOrderThroughPriceList(String order_through);

    @Query("SELECT * FROM order_table WHERE order_through = :order_through AND date_time LIKE :date")
    LiveData<List<Order>> getOrderThroughPriceList(String order_through, String date);

    @Query("SELECT * FROM order_table WHERE date_time LIKE :date")
    LiveData<List<Order>> getDailyOrdesList(String date);

}
