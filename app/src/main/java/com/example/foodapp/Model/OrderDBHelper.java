package com.example.foodapp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Order;

import java.util.List;

public class OrderDBHelper extends SQLiteOpenHelper {
    private static final String ORDER_TABLE ="Orders";
    private static final String ORDER_ID ="OrderID";
    private static final String ORDER_USERID ="UserID";
    private static final String ORDER_ADDRESS ="Address";
    private static final String ORDER_ORDER_DATE ="OrderDate";
    private static final String ORDER_SHIP_DATE ="ShipDate";
    private static final String ORDER_STATUS ="Status";

    public OrderDBHelper(@Nullable Context context) {
        super(context, ORDER_TABLE, null, 1);
    }


    public boolean insertOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_USERID, order.getUserID());
        values.put(ORDER_ADDRESS, order.getAddress());
        values.put(ORDER_ORDER_DATE, order.getOrderDate());
        values.put(ORDER_SHIP_DATE, order.getShipDate());
        values.put(ORDER_STATUS, order.getStatus());
        Log.d("infoOrder", "value insert data : " + values);
        long result = db.insert(ORDER_TABLE, null, values);
        return result != -1;
    }
    public void updateOrder(Order order){}
    public void searchOrder(Order order){}
    public void deleteOrder(String id){}
    public List<Order> listAllOrders(){return  null;}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlOrder = "CREATE TABLE ["+ORDER_TABLE+"](\n" +
                "\t"+ORDER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\t"+ORDER_USERID+" INTEGER  NOT NULL,\n" +
                "\t"+ORDER_ORDER_DATE+" TEXT  NOT NULL,\n" +
                "\t"+ORDER_SHIP_DATE+" TEXT ,\n" +
                "\t"+ORDER_STATUS+" TEXT NOT NULL, \n" +
                "\t"+ORDER_ADDRESS+" TEXT NOT NULL)";
        Log.d("infoOrder", "create order table : " + sqlOrder);
        sqLiteDatabase.execSQL(sqlOrder);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
