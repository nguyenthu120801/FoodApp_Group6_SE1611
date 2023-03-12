package com.example.foodapp.Model;

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
}
