package com.example.accountingapplication.dataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int order_number;
    private int price;
    private String delivery;
    private String payment_method;
    private String order_through;
    private String date_time;

//    public Order(int order_number, String delivery, int price, String payment_method) {
//        this.order_number = order_number;
//        this.delivery = delivery;
//        this.price = price;
//        this.payment_method = payment_method;
//        this.description = "None";
//    }

    public Order(int order_number, int price,String delivery, String payment_method, String order_through, String date_time) {
        this.order_number = order_number;
        this.delivery = delivery;
        this.price = price;
        this.payment_method = payment_method;
        this.order_through = order_through;
        this.date_time = date_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getOrder_number() {
        return order_number;
    }

    public String getDelivery() {
        return delivery;
    }

    public int getPrice() {
        return price;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getOrder_through() {
        return order_through;
    }

    public String getDate_time() {
        return date_time;
    }
}
