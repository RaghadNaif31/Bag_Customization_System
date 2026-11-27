/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Adds optional gift wrapping for SAR 10. */
public class GiftWrapDecorator extends BagDecorator {
    public GiftWrapDecorator(Bag bag) { super(bag); }

    //--------------------------------------------------------------------------
    @Override
    public String getDescription() { return wrapped.getDescription() + " | Gift Wrap"; }

    //--------------------------------------------------------------------------
    @Override
    public double getPrice() { return wrapped.getPrice() + 10; }
}
