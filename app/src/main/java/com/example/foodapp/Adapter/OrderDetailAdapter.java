package com.example.foodapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailViewHolder> {
    private final Context context;
    private final List<OrderDetail> orderDetails;
    private DAOProduct daoProduct;

    public OrderDetailAdapter(Context context, List<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
        daoProduct = new DAOProduct(this.context);
    }


    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.order_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetails.get(position);
        Product product = daoProduct.getProduct(orderDetail.getProductID());
        holder.productText.setText(product.getProductName());
        holder.quantityText.setText("Số lượng : " + orderDetail.getQuantity());
        holder.productPriceOneText.setText("Đơn giá : "  + product.getPrice());
        double totalPriceOneProduct = product.getPrice()*orderDetail.getQuantity();
        holder.producPriceTotalOnceText.setText("Tổng giá : " + totalPriceOneProduct);
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }


    public  double getTotalPrice(){
        double total = 0;
        for (int i = 0; i < getItemCount(); i++) {
            OrderDetail orderDetail = orderDetails.get(i);
            Product product = daoProduct.getProduct(orderDetail.getProductID());
            total += (orderDetail.getQuantity() * product.getPrice());
        }
        return total;
    }
}
