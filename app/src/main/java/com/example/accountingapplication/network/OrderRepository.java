package com.example.accountingapplication.network;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.database.OrderDatabase;

import java.util.List;

public class OrderRepository {
    private OrderDao orderDao;
    private LiveData<List<Order>> allOrders;

    public OrderRepository(Application application){
        OrderDatabase database = OrderDatabase.getInstance(application);
        orderDao = database.orderDao();
        allOrders = orderDao.getAllOrders();
    }

    public void insert(Order order){
        new InsertOrderAsyncTask(orderDao).execute(order);
    }

    public void update(Order order){
        new UpdateOrderAsyncTask(orderDao).execute(order);
    }

    public void delete(Order order){
        new DeleteOrderAsyncTask(orderDao).execute(order);
    }

    public void deleteAllOrders(){
        new DeleteAllOrderAsyncTask(orderDao).execute();
    }

    public LiveData<List<Order>> getAllOrders(){
        return allOrders;
    }

    public LiveData<List<Order>> getSortedByOrderNumberDESC(){
        return orderDao.getSortedByOrderNumberDESC();
    }

    public LiveData<List<Order>> getSortedByOrderNumberASC() {
        return orderDao.getSortedByOrderNumberASC();
    }

    public LiveData<List<Order>> getSortedByDateDESC(){
        return orderDao.getSortedByDateDESC();
    }

    public LiveData<List<Order>> getSortedByDateASC(){
        return orderDao.getSortedByDateASC();
    }

    public LiveData<List<Order>> getSortedByPriceDESC() {
        return orderDao.getSortedByPriceDESC();
    }

    public LiveData<List<Order>> getSortedByPriceASC(){
        return orderDao.getSortedByPriceASC();
    }

    public LiveData<List<Order>> getOrderThroughPriceList(String order_through, String date){
        return orderDao.getOrderThroughPriceList(order_through, date);
    }

    public LiveData<List<Order>> getOrderThroughPriceList(String order_through){
        return orderDao.getOrderThroughPriceList(order_through);
    }

    public LiveData<List<Order>> getDailyOrdesList(String date){
        return orderDao.getDailyOrdesList(date);
    }

    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void> {

        private OrderDao orderDao;

        private InsertOrderAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void>{

        private OrderDao orderDao;

        private UpdateOrderAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.update(orders[0]);
            return null;
        }
    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Order, Void, Void>{

        private OrderDao orderDao;

        private DeleteOrderAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.delete(orders[0]);
            return null;
        }
    }

    private static class DeleteAllOrderAsyncTask extends AsyncTask<Void, Void, Void>{

        private OrderDao orderDao;

        private DeleteAllOrderAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDao.deleteAll();
            return null;
        }
    }
}
