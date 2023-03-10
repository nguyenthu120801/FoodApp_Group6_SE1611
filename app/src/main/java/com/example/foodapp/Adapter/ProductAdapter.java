package com.example.foodapp.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodapp.Entity.Product;
import com.example.foodapp.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private final List<Product> list;

    public ProductAdapter(List<Product> list) {
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
        // if not create view
        if(view == null){
            // create view
            view = View.inflate(viewGroup.getContext(),R.layout.item_product,null);
        }
        Product product = (Product) getItem(i);
        TextView textProductName = view.findViewById(R.id.text_product_name);
        TextView textPrice = view.findViewById(R.id.text_price);
        textProductName.setText(product.getProductName());
        textPrice.setText(product.getPrice() + "");
        return view;
    }
}
