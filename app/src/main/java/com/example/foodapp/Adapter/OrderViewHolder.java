package com.example.foodapp.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public TextView orderIdText,addressText, orderDateText,shipDateText, statusText;
    public LinearLayout linearLayout,shipDateLayout;
    public ImageView statusImage;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        orderIdText = itemView.findViewById(R.id.order_id_text);
        orderDateText = itemView.findViewById(R.id.order_date_text);
        shipDateText = itemView.findViewById(R.id.ship_date_text);
        addressText = itemView.findViewById(R.id.address_text);
        statusText = itemView.findViewById(R.id.status_text);
        linearLayout = itemView.findViewById(R.id.order_all_view);
        shipDateLayout = itemView.findViewById(R.id.ship_date_layout);
        statusImage = itemView.findViewById(R.id.status_image);

    }
}
