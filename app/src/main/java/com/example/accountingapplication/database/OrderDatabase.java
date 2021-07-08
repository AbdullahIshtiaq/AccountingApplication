package com.example.accountingapplication.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.network.OrderDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Database(entities = {Order.class}, version = 2, exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {

    private static OrderDatabase instance;

    public abstract OrderDao orderDao();

    public static synchronized OrderDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), OrderDatabase.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void , Void> {
        private OrderDao orderDao;
        private PopulateDbAsyncTask(OrderDatabase db){
            orderDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss a");
//            String dateTime = simpleDateFormat.format(calendar.getTime());
//            orderDao.insert(new Order(1, 720, "Delivery",
//                    "Card","Uber Eats",dateTime));
//            orderDao.insert(new Order(2, 540, "Take away",
//                    "Cash","None",dateTime));
//            orderDao.insert(new Order(3, 180, "Take away",
//                    "Cash","None",dateTime));
            return null;
        }
    }

}
