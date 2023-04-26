package com.example;

public class DigitalProduct extends Product{

    //Constructors
    public DigitalProduct(String name, double price, String description,int quantity,String taxType) {
        super(name, price, description,quantity, taxType);
    }

    //Overriden functions
    @Override
    public String toString() {
        return String.format("DIGITAL - %s", getName());
    }
    @Override
    public String getProductType() {
        return "DIGITAL";
    }
    @Override
    public String getDisplayInfo() {
        return String.format("%s  [type= %s, description= %s, quantity= %d, price= %,.2f, message= %s, taxType= %s, tax= %,.2f]", 
        getName(), getProductType(), getDescription(), getAvailableQuantity(), getPrice(), getMessage(), getTaxType(), getTaxAmount());
    }
    
}
