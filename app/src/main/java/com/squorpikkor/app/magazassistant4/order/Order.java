package com.squorpikkor.app.magazassistant4.order;

import com.squorpikkor.app.magazassistant4.juice.Juice;

import java.util.ArrayList;
import java.util.List;

public class Order {

    /**
     * Ордер -- это заказ СПИСКА ПРОДУКТОВ для конкретного человека
     * т.е. он будет содержать список продуктов, их сумарную стоимость, их стоимость
     * с учетом соков (цена соков не учитывается) и без учета соков
     * ID кастомера -- по нему классы, которым нужна будет инфа по кастомеру будут вытягиваться сам
     * объект Customer и брать у него нужную инфу
     */


    private Float totalPrice;
    private List<Product> products;
    private List<Juice> juices;
    private int customerID;

    public int getCustomerID() {
        return customerID;
    }

    //только для проверки
    private String name;
    private String sName;
    public String getName()  { return name;  }
    public String getsName() { return sName; }

    //Только для проверки
    public Order(String name, String sName, Float totalPrice, List<Product> productList, List<Juice> juiceList) {
        this.name = name;
        this.sName = sName;
        this.products = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.products = productList;
        this.juices = juiceList;
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

    public List<Juice> getJuices() {
        return juices;
    }

    public Order(int cusId, String name, String sName) {
        this.customerID = cusId;
        this.products = new ArrayList<>();
        this.juices = new ArrayList<>();
        this.totalPrice = 0f;

        this.name = name;
        this.sName = sName;
    }




    /*public Order(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Order(String name, Float totalPrice) {
        this.name = name;
        this.products = new ArrayList<>();
        this.totalPrice = totalPrice;
    }*/

}