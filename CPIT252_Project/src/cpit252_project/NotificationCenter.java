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

/** Concrete Observer: stores messages shown in the notifications page. */
public class NotificationCenter implements OrderObserver {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void onStatusChanged(Order order, String newStatus) {
        messages.add("Order #" + order.getId() + " is now " + newStatus + ".");
    }

    //--------------------------------------------------------------------------
    public void addPlacedNotice(Order order) {
        messages.add("Order #" + order.getId() + " was placed successfully.");
    }

    //--------------------------------------------------------------------------
    public List<String> getMessages() { return Collections.unmodifiableList(messages); }
}
