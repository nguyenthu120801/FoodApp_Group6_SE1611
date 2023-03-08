package com.example.foodapp.entity;

import java.util.Date;

public class Order {
    private int id;
    private int userID;
    private String address;
    private Date orderDate;
    private Date shipDate;
    private int status;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", shipDate=" + shipDate +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order(int id, int userID, String address, Date orderDate, Date shipDate, int status) {
        this.id = id;
        this.userID = userID;
        this.address = address;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
    }

    public Order(int userID, String address, Date orderDate, Date shipDate, int status) {
        this.userID = userID;
        this.address = address;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
    }

    public Order(){}

}
