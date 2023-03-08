package com.example.foodapp.Entity;

public class Order {
    private int OrderID;
    private int UserID;
    private String status;
    private String OrderDate;
    private String ShipDate;
    private String address;
    public static final String STATUS_IN_PROGRESS = "In progress";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_REJECTED = "Rejected";

    public Order() {
    }

    public Order(int OrderID, int UserID, String status, String OrderDate, String ShipDate, String address) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.status = status;
        this.OrderDate = OrderDate;
        this.ShipDate = ShipDate;
        this.address = address;
    }

    public Order(int UserID, String status, String ShipDate, String address) {
        this.UserID = UserID;
        this.status = status;
        this.ShipDate = ShipDate;
        this.address = address;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getShipDate() {
        return ShipDate;
    }

    public void setShipDate(String ShipDate) {
        this.ShipDate = ShipDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
