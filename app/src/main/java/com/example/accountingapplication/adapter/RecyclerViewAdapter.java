package com.example.accountingapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accountingapplication.dataModel.Order;
import com.example.accountingapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OrderHolder> {
    private List<Order> orderList = new ArrayList<>();
    private OnItemClickListener listener;


//    public RecyclerViewAdapter(List<Order> orderList) {
//        this.orderList = orderList;
//    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Order currentOrder = orderList.get(position);
        holder.textViewOrderNumber.setText(String.valueOf(currentOrder.getOrder_number()));
        holder.textViewPrice.setText(String.valueOf(currentOrder.getPrice()));
        holder.textViewDelivery.setText(currentOrder.getDelivery());
        holder.textViewPaymentMethod.setText(currentOrder.getPayment_method());
        holder.textViewOrderThrough.setText(currentOrder.getOrder_through());
        holder.textViewDateTime.setText(currentOrder.getDate_time());
    }

    @Override
    public int getItemCount() {
        if (orderList == null) {
            return 0;
        } else {
            return orderList.size();
        }
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public Order getOrderAt(int position) {
        return orderList.get(position);
    }

    class OrderHolder extends RecyclerView.ViewHolder {

        private TextView textViewOrderNumber;
        private TextView textViewDelivery;
        private TextView textViewPrice;
        private TextView textViewPaymentMethod;
        private TextView textViewOrderThrough;
        private TextView textViewDateTime;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            textViewOrderNumber = itemView.findViewById(R.id.text_view_orderNumber);
            textViewDelivery = itemView.findViewById(R.id.text_view_Delivery);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            textViewPaymentMethod = itemView.findViewById(R.id.text_view_payment_method);
            textViewOrderThrough = itemView.findViewById(R.id.text_view_order_through);
            textViewDateTime = itemView.findViewById(R.id.text_view_dateTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(orderList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
