package com.squorpikkor.app.magazassistant4;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.squorpikkor.app.magazassistant4.customer.Customer;
import com.squorpikkor.app.magazassistant4.juice.JuiceFragment;
import com.squorpikkor.app.magazassistant4.juice.Juice;
import com.squorpikkor.app.magazassistant4.order.Order;
import com.squorpikkor.app.magazassistant4.order.Product;
import com.squorpikkor.app.magazassistant4.settings.SettingsDefault2;

import java.util.ArrayList;
import java.util.List;

import static com.squorpikkor.app.magazassistant4.MainActivity.TAG;

/**Все данные классы будут брать из MVM, БД дяя них существовать НЕ БУДЕТ. Сама же MVM, получает
 * данные из БД при загрузке (при старте программы). Если класс изменяет или добавляет/удаляет
 * какие-то данные, MVM заносит эти данные в БД, а затем обновляет значения своих полей из БД.
 * Таким образом, если классам нужны какие-то данные, они берут их из MVM, если изменяют -- MVM сама
 * заносит их в БД.
 */


public class MainViewModel extends AndroidViewModel {

    private DatabaseHelper db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        Log.e(TAG, "------------------------MainViewModel: STARTS");

            db = new DatabaseHelper(getApplication());
//            departments = db.getAllDepartmentsSorted();
    }


    //////////////////public static final int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

    // To calculate the total height of all items in ListView call with items = adapter.getCount()
/*    public static int getItemHeightofListView(ListView listView, int items) {
        ListAdapter adapter = listView.getAdapter();

        int grossElementHeight = 0;
        for (int i = 0; i < items; i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(UNBOUNDED, UNBOUNDED);
            Log.e(TAG, "**********size of " + i + " item = " + childView.getMeasuredHeight());
            grossElementHeight += childView.getMeasuredHeight();
        }
        return grossElementHeight;
    }*/
//----------DEPARTMENT------------------------------------------------------------------------------

    private ArrayList<Department> departments;

    public ArrayList<Department> getDepartments() {
        //todo почемуто не работает, если при старте записывать в department
//        return departments;
        return db.getAllDepartmentsSorted();
    }

    //обновляет информацию об отделах и всех кастомеров
    private void updateDepartment() {
        db.getAllDepartmentsSorted();
    }

    public void setDefaultSettings() {
        db.deleteAllDepartments();
        db.deleteAllCustomers();
        SettingsDefault2 settingsDefault2 = new SettingsDefault2();
        settingsDefault2.setDefaultSettings(db);
    }

//----------CUSTOMER--------------------------------------------------------------------------------

    public void updateCustomer(Customer customer) {
        db.updateCustomer(customer);
        updateDepartment();

    }
//----------ORDER-----------------------------------------------------------------------------------
    public ArrayList<Order> getOrderList() {
        ArrayList<Customer> customers = db.getAllCustomers();

        return orderList;
    }

    //todo Пока заплатка, потом сделать нормально
    public ArrayList<Order> getOrderListWithoutKorelin() {
        //Перебирает весь список кастомеров из БД, если кастомер не из корелинского отдела, добавляет в список
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        for (Customer customer:db.getAllCustomers()) {
            if (customer.getDepName()!=1) customers.add(customer);
//            orders.add(new Order())
        }
        //вместо всех корелинских добавляю кастомера "Корели", он будет за весь отдел
        customers.add(new Customer("1-корп", "Корелин,", 1));

        //формирую список ордеров (пока пустой), соответствующий каждому из кастомеров (по ID)
        for (Customer customer:customers) {
            orders.add(new Order(customer.getID(), customer.getName(), customer.getSurname()));
        }

        //т.е. перебираем поочереди весь список продуктов, полученный из БД, смотрим, к какому кастомеру относится этот продукт,
        //берем список ордеров, находим в нем ордер этого кастомера, берем в этом ордере список продуктов и добавляем полученный
        //из БД продукт в этот список
        for (Product product : db.getAllProducts() ) {
            orders.get(product.getCustomer()).getProducts().add(product);
        }

        return orders;
    }

    public void updateOrder(Order order) {

    }



    private ArrayList<Customer> customerList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();



    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }


    public ArrayList<Customer> getCustomersList() {
        return customerList;
    }

    public void setCustomersList(ArrayList<Customer> customersList) {
        this.customerList = customersList;
    }


    public void onStart() {

    }

//----------PRODUCT PACK----------------------------------------------------------------------------

    public void addProduct(String title, float price, int quantity, int customerID) {
        db.addProduct(title, price, quantity, customerID);
    }

    public void addProduct(String title, int quantity, int customerID) {
        Log.e(TAG, "addProduct: title - " + title + " quantity - " + quantity + " customerID - " + customerID);
        db.addProduct(title, 0, quantity, customerID);
    }

