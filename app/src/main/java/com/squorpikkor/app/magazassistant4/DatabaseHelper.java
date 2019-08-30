package com.squorpikkor.app.magazassistant4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.squorpikkor.app.magazassistant4.customer.Customer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

//      ПАРАДИГМА МОЕГО DATABASEHELPERA:
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
//--------------------------------------------------------------------------------------------------
    private static final String TABLE_CUSTOMERS = "customers_table";
    private static final String COLUMN_CUS_ID = "id";
    private static final String COLUMN_CUS_NAME = "name";
    private static final String COLUMN_CUS_SURNAME = "surname";
    private static final String COLUMN_CUS_DEPARTMENT = "department";
//--------------------------------------------------------------------------------------------------
    private static final String TABLE_DEPARTMENT = "department_table";
    private static final String COLUMN_DEP_ID = "id";
    private static final String COLUMN_DEP_NAME = "name";
    private static final String COLUMN_DEP_KOEF = "koef";   //juice per week for each
                                                            // customer in current dep
//--------------------------------------------------------------------------------------------------

    private static final String TABLE_JUICES = "juices_table";
    private static final String COLUMN_J_ID = "id";
    private static final String COLUMN_J_NAME = "name";
    private static final String COLUMN_J_PRICE = "price";

    //По поводу ID: при создании нового RA_Source ID у него ещё нет, как только создается
    //экземпляр класса, он сразу же заносится в БД. ID объекта ещё нет, в базе ID уже есть
    //как же загрузить конкретный объект класса, если для этого нужно знать его ID?
    //Очень просто. После того, как объект загружается в БД, вызывается метод getAll
    //Все объекты загружаются в лист, а адаптер обновляет ListView, таким образом только что
    //созданный объект класса появляется в активити как элемент списка. При этом в момент загрузки
    //из БД методом getAll объект получает свой ID. Voila
    private static final String MY_TAG = "my_tag";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        String CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + "("
                + COLUMN_CUS_ID + "INTEGER PRIMARY KEY,"
                + COLUMN_CUS_NAME + "TEXT, "
                + COLUMN_CUS_SURNAME + "TEXT"
                + ")";
        db.execSQL(CREATE_CUSTOMERS_TABLE);
*/

        db.execSQL("CREATE TABLE " + TABLE_CUSTOMERS + "("
        + COLUMN_CUS_ID + " INTEGER PRIMARY KEY,"
        + COLUMN_CUS_NAME + " TEXT, "
        + COLUMN_CUS_SURNAME + " TEXT,"
        + COLUMN_CUS_DEPARTMENT + " INTEGER"
        + ")"
        );

        Log.e(MY_TAG, "onCreate: " + "table customers created");

        db.execSQL("CREATE TABLE " + TABLE_DEPARTMENT + "("
                + COLUMN_DEP_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DEP_NAME + " TEXT, "
                + COLUMN_DEP_KOEF + " INTEGER"
                + ")"
        );

        Log.e(MY_TAG, "onCreate: " + "table department created");


        db.execSQL("CREATE TABLE " + TABLE_JUICES + "("
                + COLUMN_J_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_J_NAME + " TEXT,"
                + COLUMN_J_PRICE + " INTEGER"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUICES);
        onCreate(db);
    }

//---------------CUSTOMERS METHODS------------------------------------------------------------------

    public void addCustomer(String name, String surname, int department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUS_NAME, name);
        values.put(COLUMN_CUS_SURNAME, surname);
        values.put(COLUMN_CUS_DEPARTMENT, department);
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close();
    }

    /*Обертка для addCustomer -- добавление в БД кастомера с данными по-умолчанию*/
    //todo а зачем?
    public void addCustomer() {
        addCustomer("name", "surname", 0);
    }


    public Customer getCustomer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CUSTOMERS, new String[]{COLUMN_CUS_ID,
                        COLUMN_CUS_NAME,
                        COLUMN_CUS_SURNAME,
                        COLUMN_CUS_DEPARTMENT
                }, COLUMN_CUS_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Customer customer = new Customer();
        if (cursor != null) {
            cursor.moveToFirst();

            customer.setID(Integer.parseInt(cursor.getString(0)));
            customer.setName(cursor.getString(1));
            customer.setSurname(cursor.getString(2));
            customer.setDepName(Integer.parseInt(cursor.getString(3)));
        }

        if (cursor != null) {
            cursor.close();
        }

        return customer;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> sourceList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setID(Integer.parseInt(cursor.getString(0)));
                customer.setName(cursor.getString(1));
                customer.setSurname(cursor.getString(2));
                customer.setDepName(Integer.parseInt(cursor.getString(3)));
                sourceList.add(customer);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return sourceList;
    }

    //TODO сделать void?
    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CUS_NAME, customer.getName());
        values.put(COLUMN_CUS_SURNAME, customer.getSurname());
        values.put(COLUMN_CUS_DEPARTMENT, customer.getDepName());

        return db.update(TABLE_CUSTOMERS, values, COLUMN_CUS_ID + " = ?",
                new String[]{String.valueOf(customer.getID())});
    }

    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, COLUMN_CUS_ID + " = ?", new String[]{String.valueOf(customer.getID())});
        db.close();
    }

    public void deleteAllCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMERS, null, null);
