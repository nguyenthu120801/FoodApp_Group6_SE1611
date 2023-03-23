package com.example.foodapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
    TextView productText,quantityText, productPriceOneText, producPriceTotalOnceText;
    public OrderDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        productText = itemView.findViewById(R.id.detail_productID);
        quantityText = itemView.findViewById(R.id.detail_product_quantity);
        productPriceOneText = itemView.findViewById(R.id.detail_product_price_once);
        producPriceTotalOnceText = itemView.findViewById(R.id.detail_product_price_total_once);
    }
}
