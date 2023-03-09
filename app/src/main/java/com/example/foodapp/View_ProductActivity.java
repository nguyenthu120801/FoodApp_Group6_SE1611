package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.foodapp.Adapter.ProductAdapter;
import com.example.foodapp.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class View_ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Pho bo","",5.5,1));
        list.add(new Product(2,"Pho ga","",6.5,1));
        ListView view = findViewById(R.id.list_view);
        ProductAdapter adapter = new ProductAdapter(list);
        view.setAdapter(adapter);
    }
}