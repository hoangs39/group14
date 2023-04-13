package com.rmit;

import java.util.List;
import java.util.ArrayList;
/**
 * A class stores all created products.
 */
public class ProductsList {
    private List<Product> productsList;

    public ProductsList() {
        this.productsList = new ArrayList<>();
    }

    public void addItem(Product p) {
        this.productsList.add(p);
    }

    public int countItem() {
        return this.productsList.size();
    }

    public boolean removeItem(Product p) {
        if (this.productsList.contains(p)) {
            this.productsList.remove(p);
            return true;
        }
        return false;
    }

    public void browsing() {
        for (Product product : this.productsList) {
            System.out.println(product.view());
            System.out.println(product.displayAll());
        }
        return;
    }


    /**
     * Search and return Product in productList by the product's name
     * @param name        name of the product
     */
    public Product searchProduct(String name) {
        for (Product p : this.productsList) {
            boolean result = p.getName().equals(name);
            if (result) {
                return p;
            }
        }
        return null;
    }


    public List<Product> getProductsList() {
        return productsList;
    }

}
