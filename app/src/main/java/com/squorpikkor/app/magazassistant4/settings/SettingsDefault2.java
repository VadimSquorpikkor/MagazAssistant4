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
        database.addDepartment("Корелин всего",         3); //0
        database.addDepartment("Сборочный участок",     3); //1
        database.addDepartment("Корелин 1-й корпус",    3); //2
        database.addDepartment("Монтажный участок",     3); //3
        database.addDepartment("Праневич",              3); //4
        database.addDepartment("Цокольный этаж",        3); //5
        database.addDepartment("Монтаж 1-й этаж",       3); //6

//-----------------------------Корелин всего--------------------------------------------------------
        database.addCustomer("всего", "Корелин",    0);
//-----------------------------Сборка---------------------------------------------------------------
        database.addCustomer("Д.В.", "Алисевич",    1);
        database.addCustomer("О.В.", "Алисевич",    1);
        database.addCustomer("А.С.", "Бодялик",     1);
        database.addCustomer("А.А.", "Володкевич",  1);
        database.addCustomer("И.С.", "Махнюков",    1);
        database.addCustomer("Ю.В.", "Мороз",       1);
        database.addCustomer("Ю.В.", "Пекарский",   1);
        database.addCustomer("М.В.", "Шустов",      1);
//-----------------------------Праневич-------------------------------------------------------------
        database.addCustomer("А.А.", "Праневич",    4);
        database.addCustomer("М.В.", "Ячменев",     4);
//-----------------------------Цоколь---------------------------------------------------------------
        database.addCustomer("В.Ю.", "Дементьев",   5);
        database.addCustomer("А.Е.", "Яворский",    5);
//-----------------------------Корелин--------------------------------------------------------------
        database.addCustomer("С.Н.", "Барсуков",    2);
        database.addCustomer("Л.В.", "Буров",       2);
        database.addCustomer("Р.В.", "Видасев",     2);
        database.addCustomer("С.В.", "Денисенко",   2);
        database.addCustomer("Н.А.", "Дыба",        2);
        database.addCustomer("В.И.", "Зуевский",    2);
        database.addCustomer("Е.С.", "Каменщиков",  2);
        database.addCustomer("В.П.", "Кишкилевич",  2);
        database.addCustomer("А.Г.", "Колосовский", 2);
        database.addCustomer("В.Г.", "Любач",       2);
        database.addCustomer("А.М.", "Матвеенко",   2);
        database.addCustomer("М.В.", "Муравицкий",  2);
        database.addCustomer("А.А.", "Рычик",       2);
        database.addCustomer("А.К.", "Рябов",       2);
        database.addCustomer("Ю.Б.", "Станкевич",   2);
        database.addCustomer("И.А.", "Фролов",      2);
        database.addCustomer("С.А.", "Юревич",      2);
//-----------------------------Монтаж---------------------------------------------------------------
        database.addCustomer("И.Н.", "Карпук",      3);
        database.addCustomer("В.А.", "Король",      3);
        database.addCustomer("Л.И.", "Курак",       3);
        database.addCustomer("Л.С.", "Шпилевская",  3);
        database.addCustomer("И.В.", "Яцкевич",     3);
//-----------------------------Монтаж Крот----------------------------------------------------------
        database.addCustomer("А.С.", "Крот",        6);
        database.addCustomer("Г.Н.", "Штылев",      6);
        database.addCustomer("О.Н.", "Антонова",    6);
//--------------------------------------------------------------------------------------------------






    }
}
