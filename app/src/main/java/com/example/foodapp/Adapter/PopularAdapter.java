package com.example.foodapp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Product;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private final Context context;
    List<Product> productList;
    private onProductItemClick onProductItemClick;

    public PopularAdapter(Context context, List<Product> productList, com.example.foodapp.onProductItemClick onProductItemClick) {
        this.context = context;
        this.productList = productList;
        this.onProductItemClick = onProductItemClick;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.title.setText(productList.get(position).getProductName());
        holder.product_img.setImageResource(productList.get(position).getImage());
        holder.price.setText(String.valueOf(productList.get(position).getPrice()));
        holder.id.setText(String.valueOf(productList.get(position).getProductID()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, btn_add, id;
        ImageView product_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            price = itemView.findViewById(R.id.tv_fee);
            product_img = itemView.findViewById(R.id.product_img);
            btn_add = itemView.findViewById(R.id.btn_Add);
            id = itemView.findViewById(R.id.tv_id);

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   onProductItemClick.onProductClick(Integer.parseInt(id.getText().toString()), "AddToCart");

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(Integer.parseInt(id.getText().toString()), "FoodDetail");
                }
            });

        }
    }
}
