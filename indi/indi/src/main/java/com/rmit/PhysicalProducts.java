package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */
/**
 * A specific class that manage the weight attribute.
 */
public class PhysicalProducts extends Product{
    
    public PhysicalProducts(String name, double price, String description, int quantity) {
        super(name, price, description,quantity);
    }

    private double weight;
    
    
    @Override
    public String view() {
        return String.format("PHYSICAL - <%s>", this.getName());
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String displayAll() {
        return String.format("%s|%.1f|%s|%d|%.5f", 
        this.getName(),this.getPrice(),this.getDescription(),this.getQuantity(),this.getWeight());
    }
    
}
