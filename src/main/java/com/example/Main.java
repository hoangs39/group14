package com.example;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(new FilterInputStream(System.in) {
        @Override
        public void close() throws IOException {
        }
    });
    static ProductsManager store = Controller.readProductFromFile();
    static CartsManager cartsManager = Controller.createCartsObject(store);

    public static void main(String[] args) {
        start();
    }

    public static void menu() {
        System.out.println("\n******************************************");
        System.out.println("1.Handling Products:");
        System.out.println("2.Handle Shopping Cart:");
        System.out.println("3.Handle Gift-Items:");
        System.out.println("4.Handle Coupons:");
        System.out.println("5.Handle the Cart's List:");
        ;
        System.out.println("6.Exit!");
    }

    public static void start() {
        boolean active = true;
        while (active) {
            menu();
            int choice;
            do {
                System.out.println("Please enter the number 1-6 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 6);

            switch (choice) {
                // create new Product
                case 1:
                    handleProduct(sc);
                    break;
                // edit Product
                case 2:
                    handleCarts(sc);
                    break;
                // remove Product
                case 3:
                    handleGift(sc);
                    break;
                // Doing Shopping
                case 4:
                    handleCoupons(sc);
                    break;
                // Display Products in WareHouse
                case 5:
                    handleCartUI(sc);
                    break;
                case 6:
                    active = false;
                    Controller.writeProductsToFile(store.getProductList());
                    sc.close();
                    break;

            }

        }
    }

    public static void menuProduct() {
        System.out.println("\n******************************************");
        System.out.println("1.View all item into Our Store");
        System.out.println("2.Add items inside Our Store");
        System.out.println("3.Edit items inside Our Store");
        System.out.println("4.Exit!");
    }

    public static void handleProduct(Scanner sc) {
        boolean active = true;
        while (active) {
            menuProduct();
            int choice;
            do {
                System.out.println("Please enter the number 1-4 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 4);

            switch (choice) {
                // view products
                case 1:
                    store.getAllProducts();
                    break;
                case 2:
                    addProduct(sc);
                    break;
                case 3:
                    store.getAllProducts();
                    System.out.println();
                    System.out.println("Please enter the product's name");
                    String pName = sc.nextLine();
                    editProduct(pName);
                    break;
                case 4:
                    active = false;
                    break;

            }
        }
    }

    public static boolean addProduct(Scanner sc) {
        Product p;
        System.out.println("Please enter type you want for the item (PHYSICAL or DIGITAL):");
        String type = sc.nextLine();
        System.out.println("Please enter the name you want for the item:");
        String name = sc.nextLine();
        double price;
        do {
            System.out.println("Enter its price:");
            price = Double.parseDouble(sc.nextLine());
        } while (price < 0.0);
        int quantity;
        do {
            System.out.println("Enter its available quantity:");
            quantity = Integer.parseInt(sc.nextLine());
        } while (quantity < 0);
        System.out.println("Please enter the description you want for the item:");
        String description = sc.nextLine();
        System.out.println("Please enter the taxType (freeTax:0% or normalTax:10% or luxuryTax:20%)");
        String taxType = sc.nextLine();
        if (type.equalsIgnoreCase("PHYSICAL")) {
            System.out.println("Please enter weight of the item:");
            double weight = Double.parseDouble(sc.nextLine());
            p = new PhysicalProducts(name, price, description, quantity, taxType);
            PhysicalProducts phy = (PhysicalProducts) p;
            phy.setWeight(weight);
        } else {
            p = new DigitalProduct(name, price, description, quantity, taxType);
        }
        System.out.println("Do you want to add Coupon (Y or N)?");
        String c = sc.nextLine();
        if (c.equalsIgnoreCase("Y")) {
            System.out.println("what type of coupon? (percent or price)");
            String cType = sc.nextLine();
            System.out.println("what value of coupon? (0-100% or 0-...)");
            double cVal = Double.parseDouble(sc.nextLine());
            Coupon coupon = new Coupon(p.getName(), cType, cVal);
            p.setCoupon(coupon);
        }
        System.out.println("Do you make this a gift (Y or N)?");
        String g = sc.nextLine();
        if (g.equalsIgnoreCase("Y")) {
            p.setCreateGift(true);
        }
        return store.addProduct(p);
    }

    public static boolean editProduct(String pName) {
        Product p = store.getProductByName(pName);
        // change product in product list
        double price;
        do {
            System.out.println("Enter its price:");
            price = Double.parseDouble(sc.nextLine());
        } while (price < 0.0);
        int quantity;
        do {
            System.out.println("Enter its available quantity:");
            quantity = Integer.parseInt(sc.nextLine());
        } while (quantity < 0);
        System.out.println("Please enter the description you want for the item:");
        String description = sc.nextLine();
        System.out.println("Please enter the taxType (freeTax:0% or normalTax:10% or luxuryTax:20%):");
        String taxType = sc.nextLine();
        p.setDescription(description);
        p.setPrice(price);
        p.setAvailableQuantity(quantity);
        p.setTaxType(taxType);
        // change the products in cart
        if (p instanceof PhysicalProducts) {
            System.out.println("Please enter weight of the item:");
            double weight = Double.parseDouble(sc.nextLine());
            PhysicalProducts phy = (PhysicalProducts) p;
            phy.setWeight(weight);
        }
        return cartsManager.change(p);
    }

    public static void menuCart() {
        System.out.println("\n******************************************");
        System.out.println("1.Add item into Cart");
        System.out.println("2.Remove Items in Cart");
        System.out.println("3.Remove 1 item inside Cart");
        System.out.println("4.Exit!");
    }

    public static void handleCarts(Scanner sc) {
        boolean active = true;
        while (active) {
            menuCart();
            System.out.println();
            int choice;
            do {
                System.out.println("Please enter the number 1-4 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 4);
            cartsManager.displayCarts();
            System.out.println();
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            switch (choice) {
                // view products
                case 1:
                    store.getAllProducts();
                    System.out.println();
                    addItemToCart(shoppingCart, sc);
                    break;
                case 2:
                    shoppingCart.printItemsList();
                    System.out.println();
                    removeItems(shoppingCart, sc);
                    break;
                case 3:
                    shoppingCart.printItemsList();
                    System.out.println();
                    removeItemInSideCart(shoppingCart, sc);
                    break;
                case 4:
                    active = false;
                    break;

            }

        }
    }

    public static boolean addItemToCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the name of product you want:");
        String name = sc.nextLine();
        Product p = store.getProductByName(name);
        return shoppingCart.addItem(p);
    }

    public static boolean removeItemInSideCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the name of product you want:");
        String name = sc.nextLine();
        return shoppingCart.removeItem(name);
    }

    public static boolean removeItems(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the name of product you want:");
        String name = sc.nextLine();
        return shoppingCart.removeItemAll(name);
    }

    public static void menuGift() {
        System.out.println("\n******************************************");
        System.out.println("1.Update Gift in Cart");
        System.out.println("2.View Gift in Cart");
        System.out.println("3.Exit!");
    }

    public static void handleGift(Scanner sc) {
        boolean active = true;
        while (active) {
            menuGift();
            System.out.println();
            int choice;
            do {
                System.out.println("Please enter the number 1-3 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 3);
            cartsManager.displayCarts();
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            switch (choice) {
                // view products
                case 1:
                    System.out.println(shoppingCart.printItemsList());
                    System.out.println();
                    if (!updateGift(shoppingCart, sc)) {
                        System.out.println("This items is not supported for gift!");
                    }
                    break;
                case 2:
                    System.out.println(shoppingCart.printItemsList());
                    System.out.println();
                    System.out.println(viewGift(shoppingCart, sc));
                    break;
                case 3:
                    active = false;
                    break;

            }

        }
    }

    public static boolean updateGift(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        System.out.println("Enter the message:");
        String msg = sc.nextLine();

        return shoppingCart.addMessage(name, msg);

    }

    public static String viewGift(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        return shoppingCart.getMessage(name);
    }

    public static void menuCoupons() {
        System.out.println("\n******************************************");
        System.out.println("1.Apply Coupons to Cart");
        System.out.println("2.Remove Coupon in Cart");
        System.out.println("3.Exit!");
    }

    public static void handleCoupons(Scanner sc) {
        boolean active = true;
        while (active) {
            menuCoupons();
            System.out.println();
            cartsManager.displayCarts();
            int choice;
            do {
                System.out.println("Please enter the number 1-3 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 3);
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            switch (choice) {
                // view products
                case 1:
                    if (shoppingCart.getAppliedCoupon() != null) {
                        System.out.println("This Cart had an applied coupon already!");
                    } else {
                        System.out.println(shoppingCart.printItemsList());
                        System.out.print("Existing coupon in the cart: ");
                        shoppingCart.displayAllCoupons();
                        System.out.println();
                        applyCouponsToCart(shoppingCart, sc);
                        System.out.println("Sucessfully!");
                    }
                    break;
                case 2:
                    shoppingCart.displayAllCoupons();
                    System.out.println();
                    if(removeCouponsFromCart(shoppingCart, sc)){
                        System.out.println("Sucessfully!");
                    }else{
                        System.out.println("Sucessfully!");
                    }
                    break;
                case 3:
                    active = false;
                    break;

            }

        }
    }

    public static Coupon applyCouponsToCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        return shoppingCart.applyCoupon(name);
    }

    public static boolean removeCouponsFromCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        Product p = shoppingCart.searchItem(name);
        return shoppingCart.removeCoupon(p);
    }

    public static void menuCartsUI() {
        System.out.println("\n******************************************");
        System.out.println("1.View Cart Detail:");
        System.out.println("2.Sort Carts");
        System.out.println("3.Print The Recepit");

        System.out.println("4.Exit!");
    }

    public static void handleCartUI(Scanner sc) {
        boolean active = true;
        while (active) {
            menuCartsUI();
            System.out.println();
            int choice;
            do {
                System.out.println("Please enter the number 1-4 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 4);

            switch (choice) {
                // view products
                case 1:
                    if (cartsManager.countCarts() != 0) {
                        cartsManager.displayCarts();
                        System.out.println();
                        System.out.println("Please enter the cartId:");
                        String cartId = sc.nextLine();
                        ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
                        System.out.println();
                        System.out.println(shoppingCart.toString());
                        System.out.println(shoppingCart.printItemsList());
                    } else {
                        System.out.println("Empty!");
                    }

                    break;
                case 2:
                    if (cartsManager.countCarts() != 0) {
                        cartsManager.displayCarts();
                    } else {
                        System.out.println("Empty!");
                    }
                    break;
                case 3:
                    if (cartsManager.countCarts() != 0) {
                        System.out.println(cartsManager.buy());
                    } else {
                        System.out.println("Empty Carts!");
                    }
                    break;
                case 4:
                    active = false;
                    break;
            }

        }
    }

}
