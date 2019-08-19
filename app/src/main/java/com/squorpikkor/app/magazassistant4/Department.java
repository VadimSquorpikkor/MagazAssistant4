package com.squorpikkor.app.magazassistant4;

public class Department {
    private String name;
    private float juicePerWeek;

    public Department(String name, float juicePerWeek) {
        this.name = name;
        this.juicePerWeek = juicePerWeek;
    }

    public String getName() {
        return name;
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
}
