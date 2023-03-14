package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
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
        holder.product_image.setImageResource(productList.get(position).getImage());
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
            ProductName = itemView.findViewById(R.id.tv_proName);
            price = itemView.findViewById(R.id.tv_pprice);
            cateName = itemView.findViewById(R.id.tv_cateName);
            product_image = itemView.findViewById(R.id.pro_img);
            btn_addproduct = itemView.findViewById(R.id.btn_AddProduct);
        }
    }
    // đừng sửa gì hay xóa gì trong này nhé đức
}
