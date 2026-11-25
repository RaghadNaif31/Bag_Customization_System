/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

import java.util.HashMap;
import java.util.Map;

/** Facade: validates stock, charges a demo payment, and creates an order. */
public class CheckoutFacade {
    private final Map<String, Integer> stock = new HashMap<>();

    public CheckoutFacade() {
        for (String model : new String[]{"Tote Bag", "Laptop Bag", "Backpack",
                "Crossbody Bag", "Travel Bag", "Shoulder Bag"}) {
            stock.put(model, 10);
        }
    }

    //--------------------------------------------------------------------------
    public double calculateVat(ShoppingCart cart) {
        return cart.getSubtotal() * StoreConfig.getInstance().getVatRate();
    }

    //--------------------------------------------------------------------------
    public double calculateTotal(ShoppingCart cart) {
        if (cart.isEmpty()) {
            return 0.0;
        }
        return cart.getSubtotal() + calculateVat(cart)
                + StoreConfig.getInstance().getShippingFee();
    }

    //--------------------------------------------------------------------------
    /** Completes an order only after all stock checks pass. */
    public Order placeOrder(ShoppingCart cart, PaymentStrategy payment) {
        if (cart.isEmpty()) throw new IllegalStateException("Your cart is empty.");
        Map<String, Integer> required = new HashMap<>();
        for (Bag bag : cart.getItems()) {
            required.merge(bag.getModel(), 1, Integer::sum);
        }
        for (Map.Entry<String, Integer> entry : required.entrySet()) {
            if (stock.get(entry.getKey()) < entry.getValue()) {
                throw new IllegalStateException("Out of stock: " + entry.getKey());
            }
        }
        double total = calculateTotal(cart);
        if (!payment.pay(total)) throw new IllegalStateException("Payment was unsuccessful.");
        Order order = new Order(cart.getItems(), total, payment.getName());
        for (Map.Entry<String, Integer> entry : required.entrySet()) {
            stock.put(entry.getKey(), stock.get(entry.getKey()) - entry.getValue());
        }
        cart.clear();
        return order;
    }
}
