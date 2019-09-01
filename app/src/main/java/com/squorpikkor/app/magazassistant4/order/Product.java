package com.squorpikkor.app.magazassistant4.order;

public class Product {

    /**
     * Некоторый заказанный продукт, может быть и соком
     * Входит в состав заказа (Order'а)
     * (Juice не будет наследником этого класса)
     */

    //region GETTERS & SETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String nsme) {
        this.title = nsme;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isJuice() {
        return isJuice;
    }

    public void setJuice(boolean juice) {
        isJuice = juice;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //endregion

    private int id;             //id продукта в БД -- для поиска по БД
    private String title;       //название продукта
    private Float price;        //цена
    private int quantity;       //количество продукта
    private boolean isJuice;    //сок или не сок
    private boolean purchased;  //куплен или не куплен
    private int customer;       //заказчик продукта. Интегер, потому как хранится толька ссылка на
                                // id заказчика в таблице БД

    public Product(String name, Float price) {
        this.title = name;
        this.price = price;
    }

    public Product() {
    }
}