//----------JUICE PACK------------------------------------------------------------------------------

    private List<Juice> juicesList = new ArrayList<>();

    public List<Juice> getJuicesList() {
        return juicesList;
    }

    public void addPack(String name, float price, int count) {
        juicesList.add(new Juice(name, price, count));
    }

    public void addJuice(String title, float price, int quantity, int customerID) {
        db.addJuice(title, price, quantity, customerID);
    }

    public void addJuice(String title, int quantity, int customerID) {
        db.addJuice(title, 0, quantity, customerID);
    }

//----------FRAGMENTS-------------------------------------------------------------------------------

    private JuiceFragment juiceFragment;

    public JuiceFragment getJuiceFragment() {
        return juiceFragment;
    }

    public void setJuiceFragment(JuiceFragment juiceFragment) {
        this.juiceFragment = juiceFragment;
    }

//----------ЦЕНЫ------------------------------------------------------------------------------------

    private float juiceBigPrice;

    private float juiceSmallPrice;

    private float milkBigPrice;

    private float milkSmallPrice;

    public float getJuiceBigPrice() {
        return juiceBigPrice;
    }

    public void setJuiceBigPrice(float juiceBigPrice) {
        this.juiceBigPrice = juiceBigPrice;
    }

    public float getJuiceSmallPrice() {
        return juiceSmallPrice;
    }

    public void setJuiceSmallPrice(float juiceSmallPrice) {
        this.juiceSmallPrice = juiceSmallPrice;
    }

    public float getMilkBigPrice() {
        return milkBigPrice;
    }

    public void setMilkBigPrice(float milkBigPrice) {
        this.milkBigPrice = milkBigPrice;
    }

    public float getMilkSmallPrice() {
        return milkSmallPrice;
    }

    public void setMilkSmallPrice(float milkSmallPrice) {
        this.milkSmallPrice = milkSmallPrice;
    }

//----------НАКЛАДНАЯ-------------------------------------------------------------------------------

    private int juiceInvoiceCount;

    private int juiceBigCount;

    private int juiceSmallCount;

    private int milkBigCount;

    private int milkSmallCount;

    private float invoiceTotalPrice;

    public int getJuiceInvoiceCount() {
        return juiceInvoiceCount;
    }

    public void setJuiceInvoiceCount(int juiceInvoiceCount) {
        this.juiceInvoiceCount = juiceInvoiceCount;
    }

    public int getJuiceBigCount() {
        return juiceBigCount;
    }

    public void setJuiceBigCount(int juiceBigCount) {
        this.juiceBigCount = juiceBigCount;
    }

    public int getJuiceSmallCount() {
        return juiceSmallCount;
    }

    public void setJuiceSmallCount(int juiceSmallCount) {
        this.juiceSmallCount = juiceSmallCount;
    }

    public int getMilkBigCount() {
        return milkBigCount;
    }

    public void setMilkBigCount(int milkBigCount) {
        this.milkBigCount = milkBigCount;
    }

    public int getMilkSmallCount() {
        return milkSmallCount;
    }

    public void setMilkSmallCount(int milkSmallCount) {
        this.milkSmallCount = milkSmallCount;
    }

    public float getInvoiceTotalPrice() {
        return invoiceTotalPrice;
    }

    public void setInvoiceTotalPrice(float invoiceTotalPrice) {
        this.invoiceTotalPrice = invoiceTotalPrice;
    }

//----------КОЛИЧЕСТВО ЛЮДЕЙ------------------------------------------------------------------------

    //todo useless
    private int korelinCount;
    private int sborkaCount;
    private int montazhCount;
    private int krotCount;
    private int pranevichCount;
    private int podvalCount;
    private int totalCustomersCount;

    public int getKorelinCount() {
        return korelinCount;
    }

    public void setKorelinCount(int korelinCount) {
        this.korelinCount = korelinCount;
    }

    public int getSborkaCount() {
        return sborkaCount;
    }

    public void setSborkaCount(int sborkaCount) {
        this.sborkaCount = sborkaCount;
    }

    public int getMontazhCount() {
        return montazhCount;
    }

    public void setMontazhCount(int montazhCount) {
        this.montazhCount = montazhCount;
    }

    public int getKrotCount() {
        return krotCount;
    }

    public void setKrotCount(int krotCount) {
        this.krotCount = krotCount;
    }

    public int getPranevichCount() {
        return pranevichCount;
    }

    public void setPranevichCount(int pranevichCount) {
        this.pranevichCount = pranevichCount;
    }

    public int getPodvalCount() {
        return podvalCount;
    }

    public void setPodvalCount(int podvalCount) {
        this.podvalCount = podvalCount;
    }

    public int getTotalCustomersCount() {
        return totalCustomersCount;
    }

    public void setTotalCustomersCount(int totalCustomersCount) {
        this.totalCustomersCount = totalCustomersCount;
    }


}
