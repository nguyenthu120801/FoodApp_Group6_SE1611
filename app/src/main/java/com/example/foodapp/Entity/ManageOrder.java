package com.example.foodapp.Entity;

public class ManageOrder {
    private int OrderID;
    private String status;
    private String ProductName;
    private int quantity;
    private int image;


    public ManageOrder(int orderID, String status, String productName, int quantity, int image) {
        OrderID = orderID;
        this.status = status;
        ProductName = productName;
        this.quantity = quantity;
        this.image = image;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
