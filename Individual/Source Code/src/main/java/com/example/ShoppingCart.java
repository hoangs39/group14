package com.example;
/**
 * The shopping cart class will contain products chosen by the user
 * @author <Hoang Vinh Khue - s3927474>
 */

import java.util.HashSet;

public class ShoppingCart implements Comparable<ShoppingCart> {
    // Attributes
    private HashSet<String> items;
    private double totalWeight;

    // Constructors
    public ShoppingCart() {
        this.totalWeight = 0;
        items = new HashSet<>();
    }

    // Getters
    public HashSet<String> getItems() {
        return items;
    }

    // Setters
    public void setItems(HashSet<String> items) {
        this.items = items;
    }

    /**
     * This function will add the desired item to the cart,
     * decreease the product's quantity by 1
     * @param productName the String representing the product's name
     * @return true if the product exists in the system and its quantity is > 0
     */
    public boolean addItem(String productName) {
        Product product = ProductService.getpServiceInstance().getProduct(productName);
        if (product == null || product.getQuantity() == 0 || items.contains(productName)) {
            return false;
        }
        items.add(productName);
        product.setQuantity(product.getQuantity() - 1);
        if (product.isPhysical()) {
            totalWeight += ((PhysicalProduct) product).getWeight();
        }
        return true;
    }

    /**
     * This function will remove the desired item from the cart,
     * increease the product's quantity by 1
     * @param productName the String representing the product's name
     * @return true if the product exists in the cart
     */
    public boolean removeItem(String productName) {
        Product product = ProductService.getpServiceInstance().getProduct(productName);
        if (product == null || !items.contains(productName)) {
            return false;
        }
        items.remove(productName);
        product.setQuantity(product.getQuantity() + 1);
        if (product.isPhysical()) {
            totalWeight -= ((PhysicalProduct) product).getWeight();
        }
        return true;
    }

    /**
     * This function calculate the price of the shopping cart based
     * on the price of each product with its weight
     * @return amount which is the total price of the cart
     */
    public double cartAmount() {
        double amount = 0;
        for (String itemName : items) {
            Product product = ProductService.getpServiceInstance().getProduct(itemName);
            amount += product.getPrice();
        }
        if (totalWeight > 0) {
            amount += totalWeight * 0.1;
        }
        return amount;
    }

    @Override
    public int compareTo(ShoppingCart other) {
        if (this.totalWeight == other.totalWeight) {
            return 0;
        } else if (this.totalWeight < other.totalWeight) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Items: " + items + ", cart amount: " + cartAmount() + ", total weight: " + totalWeight;
    }

}