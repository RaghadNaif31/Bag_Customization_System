/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit252_project;

/**
 *
 * @author ragha
 */

/**
 * Factory Method Pattern:
 * The abstract creator defines a method for creating bags.
 * The concrete creator decides which bag type to instantiate.
 */
public abstract class BagFactory {

    //--------------------------------------------------------------------------
    /**
     * Factory Method:
     * Creates a bag based on the selected model and customization options.
     */
    
    public abstract Bag createBag(
            String model,
            String color,
            String size,
            String material,
            String initials
    );

    //--------------------------------------------------------------------------
    /**
     * Concrete Creator:
     * Selects and creates one of the six available bag types.
     */
    public static class CatalogCreator extends BagFactory {

        //--------------------------------------------------------------------------
        @Override
        public Bag createBag(
                String model,
                String color,
                String size,
                String material,
                String initials
        ) {

            // Create the appropriate concrete bag based on the model.
            switch (model) {

                case "Tote Bag":
                    return new ToteBag(
                            color, size, material, initials
                    );

                case "Laptop Bag":
                    return new LaptopBag(
                            color, size, material, initials
                    );

                case "Backpack":
                    return new Backpack(
                            color, size, material, initials
                    );

                case "Crossbody Bag":
                    return new CrossbodyBag(
                            color, size, material, initials
                    );

                case "Travel Bag":
                    return new TravelBag(
                            color, size, material, initials
                    );

                case "Shoulder Bag":
                    return new ShoulderBag(
                            color, size, material, initials
                    );

                default:
                    throw new IllegalArgumentException(
                            "Unknown bag model: " + model
                    );
            }
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Tote Bag.
     * Defines the model name and its base price.
     */
    private static class ToteBag extends Bag {

        public ToteBag(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Tote Bag", 120, color, size, material, initials);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Laptop Bag.
     * Defines the model name and its base price.
     */
    private static class LaptopBag extends Bag {

        public LaptopBag(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Laptop Bag", 180, color, size, material, initials);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Backpack.
     * Defines the model name and its base price.
     */
    private static class Backpack extends Bag {

        public Backpack(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Backpack", 160, color, size, material, initials);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Crossbody Bag.
     * Defines the model name and its base price.
     */
    private static class CrossbodyBag extends Bag {

        public CrossbodyBag(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Crossbody Bag", 140, color, size, material, initials);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Travel Bag.
     * Defines the model name and its base price.
     */
    private static class TravelBag extends Bag {

        public TravelBag(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Travel Bag", 220, color, size, material, initials);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Concrete Product: Shoulder Bag.
     * Defines the model name and its base price.
     */
    private static class ShoulderBag extends Bag {

        public ShoulderBag(
                String color,
                String size,
                String material,
                String initials
        ) {
            super("Shoulder Bag", 150, color, size, material, initials);
        }
    }
}