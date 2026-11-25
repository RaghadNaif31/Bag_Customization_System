/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Decorator base class for optional gift services. */
public abstract class BagDecorator extends Bag {
    protected final Bag wrapped;

    protected BagDecorator(Bag wrapped) {
        super(wrapped.getModel(), 0, "Black", "Medium", "Canvas", "");
        this.wrapped = wrapped;
    }

    //--------------------------------------------------------------------------
    @Override
    public String getDescription() { return wrapped.getDescription(); }

    //--------------------------------------------------------------------------
    @Override
    public double getPrice() { return wrapped.getPrice(); }
}
