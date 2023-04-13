package com.rmit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.rmit.Loader.*;



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

    public String buy(ProductsList store){
        double payment = 0.0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            payment += shoppingCart.totalPrice;
        }
        String noti = String.format("You Have Just Done Your Shopping! and This is Your Bill: %.5f",  payment);
        Loader.writeProductsToFile(store.getProductsList());
        Loader.writeCartsToFile(shoppingCarts);
        shoppingCarts.removeAll(shoppingCarts);
        return noti;
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


    public void listenToChange() {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.cartAmount();
        }
        return;
    }

    public void listenToRemove(Product p) {
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.catchRemove(p.getName());
        }
        return;
    }
    
    public int countCarts() {
        return this.shoppingCarts.size();
    }
}
