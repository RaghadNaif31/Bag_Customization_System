/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Cash on delivery demonstration strategy. */
public class CashOnDelivery implements PaymentStrategy {
    @Override
    public String getName() { return "Cash on Delivery"; }

    //--------------------------------------------------------------------------
    @Override
    public boolean pay(double amount) { return amount > 0; }
}
