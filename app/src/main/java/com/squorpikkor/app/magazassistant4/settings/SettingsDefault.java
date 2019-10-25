package com.squorpikkor.app.magazassistant4.settings;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Конечно совсем не обязательно было создавать отдельный класс ради одного метода,
 * но так удобнее будет вносить изменения, и весь этот метод (почти ненужный -- он
 * больше нужен мне для тестирования) не будет путаться в коде SettingsFragment
 */

class SettingsDefault {
    //Map<Integer, String> depName = new HashMap<>();
    void setDefaultSettings(DatabaseHelper database) {
        database.addDepartment("Сборочный участок", 3);     //0
        database.addDepartment("Корелин 1-й корпус", 3);    //1
        database.addDepartment("Монтажный участок", 3);     //2
        database.addDepartment("Праневич", 3);              //3
        database.addDepartment("Цокольный этаж", 3);        //4
        database.addDepartment("Монтаж 1-й этаж", 3);       //5

        database.addCustomer("Максим", "Шустов", 0);
        database.addCustomer("Ваня", "Махнюков", 0);
        database.addCustomer("Олег", "Алисевич", 0);
        database.addCustomer("Алексей", "Крот", 5);


    }
}
