package com.example.foodapp.Entity;

import java.io.Serializable;

public class ManageOrder implements Serializable {
    private int OrderID;
    private String status;
    private String FullName;
    private String Address;

    public ManageOrder(int orderID, String status, String fullName, String address) {
        OrderID = orderID;
        this.status = status;
        FullName = fullName;
        Address = address;
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
}