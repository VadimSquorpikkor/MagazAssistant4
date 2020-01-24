package com.squorpikkor.app.magazassistant4.settings;

import com.squorpikkor.app.magazassistant4.DatabaseHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Конечно совсем не обязательно было создавать отдельный класс ради одного метода,
 * но так удобнее будет вносить изменения, и весь этот метод (почти ненужный -- он
 * больше нужен мне для тестирования) не будет путаться в коде SettingsFragment
 */

public class SettingsDefault2 {
    //Map<Integer, String> depName = new HashMap<>();
    public void setDefaultSettings(DatabaseHelper database) {
        database.addDepartment("Сборочный участок", 3);     //0
        database.addDepartment("Корелин 1-й корпус", 3);    //1
        database.addDepartment("Монтажный участок", 3);     //2
        database.addDepartment("Праневич", 3);              //3
        database.addDepartment("Цокольный этаж", 3);        //4
        database.addDepartment("Монтаж 1-й этаж", 3);       //5


//-----------------------------Сборка--------------------------------------------------------------
        database.addCustomer("Д.В.", "Алисевич", 0);
        database.addCustomer("О.В.", "Алисевич", 0);
        database.addCustomer("А.С.", "Бодялик", 0);
        database.addCustomer("А.А.", "Володкевич", 0);
        database.addCustomer("И.С.", "Махнюков", 0);
        database.addCustomer("Ю.В.", "Мороз", 0);
        database.addCustomer("Ю.В.", "Пекарский", 0);
        database.addCustomer("М.В.", "Шустов", 0);
//-----------------------------Праневич--------------------------------------------------------------
        database.addCustomer("А.А.", "Праневич", 3);
        database.addCustomer("М.В.", "Ячменев", 3);
//-----------------------------Цоколь--------------------------------------------------------------
        database.addCustomer("В.Ю.", "Дементьев", 4);
        database.addCustomer("А.Е.", "Яворский", 4);
//-----------------------------Корелин--------------------------------------------------------------
        database.addCustomer("С.Н.", "Барсуков", 1);
        database.addCustomer("Л.В.", "Буров", 1);
        database.addCustomer("Р.В.", "Видасев", 1);
        database.addCustomer("С.В.", "Денисенко", 1);
        database.addCustomer("Н.А.", "Дыба", 1);
        database.addCustomer("В.И.", "Зуевский", 1);
        database.addCustomer("Е.С.", "Каменщиков", 1);
        database.addCustomer("В.П.", "Кишкилевич", 1);
        database.addCustomer("А.Г.", "Колосовский", 1);
        database.addCustomer("В.Г.", "Любач", 1);
        database.addCustomer("А.М.", "Матвеенко", 1);
        database.addCustomer("М.В.", "Муравицкий", 1);
        database.addCustomer("А.А.", "Рычик", 1);
        database.addCustomer("А.К.", "Рябов", 1);
        database.addCustomer("Ю.Б.", "Станкевич", 1);
        database.addCustomer("И.А.", "Фролов", 1);
        database.addCustomer("С.А.", "Юревич", 1);
//-----------------------------Монтаж---------------------------------------------------------------
        database.addCustomer("И.Н.", "Карпук", 2);
        database.addCustomer("В.А.", "Король", 2);
        database.addCustomer("Л.И.", "Курак", 2);
        database.addCustomer("Л.С.", "Шпилевская", 2);
        database.addCustomer("И.В.", "Яцкевич", 2);
//-----------------------------Монтаж Крот----------------------------------------------------------
        database.addCustomer("А.С.", "Крот", 5);
        database.addCustomer("Г.Н.", "Штылев", 5);
        database.addCustomer("О.Н.", "Антонова", 5);
//--------------------------------------------------------------------------------------------------






    }
}
