package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

 /**
 * A specific class of Product class.
 */
public class DigitalProduct extends Product{

    public DigitalProduct(String name, double price, String description,int quantity,String taxType) {
        super(name, price, description,quantity, taxType);
    }

    @Override
    public String view() {
        return String.format("DIGITAL - <%s>", this.getName());
    }
    
    @Override
    public String displayAll() {
        return String.format("%s|%.1f|%s|%d", 
        this.getName(),this.getPrice(),this.getDescription(),this.getQuantity());
    }
    
}
