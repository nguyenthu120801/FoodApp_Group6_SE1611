package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.Entity.Food;
import com.example.foodapp.Entity.Product;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {
    public double total;
    public List<Product> productList;
    private onChangeItem onChangeItem;

    public FoodAdapter(double total, List<Product> productList, com.example.foodapp.onChangeItem onChangeItem) {
        this.total = total;
        this.productList = productList;
        this.onChangeItem = onChangeItem;
    }

    class FoodHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView tv_foodName;
        public TextView tv_price;
        public TextView tv_TotlaPrice;
        TextView tv_quantity;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cartFood);
            tv_foodName = itemView.findViewById(R.id.tv_cartFoodName);
            tv_price = itemView.findViewById(R.id.tv_foodPrice);
            tv_TotlaPrice = itemView.findViewById(R.id.tv_totalPriceFood);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);


            itemView.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int newQuantity = Integer.parseInt(tv_quantity.getText().toString()) + 1;
                    tv_quantity.setText(String.valueOf(newQuantity));
                    tv_TotlaPrice.setText("$" + Double.parseDouble(tv_price.getText().toString()) * newQuantity);
                    total += Double.parseDouble(tv_price.getText().toString());
                    onChangeItem.onPriceChange(total);
                }
            });

            itemView.findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(tv_quantity.getText().toString()) > 1) {
                        tv_quantity.setText("1");
                        int newQuantity = Integer.parseInt(tv_quantity.getText().toString()) - 1;
                        tv_quantity.setText(String.valueOf(newQuantity));
                        tv_TotlaPrice.setText("$" + Double.parseDouble(tv_price.getText().toString()) * Integer.parseInt(tv_quantity.getText().toString()));
                        total -= Double.parseDouble(tv_price.getText().toString());
                        onChangeItem.onPriceChange(total);
                    }
                }
            });


        }
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_food_adapter, parent, false);
        return new FoodHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        holder.img.setImageResource(productList.get(position).getImage());
        holder.tv_foodName.setText(productList.get(position).getProductName());
        holder.tv_price.setText(String.valueOf(productList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



}