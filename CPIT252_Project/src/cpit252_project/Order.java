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

/** Order context for State; also publishes Observer notifications. */
public class Order {
    private static int nextId = 1001;
    private final int id;
    private final List<Bag> items;
    private final double total;
    private final String paymentMethod;
    private final List<OrderObserver> observers = new ArrayList<>();
    private OrderState state = new PendingState();

    public Order(List<Bag> items, double total, String paymentMethod) {
        id = nextId++;
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    //--------------------------------------------------------------------------
    public int getId() { return id; }

    //--------------------------------------------------------------------------
    public List<Bag> getItems() { return items; }

    //--------------------------------------------------------------------------
    public double getTotal() { return total; }

    //--------------------------------------------------------------------------
    public String getPaymentMethod() { return paymentMethod; }

    //--------------------------------------------------------------------------
    public String getStatus() { return state.getName(); }

    //--------------------------------------------------------------------------
    public void addObserver(OrderObserver observer) {
        if (observer != null && !observers.contains(observer)) observers.add(observer);
    }

    //--------------------------------------------------------------------------
    /** Delegates the transition to the current state. */
    public void advanceStatus() { state.advance(this); }

    //--------------------------------------------------------------------------
    /** Updates state and notifies every registered observer. */
    void changeState(OrderState newState) {
        state = newState;
        for (OrderObserver observer : observers) {
            observer.onStatusChanged(this, state.getName());
        }
    }
}
