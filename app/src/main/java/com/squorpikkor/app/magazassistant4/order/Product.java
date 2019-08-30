package com.squorpikkor.app.magazassistant4.order;

public class Product {

    /**
     * Некоторый заказанный продукт, может быть и соком
     * Входит в состав заказа (Order'а)
     * (Juice не будет наследником этого класса)
     */

    public String getName() {
        return name;
    }

    public void setName(String nsme) {
        this.name = nsme;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    private String name;
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }
}
