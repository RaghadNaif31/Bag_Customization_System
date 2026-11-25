/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Adds an optional gift card for SAR 5. */
public class GiftCardDecorator extends BagDecorator {
    public GiftCardDecorator(Bag bag) { super(bag); }

    //--------------------------------------------------------------------------
    @Override
    public String getDescription() { return wrapped.getDescription() + " | Gift Card"; }

    //--------------------------------------------------------------------------
    @Override
    public double getPrice() { return wrapped.getPrice() + 5; }
}
