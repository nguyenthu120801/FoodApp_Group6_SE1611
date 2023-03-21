package com.example.foodapp.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.example.foodapp.Entity.Order;
import com.example.foodapp.provider.OrderContentProvider;

import java.util.ArrayList;
import java.util.List;

public class OrderDBHelper extends ConnectDatabase {
    public static final String ORDER_TABLE = "[Order]";
    public static final String ORDER_ID = "OrderID";
    public static final String ORDER_USERID = "UserID";
    public static final String ORDER_ADDRESS = "Address";
    public static final String ORDER_ORDER_DATE = "OrderDate";
    public static final String ORDER_SHIP_DATE = "ShipDate";
    public static final String ORDER_STATUS = "Status";
    private Context context;

    public OrderDBHelper(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public int insertOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put(OrderDBHelper.ORDER_USERID, order.getUserID());
        values.put(OrderDBHelper.ORDER_ADDRESS, order.getAddress());
        values.put(OrderDBHelper.ORDER_ORDER_DATE, order.getOrderDate());
        values.put(OrderDBHelper.ORDER_SHIP_DATE, order.getShipDate());
        values.put(OrderDBHelper.ORDER_STATUS, order.getStatus());
        Uri uri = context.getContentResolver().insert(OrderContentProvider.CONTENT_URI, values);
        if (uri != null) {
            Log.d("infoOrder", "Đã insert bằng content provider với order " + order);
            return Integer.parseInt(uri.getLastPathSegment());
        }
        return -1;
    }



    public boolean updateOrder(Order order) {
        String selection = OrderDBHelper.ORDER_ID + "= ?";
        String[] selectionArgs = {String.valueOf(order.getOrderID())};
        ContentValues values = new ContentValues();
        values.put(OrderDBHelper.ORDER_USERID, order.getUserID());
        values.put(OrderDBHelper.ORDER_ADDRESS, order.getAddress());
        values.put(OrderDBHelper.ORDER_ORDER_DATE, order.getOrderDate());
        values.put(OrderDBHelper.ORDER_SHIP_DATE, order.getShipDate());
        values.put(OrderDBHelper.ORDER_STATUS, order.getStatus());
        int rowsUpdated = context.getContentResolver().update(OrderContentProvider.CONTENT_URI, values, selection, selectionArgs);
        return rowsUpdated > 0;
    }

    @SuppressLint("Range")
    public Order searchOrder(String orderId) {
        Order order = null;
        String[] projection = {OrderDBHelper.ORDER_ID, OrderDBHelper.ORDER_USERID, OrderDBHelper.ORDER_ADDRESS, OrderDBHelper.ORDER_ORDER_DATE, OrderDBHelper.ORDER_SHIP_DATE, OrderDBHelper.ORDER_STATUS};
        String selection = OrderDBHelper.ORDER_ID + "=?";
        String[] selectionArgs = {orderId};
        Cursor cursor = context.getContentResolver().query(OrderContentProvider.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst()) {
            int orderIdFromCursor = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ID));
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_USERID));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ADDRESS));
            String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ORDER_DATE));
            String shipDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_SHIP_DATE));
            String status = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_STATUS));
            order = new Order(orderIdFromCursor, userId, orderDate, shipDate, status, address);
            cursor.close();
        }
        return order;
    }

    @SuppressLint("Range")
    public List<Order> searchOrder(int userId) {
        List<Order> orders = new ArrayList<>();
        String[] projection = {OrderDBHelper.ORDER_ID, OrderDBHelper.ORDER_USERID, OrderDBHelper.ORDER_ADDRESS, OrderDBHelper.ORDER_ORDER_DATE, OrderDBHelper.ORDER_SHIP_DATE, OrderDBHelper.ORDER_STATUS};
        String selection = OrderDBHelper.ORDER_USERID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = context.getContentResolver().query(OrderContentProvider.CONTENT_URI, projection, selection, selectionArgs, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ID));
                int userIdFromCursor = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_USERID));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ADDRESS));
                String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ORDER_DATE));
                String shipDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_SHIP_DATE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_STATUS));
                Order order = new Order(orderId, userIdFromCursor, orderDate, shipDate, status, address);
                orders.add(order);
            }
            cursor.close();
        }
        return orders;
    }


    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String[] projection = {OrderDBHelper.ORDER_ID, OrderDBHelper.ORDER_USERID, OrderDBHelper.ORDER_ADDRESS, OrderDBHelper.ORDER_ORDER_DATE, OrderDBHelper.ORDER_SHIP_DATE, OrderDBHelper.ORDER_STATUS};
        Cursor cursor = context.getContentResolver().query(OrderContentProvider.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ID));
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_USERID));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ADDRESS));
                String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_ORDER_DATE));
                String shipDate = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_SHIP_DATE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(OrderDBHelper.ORDER_STATUS));
                Order order = new Order(orderId, userId, orderDate, shipDate, status, address);
                orders.add(order);
            }
            cursor.close();
        }
        return orders;
    }

    public boolean removeOrder(int orderId) {
        String selection = OrderDBHelper.ORDER_ID + "= ?";
        String[] selectionArgs = {String.valueOf(orderId)};
        Log.d("infoOrder", "begin call database");
        int rowsDeleted = context.getContentResolver().delete(OrderContentProvider.CONTENT_URI, selection, selectionArgs);
        Log.d("infoOrder", "end call database");
        return rowsDeleted != -1;
    }


}
