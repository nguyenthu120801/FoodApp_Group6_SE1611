package com.example.foodapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.R;

import java.util.List;
import java.util.zip.Inflater;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_view_order, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        Log.d("infoOrder", "vị trí : "+ position);
        Log.d("infoOrder", "order : "+ order);
        holder.orderIdText.setText(String.valueOf(order.getOrderID()));
        holder.userIdText.setText(String.valueOf(order.getUserID()));
        holder.orderDateText.setText(order.getOrderDate());
        holder.shipDateText.setText(order.getShipDate());
        holder.addressText.setText(order.getAddress());
        holder.statusText.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
