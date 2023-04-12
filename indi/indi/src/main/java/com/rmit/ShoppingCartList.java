package com.rmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

/**
 * ShoppingCartList store all created carts and executing changing methods on all carts 
 * when there is a change in product list.
 */
public class ShoppingCartList {
    List<ShoppingCart> shoppingCarts;
    public ShoppingCartList(){
        shoppingCarts = new ArrayList<>();
    }
    public void addCart(ShoppingCart cart){
        shoppingCarts.add(cart);
        cart.cartAmount();
        return;
    }

    public ShoppingCart search(String id){
        for (ShoppingCart shoppingCart : shoppingCarts) {
            boolean result = shoppingCart.id.equals(id);
            if(result){
                return shoppingCart;
            }
        }
        return null;
    }

    public void speedUpShipping(){
            shoppingCarts.sort(new Comparator<ShoppingCart>() {
                @Override
                public int compare(ShoppingCart o1, ShoppingCart o2) {
                    boolean greater = o1.totalWeight > o2.totalWeight;
                    boolean equal = o1.totalWeight == o2.totalWeight;
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

    public String buy(){
        double payment = 0.0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            payment += shoppingCart.total;
        }
        String noti = String.format("You Have Just Done Your Shopping! and This is Your Bill: %.5f",  payment);
        shoppingCarts.removeAll(shoppingCarts);
        return noti;
    }

    public void displayCartBasedOnWeight(){
        speedUpShipping();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
            System.out.println(shoppingCart.displayCartInfo());
        }
        return;
    }
    public void displayCarts(){
        speedUpShipping();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
            System.out.println(shoppingCart.displayCartInfo() + "->");
            shoppingCart.displayAllProducts();
            // System.out.println("Total Price: " + shoppingCart.cartAmount());
        }
        return;
    }

    public void listenToChange(Product p, String description, double price) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.catchUpdate(p,price,description);
        }
        return;

    }
    public void listenToChange(Product p, String description, double price, double weight) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.catchUpdate(p,price,description,weight);
        }
        return;
    }
    public void listenToRemove(Product p) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.removeItem(p.getName());
            shoppingCart.cartAmount();
        }
        return;
    }
    public int countCarts() {
        return this.shoppingCarts.size();
    }
}
