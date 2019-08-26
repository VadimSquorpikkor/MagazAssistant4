package com.squorpikkor.app.magazassistant4;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.squorpikkor.app.magazassistant4.customer.Customer;
import com.squorpikkor.app.magazassistant4.juice.JuiceFragment;
import com.squorpikkor.app.magazassistant4.juice.JuicePack;
import com.squorpikkor.app.magazassistant4.order.Order;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);

        customerList.add(new Customer("Максим", "Шустов", 0));
        customerList.add(new Customer("Ваня", "Махнюков", 0));
        customerList.add(new Customer("Олег", "Алисевич", 0));


    }

//----------JUICE PACK------------------------------------------------------------------------------

    private List<JuicePack> juicesList = new ArrayList<>();

    public List<JuicePack> getJuicesList() {
        return juicesList;
    }

    public void addPack(String name, Float price, int count) {
        juicesList.add(new JuicePack(name, price, count));
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

//----------CUSTOMER--------------------------------------------------------------------------------

    private ArrayList<Customer> customerList = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Customer> getCustomersList() {
        return customerList;
    }

    public void setCustomersList(ArrayList<Customer> customersList) {
        this.customerList = customersList;
    }

}
