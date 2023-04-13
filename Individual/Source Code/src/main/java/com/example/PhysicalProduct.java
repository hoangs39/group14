package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

public class PhysicalProduct extends Product{
    // Attributes
    private double weight;

    // Constructors
    public PhysicalProduct(String name, String description, int quantity, double price, double weight) {
        super(name, description, quantity, price);
        this.weight = weight;
    }

    public PhysicalProduct(String name, String description, int quantity, double price, double weight, String message) {
        super(name, description, quantity, price, message);
        this.weight = weight;
    }

    // Getters
    public double getWeight() {
        return weight;
    }

    // Setters
    public void setWeight(double weight) {
        this.weight = weight;
    }   
    
    // toString
    @Override
    public String toString() {
        return "PHYSICAL - " + getName();
    }

    @Override
    public String viewProductDetail() {
        return String.format("PHYSICAL - %s - %s - %d - %.2f - %.1f", getName(), getDescription(), getQuantity(), getPrice(), getWeight());
    }
}
