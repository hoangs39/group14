package com.example;
/**
 * This class will help manage all the products of the store
 * @author <Hoang Vinh Khue - s3927474>
 */

import java.util.HashMap;
import java.util.Map;

public class ProductService {
    // Attributes
    static ProductService instance = new ProductService();
    private Map<String, Product> products;

    // Constructors
    private ProductService() {
        products = new HashMap<>();
    }

    // Get an instance of the product service
    public static ProductService getpServiceInstance() {
        return instance;
    }

    // Add product to the product map
    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    /**
     * This function will get the desired product using only the product's name
     * @param name the product's name
     * @return the product that matches the name
     */
    public Product getProduct(String name) {
        return products.get(name);
    }

    /**
     * This function will remove the desired product using only the product's name
     * @param name the product's name
     */
    public void removeProduct(String name) {
        products.remove(name);
    }

    /**
     * This function will present all products in the list
     * @return the String representation of all products
     */
    public String listProducts() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Type - Name - Description - Quantity - Price - Weight (Physical product only)");
        for (Product product : products.values()) {
            sb.append(product.viewProductDetail());
            sb.append("\n");
        }
        return sb.toString();
    }
}
