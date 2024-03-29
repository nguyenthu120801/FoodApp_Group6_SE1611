package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Adapter.FoodAdapter;
import com.example.foodapp.Entity.Cart;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;
import com.example.foodapp.Entity.User;
import com.example.foodapp.Model.DAOCart;
import com.example.foodapp.Model.DAOOrderDetail;
import com.example.foodapp.Model.DAOProduct;
import com.example.foodapp.Model.DAOUser;
import com.example.foodapp.Model.OrderDBHelper;
import com.example.foodapp.R;
import com.example.foodapp.onChangeItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements onChangeItem {
    RecyclerView rcv;
    TextView tv_total;
    LinearLayout homebtn;
    ItemTouchHelper.SimpleCallback simpleCallback;
    List<Cart> cartList = new ArrayList<>();
    OrderDBHelper orderDBHelper;
    DAOOrderDetail daoOrderDetail;
    EditText addressText;
    DAOUser daoUser;
    Button checkoutBtn;
    SessionManager sessionManager;
    FoodAdapter adapter;
    int userID, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        sessionManager = new SessionManager(this);
        tv_total = findViewById(R.id.tv_totalPrice);
        addressText = findViewById(R.id.input_address_text);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String username = user.get(SessionManager.KEY_USERNAME);
        daoUser = new DAOUser(this);
        userID = daoUser.getIDUser(username);
        cartList = new DAOCart(this).getListCart(userID);
        rcv = findViewById(R.id.rv_category);
        int id = getIntent().getIntExtra("id", 0);
         quantity = getIntent().getIntExtra("quantity", 1);
        LoadRecyclerView(cartList, id);
        homebtn = findViewById(R.id.btn_homePage);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddToCartActivity.this, MainActivity.class));
            }
        });

        simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 new AlertDialog.Builder(AddToCartActivity.this)
                        .setMessage("Do you want to delete this product!")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int n = new DAOCart(AddToCartActivity.this).DeleteCart(cartList.get(viewHolder.getAdapterPosition()).getCartID());
                                if (n > 0) {
                                    Toast.makeText(AddToCartActivity.this, "Remove successfully", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(AddToCartActivity.this, "Remove Fail", Toast.LENGTH_LONG).show();
                                }
                                cartList.remove(viewHolder.getAdapterPosition());
                                adapter.notifyDataSetChanged();
                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                                LoadRecyclerView(new DAOCart(AddToCartActivity.this).getListCart(userID), -1);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                LoadRecyclerView(new DAOCart(AddToCartActivity.this).getListCart(userID),-1);
                            }
                        }).show();
            }
        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcv);


        orderDBHelper = new OrderDBHelper(this);
        daoOrderDetail = new DAOOrderDetail(this);
        checkoutBtn = findViewById(R.id.btn_checkout);
        checkoutBtn.setOnClickListener(view -> checkout());

    }

    private void checkout() {
        if (addressText.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notify");
            builder.setMessage("Please fill address!");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        double totalPrice = getTotalPrice();
        Log.d("infoOrder", "Total Price is : " + totalPrice);
        User user = daoUser.getUser(String.valueOf(userID));
        if(user.getMoney()<= totalPrice){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notify");
            builder.setMessage("Account don't have enough money to pay!");
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }
        new DAOCart(this).DeleteAllCart();
        User u = new DAOUser(this).getInfoUser(userID);
        new DAOUser(this).UpdateMoney(u.getMoney() - totalPrice,userID);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        user.setMoney(user.getMoney()- totalPrice);
        daoUser.updateUser(user);
        Order order = new Order();
        order.setUserID(userID);
        order.setOrderDate(dateFormat.format(new Date()));
        order.setStatus(Order.STATUS_NEW);
        order.setAddress(addressText.getText().toString());
        int orderid = orderDBHelper.insertOrder(order);
        Log.d("infoOrder", "order id vừa insert là : " + orderid);
        if (orderid != -1) {
            Log.d("infoOrder", "insert order successful");
            cartList = new DAOCart(this).getListCart(sessionManager.getUserID());
            Log.d("infoOrder", "Số lượng cart là : " + cartList.size());
            OrderDetail orderDetail = new OrderDetail();
            for (Cart cart : cartList) {
                orderDetail.setOrderID(orderid);
                orderDetail.setProductID(cart.getProductID());
                orderDetail.setQuantity(cart.getQuantity());
                Log.d("infoOrder", "Order Detail add vào db : " + orderDetail);
                daoOrderDetail.AddOrderDetail(orderDetail);
            }
            new DAOCart(this).DeleteAllCart();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notify");
            builder.setMessage("Checkout success!");
            builder.setPositiveButton("OK", (dialog, which) -> {
                Intent intent = new Intent(this, ListUserOrderActivity.class);
                startActivity(intent);
            });
            builder.show();
        } else {
            Log.d("infoOrder", "insert order fail");
        }


    }
    public  double getTotalPrice(){
        double total = 0;
        cartList = new DAOCart(this).getListCart(sessionManager.getUserID());
        for (Cart cart : cartList) {
            Product product = new DAOProduct(this).getProduct(cart.getProductID());
            Log.d("infoOrder", "quantity : " + cart.getQuantity() + ", product price: "+ product.getPrice());
            total += (cart.getQuantity() * product.getPrice());
        }
        return total;
    }
    @Override
    public void onPriceChange(double price) {
        tv_total.setText("$" + price);
    }


    public void LoadRecyclerView(List<Cart> cartList, int id) {
        double total = 0;
        int pID = 0;

        List<Product> productList = new ArrayList<>();
        Product product = new DAOProduct(this).getProduct(id);
        if (product != null) {
            for (Cart c : cartList) {
                if (c.getProductID() == id) {
                    pID = id;
                    c.setQuantity(c.getQuantity() + quantity);
                    int n = new DAOCart(this).UpdateCart(c);
                }

            }
            if (pID != id) {
                long n = new DAOCart(this).AddCart(new Cart(userID, product.getProductID(), quantity));
                cartList.add(new Cart(new DAOCart(this).getMaxCartID(), userID, product.getProductID(), quantity));
            }

        }
        if (cartList.size() != 0) {
            for (Cart c : cartList) {
                Product p = new DAOProduct(this).getProduct(c.getProductID());
                total += p.getPrice() * c.getQuantity();
                productList.add(p);
            }

            adapter = new FoodAdapter(this,cartList, total, productList, this);
            rcv.setLayoutManager(new LinearLayoutManager(this));
            rcv.setAdapter(adapter);
        } else {

            findViewById(R.id.imv_cartEmpty).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_checkout).setVisibility(View.GONE);
            tv_total.setText("$0");
            rcv.setVisibility(View.GONE);
        }


    }
}