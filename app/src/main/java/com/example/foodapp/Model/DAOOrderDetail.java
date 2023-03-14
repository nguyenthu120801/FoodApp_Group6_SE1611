package com.example.foodapp.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.OrderDetail;
import com.example.foodapp.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DAOOrderDetail extends ConnectDatabase{
    public DAOOrderDetail(@Nullable Context context) {
        super(context);
    }

    public boolean CheckProductExist(int ProductID){
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select * from OrderDetail where ProductID = ?";
        String [] selectionArgs = {ProductID + ""};
        Cursor cursor = lite.rawQuery(sql, selectionArgs);
        // if get data successful
        if(cursor!= null && cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public List<OrderDetail> getListOrderDetail(int orderID){
        List<OrderDetail> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "Select * from OrderDetail where OrderID = ?";
        String[] selectionArgs = {String.valueOf(orderID)};
        Cursor cursor = lite.rawQuery(sql , selectionArgs);
        while(cursor!= null && cursor.moveToNext()){
            int detailID = cursor.getInt(0);
            int orderId = cursor.getInt(1);
            int productId = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            OrderDetail orderDetail = new OrderDetail(detailID, orderId, productId, quantity);
            list.add(orderDetail);
        }
        return list;
    }

    public long AddOrderDetail (OrderDetail orderDetail){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OrderID",orderDetail.getOrderID());
        values.put("ProductID", orderDetail.getProductID());
        values.put("quantity",orderDetail.getQuantity());
        return lite.insert("OrderDetail",null,values);
    }

    public int UpdateOrderDetail(OrderDetail orderDetail){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = "DetailID = ?";
        String[] whereArgs = {String.valueOf(orderDetail.getDetailID())};
        values.put("OrderID",orderDetail.getOrderID());
        values.put("ProductID", orderDetail.getProductID());
        values.put("quantity",orderDetail.getQuantity());
        return lite.update("OrderDetail",values,whereClause,whereArgs);
    }

    public int DeleteOrderDetail(int detailID){
        SQLiteDatabase lite = getWritableDatabase();
        String whereClause = "DetailID = ?";
        String[] whereArgs = {String.valueOf(detailID)};
        return lite.delete("OrderDetail",whereClause,whereArgs);
    }
    @SuppressLint("Range")
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { "DetailID", "OrderID", "ProductID", "quantity" };
        String selection = "OrderID = ?";
        String[] selectionArgs = { String.valueOf(orderId) };
        Cursor cursor = db.query("OrderDetail", columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int detailId = cursor.getInt(cursor.getColumnIndex("DetailID"));
                int productId = cursor.getInt(cursor.getColumnIndex("ProductID"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                OrderDetail orderDetail = new OrderDetail(detailId, orderId, productId, quantity);
                orderDetails.add(orderDetail);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return orderDetails;
    }
}
