package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.OnRefreshViewListner;
import com.example.foodapp.R;
import com.example.foodapp.activity.OrderDetailActivity;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private final Context context;
    private final List<Order> orderList;
    private final OnRefreshViewListner mRefreshListner;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
        mRefreshListner = (OnRefreshViewListner) context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_view_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderIdText.setText("Order ID : " + order.getOrderID());
        holder.orderDateText.setText(order.getOrderDate());
        if (order.getShipDate() == null || order.getShipDate().trim().isEmpty()) {
            holder.shipDateLayout.setVisibility(View.GONE);
        } else {
            holder.shipDateText.setText(order.getShipDate());
        }
        holder.addressText.setText(order.getAddress());
        setDisplayStatus(order, holder);
//        setDisplayCancelButton(order, holder);
        holder.linearLayout.setOnClickListener(view -> toOrderDetail(order.getOrderID()));
    }

    private void setDisplayStatus(Order order, OrderViewHolder holder) {
        if (order.getStatus().equals(Order.STATUS_CANCELLED)) {
            holder.statusImage.setImageResource(R.drawable.rejected);
        } else if (order.getStatus().equals(Order.STATUS_SHIPPING)) {
            holder.statusImage.setImageResource(R.drawable.fast_shipping);
        } else if (order.getStatus().equals(Order.STATUS_COMPLETED)) {
            holder.statusImage.setImageResource(R.drawable.accept);
        } else {
            holder.statusImage.setImageResource(R.drawable.pending);
        }
        holder.statusText.setText(order.getStatus());
    }

//    private void setDisplayCancelButton(Order order, OrderViewHolder holder) {
//        boolean displayCancelButton = order.getStatus().equals(Order.STATUS_COMPLETED) || order.getStatus().equals(Order.STATUS_CANCELLED);
//        if (displayCancelButton) {
//            holder.cancelButton.setVisibility(View.GONE);
//        }else {
//            holder.cancelButton.setVisibility(View.VISIBLE);
//            holder.cancelButton.setOnClickListener(view -> cancelOrder(order.getOrderID()));
//        }
//    }

    private void cancelOrder(int orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm");
        builder.setMessage("Do you really want to delete order with id " + orderId + "?");
        builder.setPositiveButton("No", (dialog, which) -> {
            // Xử lý sự kiện khi người dùng chọn nút Không
        });
        builder.setNegativeButton("Yes", (dialog, which) -> {
            // Xử lý sự kiện khi người dùng chọn nút Có
            OrderDBHelper orderDBHelper = new OrderDBHelper(context);
            boolean isSuccess = orderDBHelper.removeOrder(orderId);
            Log.d("infoOrder", "is success : " + isSuccess);
            mRefreshListner.refreshView();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void toOrderDetail(int orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("order_id", String.valueOf(orderId));
        Log.d("infoOrder", "Thực hiện hành động chuyển sang order detail activity");
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
