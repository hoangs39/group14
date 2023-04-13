package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

public class DigitalProduct extends Product {
    
    // Constructors
    public DigitalProduct(String name, String description, int quantity, double price) {
        super(name, description, quantity, price);
    }

    // toString
    @Override
    public String toString() {
        return "DIGITAL - " + getName();
    }
    
    @Override
    public String viewProductDetail() {
        return String.format("DIGITAL - %s - %s - %d - %.2f", getName(), getDescription(), getQuantity(), getPrice());
    }
}
