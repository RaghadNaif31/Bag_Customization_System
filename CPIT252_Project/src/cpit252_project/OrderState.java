/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** State interface: defines an allowed order-status transition. */
public interface OrderState {
    String getName();

    //--------------------------------------------------------------------------
    void advance(Order order);
}
