package com.example.foodapp.Entity;

import java.io.Serializable;

public class ManageOrder implements Serializable {
    private int OrderID;
    private String status;
    private String FullName;
    private String Address;

    private String orderDate;

    public ManageOrder(int orderID, String status, String fullName, String address, String orderDate) {
        OrderID = orderID;
        this.status = status;
        FullName = fullName;
        Address = address;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
