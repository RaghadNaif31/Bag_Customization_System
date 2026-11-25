/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Demo card payment. No actual card information is collected. */
public class CardPayment implements PaymentStrategy {
    @Override
    public String getName() { return "Card (Demo)"; }

    //--------------------------------------------------------------------------
    @Override
    public boolean pay(double amount) { return amount > 0; }
}
