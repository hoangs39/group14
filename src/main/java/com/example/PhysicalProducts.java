package com.example;

/**
 * A specific class that manage the weight attribute.
 */
public class PhysicalProducts extends Product{
    /**
     * Physical product attribute
     */
    private double weight;
    
    // constructors
    public PhysicalProducts(String name, double price, String description, int quantity,String taxType) {
        super(name, price, description,quantity, taxType);
    }
    
    // overriden functions
    @Override
    public String toString() {
        return String.format("PHYSICAL - %s", getName());
    }
    @Override
    public String getProductType() {
        return "PHYSICAL";
    }
    @Override
    public String getDisplayInfo() {
        return String.format("%s  [type= %s, description= %s, quantity= %d, price= %,.2f, weight= %,.2f, message= %s, taxType= %s, tax= %,.2f]", 
        getName(), getProductType(), getDescription(), getAvailableQuantity(), getPrice(), getWeight(), getMessage(), getTaxType(), getTaxAmount());
    }

    // getter and setter
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    
}
