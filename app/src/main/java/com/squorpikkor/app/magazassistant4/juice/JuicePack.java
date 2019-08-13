package com.squorpikkor.app.magazassistant4.juice;

public class JuicePack {
    private Float price;
    private int count;

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

    public JuicePack(Float price, int count) {
        this.price = price;
        this.count = count;
    }
}
