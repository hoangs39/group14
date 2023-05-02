package com.example;
/**
 * @author Group14
 */
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Controllerr {
    private ProductsManager storeModel;
    private CartsManager cartsModel;
    private View view;

    public Controllerr(ProductsManager productsManager, CartsManager cartsManager, View view) {
        this.cartsModel = cartsManager;
        this.storeModel = productsManager;
        this.view = view;
    }

    /**
     * <p>
     * works as a menu form to receive the users'input data.
     * </p>
     */
    public void menu(Scanner sc) {
        String options = "";
        List<String> menuTitle = view.title();
        Map<String, List<String>> menu = view.generateMenu();
        do {
            view.menuForm(menuTitle, sc);
            System.out.println("Pls enter choice");
            options = sc.nextLine();
            if (!options.equals("Exit")) {
                List<String> submenu = menu.get(options);
                if (submenu != null) {
                    subMenu(options, menu.get(options), sc);
                } else {
                    System.out.println("There is an error!");
                }
            }
        } while (!options.equals("Exit"));
        Loader.writeProductsIntoFile(storeModel.getProductList());
        return;
    }

        /**
     * <p>
     * works as a submenu form to receive the users'input data.
     * </p>
     */
    public void subMenu(String option, List<String> submenu, Scanner sc) {
        String choices = "";
        do {
            view.subMenuForm(submenu, sc);
            System.out.println("Pls choose a option:");
            choices = sc.nextLine();
            if (!choices.equals("Exit")) {
                if (option.equals("cartlist")) {
                    handleCartsList(sc, choices);
                } else if (option.equals("product")) {
                    view.displayProductInStore(storeModel);
                    if (!choices.equals("viewP")) {
                        handleProduct(sc, choices);
                    }
                } else {
                    view.displayCarts(cartsModel);
                    System.out.println("Enter cart Id");
                    String id = sc.nextLine();
                    ShoppingCart shoppingCart = cartsModel.getCartById(id);
                    if (option.equals("gift")) {
                        view.displayCart(shoppingCart);
                        handleGift(shoppingCart, sc, choices);
                    } else if (option.equals("coupon")) {
                        view.displayCart(shoppingCart);
                        if (view.displayCoupons(shoppingCart)) {
                            view.displayAppliedCoupons(shoppingCart);
                            handleCoupons(shoppingCart, sc, choices);
                        }
                    } else {
                        view.displayCart(shoppingCart);
                        if (!handleCart(shoppingCart, sc, choices)) {
                            System.out.println("Unsuccess");
                        }
                    }
                }
            }
        } while (!choices.equals("Exit"));
    }
    /**
     * <p>
     *create , edit, view product items based on input order
     * </p>
     */
    public boolean handleProduct(Scanner sc, String choice) {
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

        if (choice.equalsIgnoreCase("edit")) {
            p = storeModel.getProductByName(name);
            if (p != null) {
                p.setDescription(description);
                p.setPrice(price);
                if(p.getCoupon().getType().equals("price")){
                    if(p.getCoupon().getValue() > price){
                        System.out.println("Current Coupon value is greater than the price -> the program will automatically delete its coupon!");
                        p.setCoupon(null);
                    }
                }
                p.setAvailableQuantity(quantity);
                if (p instanceof PhysicalProducts) {
                    System.out.println("Please enter weight of the item:");
                    double weight = Double.parseDouble(sc.nextLine());
                    PhysicalProducts phy = (PhysicalProducts) p;
                    phy.setWeight(weight);
                }
                p.setTaxType(taxType);
                p.calculateTax(taxType);
                return cartsModel.change(p);
            }
            System.out.println("False to Edit!");
            return false;
        } else {
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
                System.out.println(String.format("What value of coupon? (percent: 0-100 or price value: 0- %,.2f)",price));
                double cVal = Double.parseDouble(sc.nextLine());
                Coupon coupon = new Coupon(p.getName(), cType, cVal);
                p.setCoupon(coupon);
            }
            System.out.println("Do you make this a gift (Y or N)?");
            String g = sc.nextLine();
            if (g.equalsIgnoreCase("Y")) {
                p.setCreateGift(true);
            }
            return storeModel.addProduct(p);
        }
    }
    /**
     * <p>
     *add , remove 1, remove many product items based on input order
     * </p>
     */
    public boolean handleCart(ShoppingCart shoppingCart, Scanner sc, String choices) {
        if (choices.equalsIgnoreCase("add")) {
            System.out.println("--------------------------\n");
            storeModel.getAllProducts();
        }
        System.out.println("Enter the name of product you want:");
        String name = sc.nextLine();
        if (choices.equalsIgnoreCase("add")) {
            Product p = storeModel.getProductByName(name);
            return shoppingCart.addItem(p);
        } else if (choices.equalsIgnoreCase("removeM")) {
            return shoppingCart.removeItemAll(name);
        } else {
            System.out.println("Enter the Id:");
            int id = Integer.parseInt(sc.nextLine());
            return shoppingCart.removeItemById(id);
        }
    }
    /**
     * <p>
     *add , view product items' message based on input order
     * </p>
     */
    public boolean handleGift(ShoppingCart shoppingCart, Scanner sc, String choices) {
        System.out.println("Enter the Id:");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the product:");
        String name = sc.nextLine();
        if (choices.equalsIgnoreCase("update")) {
            System.out.println("Enter the message:");
            String msg = sc.nextLine();
            return shoppingCart.addMessage(name, id, msg);
        } else {
            System.out.println(shoppingCart.getMessage(name, id));
            return true;
        }
    }

       /**
     * <p>
     *apply , remove product items' coupons based on input order
     * </p>
     */
    public Coupon handleCoupons(ShoppingCart shoppingCart, Scanner sc, String choices) {
        System.out.println("\nEnter the coupon's product:");
        String name = sc.nextLine();
        if (choices.equalsIgnoreCase("apply")) {
            if (shoppingCart.searchCoupon(name) == null) {
                System.out.println("No Available coupon in the cart!");
                return null;
            } else {
                System.out.println("Successfully!");
                return shoppingCart.applyCoupon(name);
            }
        } else {
            System.out.println("Enter the Id:");
            int id = Integer.parseInt(sc.nextLine());
            Product p = shoppingCart.searchItem(name, id);
            if (!shoppingCart.removeCoupon(p.getName())) {
                System.out.println("Unsuccessfully");
            }
            return null;
        }
    }
    /**
     * <p>
     *view cart's detailed information , buy and print reciept of carts list, sort carts list in increasing order based on input order
     * </p>
     */
    public void handleCartsList(Scanner sc, String choices) {
        if (cartsModel.countCarts() != 0) {
            cartsModel.displayCarts();
            if (choices.equalsIgnoreCase("view")) {
                System.out.println("Please enter the cartId:");
                String cartId = sc.nextLine();
                ShoppingCart shoppingCart = cartsModel.getCartById(cartId);
                view.displayCart(shoppingCart);
            }
            if (choices.equalsIgnoreCase("buy")) {
                System.out.println(cartsModel.buy());
            }
        } else {
            System.out.println("Empty!");
        }
    }

}
