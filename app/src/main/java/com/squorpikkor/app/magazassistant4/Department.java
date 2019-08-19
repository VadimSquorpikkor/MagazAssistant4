package com.squorpikkor.app.magazassistant4;

import com.squorpikkor.app.magazassistant4.customer.Customer;

import java.util.ArrayList;

public class Department {


    private int ID;
    private String name;
    private float juicePerWeek;
    private ArrayList<Customer> depCustomers;

    public Department(String name, float juicePerWeek) {
        this.name = name;
        this.juicePerWeek = juicePerWeek;
        this.depCustomers = new ArrayList<>();
    }

    //Конструктор для БД
    public Department() {

    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCurrentDepCustomers() {
        return depCustomers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getJuicePerWeek() {
        return juicePerWeek;
    }

    public void setJuicePerWeek(float juicePerWeek) {
        this.juicePerWeek = juicePerWeek;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
