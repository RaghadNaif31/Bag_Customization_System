/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */


/** A customizable store product. Decorators may wrap this product. */
public class Bag {
    private final String model;
    private final double basePrice;
    private final String color;
    private final String size;
    private final String material;
    private final String initials;

    public Bag(String model, double basePrice, String color,
               String size, String material, String initials) {
        this.model = model;
        this.basePrice = basePrice;
        this.color = color;
        this.size = size;
        this.material = material;
        this.initials = initials;
    }

    //--------------------------------------------------------------------------
    /** Returns the product model. */
    public String getModel() { return model; }

    //--------------------------------------------------------------------------
    /** Returns the selected options in a readable format. */
    public String getDescription() {
        String details = color + " | " + size + " | " + material;
        return initials.isEmpty() ? details : details + " | Initials: " + initials;
    }

    //--------------------------------------------------------------------------
    /** Calculates the bag price including selected customizations. */
    public double getPrice() {
      double result = basePrice;

      // Size pricing
     if ("Small".equals(size)) {
        result -= 15;
    } else if ("Large".equals(size)) {
        result += 25;
    }

      // Material pricing
      if ("Nylon".equals(material)) {
        result += 15;
    } else if ("Premium Leather".equals(material)) {
        result += 40;
    }

      // Initials pricing
     if (!initials.isEmpty()) {
        result += 15;
    }

    return result;
}}
