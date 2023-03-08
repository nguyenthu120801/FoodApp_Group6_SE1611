package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.foodapp.Entity.Order;

import java.util.List;

public class OrderDBHelper extends SQLiteOpenHelper {
    private static final String ORDER_DATABASE ="orders.db";
    private static final int ORDER_VERSION =1;
    private static final String ORDER_TABLE ="orders";
    private static final String ORDER_ID ="id";
    private static final String ORDER_USERID ="user_id";
    private static final String ORDER_ADDRESS ="address";
    private static final String ORDER_ORDER_DATE ="order_date";
    private static final String ORDER_SHIP_DATE ="ship_date";
    private static final String ORDER_STATUS ="status";

    public OrderDBHelper( Context context) {
        super(context, ORDER_DATABASE, null, ORDER_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + ORDER_TABLE + "(" +
                ORDER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_USERID+  " INTEGER, " +
                ORDER_ADDRESS+ " TEXT, " +
                ORDER_ORDER_DATE+  " DATE, " +
                ORDER_SHIP_DATE+  " DATE, " +
                ORDER_STATUS+  " INTEGER" +
                ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_USERID, order.getUserID());
        values.put(ORDER_ADDRESS, order.getAddress());
        values.put(ORDER_ORDER_DATE, order.getOrderDate().toString());
        values.put(ORDER_SHIP_DATE, order.getShipDate().toString());
        values.put(ORDER_STATUS, order.getStatus());
        long result = db.insert(ORDER_TABLE, null, values);
        return result != -1;
    }
    public void updateOrder(Order order){}
    public void searchOrder(Order order){}
    public void deleteOrder(String id){}
    public List<Order> listAllOrders(){return  null;}
}