//        db.execSQL("DELETE FROM " + TABLE_CUSTOMERS);//то же самое, можно и как в предыдущей строке, и как в этой
        db.close();
    }

    public int getCustomerCount() {
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        String countQuery = "SELECT * FROM " + TABLE_CUSTOMERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

//---------------DEPARTMENT METHODS-----------------------------------------------------------------

    public void addDepartment(String name, int koef) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEP_NAME, name);
        values.put(COLUMN_DEP_KOEF, koef);
        db.insert(TABLE_DEPARTMENT, null, values);
        db.close();
    }

    public Department getDepartment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEPARTMENT, new String[]{COLUMN_DEP_ID,
                        COLUMN_DEP_NAME,
                        COLUMN_DEP_KOEF
                }, COLUMN_CUS_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Department department = new Department();
        if (cursor != null) {
            cursor.moveToFirst();

            department.setID(Integer.parseInt(cursor.getString(0)));
            department.setName(cursor.getString(1));
            department.setJuicePerWeek(Integer.parseInt(cursor.getString(2)));
        }

        if (cursor != null) {
            cursor.close();
        }

        return department;
    }

    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> sourceList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Department department = new Department();
                department.setID(Integer.parseInt(cursor.getString(0)));
                department.setName(cursor.getString(1));
                department.setJuicePerWeek(Integer.parseInt(cursor.getString(2)));
                sourceList.add(department);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return sourceList;
    }

    //Метод принимает на вход коллекции департментс и кастомерс и распределяет каждого кастомера по своему отделу
    //Этот метод заменяет сложный SQL запрос с выборкой по двум связанным таблицам (см. парадигму моего хелпера)
    public ArrayList<Department> getAllDepartmentsSorted() {
        //Вообще можнобыло бы и в одну строчку захреначить, но оставил как есть, чтоб понятнее было
        ////////// for (Customer customer : getAllCustomers()) getAllDepartments().get(customer.getDepName()).getCurrentDepCustomers().add(customer); /////////////
        ArrayList<Department> departments = getAllDepartments();
        ArrayList<Customer> customers = getAllCustomers();
        //т.е. беру человека, смотрю, какой номер отдела у него прописан, и добавляю его в
        // этот отдел. И так для всех людей, так я рассортировываю людей по отделам
        for (Customer customer : customers) {
            departments.get(customer.getDepName()).getCurrentDepCustomers().add(customer);
//            departments.get(0).getCurrentDepCustomers().add(customer);
        }
        return departments;
    }

/*    private void sortCustomers(ArrayList<Department> departments, ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            //т.е. беру человека, смотрю, какой номер отдела у него прописан, и добавляю его в
            // этот отдел. И так для всех людей, так я рассортировываю людей по отделам
            departments.get(customer.getDepName()).getCurrentDepCustomers().add(customer);
        }
    }*/

    //TODO сделать void?
    public int updateDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DEP_NAME, department.getName());
        values.put(COLUMN_DEP_KOEF, department.getJuicePerWeek());

        return db.update(TABLE_DEPARTMENT, values, COLUMN_DEP_ID + " = ?",
                new String[]{String.valueOf(department.getID())});
    }

    public void deleteDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENT, COLUMN_DEP_ID + " = ?", new String[]{String.valueOf(department.getID())});
        db.close();
    }

    public void deleteAllDepartments() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENT, null, null);
//        db.execSQL("DELETE FROM " + TABLE_DEPARTMENT);
        db.close();
//        }
    }

    public int getDepartmentsCount() {
        String countQuery = "SELECT * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }


}
