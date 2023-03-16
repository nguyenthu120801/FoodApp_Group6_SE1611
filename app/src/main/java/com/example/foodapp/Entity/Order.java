package com.example.foodapp.Entity;

import java.util.Date;

public class Order {
    private int orderID;
    private int userID;
    private String orderDate;
    private String shipDate;
    private String status;
    private String address;
    public static final String STATUS_IN_PROGRESS = "In progress";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_REJECTED = "Rejected";

    public Order() {
    }

    public Order(int userID, String orderDate, String shipDate, String status, String address) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.address = address;
    }

    public Order(int orderID, String status) {
        this.orderID = orderID;
        this.status = status;
    }

    public Order(int orderID, int userID, String orderDate, String shipDate, String status, String address) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.address = address;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", orderDate='" + orderDate + '\'' +
                ", shipDate='" + shipDate + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
