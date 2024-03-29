package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    List<Product> productList;
    private final onProductItemClick onProductItemClick;

    public ProductAdapter(Context context, List<Product> productList, onProductItemClick onProductItemClick) {
        this.context = context;
        this.productList = productList;
        this.onProductItemClick = onProductItemClick;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_product, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(productList.get(position).getProductID()));
        Glide.with(holder.itemView.getContext()).load(productList.get(position).getImage().trim()).into(holder.product_image);
        holder.ProductName.setText(productList.get(position).getProductName());
        holder.price.setText(String.valueOf(productList.get(position).getPrice()));
        holder.cateName.setText(new DAOCategory(context).getCategoryName(productList.get(position).getCategoryID()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName, price,cateName, btn_addproduct, tv_id;
        ImageView product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            ProductName = itemView.findViewById(R.id.tv_product_name);
            price = itemView.findViewById(R.id.tv_product_price);
            cateName = itemView.findViewById(R.id.tv_category);
            product_image = itemView.findViewById(R.id.viewImage);
            btn_addproduct = itemView.findViewById(R.id.btn_Delete);

            btn_addproduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(Integer.parseInt(tv_id.getText().toString()), "AddToCart");

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(Integer.parseInt(tv_id.getText().toString()), "FoodDetail");
                }
            });
        }
    }
    // đừng sửa gì hay xóa gì trong này nhé đức
}
