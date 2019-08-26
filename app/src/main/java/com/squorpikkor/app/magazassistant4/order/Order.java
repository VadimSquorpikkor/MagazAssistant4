package com.squorpikkor.app.magazassistant4.order;

public class Order {

    private String name;
    private Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Order(String name, Float price) {
        this.name = name;
        this.price = price;
    }
}
