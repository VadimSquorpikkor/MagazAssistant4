package com.squorpikkor.app.magazassistant4.juice;

import com.squorpikkor.app.magazassistant4.order.Product;

/**Методов и конструкторов в классе не будет, класс аналогичен Product
 *
 */

public class Juice extends Product {

    public Juice(String name, float price, int count) {
        super(name, price, count);
    }

    public Juice() {
        super();
    }
}
