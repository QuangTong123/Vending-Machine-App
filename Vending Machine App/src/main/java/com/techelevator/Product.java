package com.techelevator;

public class Product {
    private String slotLocation;
    private String name;
    private double price;
    private String type;
    private int quantity;


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void decreaseQuantity(){
        if (quantity > 0){
            quantity--;
        }
    }

    public Product(){


    }

    public Product(String slotLocation, String name, double price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.slotLocation = slotLocation;
    }

}
