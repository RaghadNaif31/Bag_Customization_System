/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Concrete State: Pending order. */
public class PendingState implements OrderState {
    @Override
    public String getName() { return "Pending"; }

    //--------------------------------------------------------------------------

    @Override
    public void advance(Order order) {
        order.changeState(new ConfirmedState());
    }
}
