package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.OnRefreshViewListner;
import com.example.foodapp.R;
import com.example.foodapp.activity.OrderDetailActivity;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private Context context;
    private List<Order> orderList;
    private OnRefreshViewListner mRefreshListner;
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
        holder.orderIdText.setText(String.valueOf(order.getOrderID()));
        holder.orderDateText.setText(order.getOrderDate());
        holder.shipDateText.setText(order.getShipDate());
        holder.addressText.setText(order.getAddress());
        holder.statusText.setText(order.getStatus());

        holder.cancelButton.setOnClickListener(view -> cancelOrder(order.getOrderID()));
        holder.linearLayout.setOnClickListener(view -> toOrderDetail(order.getOrderID()));
    }
    private void cancelOrder(int orderId){
        OrderDBHelper orderDBHelper = new OrderDBHelper(context);
        boolean isSuccess =  orderDBHelper.removeOrder(orderId);
        Log.d("infoOrder", "is success : " + isSuccess);
        mRefreshListner.refreshView();

    }

    private void toOrderDetail(int orderId){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("order_id",String.valueOf(orderId));
        Log.d("infoOrder", "Thực hiện hành động chuyển sang order detail activity");
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
