package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Adapter.PopularAdapter;
import com.example.foodapp.Adapter.SearchAdapter;
import com.example.foodapp.Entity.Category;

import java.util.HashMap;
import java.util.List;

import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCategory;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.R;
import com.example.foodapp.onProductItemClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements onProductItemClick {
    private RecyclerView.Adapter adapter, adapter1, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList,rv_search_product;
    ImageView img_user;
    LinearLayout logout;
    TextView tv_welcome;
    List<Category> catList;
    List<Product> productList;
    String username;
    TextView tv_showProduct;

    TextView tv_search;
    EditText edt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout = findViewById(R.id.LogOut);
        img_user = findViewById(R.id.img_user);
        tv_welcome = findViewById(R.id.tv_welcome);
        tv_showProduct = findViewById(R.id.tv_ShowProduct);
        recyclerViewCategory();
        recyclerViewPopular();
        // session
        SessionManager sessionManager = new SessionManager(MainActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        username = user.get(SessionManager.KEY_USERNAME);
        if (username != null) {
            tv_welcome.setText("Welcome, " + username);
        }
        // request Login
        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        //request Show All Product
        tv_showProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowAllProduct.class));
            }
        });

        // Request LogOut
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(MainActivity.this);
                sessionManager.logoutUser();
                tv_welcome.setText("Order & Eat");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton toOrderBtn = findViewById(R.id.to_order_btn);
        toOrderBtn.setOnClickListener(view -> toOrder());

        tv_search = findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog searchDialog = new Dialog(MainActivity.this);
                searchDialog.setContentView(R.layout.dialog_search_product);
                RecyclerViewSearch(searchDialog);
                EditText searchEditText = searchDialog.findViewById(R.id.edt_search);
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        String query = s.toString().trim();
                        ((Filterable) adapter2).getFilter().filter(query);
                    }
                });
                searchDialog.show();
            }
        });
    }
    private void RecyclerViewSearch(Dialog searchDialog) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_search_product = searchDialog.findViewById(R.id.rv_search_product);
        rv_search_product.setLayoutManager(linearLayoutManager);
        DAOProduct daoProduct = new DAOProduct(this);
        productList = daoProduct.ListProduct();
        adapter2 = new SearchAdapter(this,productList, this::onProductClick);
        rv_search_product.setAdapter(adapter2);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_search_product.addItemDecoration(itemDecoration);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.rv_category);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        DAOCategory daoCategory = new DAOCategory(this);
        catList = daoCategory.getAllCategory();
        adapter = new CategoryAdapter(this, catList);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.rv_popular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        DAOProduct daoProduct = new DAOProduct(this);
        productList = daoProduct.get5Product();
        adapter1 = new PopularAdapter(this, productList, this::onProductClick);
        recyclerViewPopularList.setAdapter(adapter1);
    }

    public void toOrder() {
        if (username == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }else {
            Intent intent = new Intent(MainActivity.this, AddToCartActivity.class);
            startActivity(intent);
        }
    }

    public void toListOrder(View view) {
        Intent intent = new Intent(MainActivity.this, ListUserOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public void onProductClick(int id, String activity) {
        if (username == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            if (activity.equals("AddToCart")) {
                Intent intent = new Intent(this, AddToCartActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }
        if(activity.equals("FoodDetail")) {
                Intent intent = new Intent(this, FoodDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

    }
}