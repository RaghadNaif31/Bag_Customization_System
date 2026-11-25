/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */


/** Builder: validates and collects customization options before creation. */
public class BagBuilder {
    private final String model;
    private String color = "Black";
    private String size = "Medium";
    private String material = "Canvas";
    private String initials = "";

    public BagBuilder(String model) {
        this.model = model;
    }

    //--------------------------------------------------------------------------
    public BagBuilder color(String value) {
        if (!value.matches("Black|Beige|Brown|Burgundy")) {
            throw new IllegalArgumentException("Invalid color.");
        }
        color = value;
        return this;
    }

    //--------------------------------------------------------------------------
    public BagBuilder size(String value) {
        if (!value.matches("Small|Medium|Large")) {
            throw new IllegalArgumentException("Invalid size.");
        }
        size = value;
        return this;
    }

    //--------------------------------------------------------------------------
    public BagBuilder material(String value) {
        if (!value.matches("Canvas|Nylon|Premium Leather")) {
            throw new IllegalArgumentException("Invalid material.");
        }
        material = value;
        return this;
    }

    //--------------------------------------------------------------------------
    public BagBuilder initials(String value) {
        String normalized = value == null ? "" : value.trim().toUpperCase();
        if (!normalized.matches("[A-Z]{0,10}")) {
            throw new IllegalArgumentException("Initials must be 0-10 English letters.");
        }
        initials = normalized;
        return this;
    }

    //--------------------------------------------------------------------------
    /** Builds the selected product using the Factory Method creator. */
    public Bag build() {
        BagFactory creator = new BagFactory.CatalogCreator();
        return creator.createBag(model, color, size, material, initials);
    }
}
