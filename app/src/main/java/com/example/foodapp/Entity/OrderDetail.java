package com.example.foodapp.Entity;

public class OrderDetail {
    private int DetailID;
    private int OrderID;
    private int ProductID;
    private int quantity;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "DetailID=" + DetailID +
                ", OrderID=" + OrderID +
                ", ProductID=" + ProductID +
                ", quantity=" + quantity +
                '}';
    }

    public OrderDetail() {
    }

    public OrderDetail(int DetailID, int OrderID, int ProductID, int quantity) {
        this.DetailID = DetailID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.quantity = quantity;
    }

    public OrderDetail(int OrderID, int ProductID, int quantity) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.quantity = quantity;
    }

    public int getDetailID() {
        return DetailID;
    }

    public void setDetailID(int DetailID) {
        this.DetailID = DetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
