package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

import java.util.Scanner;

public abstract class Product implements GiftProduct{
    // Attributes
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String message;

    // Constructors
    public Product(String name, String description, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, String description, int quantity, double price, String message) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.message = message;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }   

    // toString
    public abstract String toString();

    /**
     * This function will provide all the information of a product
     * @return a string that contain all the product's information
     */
    public abstract String viewProductDetail();
    
    /**
     * This method will detect if the product is digital
     * @return true if the product is a DigitalProduct
     */
    public boolean isDigital() {
        return this instanceof DigitalProduct;
    }

    /**
     * This method will detect if the product is physical
     * @return true if the product is a PhysicalProduct
     */
    public boolean isPhysical() {
        return this instanceof PhysicalProduct;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your message: ");
        this.message = scanner.nextLine();
    }
}
