package com.example.foodapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
    TextView productText,quantityText;
    public OrderDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        productText = itemView.findViewById(R.id.detail_productID);
        quantityText = itemView.findViewById(R.id.detail_product_quantity);
    }
}
