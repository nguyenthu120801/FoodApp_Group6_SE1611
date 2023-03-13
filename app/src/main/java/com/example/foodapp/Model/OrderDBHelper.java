package com.example.foodapp.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDBHelper extends ConnectDatabase {
    private static final String ORDER_TABLE = "[Order]";
    private static final String ORDER_ID = "OrderID";
    private static final String ORDER_USERID = "UserID";
    private static final String ORDER_ADDRESS = "Address";
    private static final String ORDER_ORDER_DATE = "OrderDate";
    private static final String ORDER_SHIP_DATE = "ShipDate";
    private static final String ORDER_STATUS = "Status";

    public OrderDBHelper(@Nullable Context context) {
        super(context);
    }


    public boolean insertOrder(Order order) {
        SQLiteDatabase db = getWritableDatabase();
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

    public void updateOrder(Order order) {
    }

    @SuppressLint("Range")
    public Order searchOrder(String orderId) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = ORDER_ID + " = ?";
        String[] selectionArgs = {orderId};
        Cursor cursor = db.query(ORDER_TABLE, null, selection, selectionArgs, null, null, null);
        Order order = null;
        if (cursor != null && cursor.moveToFirst()) {
            order = new Order();
            order.setOrderID(cursor.getInt(cursor.getColumnIndex(ORDER_ID)));
            order.setUserID(cursor.getInt(cursor.getColumnIndex(ORDER_USERID)));
            order.setAddress(cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS)));
            String orderDateString = cursor.getString(cursor.getColumnIndex(ORDER_ORDER_DATE));
            order.setOrderDate(orderDateString);
            String shipDateString = cursor.getString(cursor.getColumnIndex(ORDER_SHIP_DATE));
            if (shipDateString != null) {
                order.setShipDate(shipDateString);
            }
            order.setStatus(cursor.getString(cursor.getColumnIndex(ORDER_STATUS)));
        }
        if (cursor != null) {
            cursor.close();
        }
        return order;
    }

    @SuppressLint("Range")
    public List<Order> searchOrder(int userId) {
        SQLiteDatabase database = getReadableDatabase();
        List<Order> orderList = new ArrayList<>();
        Cursor cursor = database.query(ORDER_TABLE, null, ORDER_USERID + "=?", new String[]{String.valueOf(userId)}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Order order = new Order();
                order.setOrderID(cursor.getInt(cursor.getColumnIndex(ORDER_ID)));
                order.setUserID(cursor.getInt(cursor.getColumnIndex(ORDER_USERID)));
                order.setOrderDate(cursor.getString(cursor.getColumnIndex(ORDER_ORDER_DATE)));
                order.setShipDate(cursor.getString(cursor.getColumnIndex(ORDER_SHIP_DATE)));
                order.setStatus(cursor.getString(cursor.getColumnIndex(ORDER_STATUS)));
                order.setAddress(cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS)));
                orderList.add(order);
            }
            cursor.close();
        }
        return orderList;
    }

    public void deleteOrder(String id) {
    }

    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + ORDER_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderID(cursor.getInt(cursor.getColumnIndex(ORDER_ID)));
                order.setUserID(cursor.getInt(cursor.getColumnIndex(ORDER_USERID)));
                order.setOrderDate(cursor.getString(cursor.getColumnIndex(ORDER_ORDER_DATE)));
                order.setShipDate(cursor.getString(cursor.getColumnIndex(ORDER_SHIP_DATE)));
                order.setStatus(cursor.getString(cursor.getColumnIndex(ORDER_STATUS)));
                order.setAddress(cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS)));
                orders.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orders;
    }

    public boolean removeOrder(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("infoOrder", "begin call database");
        int isSuccess = db.delete( ORDER_TABLE, ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
        Log.d("infoOrder", "end call database");
        db.close();
        return isSuccess != -1;
    }


}
