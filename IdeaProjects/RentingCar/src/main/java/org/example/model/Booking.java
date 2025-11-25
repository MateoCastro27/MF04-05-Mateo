package org.example.model;

public class Booking {

    private String id;
    // private Client  client
    private Car car;
    private int days;
    private double price;
    private boolean isActive;

    public  Booking(String id, Car car, double price, boolean isActive) {
        this.id = id;
        this.car = car;
        this.days = days;
        this.price = price;
        this.isActive = isActive;

    }

    public Booking() {}

    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }
}



