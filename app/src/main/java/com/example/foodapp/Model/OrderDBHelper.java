package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.foodapp.Entity.Order;

import java.util.List;

public class OrderDBHelper extends ConnectDatabase {
    private static final String ORDER_TABLE ="Order";
    private static final String ORDER_ID ="OrderID";
    private static final String ORDER_USERID ="UserID";
    private static final String ORDER_ADDRESS ="Address";
    private static final String ORDER_ORDER_DATE ="OrderDate";
    private static final String ORDER_SHIP_DATE ="ShipDate";
    private static final String ORDER_STATUS ="Status";

    public OrderDBHelper( Context context) {
        super(context);
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
