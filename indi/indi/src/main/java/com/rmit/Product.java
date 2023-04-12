package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

 /**
 * A general Class that manage all common attributes and methods of a product.
 */
public abstract class Product implements Gift<Product>{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String message;
    public Product(String name, double price,String description, int quantity){
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
    
    public abstract String view();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    abstract public String displayAll();
    

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

}
