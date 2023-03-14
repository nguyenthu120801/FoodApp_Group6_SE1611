package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Entity.Cart;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Model.DAOCart;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    TextView tv_notification;
    List<Product> productList = new ArrayList<>();
    List<Cart> cartList = new ArrayList<>();
    OrderDBHelper orderDBHelper;
    DAOOrderDetail daoOrderDetail;
    Button checkoutBtn;
    SessionManager sessionManager;
    FoodAdapter adapter;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        sessionManager = new SessionManager(this);
        tv_total = findViewById(R.id.tv_totalPrice);
        tv_notification = findViewById(R.id.tv_noti);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(SessionManager.KEY_USERNAME);
        userID = 2;
       // int userID = new DAOUser(this).getIDUser(username);
        cartList = new DAOCart(this).getListCart(userID);
        rcv = findViewById(R.id.rv_category);
        int id = getIntent().getIntExtra("id", 0);
        LoadRecyclerView(cartList, id);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new DAOCart(AddToCartActivity.this).DeleteCart(cartList.get(viewHolder.getAdapterPosition()).getCartID());
                cartList.remove(viewHolder.getAdapterPosition());
                productList.clear();
                LoadRecyclerView( new DAOCart(AddToCartActivity.this).getListCart(userID), -1);
                adapter.notifyDataSetChanged();
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcv);

        ((Button)findViewById(R.id.btn_checkout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToCartActivity.this, OrderActivity.class);
            }
        });
       orderDBHelper = new OrderDBHelper(this);
       daoOrderDetail = new DAOOrderDetail(this);
        checkoutBtn = findViewById(R.id.btn_checkout);
        checkoutBtn.setOnClickListener(view -> checkout());

    }

    private void checkout(){
        Order order = new Order();
        order.setUserID(sessionManager.getUserID());
        order.setOrderDate(new Date().toString());
        order.setStatus(Order.STATUS_IN_PROGRESS);
        order.setAddress("Fixed address");
       boolean isSuccessOrder = orderDBHelper.insertOrder(order);
       if (isSuccessOrder){
           Toast.makeText(this, "insert order successful", Toast.LENGTH_SHORT).show();
           cartList = new DAOCart(this).getListCart(2);
           OrderDetail orderDetail = new OrderDetail();
           for (Cart cart : cartList){
               orderDetail.setOrderID(order.getOrderID());
               orderDetail.setProductID(cart.getProductID());
               orderDetail.setQuantity(cart.getQuantity());
           }
//        for (Product product: productList) {
//            orderDetail.setOrderID(order.getOrderID());
//            orderDetail.setProductID(product.getProductID());
//        }
          long isSuccessOrderDetails = daoOrderDetail.AddOrderDetail(orderDetail);
          if(isSuccessOrderDetails != -1){
              Log.d("infoOrder","insert order detail success" );
          }else {
              Log.d("infoOrder","insert order detail fail" );
          }
       }else {
           Log.d("infoOrder","insert order fail" );
       }


    }

    @Override
    public void onPriceChange(double price) {
        tv_total.setText("$" + price);
    }



    public void LoadRecyclerView(List<Cart> cartList, int id){
        double total = 0;
        int pID = 0;

        Product product = new DAOProduct(this).getProduct(id);
        if(product != null) {
            for (Cart c : cartList) {
                if(c.getProductID() == id){
                    pID = id;
                    c.setQuantity(c.getQuantity() + 1);
                    int n = new DAOCart(this).UpdateCart(c);
                }
            }
            if(pID != id){
                cartList.add(new Cart(2, product.getProductID(), 1));
                new DAOCart(this).AddCart(new Cart(2, product.getProductID(), 1));
            }
        }
        if(cartList.size() != 0) {
            for (Cart c : cartList) {
                Product p = new DAOProduct(this).getProduct(c.getProductID());
                total += p.getPrice() * c.getQuantity();
                productList.add(p);
            }
            adapter = new FoodAdapter(cartList, total, productList, this);
            rcv.setLayoutManager(new LinearLayoutManager(this));
            rcv.setAdapter(adapter);
        }else{
            tv_notification.setText("Cart is empty, please buy food to continues");
            tv_notification.setVisibility(View.VISIBLE);
            tv_notification.setTextColor(Color.RED);
            ((Button)findViewById(R.id.btn_checkout)).setVisibility(View.GONE);
            tv_total.setText("$0");

        }


    }
}