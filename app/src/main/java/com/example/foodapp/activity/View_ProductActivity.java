package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.foodapp.Entity.Adapter.ProductAdapter;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View_ProductActivity extends AppCompatActivity {
    private ListView view;
    private Spinner spinner;
    private ProductAdapter adapter;
    private final Map<String, Integer> map = new HashMap<>();
    private final List<Category> listCategory = new ArrayList<>();
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final DAOProduct daoProduct = new DAOProduct(this);


    private void setMap(){
        for(Category category: listCategory){
            map.put(category.getName(),category.getID());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        setMap();
    }
}