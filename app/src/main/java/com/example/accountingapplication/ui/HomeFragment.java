package com.example.accountingapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.viewModel.OrderViewModel;
import com.example.accountingapplication.R;
import com.example.accountingapplication.adapter.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final int ADD_ORDER_REQUEST = 1;
    public static final int EDIT_ORDER_REQUEST = 2;
    public static final int RESULT_OK = -1;

    private OrderViewModel orderViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ImageView mImageViewSort;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.button_add_order);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEditOrderActivity.class);
                startActivityForResult(intent, ADD_ORDER_REQUEST);
            }
        });

        mImageViewSort = view.findViewById(R.id.image_view_home_Appbar_image);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

        orderViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getActivity().getApplication()))
                .get(OrderViewModel.class);

        orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                //Toast.makeText(getContext(),"Size "+orders.size(),Toast.LENGTH_SHORT).show();
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                Intent intent = new Intent(getContext(), AddEditOrderActivity.class);
                intent.putExtra(AddEditOrderActivity.EXTRA_ID, order.getId());
                intent.putExtra(AddEditOrderActivity.EXTRA_ORDER_NUMBER, order.getOrder_number());
                intent.putExtra(AddEditOrderActivity.EXTRA_PRICE, order.getPrice());
                intent.putExtra(AddEditOrderActivity.EXTRA_DELIVERY, order.getDelivery());
                intent.putExtra(AddEditOrderActivity.EXTRA_PAYMENT_METHOD, order.getPayment_method());
                intent.putExtra(AddEditOrderActivity.EXTRA_ORDER_THROUGH, order.getOrder_through());
                intent.putExtra(AddEditOrderActivity.EXTRA_DATE_TIME, order.getDate_time());
                startActivityForResult(intent, EDIT_ORDER_REQUEST);
            }
        });

        mImageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.sort();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == ADD_ORDER_REQUEST && resultCode == RESULT_OK) {
            int order_number = data.getIntExtra(AddEditOrderActivity.EXTRA_ORDER_NUMBER, -1);
            int price = data.getIntExtra(AddEditOrderActivity.EXTRA_PRICE, 0);
            String delivery = data.getStringExtra(AddEditOrderActivity.EXTRA_DELIVERY);
            String payment_method = data.getStringExtra(AddEditOrderActivity.EXTRA_PAYMENT_METHOD);
            String order_through = data.getStringExtra(AddEditOrderActivity.EXTRA_ORDER_THROUGH);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss a");
            String dateTime = simpleDateFormat.format(calendar.getTime());

            //Toast.makeText(getContext(), "Date: " + dateTime, Toast.LENGTH_LONG).show();

            Order order = new Order(order_number, price,delivery , payment_method,order_through,dateTime);
            orderViewModel.insert(order);

            Toast.makeText(getContext(), "Order saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_ORDER_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(AddEditOrderActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(getContext(), "Order can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            int order_number = data.getIntExtra(AddEditOrderActivity.EXTRA_ORDER_NUMBER, -1);
            int price = data.getIntExtra(AddEditOrderActivity.EXTRA_PRICE, 0);
            String delivery = data.getStringExtra(AddEditOrderActivity.EXTRA_DELIVERY);
            String payment_method = data.getStringExtra(AddEditOrderActivity.EXTRA_PAYMENT_METHOD);
            String order_through = data.getStringExtra(AddEditOrderActivity.EXTRA_ORDER_THROUGH);
            String date_time = data.getStringExtra(AddEditOrderActivity.EXTRA_DATE_TIME);

            Order order = new Order(order_number, price, delivery, payment_method, order_through, date_time);
            order.setId(id);
            orderViewModel.update(order);

            Toast.makeText(getContext(), "Order updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Order not saved", Toast.LENGTH_SHORT).show();
        }
    }

    public static CharSequence format(CharSequence inFormat, Date inDate) {

        return inFormat;
    }

    public void sortedByOrderNumberDecs() {
        orderViewModel.getSortedByOrderNumberDESC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                //Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sortedByOrderNumberAcs() {
        orderViewModel.getSortedByOrderNumberASC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                // Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sortedByDateDecs() {
        orderViewModel.getSortedByDateDESC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                // Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sortedByDateAcs() {
        orderViewModel.getSortedByDateASC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                // Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sortedByPriceDecs() {
        orderViewModel.getSortedByPriceDESC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                // Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sortedByPriceAcs() {
        orderViewModel.getSortedByPriceASC().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                recyclerViewAdapter.setOrderList(orders);
                recyclerViewAdapter.notifyDataSetChanged();
                //Toast.makeText(getContext(), "Sorted", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
