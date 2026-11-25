/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Strategy: interchangeable demonstration payment methods. */
public interface PaymentStrategy {
    String getName();

    //--------------------------------------------------------------------------
    boolean pay(double amount);
}