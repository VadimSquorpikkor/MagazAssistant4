package com.squorpikkor.app.magazassistant4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

//      В проекте TrenkaAssistant_4 я пытался сделать класс работы с базой данных SQLite с
//  использованием нескольких итаблиц с разными типами связи друг с другом (один ко многим) и с
//  запросами sql, вытягивающими данные сразу с нескольких таблиц (через foreign key от одной
//  таблицы к другой), что в итоге превратилось в просто адский код, уже почти не понятный для
//  меня уже в момент написания.
//      В этом приложении я решил пойти другим путем -- база данных так же будет разделена на
//  несколько таблиц, но при этом каждая таблица знать ничего не будет о других таблицах, все
//  foreign key будут работать на уровне классов, а не на уровне БД. Т.е., например, класс User
//  будет через DBHelper сохранять данные в БД, при этом в таблицу, например, списка покупок на
//  месте ячеек имени будет заносить ID юзера из таблицы Юзеров (такой аналог один ко многим, но
//  на уровне класса). При чтении данных, класс покупок (в момент создания конструктором, например)
//  будет вытягивать данные из соответствующей таблицы, а значения юзера будет уже вытягивать из
//  таблицы Юзеров по ID, сохраненному как integer в ячейках таблицы. Так данные будут храниться по
//  нормальной форме, при этом, что самое главное, все запросы sql будут как при работе с одной
//  таблицей. Так класс BDHelper состоит из нескольких простых хелперов, написание и расширение
//  такого класса должно быть не сложной задачей. Как вариант -- данные может формировать из
//  нескольких простых запросов сам класс DBHelper, я ещё подумаю, но скорее все-таки нет

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "magaz_db";
    //-----------------------------------------------------------------
    private static final String TABLE_CUSTOMERS = "customers_table";
    private static final String COLUMN_CUS_ID = "id";
    private static final String COLUMN_CUS_NAME = "name";
    private static final String COLUMN_CUS_SURNAME = "surname";
    //-----------------------------------------------------------------
    public static final String TABLE_JUICES = "juices_table";
    public static final String COLUMN_J_ID = "id";
    public static final String COLUMN_J_NAME = "name";
    public static final String COLUMN_J_PRICE = "price";

    //По поводу ID: при создании нового RA_Source ID у него ещё нет, как только создается
    //экземпляр класса, он сразу же заносится в БД. ID объекта ещё нет, в базе ID уже есть
    //как же загрузить конкретный объект класса, если для этого нужно знать его ID?
    //Очень просто. После того, как объект загружается в БД, вызывается метод getAll
    //Все объекты загружаются в лист, а адаптер обновляет ListView, таким образом только что
    //созданный объект класса появляется в активити как элемент списка. При этом в момент загрузки
    //из БД методом getAll объект получает свой ID. Voila

    DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CUSTOMERS + "("
        + COLUMN_CUS_ID + "INTEGER PRIMARY KEY, "
        + COLUMN_CUS_NAME + "TEXT, "
        + COLUMN_CUS_SURNAME + "TEXT"
        + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
