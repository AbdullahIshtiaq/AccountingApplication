package com.example.accountingapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.network.OrderRepository;

import java.util.List;

public class
OrderViewModel extends AndroidViewModel {
    private OrderRepository repository;
    private LiveData<List<Order>> allOrders;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        allOrders = repository.getAllOrders();
    }

    public void insert(Order order){
        repository.insert(order);
    }

    public void update(Order order){
        repository.update(order);
    }

    public void delete(Order order){
        repository.delete(order);
    }

    public void deleteAllOrders(){
        repository.deleteAllOrders();
    }

    public LiveData<List<Order>> getAllOrders(){
        return allOrders;
    }

    public LiveData<List<Order>> getSortedByOrderNumberDESC(){
        return repository.getSortedByOrderNumberDESC();
    }

    public LiveData<List<Order>> getSortedByOrderNumberASC() {
        return repository.getSortedByOrderNumberASC();
    }

    public LiveData<List<Order>> getSortedByDateDESC(){
        return repository.getSortedByDateDESC();
    }

    public LiveData<List<Order>> getSortedByDateASC(){
        return repository.getSortedByDateASC();
    }

    public LiveData<List<Order>> getSortedByPriceDESC() {
        return repository.getSortedByPriceDESC();
    }

    public LiveData<List<Order>> getSortedByPriceASC(){
        return repository.getSortedByPriceASC();
    }

    public LiveData<List<Order>> getOrderThroughPriceList(String order_through, String date){
        return repository.getOrderThroughPriceList(order_through, date);
    }

    public LiveData<List<Order>> getOrderThroughPriceList(String order_through){
        return repository.getOrderThroughPriceList(order_through);
    }

    public LiveData<List<Order>> getDailyOrdesList(String date){
        return repository.getDailyOrdesList(date);
    }
}
