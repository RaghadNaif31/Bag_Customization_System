/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Holds selected products and calculates the subtotal. */
public class ShoppingCart {
    private final List<Bag> items = new ArrayList<>();

    public void add(Bag bag) { items.add(bag); }

    //--------------------------------------------------------------------------
    public void remove(int index) { items.remove(index); }

    //--------------------------------------------------------------------------
    public List<Bag> getItems() { return Collections.unmodifiableList(items); }

    //--------------------------------------------------------------------------
    public boolean isEmpty() { return items.isEmpty(); }

    //--------------------------------------------------------------------------
    public void clear() { items.clear(); }

    //--------------------------------------------------------------------------
    public double getSubtotal() {
        double total = 0;
        for (Bag bag : items) total += bag.getPrice();
        return total;
    }
}
