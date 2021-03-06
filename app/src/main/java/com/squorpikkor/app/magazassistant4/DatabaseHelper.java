package com.squorpikkor.app.magazassistant4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.squorpikkor.app.magazassistant4.customer.Customer;
import com.squorpikkor.app.magazassistant4.juice.Juice;
import com.squorpikkor.app.magazassistant4.order.Product;

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
    private static final String COLUMN_CUS_ON_DUTY = "onduty";
    private static final String COLUMN_CUS_DEPARTMENT = "department";
    //--------------------------------------------------------------------------------------------------
    private static final String TABLE_DEPARTMENT = "department_table";
    private static final String COLUMN_DEP_ID = "id";
    private static final String COLUMN_DEP_NAME = "name";
    private static final String COLUMN_DEP_KOEF = "koef";//juice per week for each customer in current dep
    //--------------------------------------------------------------------------------------------------
    private static final String TABLE_PRODUCTS = "products_table";
    private static final String COLUMN_PROD_ID = "id";
    private static final String COLUMN_PROD_TITLE = "title";
    private static final String COLUMN_PROD_PRICE = "price";
    private static final String COLUMN_PROD_QUANTITY = "quantity";
    private static final String COLUMN_PROD_ISJUICE = "prod_is_juice";
    private static final String COLUMN_PROD_PURCHASED = "purchased";
    private static final String COLUMN_PROD_CUSTOMER = "customer";
    //--------------------------------------------------------------------------------------------------
    private static final String TABLE_JUICES = "juices_table";
    private static final String COLUMN_J_ID = "id";
    private static final String COLUMN_J_TITLE = "j_name";
    private static final String COLUMN_J_PRICE = "j_price";
    private static final String COLUMN_J_QUANTITY = "j_quantity";
    private static final String COLUMN_J_PURCHASED = "j_purchased";
    private static final String COLUMN_J_CUSTOMER = "j_customer";

    //По поводу ID: при создании нового RA_Source ID у него ещё нет, как только создается
    //экземпляр класса, он сразу же заносится в БД. ID объекта ещё нет, в базе ID уже есть
    //как же загрузить конкретный объект класса, если для этого нужно знать его ID?
    //Очень просто. После того, как объект загружается в БД, вызывается метод getAll
    //Все объекты загружаются в лист, а адаптер обновляет ListView, таким образом только что
    //созданный объект класса появляется в активити как элемент списка. При этом в момент загрузки
    //из БД методом getAll объект получает свой ID. Voila
    private static final String MY_TAG = "my_tag";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_CUSTOMERS + "("
                + COLUMN_CUS_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CUS_NAME + " TEXT, "
                + COLUMN_CUS_SURNAME + " TEXT,"
                + COLUMN_CUS_ON_DUTY + " INTEGER," //нет boolean в sqlite, использую 0 и 1
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
                + COLUMN_J_TITLE + " TEXT,"
                + COLUMN_J_PRICE + " INTEGER,"
                + COLUMN_J_QUANTITY + " INTEGER,"
                + COLUMN_J_PURCHASED + " TEXT,"
                + COLUMN_J_CUSTOMER + " INTEGER"
                + ")"
        );

        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PROD_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PROD_TITLE + " TEXT,"
                + COLUMN_PROD_PRICE + " REAL,"
                + COLUMN_PROD_QUANTITY + " INTEGER,"
                + COLUMN_PROD_ISJUICE + " INTEGER,"
                + COLUMN_PROD_PURCHASED + " INTEGER,"
                + COLUMN_PROD_CUSTOMER + " INTEGER"
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

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

