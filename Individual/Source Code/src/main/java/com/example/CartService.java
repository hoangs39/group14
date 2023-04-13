package com.example;
/**
 * This class will help manage all the shopping carts of the store
 * @author <Hoang Vinh Khue - s3927474>
 */

import java.util.ArrayList;
import java.util.Collections;

public class CartService {
    // Attributes
    static CartService instance = new CartService();
    private static ArrayList<ShoppingCart> carts;

    // Constructors
    private CartService() {
        carts = new ArrayList<>();
    }
    
    // Get an instance of the cart service
    public static CartService getcServiceInstance() {
        return instance;
    }

    // Add a cart to the carts list
    public void addCart(ShoppingCart cart) {
        carts.add(cart);
    }

    // Remove a cart from the carts list
    public void removeCart (ShoppingCart cart) {
        carts.remove(cart);
    }
    
    /**
     * This function will print all shopping cart with index number
     * to help user select it
     */
    public void listShoppingCartbyIndex() {
        for (int i = 0; i < carts.size(); i++) {
            System.out.println(i+1 + " - " + carts.get(i));
        }
    }
    
    /**
     * This function return the desired shopping cart with their 
     * printed index number
     * @param i index number
     * @return the shopping cart that matches the index
     */
    public ShoppingCart getCart(int i) {
        return carts.get(i - 1);
    }

    /**
     * This function return the shopping cart that contain
     * a specific item
     * @param product the product's name
     * @return the shopping cart that contain the item
     */
    public ShoppingCart getCartWithMatchingItem (String product) {
        for (ShoppingCart shoppingCart : carts) {
            if (shoppingCart.getItems().contains(product)) {
                return shoppingCart;
            }
        }
        return null;
    }

    public void printCart(ShoppingCart cart) {
        for (String item : cart.getItems()) {
            System.out.println(item);
        }
    }

    /**
     * This function will display all shopping carts based on their total weight
     */
    public void sortShoppingCartByWeight() {
        Collections.sort(carts);
        for (ShoppingCart shoppingCart : carts) {
            System.out.println(shoppingCart);
        }
    }
}


