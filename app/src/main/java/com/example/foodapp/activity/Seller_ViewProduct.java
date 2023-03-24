package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.foodapp.Adapter.ViewProductAdapter;
import com.example.foodapp.Entity.Category;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.ManageOrderActivity;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller_ViewProduct extends AppCompatActivity {
    public static ListView listView;
    private Spinner spinnerSearch;
    private ViewProductAdapter adapter;
    private final Map<String, Integer> map = new HashMap<>();
    private List<Category> listCategory;
    private List<Product> listProduct;
    private final List<String> listName = new ArrayList<>();
    private final DAOCategory daoCategory = new DAOCategory(this);
    private final DAOProduct daoProduct = new DAOProduct(this);
    private ArrayAdapter<String> adapterArr;
    private Integer CategoryID = null;
    private Button buttonAdd;
    private LinearLayout logout;
    private LinearLayout managerOrder;
    private LinearLayout account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_view_product);
        listView = findViewById(R.id.list);
        spinnerSearch = findViewById(R.id.spinner_search);
        buttonAdd = findViewById(R.id.btnAdd);
        logout = findViewById(R.id.LogOut);
        managerOrder = findViewById(R.id.manage_order);
        account = findViewById(R.id.view_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(Seller_ViewProduct.this , UserInfoActivity.class));
            }
        });
        setDataSearch();
        setMap();
        DisplayListProduct(CategoryID);
        setSelectedSearch();
        setSelectedItem();
        AddProductActivity();
        Logout();
        ManagerOrder();
    }
    private void ManagerOrder(){
        managerOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Seller_ViewProduct.this, ManageOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public  void startActivity(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }

    private void Logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(Seller_ViewProduct.this);
                sessionManager.logoutUser();
                Intent intent = new Intent(Seller_ViewProduct.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AddProductActivity(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Seller_ViewProduct.this , Seller_AddActivity.class));
            }
        });
    }

    private void setSelectedItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) adapter.getItem(i);
                Seller_Edit_DeleteProduct.ProductID = product.getProductID();
                startActivity(new Intent(Seller_ViewProduct.this,Seller_Edit_DeleteProduct.class));
            }
        });

    }

    private void setDataSearch(){
        listCategory = daoCategory.getAllCategory();
        listName.add("ALL");
        for(Category category: listCategory){
            listName.add(category.getName());
        }
        adapterArr =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listName);
        adapterArr.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerSearch.setAdapter(adapterArr);
    }


    private void setMap(){
        for(Category category: listCategory){
            map.put(category.getName(),category.getID());
        }
    }
    private void DisplayListProduct(Integer CategoryID){
        listProduct = daoProduct.getListProduct(CategoryID);
        adapter = new ViewProductAdapter(listProduct);
        listView.setAdapter(adapter);
    }

    private void setSelectedSearch(){
        spinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryID = map.get(listName.get(i));
                spinnerSearch.setSelection(adapterArr.getPosition(listName.get(i)));
                DisplayListProduct(CategoryID);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



}