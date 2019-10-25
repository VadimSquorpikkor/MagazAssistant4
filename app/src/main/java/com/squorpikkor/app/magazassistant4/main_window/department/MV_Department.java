package com.squorpikkor.app.magazassistant4.main_window.department;


//TODO USELESS

/**Список отделов, колличество присутствующих людей количество соков на отдел
 *  и количество соков всего. Это элемент списка для Табба "Цены" -> "Количество людей"*/

public class MV_Department {
    private String depName;
    private int customersCount;
    private int juiceCount;
    private double koef;



    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getCustomersCount() {
        return customersCount;
    }

    public void setCustomersCount(int customersCount) {
        this.customersCount = customersCount;
    }

    public int getJuiceCount() {
        return juiceCount;
    }

    public void setJuiceCount(int juiceCount) {
        this.juiceCount = juiceCount;
    }

    public double getKoef() {
        return koef;
    }

    public void setKoef(double koef) {
        this.koef = koef;
    }
}
