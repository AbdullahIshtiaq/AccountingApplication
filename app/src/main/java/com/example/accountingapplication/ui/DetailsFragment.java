package com.example.accountingapplication.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountingapplication.R;
import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.viewModel.OrderViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailsFragment extends Fragment {

    private OrderViewModel orderViewModel;
    private TextView textView_TotalOrders, textView_Delivero, textView_Total_UberEats,
            textView_Sale, textView_JustEats, textView_Balance;
    private TextView textView_TodayOrders, textView_today_Delivero, textView_today_UberEats,
            textView_today_Sale, textView_today_JustEats, textView_today_Balance;
    private List<Order> myList;
    private double total_sale = 0, uberEats_sum = 0, justEats_sum = 0, delivero_sum = 0, balance = 0,
            today_sale = 0, today_uberEats_sum = 0, today_justEats_sum = 0, today_delivero_sum = 0, today_balance = 0;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        textView_TotalOrders = view.findViewById(R.id.text_view_total_order);
        textView_Sale = view.findViewById(R.id.text_view_total_sale);
        textView_Delivero = view.findViewById(R.id.text_view_total_delivero);
        textView_Total_UberEats = view.findViewById(R.id.text_view_total_uber_eats);
        textView_JustEats = view.findViewById(R.id.text_view_total_justEats);
        textView_Balance = view.findViewById(R.id.text_view_total_balance);

        textView_TodayOrders = view.findViewById(R.id.text_view_todays_order);
        textView_today_Sale = view.findViewById(R.id.text_view_today_sale);
        textView_today_Delivero = view.findViewById(R.id.text_view_today_delivero);
        textView_today_UberEats = view.findViewById(R.id.text_view_today_uber_eats);
        textView_today_JustEats = view.findViewById(R.id.text_view_today_justEats);
        textView_today_Balance = view.findViewById(R.id.text_view_today_balance);

        orderViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getActivity().getApplication()))
                .get(OrderViewModel.class);

        orderViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                textView_TotalOrders.setText(Integer.toString(orders.size()));
                myList = orders;
                total_sale = 0;
                for (int i = 0; i < myList.size(); i++) {
                    total_sale = total_sale + myList.get(i).getPrice();
                }
                // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                textView_Sale.setText(Double.toString(total_sale));

                orderViewModel.getOrderThroughPriceList("Uber Eats").observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                    @Override
                    public void onChanged(List<Order> orders) {
                        myList = orders;
                        uberEats_sum = 0;
                        for (int i = 0; i < myList.size(); i++) {
                            uberEats_sum = uberEats_sum + (myList.get(i).getPrice()) * 0.30;
                        }
                        // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                        textView_Total_UberEats.setText(Double.toString(uberEats_sum));

                        orderViewModel.getOrderThroughPriceList("Delivero").observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                            @Override
                            public void onChanged(List<Order> orders) {
                                myList = orders;
                                delivero_sum = 0;
                                for (int i = 0; i < myList.size(); i++) {
                                    delivero_sum = delivero_sum + (myList.get(i).getPrice()) * 0.35;
                                }
                                // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                                textView_Delivero.setText(Double.toString(delivero_sum));

                                orderViewModel.getOrderThroughPriceList("Just Eats").observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                                    @Override
                                    public void onChanged(List<Order> orders) {
                                        myList = orders;
                                        justEats_sum = 0;
                                        for (int i = 0; i < myList.size(); i++) {
                                            justEats_sum = justEats_sum + (myList.get(i).getPrice()) * 0.20;
                                        }
                                        // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                                        textView_JustEats.setText(Double.toString(justEats_sum));

                                        balance = total_sale - (uberEats_sum + justEats_sum + delivero_sum);
                                        //Toast.makeText(getContext(), "Balance: " + balance, Toast.LENGTH_LONG).show();
                                        textView_Balance.setText(Double.toString(balance));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        final String finaldate = date + "%";

        orderViewModel.getDailyOrdesList(finaldate).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                myList = orders;
                //Toast.makeText(getContext(), "Size: "+ myList.size(), Toast.LENGTH_LONG).show();
                textView_TodayOrders.setText(Integer.toString(myList.size()));
                today_sale = 0;
                for (int i = 0; i < myList.size(); i++) {
                    today_sale = today_sale + myList.get(i).getPrice();
                }
                // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                textView_today_Sale.setText(Double.toString(today_sale));

                orderViewModel.getOrderThroughPriceList("Uber Eats", finaldate).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                    @Override
                    public void onChanged(List<Order> orders) {
                        myList = orders;
                        today_uberEats_sum = 0;
                        for (int i = 0; i < myList.size(); i++) {
                            today_uberEats_sum = today_uberEats_sum + (myList.get(i).getPrice()) * 0.30;
                        }
                        // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                        textView_today_UberEats.setText(Double.toString(today_uberEats_sum));

                        orderViewModel.getOrderThroughPriceList("Delivero", finaldate).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                            @Override
                            public void onChanged(List<Order> orders) {
                                myList = orders;
                                today_delivero_sum = 0;
                                for (int i = 0; i < myList.size(); i++) {
                                    today_delivero_sum = today_delivero_sum + (myList.get(i).getPrice()) * 0.35;
                                }
                                // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                                textView_today_Delivero.setText(Double.toString(today_delivero_sum));

                                orderViewModel.getOrderThroughPriceList("Just Eats", finaldate).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
                                    @Override
                                    public void onChanged(List<Order> orders) {
                                        myList = orders;
                                        today_justEats_sum = 0;
                                        for (int i = 0; i < myList.size(); i++) {
                                            today_justEats_sum = today_justEats_sum + (myList.get(i).getPrice()) * 0.20;
                                        }
                                        // Toast.makeText(getContext(), "Sum: "+ Sum, Toast.LENGTH_LONG).show();
                                        textView_today_JustEats.setText(Double.toString(today_justEats_sum));

                                        today_balance = today_sale - (today_uberEats_sum + today_justEats_sum + today_delivero_sum);
                                        //Toast.makeText(getContext(), "Balance: " + balance, Toast.LENGTH_LONG).show();
                                        textView_today_Balance.setText(Double.toString(today_balance));
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        return view;
    }
}