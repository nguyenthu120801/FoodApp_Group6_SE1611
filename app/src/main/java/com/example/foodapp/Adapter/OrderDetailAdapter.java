package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailViewHolder> {
    private Context context;
    private List<OrderDetail> orderDetails;

    public OrderDetailAdapter(Context context, List<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.order_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
            OrderDetail orderDetail = orderDetails.get(position);
            holder.productText.setText("Product id : "+orderDetail.getProductID());
            holder.quantityText.setText("Quantity : " + orderDetail.getQuantity());
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }
}
