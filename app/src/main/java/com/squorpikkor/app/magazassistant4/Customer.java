package com.squorpikkor.app.magazassistant4;

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
}
