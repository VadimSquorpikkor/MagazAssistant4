package com.squorpikkor.app.magazassistant4.order;

import java.util.ArrayList;
import java.util.List;

public class Order {

    /**
     * Ордер -- это заказ СПИСКА ПРОДУКТОВ для конкретного человека
     * т.е. он будет содержать список продуктов, их сумарную стоимость, их стоимость
     * с учетом соков (цена соков не учитывается) и без учета соков
     * Имя человека, сделавшего заказ и т.д.
     */


    private String name;
    private Float totalPrice;
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Order(String name, Float totalPrice) {
        this.name = name;
        this.products = new ArrayList<>();
        this.totalPrice = totalPrice;
    }

    //Только для проверки
    public Order(String name, Float totalPrice, List<Product> productList) {
        this.name = name;
        this.products = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.products = productList;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
