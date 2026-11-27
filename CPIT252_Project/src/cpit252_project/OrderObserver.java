/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/** Observer: notified whenever an order changes status. */
public interface OrderObserver {
    void onStatusChanged(Order order, String newStatus);
}
