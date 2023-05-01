package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CartsManager {
    // CartsManager's attributes
    List<ShoppingCart> shoppingCarts;

    // constructors
    public CartsManager() {
        shoppingCarts = new ArrayList<>();
    }

    // functions
    /**
     * add cart into list of carts
     * 
     * @param searchCartId the search cart id
     *                     <p>
     *                     add the cart if shopping carts list does not contain it
     *                     and return true, else return false
     *                     </p>
     * @return boolean
     */
    public boolean addCart(ShoppingCart cart) {
        if (!shoppingCarts.contains(cart)) {
            shoppingCarts.add(cart);
            return true;
        }
        return false;
    }

    /**
     * get shopping cart by cart id
     * <p>
     * Given an id String
     * Return the shopping cart with the given id String
     * If no results were found -> return null
     * </p>
     * 
     * @param searchCartId the search cart id
     * @return shopping cart if found / null if not found
     */
    public ShoppingCart getCartById(String id) {
        if (shoppingCarts != null) {
            for (ShoppingCart shoppingCart : shoppingCarts) {
                if (id.equals(shoppingCart.getCartId()))
                    return shoppingCart;
            }
        }
        return null;
    }

    /**
     * sort shopping carts list by cart's total weight in increasing order
     */
    public void speedUpShipping() {
        shoppingCarts.sort(new Comparator<ShoppingCart>() {
            @Override
            public int compare(ShoppingCart o1, ShoppingCart o2) {
                boolean greater = o1.getTotalWeight() > o2.getTotalWeight();
                boolean equal = o1.getTotalWeight() == o2.getTotalWeight();
                if (greater) {
                    return 1;
                }
                if (equal) {
                    return 0;
                }
                return -1;
            }
        });
        return;

    }

    /**
     * print the total payment
     * <p>
     * calculate the total payment of all carts in the list
     * then write these transactions into file and delete all the carts
     * </p>
     */
    public String buy() {
        double payment = 0.0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            System.out.println("-----------------------");
            System.out.println(shoppingCart.toString());
            shoppingCart.printItemsList();
            payment += shoppingCart.getTotalPrice();
        }
        String noti = String.format("You have done your shopping and this is you bill: %.2f", payment);
        // Controller.writeCartsToFile(shoppingCarts);
        Loader.writeCartsIntoFile(shoppingCarts);
        shoppingCarts.removeAll(shoppingCarts);
        return noti;
    }

    /**
     * <p>
     * display all the carts'information in the list
     * </p>
     */
    public void displayCarts() {
        speedUpShipping();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
            System.out.println(shoppingCart.toString());
        }
        return;
    }

    /**
     * <p>
     * change all carts's total price whenever there is a change inside the products
     * list
     * </p>
     */
    public boolean change(Product p) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.changeItemAll(p);
        }
        return true;
    }

    /**
     * <p>
     * remove a cart's product whenever there is a removal inside the products list
     * </p>
     */
    public boolean removeItemInCart(Product p) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.removeItemAll(p.getName());
        }
        return true;
    }

    // getter
    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    /**
     * <p>
     * count the items that are contained in the list
     * </p>
     */
    public int countCarts() {
        return this.shoppingCarts.size();
    }
}
