package com.rmit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class CartsManager {
    // CartsManager's attributes
    List<ShoppingCart> shoppingCarts;

    // constructors
    public CartsManager(){
        shoppingCarts = new ArrayList<>();
    }

    // functions
    /**
     * create new empty shopping cart
     * <p>
     * Create a new empty shopping cart with no product inside
     * Print success/fail message
     * </p>
     */
    public boolean addCart(ShoppingCart cart){
        shoppingCarts.add(cart);
        return true;
    }

    /**
     * get shopping cart by cart id
     * <p>
     * Given an id String
     * Return the shopping cart with the given id String
     * If no results were found -> return null
     * </p>
     * @param searchCartId the search cart id
     * @return shopping cart if found / null if not found
     */
    public ShoppingCart getById(String id){
        if (shoppingCarts != null) {
            for (ShoppingCart shoppingCart : shoppingCarts) {
                if (id.equals(shoppingCart.getCartId())) return shoppingCart;
            }
            return null;
        }
        return null;
    }


    /**
     * sort shopping carts list by cart's total weight in increasing order
     */
    public void speedUpShipping(){
            shoppingCarts.sort(new Comparator<ShoppingCart>() {
                @Override
                public int compare(ShoppingCart o1, ShoppingCart o2) {
                    boolean greater = o1.getTotalWeight() > o2.getTotalWeight();
                    boolean equal = o1.getTotalWeight() == o2.getTotalWeight();
                    if(greater){
                        return 1;
                    }
                    if(equal){
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
    public String buy(){
        double payment = 0.0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            payment += shoppingCart.getTotalPrice();
        }
        String noti = String.format("You Have Just Done Your Shopping! and This is Your Bill: %.5f",  payment);
        Loader.writeCartsToFile(shoppingCarts);
        shoppingCarts.removeAll(shoppingCarts);
        return noti;
    }

    // display all the carts'information in the list
    public void displayCarts(){
        speedUpShipping();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
            System.out.println(shoppingCart.toString());
        }
        return;
    }

    // change all carts's total price whenever there is a change inside the products list
    public boolean Change() {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
        }
        return true;
    }

    // remove a cart's product whenever there is a removal inside the products list
    public boolean RemoveItem(String p) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.RemoveAllProductByName(p);
        }
        return true;
    }
    
    public int countCarts() {
        return this.shoppingCarts.size();
    }
}
