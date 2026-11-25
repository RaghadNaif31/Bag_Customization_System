/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Concrete State: Delivered order. */
public class DeliveredState implements OrderState {
    @Override
    public String getName() { return "Delivered"; }

    //--------------------------------------------------------------------------
    @Override
    public void advance(Order order) {
        throw new IllegalStateException("This order has already been delivered.");
    }
}

