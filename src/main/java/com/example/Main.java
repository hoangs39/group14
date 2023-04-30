package com.example;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // scanner for user's input
    static Scanner sc = new Scanner(new FilterInputStream(System.in) {
        @Override
        public void close() throws IOException {
        }
    });

    // read file and assign it new object
    static ProductsManager store = Controller.readProductFromFile();
    static CartsManager cartsManager = Controller.createCartsObject(store);

    public static void main(String[] args) {
        start();
    }
    // initial menu
    public static void menu() {
        System.out.println("\n******************************************");
        System.out.println("1.Handling Products:");
        System.out.println("2.Handle Shopping Cart:");
        System.out.println("3.Handle Gift-Items:");
        System.out.println("4.Handle Coupons:");
        System.out.println("5.Handle the Cart's List:");
        System.out.println("6.Exit!");
    }

    // first menu
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
                // view/add/edit item in product list
                case 1:
                    handleProduct(sc);
                    break;
                // add / remove items in carts
                case 2:
                    handleCarts(sc);
                    break;
                // update / view message
                case 3:
                    handleGift(sc);
                    break;
                // apply / remove coupon
                case 4:
                    handleCoupons(sc);
                    break;
                // display cart's info / Sort carts / print the reciept - store data in file
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

    // sub menu
    public static void menuProduct() {
        System.out.println("\n******************************************");
        System.out.println("1.View all item into our Store");
        System.out.println("2.Add items inside our Store");
        System.out.println("3.Edit items inside our Store");
        System.out.println("4.Exit!");
    }
    // sub menu
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
                case 1:
                    // view items
                    store.getAllProducts();
                    break;
                case 2:
                    //create and then  add items to product list
                    addProduct(sc);
                    break;
                case 3:
                    // edit items in list of products
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

    // create items and then add it to new ProductManager object for later usage
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
        System.out.println("Do you want to add coupon (Y or N)?");
        String c = sc.nextLine();
        if (c.equalsIgnoreCase("Y")) {
            System.out.println("What type of coupon? (percent or price)");
            String cType = sc.nextLine();
            System.out.println("What value of coupon? (0-100% or 0-...)");
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

    // edit items' infomation in then change all info in cart
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
        p.calculateTax(taxType);
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
            cartsManager.displayCarts();
            System.out.println();
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            do {
                System.out.println("Please enter the number 1-4 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 4);
            switch (choice) {
                // view products
                case 1:
                // add items to cart based on what are displayed
                    store.getAllProducts();
                    System.out.println();
                    addItemToCart(shoppingCart, sc);
                    break;
                case 2:
                // remove all items that has the same name
                    shoppingCart.printItemsList();
                    System.out.println();
                    removeItems(shoppingCart, sc);
                    break;
                case 3:
                // remove 1 item out of cart
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

    // add items to cart
    public static boolean addItemToCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the name of product you want:");
        String name = sc.nextLine();
        Product p = store.getProductByName(name);
        return shoppingCart.addItem(p);
    }

    // remove 1 item based on its id
    public static boolean removeItemInSideCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the Id:");
        int id = Integer.parseInt(sc.nextLine());
        return shoppingCart.removeItemById(id);
    }
    // remove items based on thier name
    public static boolean removeItems(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the Name:");
        String name= sc.nextLine();
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
            cartsManager.displayCarts();
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            int choice;
            do {
                System.out.println("Please enter the number 1-3 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 3);
            switch (choice) {
                // view products
                case 1:
                // add gift's msg
                    shoppingCart.printItemsList();
                    System.out.println();
                    if (!updateGift(shoppingCart, sc)) {
                        System.out.println("This items is not supported for gift!");
                    }
                    break;
                case 2:
                // view msg
                    shoppingCart.printItemsList();
                    System.out.println();
                    System.out.println(viewGift(shoppingCart, sc));
                    break;
                case 3:
                    active = false;
                    break;

            }

        }
    }


    // add gift's msg by searching items in cart with its name and id
    public static boolean updateGift(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the Id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        System.out.println("Enter the message:");
        String msg = sc.nextLine();


        return shoppingCart.addMessage(name,id,msg);

    }
    // view gift's msg by searching items in cart with its name and id
    public static String viewGift(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        System.out.println("Enter the Id:");
        int id = Integer.parseInt(sc.nextLine());
        return shoppingCart.getMessage(name,id);
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
            System.out.println("Please enter the cartId:");
            String cartId = sc.nextLine();
            ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
            int choice;
            do {
                System.out.println("Please enter the number 1-3 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 3);

            switch (choice) {
                // view products
                case 1:
                // apply coupons
                    if (shoppingCart.getAppliedCoupon() != null) {
                        System.out.println("This Cart had an applied coupon already!");
                    } else {
                        System.out.print("Products in the cart: ");
                        shoppingCart.printItemsList();
                        System.out.print("Available coupons in the cart: ");
                        if(shoppingCart.getCoupons().size() != 0){
                            shoppingCart.displayAllCoupons();
                            System.out.println();
                            applyCouponsToCart(shoppingCart, sc);
                            
                        }else{
                            System.out.println("Coupon list is empty!");
                        }
                    }
                    break;
                case 2:
                // remove the coupons
                    if(shoppingCart.getCoupons().size() != 0){
                        shoppingCart.printItemsList();
                        System.out.println();
                        System.out.print("Available coupons in the cart: ");
                        shoppingCart.displayAllCoupons();
                        if(removeCouponsFromCart(shoppingCart, sc)){
                            System.out.println("Remove Successfully!");
                        }else{
                            System.out.println("Remove Unsuccessfully!");
                        }
                        break;
                    }else{
                        System.out.println("Coupons list is empty!");
                    }
                    break;
                case 3:
                    active = false;
                    break;

            }

        }
    }

    // apply coupons based on its product's name
    public static Coupon applyCouponsToCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the coupon's product:");
        String name = sc.nextLine();
        if(shoppingCart.searchCoupon(name) == null){
            System.out.println("No Available coupon in the cart!");
            return null;
        }else{
            System.out.println("Successfully!");
            return shoppingCart.applyCoupon(name);
        }
    }
    // apply coupons based on its product's name and product's id
    public static boolean removeCouponsFromCart(ShoppingCart shoppingCart, Scanner sc) {
        System.out.println("Enter the product that applied Coupon:");
        String name = sc.nextLine();
        System.out.println("Enter the Id:");
        int id = Integer.parseInt(sc.nextLine());
        Product p = shoppingCart.searchItem(name,id);
        return shoppingCart.removeCoupon(p.getName());
    }

    public static void menuCartsUI() {
        System.out.println("\n******************************************");
        System.out.println("1.View Cart Detail:");
        System.out.println("2.Sort Carts");
        System.out.println("3.Print The Receipt");

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
                // search cart and show information
                    if (cartsManager.countCarts() != 0) {
                        cartsManager.displayCarts();
                        System.out.println();
                        System.out.println("Please enter the cartId:");
                        String cartId = sc.nextLine();
                        ShoppingCart shoppingCart = cartsManager.getCartById(cartId);
                        System.out.println();
                        System.out.println(shoppingCart.toString());
                        shoppingCart.printItemsList();
                    } else {
                        System.out.println("Empty!");
                    }

                    break;
                case 2:
                // sort the carts
                    if (cartsManager.countCarts() != 0) {
                        cartsManager.displayCarts();
                    } else {
                        System.out.println("Empty!");
                    }
                    break;
                case 3:
                // caluclate the total price, then print it and write it into file
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
