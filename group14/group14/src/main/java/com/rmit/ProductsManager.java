package com.rmit;

import java.util.HashSet;

/**
 * class to manage product functions
 */
public class ProductsManager {
    /**
     * product manager attributes
     */
    // list of products
    private HashSet<Product> productList;

    // constructor
    public ProductsManager() {
        this.productList = new HashSet<>();
    }

    // getter
    public HashSet<Product> getProductList() {
        return productList;
    }

    // product list functions
    public boolean addProduct(Product p) {
        if(!productList.contains(p)){
            this.productList.add(p);
            return true;
        }
        return false;
    }

    //calculate the size od the set
    public int countProducts() {
        return this.productList.size();
    }

    // remove the prodcuct in the set based on product's name
    public boolean removeProduct(Product p) {
        if (this.productList.contains(p)) {
            this.productList.remove(p);
            return true;
        }
        return false;
    }

    /**
     * print all products
     * <p>
     * If the list of product is empty -> print message
     * else print all product
     * </p>
     */
    public void getAllProducts() {
        if (productList.isEmpty()) {
            System.out.println("There are no products in the store!");
        } else {
            for (Product product : productList) {
                System.out.printf("%s\n", product.getDisplayInfo());
            }
        }
    }

    /**
     * get product by name
     * <p>
     * Given a name String
     * Return the sproduct with the given name
     * If no results were found -> return null
     * </p>
     * @param searchName the search product name
     * @return product if found / null if not found
     */
    public Product getProductByName(String searchName) {
        if (productList != null) {
            for (Product product : productList) {
                if (searchName.equals(product.getName())) return product;
            }
        }
        return null;
    }


}
