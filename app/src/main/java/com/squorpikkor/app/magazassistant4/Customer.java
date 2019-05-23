package com.squorpikkor.app.magazassistant4;

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

/*    Customer() {
        this.name = "name";
        this.surname = "surname";
    }

    Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }*/
}
