package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * <p>
 * The class functions as the UI handler in this system
 * </p>
 */
public class View {

    /**
     * <p>
     * disply the menu
     * </p>
     */
    public void menuForm(List<String> titles, Scanner sc) {
        Stream<String> menuTitles = titles.stream();
        menuTitles.forEach(m -> System.out.println("+ " + m + "\n"));
    }

    /**
     * <p>
     * disply the submenu
     * </p>
     */
    public void subMenuForm(List<String> submenu, Scanner sc) {
        Stream<String> subMenuTitles = submenu.stream();
        subMenuTitles.forEach(m -> System.out.println("+ " + m + "\n"));
    }

    /**
     * <p>
     * display all the carts
     * </p>
     */
    public void displayCarts(CartsManager cartsManager) {
        cartsManager.displayCarts();
    }

    /**
     * <p>
     * display the single cart
     * </p>
     */
    public void displayCart(ShoppingCart shoppingCart) {
        System.out.println(shoppingCart.toString());
        shoppingCart.printItemsList();
    }

    /**
     * <p>
     * display the coupons
     * </p>
     */
    public boolean displayCoupons(ShoppingCart shoppingCart) {
        if (!shoppingCart.getCoupons().isEmpty()) {
            shoppingCart.displayAllCoupons();
            return true;
        } else {
            System.out.println("This Coupons List is empty!");
            return false;
        }
    }

    /**
     * <p>
     * display the applied of a cart
     * </p>
     */
    public void displayAppliedCoupons(ShoppingCart shoppingCart) {
        Coupon c = shoppingCart.getAppliedCoupon();
        if (c != null) {
            System.out.printf("Applied Coupons: %s, Type: %s, Value: %.2f", c.getProduct(), c.getType(), c.getValue());
        } else {
            System.out.println("There is no applied coupon!");
        }
    }

    /**
     * <p>
     * display the products in the product list
     * </p>
     */
    public void displayProductInStore(ProductsManager store) {
        store.getAllProducts();
    }

    /**
     * <p>
     * generate the map ofstirng and list of String for storing menu's key and
     * submenu's contents
     * </p>
     */
    public Map<String, List<String>> generateMenu() {
        Map<String, List<String>> menu = new HashMap<>();

        List<String> products = new ArrayList<>();
        products.add("View Products -> Enter: viewP");
        products.add("Create New Product -> Enter: create");
        products.add("Edit New Product -> Enter: edit");
        products.add("Exit -> Enter: Exit");
        menu.put("product", products);
        List<String> cart = new ArrayList<>();
        cart.add("Add Product to Cart -> Enter: add");
        cart.add("Remove A Product  From Cart-> Enter: remove1");
        cart.add("Remove Products  From Cart-> Enter: removeM");
        cart.add("Exit -> Enter: Exit");
        menu.put("cart", cart);

        List<String> coupons = new ArrayList<>();
        coupons.add("Apply Coupon to Cart -> Enter: apply");
        coupons.add("Romve Coupon From Cart -> Enter: remove");
        coupons.add("Exit -> Enter: Exit");
        menu.put("coupon", coupons);

        List<String> gift = new ArrayList<>();
        gift.add("Update Gift-> Enter: update");
        gift.add("View Gift -> Enter: view");
        gift.add("Exit -> Enter: Exit");
        menu.put("gift", gift);

        List<String> carts = new ArrayList<>();
        carts.add("View Carts's Info -> Enter: view");
        carts.add("Sort Carts List -> Enter: sort");
        carts.add("Buy and Print reciept -> Enter: buy");
        carts.add("Exit -> Enter: Exit");
        menu.put("cartlist", carts);

        return menu;
    }

    /**
     * <p>
     * store the title of the menu
     * </p>
     */
    public List<String> title() {
        List<String> menu = new ArrayList<>();
        menu.add("Handle Product -> Enter: product");
        menu.add("Handle Cart -> Enter: cart");
        menu.add("Handle Coupons -> Enter: coupon");
        menu.add("Handle Gift -> Enter: gift");
        menu.add("Handle Carts List -> Enter: cartlist");
        menu.add("Exit -> Enter: Exit");
        return menu;
    }
}