//---------------CUSTOMERS METHODS------------------------------------------------------------------
//region CUSTOMERS METHODS

    public void addCustomer(String name, String surname, int department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUS_NAME, name);
        values.put(COLUMN_CUS_SURNAME, surname);
        values.put(COLUMN_CUS_DEPARTMENT, department);
        values.put(COLUMN_CUS_ON_DUTY, 1);
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
                        COLUMN_CUS_ON_DUTY,
                        COLUMN_CUS_DEPARTMENT
                }, COLUMN_CUS_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Customer customer = new Customer();
        if (cursor != null) {
            cursor.moveToFirst();

            customer.setID(Integer.parseInt(cursor.getString(0)));
            customer.setName(cursor.getString(1));
            customer.setSurname(cursor.getString(2));
            customer.setDepName(Integer.parseInt(cursor.getString(4)));
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
                customer.setWorking(Integer.parseInt(cursor.getString(3))==1);  // если ==1, возвращает true
                customer.setDepName(Integer.parseInt(cursor.getString(4)));
                sourceList.add(customer);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return sourceList;
    }

    //todo или использовать вместо этого updateCustomer?
    public int setCustomerWorkingStatus(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CUS_ON_DUTY, customer.isWorking()?1:0);

        return db.update(TABLE_CUSTOMERS, values, COLUMN_CUS_ID + " = ?",
                new String[]{String.valueOf(customer.getID())});
    }

    //TODO сделать void? с другой стороны так будет просто сделать проверку на успешную запись
    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CUS_NAME, customer.getName());
        values.put(COLUMN_CUS_SURNAME, customer.getSurname());
        values.put(COLUMN_CUS_ON_DUTY, customer.isWorking()?1:0);
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

//endregion
//---------------DEPARTMENT METHODS-----------------------------------------------------------------
//region DEPARTMENTS METHODS

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
//        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT + " WHERE " + COLUMN_DEP_ID + " <> '1'"; //т.е. без департмента где я храню кастомера, который весь отдел (корелин)

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
        Log.e(TAG, "getAllDepartments: SIZE " + sourceList.size());
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

    public ArrayList<Department> getAllDepartmentsAccordingTo() {
        ArrayList<Department> departments = getAllDepartments();
        ArrayList<Customer> customers = getAllCustomers();
        for (Customer customer : customers) {
            departments.get(customer.getDepName()).getCurrentDepCustomers().add(customer);
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
//endregion
//---------------PRODUCTS METHODS-------------------------------------------------------------------
//region PRODUCTS METHODS
    public void addProduct(String title, double price, int quantity, int customer) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_PROD_TITLE, title);
    values.put(COLUMN_PROD_PRICE, price);
    values.put(COLUMN_PROD_QUANTITY, quantity);
    values.put(COLUMN_PROD_PURCHASED, "false");
    values.put(COLUMN_PROD_CUSTOMER, customer);

    db.insert(TABLE_PRODUCTS, null, values);
    db.close();
}

    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{
                        COLUMN_PROD_ID,
                        COLUMN_PROD_TITLE,
                        COLUMN_PROD_PRICE,
                        COLUMN_PROD_QUANTITY,
                        COLUMN_PROD_PURCHASED,
                        COLUMN_PROD_CUSTOMER
                }, COLUMN_CUS_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Product product = new Product();
        if (cursor != null) {
            cursor.moveToFirst();

            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setTitle(cursor.getString(1));
            product.setPrice(Float.parseFloat(cursor.getString(2)));
            product.setQuantity(Integer.parseInt(cursor.getString(3)));
            product.setIsJuice(cursor.getInt(4) == 1);//if 1, isJuice = true
            product.setPurchased(cursor.getInt(5)== 1);
            product.setCustomer(Integer.parseInt(cursor.getString(6)));
        }

        if (cursor != null) {
            cursor.close();
        }

        return product;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> prodList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setTitle(cursor.getString(1));
                product.setPrice(Float.parseFloat(cursor.getString(2)));
                product.setQuantity(Integer.parseInt(cursor.getString(3)));
                product.setIsJuice(cursor.getInt(4) == 1);//if 1, isJuice = true
                product.setPurchased(cursor.getInt(5)== 1);
                product.setCustomer(cursor.getInt(6));
                prodList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return prodList;
    }

    public ArrayList<Product> getProductsByID(int id) {
        ArrayList<Product> prodList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PROD_CUSTOMER + " = '" + id + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setTitle(cursor.getString(1));
                product.setPrice(Float.parseFloat(cursor.getString(2)));
                product.setQuantity(Integer.parseInt(cursor.getString(3)));
                product.setIsJuice(cursor.getInt(4) == 1);//if 1, isJuice = true
                product.setPurchased(cursor.getInt(5)== 1);
                product.setCustomer(cursor.getInt(6));
                prodList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        Log.e(TAG, "getProductsByID: prodList size = " + prodList.size());

        return prodList;
    }

