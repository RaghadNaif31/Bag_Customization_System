/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Singleton: shared tax and delivery configuration. */
public final class StoreConfig {
    private static final StoreConfig INSTANCE = new StoreConfig();
    private StoreConfig() { }

    //--------------------------------------------------------------------------
    public static StoreConfig getInstance() { return INSTANCE; }

    //--------------------------------------------------------------------------
    public double getVatRate() { return 0.15; }

    //--------------------------------------------------------------------------
    public double getShippingFee() { return 20.0; }
}
