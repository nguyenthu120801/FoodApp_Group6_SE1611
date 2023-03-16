package com.example.foodapp.Adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.R;

import java.util.List;

public class ViewProductAdapter extends BaseAdapter {
    private List<Product> list;

    public ViewProductAdapter(List<Product> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getProductID();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view =  View.inflate(viewGroup.getContext(), R.layout.item_product, null);
        }
        Product product = (Product) getItem(i);
        TextView textName = view.findViewById(R.id.text_product_name);
        TextView textPrice = view.findViewById(R.id.text_price);
        ImageView image = view.findViewById(R.id.viewImage);
        textName.setText(product.getProductName());
        textPrice.setText(product.getPrice() + "$");
        Drawable drawable = viewGroup.getContext().getDrawable(R.drawable.logo);
        Glide.with(viewGroup.getContext())
                .load(product.getImage())
                .into(image);
        return view;
    }
}
