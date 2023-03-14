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
import android.widget.ImageView;
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
import com.example.foodapp.activity.ListUserOrderActivity;
import com.example.foodapp.activity.OrderActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    ItemTouchHelper.SimpleCallback simpleCallback;
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

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(SessionManager.KEY_USERNAME);
        userID = new DAOUser(this).getIDUser(username);
        cartList = new DAOCart(this).getListCart(userID);
        rcv = findViewById(R.id.rv_category);
        int id = getIntent().getIntExtra("id", 0);
        LoadRecyclerView(cartList, id);


         simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                rcv.getAdapter();
                int n = new DAOCart(AddToCartActivity.this).DeleteCart(cartList.get(viewHolder.getAdapterPosition()).getCartID());
                if(n>0){
                    Toast.makeText(AddToCartActivity.this, "Remove successfully", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(AddToCartActivity.this, "Remove Fail", Toast.LENGTH_LONG).show();
                }
                cartList.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                LoadRecyclerView( new DAOCart(AddToCartActivity.this).getListCart(userID), -1);

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
       int orderid = orderDBHelper.insertOrder(order);
       Log.d("infoOrder", "order id vừa insert là : "+orderid);
       if (orderid != -1){
           Log.d("infoOrder","insert order successful" );
           cartList = new DAOCart(this).getListCart(sessionManager.getUserID());
           Log.d("infoOrder", "Số lượng cart là : " + cartList.size());
           OrderDetail orderDetail = new OrderDetail();
           for (Cart cart : cartList){
               Log.d("infoOrder", "Cart : "+ cart);
               orderDetail.setOrderID(orderid);
               orderDetail.setProductID(cart.getProductID());
               orderDetail.setQuantity(cart.getQuantity());
               Log.d("infoOrder", "Order Detail add vào db : " + orderDetail);
               daoOrderDetail.AddOrderDetail(orderDetail);
           }
//        for (Product product: productList) {
//            orderDetail.setOrderID(order.getOrderID());
//            orderDetail.setProductID(product.getProductID());
//        }
              Log.d("infoOrder","insert order detail successful" );
              Intent intent = new Intent(this, ListUserOrderActivity.class);
              startActivity(intent);
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
        int cartID = 0;
        List<Product> productList = new ArrayList<>();
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
                long n = new DAOCart(this).AddCart(new Cart(userID, product.getProductID(), 1));
                cartList.add(new Cart(new DAOCart(this).getMaxCartID(),userID, product.getProductID(), 1));
            }
            for(int i =0; i< cartList.size(); i++) {
                Log.d("CartID:", String.valueOf(cartList.get(i).getCartID()));
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
            ((ImageView)findViewById(R.id.imv_cartEmpty)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.btn_checkout)).setVisibility(View.GONE);
            tv_total.setText("$0");
            rcv.setVisibility(View.GONE);
        }



    }
}