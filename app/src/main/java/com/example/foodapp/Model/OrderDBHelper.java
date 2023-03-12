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

public class OrderDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PRM392_APP_FOOD";
    private static final String ORDER_TABLE = "Orders";
    private static final String ORDER_ID = "OrderID";
    private static final String ORDER_USERID = "UserID";
    private static final String ORDER_ADDRESS = "Address";
    private static final String ORDER_ORDER_DATE = "OrderDate";
    private static final String ORDER_SHIP_DATE = "ShipDate";
    private static final String ORDER_STATUS = "Status";

    public OrderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlOrder = "CREATE TABLE " + ORDER_TABLE + " (" +
                ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ORDER_USERID + " INTEGER NOT NULL, " +
                ORDER_ORDER_DATE + " TEXT NOT NULL, " +
                ORDER_SHIP_DATE + " TEXT, " +
                ORDER_ADDRESS + " TEXT NOT NULL, " +
                ORDER_STATUS + " TEXT NOT NULL);";
        Log.d("infoOrder", "create order table : " + sqlOrder);
        sqLiteDatabase.execSQL(sqlOrder);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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

    public void updateOrder(Order order) {
    }

    @SuppressLint("Range")
    public Order searchOrder(String orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {ORDER_ID, ORDER_USERID, ORDER_ADDRESS, ORDER_ORDER_DATE, ORDER_SHIP_DATE, ORDER_STATUS};
        String selection = ORDER_ID + " = ?";
        String[] selectionArgs = {orderId};
        Cursor cursor = db.query(ORDER_TABLE, columns, selection, selectionArgs, null, null, null);
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

    public void deleteOrder(String id) {
    }

    @SuppressLint("Range")
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

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

    public boolean removeOrder(String orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isSuccess = db.delete(ORDER_TABLE, ORDER_ID + " = ?", new String[]{orderId});
        db.close();
        return isSuccess != -1;
    }


}
