package com.squorpikkor.app.magazassistant4.juice;

public class JuicePack {
    private Float price;
    private int count;
    private String name;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JuicePack(String name, Float price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }
}
