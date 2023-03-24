package com.example.foodapp.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.foodapp.Entity.ManageOrder;
import com.example.foodapp.Entity.Order;
import com.example.foodapp.Entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class DAOManageOrder extends ConnectDatabase {
    public DAOManageOrder(@Nullable Context context) {
        super(context);
    }

    public List<ManageOrder> getAllManageOrder(){
        List<ManageOrder> list = new ArrayList<>();
        SQLiteDatabase lite = getReadableDatabase();
        String sql = "select  [Order].OrderID,[Order].OrderDate, [Order].ShipDate, [Order].status, User.FullName,[Order].Address from [Order] " +
                "INNER JOIN User ON [Order].UserID = User.ID ";
        Cursor cursor = lite.rawQuery(sql , null);
        while (cursor != null && cursor.moveToNext()){
            int orderId = cursor.getInt(0);
            String orderDate = cursor.getString(1);
            String shipDate = cursor.getString(2);
            String status = cursor.getString(3);
            String fullName = cursor.getString(4);
            String address = cursor.getString(5);
            ManageOrder manageOrder = new ManageOrder(orderId, status, fullName,address, orderDate,shipDate);
            list.add(manageOrder);
        }
        cursor.close();
        return list;
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





    public void UpdateStatus(int orderId, String status){
        SQLiteDatabase lite = getWritableDatabase();
        ContentValues values = new ContentValues();
        String sql = "Update [Order] SET Status = ? WHERE OrderID = ?";
        String[] strings = {status,String.valueOf(orderId)};
        lite.execSQL(sql, strings);

        //Toast.makeText(context,"Update success", Toast.LENGTH_LONG).show();


        //Cursor cursor = lite.rawQuery(sql, strings);
        //String whereClause = "OrderID = ?";
        //String[] whereArgs = {String.valueOf(order.getOrderID())};
        /*values.put("UserID",order.getUserID());
        values.put("OrderDate",order.getOrderDate());
        values.put("ShipDate",order.getShipDate());
        values.put("Status",order.getStatus());
        values.put("Address",order.getAddress());


        return lite.update("Order",values,whereClause,whereArgs);*/

    }


    public int getStatus(int orderId){
        int status = 0;
        SQLiteDatabase lite = getWritableDatabase();
        String sql = "Select Status from [Order] where OrderID = ? ";
        String[] strings = {String.valueOf(orderId)};
        Cursor cursor = lite.rawQuery(sql, strings);
        while (cursor != null && cursor.moveToNext()){
            boolean check = false;
            if(cursor.getString(0).equals("In progress")){
                status = 1;
            } else if (cursor.getString(0).equals("Cancel")) {
                status = 2;
            }


        }
        cursor.close();
        return status;
    }

    public Order getOrder(int OrderID){
        SQLiteDatabase lite = getWritableDatabase();
        String sql = "Select * from [Order] where OrderID = ?";
        String[] selectionArgs = {OrderID + ""};
        Cursor cursor = lite.rawQuery(sql, selectionArgs);
        while (cursor != null && cursor.moveToNext()){
              int UserID = cursor.getInt(1);
              String OrderDate = cursor.getString(2);
              String ShipDate = cursor.getString(3);
              String status = cursor.getString(4);
              String address = cursor.getString(5);
              Order order = new Order(OrderID,UserID,OrderDate,ShipDate,status,address);
              return order;
        }
        cursor.close();
        return null;
    }



}

