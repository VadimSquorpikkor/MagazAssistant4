package com.squorpikkor.app.magazassistant4.customer;

import com.squorpikkor.app.magazassistant4.Juice;
import com.squorpikkor.app.magazassistant4.order.Product;

import java.util.ArrayList;

public class Customer {
    int id; //ID для нахождения объекта в БД и его апдейта или удаления

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    private String name;
    private String surname;
    private int depName;


    private ArrayList<Juice> juiceList;
    private ArrayList<Product> prodList;
    private float moneyLeft;
    private boolean isWorking;
    private boolean isComplete;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public ArrayList<Juice> getJuiceList() {
        return juiceList;
    }
    public void setJuiceList(ArrayList<Juice> juiceList) {
        this.juiceList = juiceList;
    }
    public ArrayList<Product> getProdList() {
        return prodList;
    }
    public void setProdList(ArrayList<Product> prodList) {
        this.prodList = prodList;
    }
    public float getMoneyLeft() {
        return moneyLeft;
    }
    public void setMoneyLeft(float moneyLeft) {
        this.moneyLeft = moneyLeft;
    }
    public boolean isWorking() {
        return isWorking;
    }
    public void setWorking(boolean working) {
        isWorking = working;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    public int getDepName() {
        return depName;
    }
    public void setDepName(int depName) {
        this.depName = depName;
    }

    public Customer(String name, String surname, int depName) {
        this.name = name;
        this.surname = surname;
        this.isWorking = true;
        this.depName = depName;
    }

    //конструктор для DBHelper
    public Customer() {

    }

}
