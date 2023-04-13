package com.rmit;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * ShoppingCart take a instance of a ProductList as a reference variable.
 * Whenever a product was add to cart, it automatically add to both 2 set: set
 * of String and set of Product
 * for more convenience when using below functions.
 * When a change was happened in the product list, the shopping cart
 * will reset all the attribute in the product in the item list(set of Product)
 * and re call cartAmount() again.
 */
public class ShoppingCart {
    Map<Integer, String> itemsList;
    Set<Coupon> coupons;
    Coupon appliedCoupon;
    String id;
    ProductsList store;
    double totalWeight = 0.0;
    final double BASE_FEE = 0.1;
    double totalPrice = 0.0;
    int itemsID = 1;

    public ShoppingCart(String id, ProductsList store) {
        this.id = id;
        this.store = store;
        this.itemsList = new HashMap<>();
        this.coupons = new HashSet<>();
    }

    public String displayCartInfo() {
        return String.format("CartID: %s, Total Weight: %.5f", id, totalWeight);
    }


    public void catchRemove(String p) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getValue().equals(p)) {
                iterator.remove();
            }
        }
        cartAmount();
        return;
    }

    public boolean addItemToCart(String p) {
        Product product = store.searchProduct(p);
        if (product.getQuantity() != 0) {
            itemsList.put(itemsID++, p);
            product.setQuantity(product.getQuantity() - 1);
            if(product.getCoupon() != null){
                Coupon coupon = product.getCoupon();
                coupons.add(coupon);
            }
            return true;
        }
        return false;
    }

    public int countItem() {
        return itemsList.size();
    }

    public boolean removeItem(String p) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getValue().equals(p)) {
                iterator.remove();
                Product product = store.searchProduct(p);
                product.setQuantity(product.getQuantity() + 1);
                return true;
            }
        }
        return false;
    }

    public double cartAmount() {
        resetAll();
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Product product = store.searchProduct(entry.getValue());
            if (product instanceof PhysicalProducts) {
                PhysicalProducts phy = (PhysicalProducts) product;
                totalWeight += phy.getWeight();
            }
            totalPrice = product.getPrice() + product.getTaxAmount();
        }
        if (this.appliedCoupon.getType().equals("percent")) {
            totalPrice = totalPrice * (1 - appliedCoupon.getValue());
        } else if (this.appliedCoupon.getType().equals("price")) {
            totalPrice -= this.appliedCoupon.getValue();
        }
        return totalPrice;
    }

    private void resetAll() {
        totalPrice = 0;
        totalWeight = 0;
    }

    public Coupon applyCoupon(String name) {
        for (Coupon coupon : coupons) {
            if (coupon.getProduct().equals(name)) {
                Product p = store.searchProduct(name);
                p.setCoupon(null);
                this.appliedCoupon = coupon;
                return appliedCoupon;
            }
        }
        return null;
    }

    private void displayAllCoupons() {
        for (Coupon coupon : coupons) {
            System.out.println(coupon.getType() + ":" + coupon.getValue() + ":" + coupon.getProduct());
        }
    }

    public void displayAllProducts() {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Product product = store.searchProduct(entry.getValue());
            System.out.println(entry.getKey() + ":" + entry.getValue() + ":" + product.getPrice());
        }
    }

    /**
     * Search For item in the item list and then get/set message for the Product
     * item
     * 
     * @param name    the product name used for searching
     * @param message the new message of the Product item
     */
    public boolean addMessage(String name, int id, String message) {
        if (searchForProductItem(name, id) != null) {
            Product product = store.searchProduct(name);
            product.setMessage(message);
            return true;
        }
        return false;
    }

    public boolean lookMessage(String name, int id) {
        if (searchForProductItem(name, id) != null) {
            Product product = store.searchProduct(name);
            product.getMessage();
            return true;
        }
        return false;
    }

    public Product searchForProductItem(String name, int id) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == id && entry.getValue().equals(name)) {
                Product product = store.searchProduct(entry.getValue());
                return product;
            }
        }
        return null;
    }
}
