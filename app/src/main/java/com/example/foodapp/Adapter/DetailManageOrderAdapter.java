package com.example.foodapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.List;

public class DetailManageOrderAdapter extends RecyclerView.Adapter<DetailManageOrderAdapter.ViewHolder> {
    public  Context context;
    public  List<OrderDetail> orderDetails;
    private DAOProduct daoProduct ;

    public DetailManageOrderAdapter(Context context, List<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_manage_order, parent, false);
        return new DetailManageOrderAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*final OrderDetail orderDetail = listOrderDetail.get(position);
        int OrderID = listOrderDetail.get(position).getOrderID();
        Product product = new DAOProduct(context).getProduct(OrderID);
        String name = product.getProductName();*/
        daoProduct = new DAOProduct(context);
        OrderDetail orderDetail = orderDetails.get(position);
        Product product = daoProduct.getProduct(orderDetail.getProductID());
        Log.d("ABC",String.valueOf(orderDetails.size()));
        holder.name.setText(product.getProductName());
        holder.quantity.setText("Số lượng : " + orderDetail.getQuantity());
        holder.quantity.setText("Đơn giá : "  + product.getPrice());
        double totalPriceOneProduct = product.getPrice()*orderDetail.getQuantity();
        holder.totalPrice.setText("Tổng giá : " + totalPriceOneProduct);



    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, quantity, price, totalPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameProduct);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            totalPrice = itemView.findViewById(R.id.totalPrice);


        }
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
