package com.example.foodapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.MainActivity;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.OnRefreshViewListner;
import com.example.foodapp.R;

import java.util.List;
import java.util.zip.Inflater;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private final Context context;
    private final List<Order> orderList;
    private final OnRefreshViewListner mRefreshListner;
    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
        mRefreshListner = (OnRefreshViewListner)context;
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
        holder.orderIdText.setText("Order ID : " + order.getOrderID());
        holder.orderDateText.setText("Order Date : " +order.getOrderDate());
        holder.shipDateText.setText("Ship Date : " +order.getShipDate());
        holder.addressText.setText("Address : " +order.getAddress());
        holder.statusText.setText("Status : " +order.getStatus());

        holder.cancelButton.setOnClickListener(view -> cancelOrder(order.getOrderID()));
    }
    private void cancelOrder(int orderId){
        OrderDBHelper orderDBHelper = new OrderDBHelper(context);
        boolean isSuccess =  orderDBHelper.removeOrder(orderId);
        Log.d("infoOrder", "is success : " + isSuccess);
        mRefreshListner.refreshView();

    }
    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
