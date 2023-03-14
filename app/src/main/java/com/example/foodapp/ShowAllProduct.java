package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.Adapter.ProductAdapter;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOProduct;

import java.util.List;

public class ShowAllProduct extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    RecyclerView rv_allProduct;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_product);
        recyclerViewAllProduct();
    }
    private void recyclerViewAllProduct(){
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_allProduct = findViewById(R.id.rv_allProdcut);
        rv_allProduct.setLayoutManager(linearLayoutManager);
        DAOProduct daoProduct = new DAOProduct(this);
        productList = daoProduct.ListProduct();
        adapter = new ProductAdapter(this, productList);
        rv_allProduct.setAdapter(adapter);
    }
}