/*          + COLUMN_PROD_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_PROD_TITLE + " TEXT,"
            + COLUMN_PROD_PRICE + " REAL,"
            + COLUMN_PROD_QUANTITY + " INTEGER,"
            + COLUMN_PROD_PURCHASED + " TEXT,"
            + COLUMN_PROD_CUSTOMER + " INTEGER"*/

    //TODO сделать void?
    public int updateProducts(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROD_TITLE, product.getTitle());
        values.put(COLUMN_PROD_PRICE, product.getPrice());
        values.put(COLUMN_PROD_QUANTITY, product.getQuantity());
        if(product.isPurchased())values.put(COLUMN_PROD_PURCHASED, "true");
        else values.put(COLUMN_PROD_PURCHASED, "false");
        values.put(COLUMN_PROD_CUSTOMER, product.getCustomer());

        return db.update(TABLE_PRODUCTS, values, COLUMN_PROD_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
    }

    public void deleteProducts(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_PROD_ID + " = ?", new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public void deleteAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
//        db.execSQL("DELETE FROM " + TABLE_PRODUCTS);//то же самое, можно и как в предыдущей строке, и как в этой
        db.close();
    }

    public int getProductsQuantity() {
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        String countQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
//endregion
//---------------JUICES METHODS---------------------------------------------------------------------
//region JUICES METHODS
public void addJuice(String title, double price, int quantity, int customer) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_J_TITLE, title);
    values.put(COLUMN_J_PRICE, price);
    values.put(COLUMN_J_QUANTITY, quantity);
    values.put(COLUMN_J_PURCHASED, "false");
    values.put(COLUMN_J_CUSTOMER, customer);

    db.insert(TABLE_JUICES, null, values);
    db.close();
}

    public Juice getJuice(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_JUICES, new String[]{
                        COLUMN_J_ID,
                        COLUMN_J_TITLE,
                        COLUMN_J_PRICE,
                        COLUMN_J_QUANTITY,
                        COLUMN_J_PURCHASED,
                        COLUMN_J_CUSTOMER
                }, COLUMN_CUS_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Juice juice = new Juice();
        if (cursor != null) {
            cursor.moveToFirst();

            juice.setId(Integer.parseInt(cursor.getString(0)));
            juice.setTitle(cursor.getString(1));
            juice.setPrice(Float.parseFloat(cursor.getString(2)));
            juice.setQuantity(Integer.parseInt(cursor.getString(3)));
            //juice.setJuice(cursor.getString(4) == "true");//if "true", isJuice = true
            juice.setPurchased(cursor.getString(4).equals("true"));
            juice.setCustomer(Integer.parseInt(cursor.getString(5)));
        }

        if (cursor != null) {
            cursor.close();
        }

        return juice;
    }

    public ArrayList<Juice> getAllJuices() {
        ArrayList<Juice> juiceList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_JUICES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Juice juice = new Juice();
                juice.setId(Integer.parseInt(cursor.getString(0)));
                juice.setTitle(cursor.getString(1));
                juice.setPrice(Float.parseFloat(cursor.getString(2)));
                juice.setQuantity(Integer.parseInt(cursor.getString(3)));
                juice.setPurchased(cursor.getString(4).equals("true"));
                juice.setCustomer(Integer.parseInt(cursor.getString(5)));
                juiceList.add(juice);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return juiceList;
    }

    //TODO сделать void?
    public int updateJuice(Juice juice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_J_TITLE, juice.getTitle());
        values.put(COLUMN_J_PRICE, juice.getPrice());
        values.put(COLUMN_J_QUANTITY, juice.getQuantity());
        if(juice.isPurchased())values.put(COLUMN_J_PURCHASED, "true");
        else values.put(COLUMN_J_PURCHASED, "false");
        values.put(COLUMN_J_CUSTOMER, juice.getCustomer());

        return db.update(TABLE_JUICES, values, COLUMN_J_ID + " = ?",
                new String[]{String.valueOf(juice.getId())});
    }

    public void deleteJuice(Juice juice) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUICES, COLUMN_J_ID + " = ?", new String[]{String.valueOf(juice.getId())});
        db.close();
    }

    public void deleteAllJuices() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JUICES, null, null);
//        db.execSQL("DELETE FROM " + TABLE_JUICES);//то же самое, можно и как в предыдущей строке, и как в этой
        db.close();
    }

    public int getJuicesQuantity() {
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        String countQuery = "SELECT * FROM " + TABLE_JUICES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
//endregion
}
