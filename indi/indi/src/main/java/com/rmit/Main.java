package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ProductsList productsList = new ProductsList();
    static ShoppingCartList shoppingCartList = new ShoppingCartList();

    public static void main(String[] args) {
        start();
    }

 
    public static void start() {
        boolean active = true;
        while (active) {
            menu();
            int choice;
            do {
                System.out.println("Please enter the number 1-8 to execute the program:");
                choice = Integer.parseInt(sc.nextLine());
            } while (choice < 1 && choice > 9);

            switch (choice) {
                // create new Product
                case 1:
                    System.out.println("Please enter type you want for the item (physical or digital):");
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
                    if (type.equalsIgnoreCase("physical")) {
                        System.out.println("Please enter weight of the item:");
                        double weight = Double.parseDouble(sc.nextLine());
                        Product p = new PhysicalProducts(name, price, description, quantity);
                        PhysicalProducts phy = (PhysicalProducts) p;
                        phy.setWeight(weight);
                        productsList.addItem(p);
                    } else {
                        Product p = new DigitalProduct(name, price, description, quantity);
                        productsList.addItem(p);
                    }
                    break;
                //edit Product
                case 2:
                    checkProductListAndSearchProduct(choice);
                    break;
                //remove Product
                case 3:
                    checkProductListAndSearchProduct(choice);
                    break;
                //Doing Shopping
                case 4:
                    checkProductListAndSearchProduct(choice);
                    break;
                //Display Products in WareHouse
                case 5:
                    checkProductListAndSearchProduct(choice);
                    break;
                //Display all the Cart sorted based on weight
                case 6:
                    checkShoppingListAndSearchCart(68);
                    break;
                //Display the cart basic info based on its id
                case 7:
                    displayCartAmount();
                    break;
                //Display the total price of all carts and clear all the carts
                case 8:
                    checkShoppingListAndSearchCart(88);
                    break;
                //exit
                case 9:
                    active = false;
                    break;

            }

        }
    }

    public static void menu() {
        System.out.println("\n******************************************");
        System.out.println("1.Add an item into Our Product Warehouse:");
        System.out.println("2.Edit items inside Our Product Warehouse:");
        System.out.println("3.Remove an item into Our Product Warehouse:");
        System.out.println("4.Shopping!");
        System.out.println("5.Browse Our Product Warehouse:");
        System.out.println("6.Display Carts:");
        System.out.println("7.Display Cart Amount:");
        System.out.println("8.Payment! (Be Careful, If you use this function, all the carts are clear and you will be unable to see you previous cart's information)");
        System.out.println("9.Exit!");
    }

    public static void editProduct(Product p, String name) {
        //change product in product list
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
        p.setDescription(description);
        p.setPrice(price);
        p.setQuantity(quantity);

        //change the products in cart
        if (p instanceof PhysicalProducts) {
            System.out.println("Please enter weight of the item:");
            double weight = Double.parseDouble(sc.nextLine());
            PhysicalProducts phy = (PhysicalProducts) p;
            phy.setWeight(weight);
            if (shoppingCartList.search(name) != null) {
                shoppingCartList.listenToChange(phy, description, price, weight);
            }
        } else {
            if (shoppingCartList.search(name) != null) {
                shoppingCartList.listenToChange(p, description, price);
            }
        }
    }

    public static void shoppingMenu() {
        // menu UI for shopping activity
        System.out.println("\n*********************************");
        System.out.println("1.Add New Cart and start to shop");
        System.out.println("2.Remove item from Cart");
        System.out.println("3.Add Message to Product");
        System.out.println("4.Show The Message of Your Product:");
        System.out.println("5.Add additional items to cart!");
        System.out.println("6.Exit!");
    }

    public static void displayCartAmount() {
        // calculate the Price of the Cart based on search ID
        checkShoppingListAndSearchCart(99);
    }

    public static void shoppingList() {
        // shopping activity
        boolean active1 = true;
        while (active1) {
            //display the list of the product in the warehouse
            System.out.println("\n-----------------------------");
            System.out.println("Products in The WareHouse include:");
            productsList.browsing();
            shoppingMenu();
            int choice1;
            do {
                System.out.println("Please enter the number 1-6 to execute the program:");
                choice1 = Integer.parseInt(sc.nextLine());
            } while (choice1 < 1 && choice1 > 6);

            switch (choice1) {
                // add and make new cart as well as add a product to the current cart.
                case 1:
                    System.out.println("Please enter the id you want for the cart:");
                    String id = sc.nextLine();
                    ShoppingCart cart = new ShoppingCart(id, productsList);
                    shopping(cart);
                    shoppingCartList.addCart(cart);
                    break;
                // remove item by the search id of the cart
                case 2:
                    checkShoppingListAndSearchCart(choice1);
                    break;
                // add message based on the cart id
                case 3:
                    checkShoppingListAndSearchCart(choice1);
                    break;
                // show message based on the cart id
                case 4:
                    checkShoppingListAndSearchCart(choice1);
                    break;
                // add more product to the search cart based on its id
                case 5:
                    checkShoppingListAndSearchCart(choice1);
                    break;
                // exit
                case 6:
                    active1 = false;
                    break;

            }

        }

    }

    public static void shopping(ShoppingCart cart) {
        // illustrate buying activity
        boolean esc = true;
        while (esc) {
            System.out.println("1.Buying");
            System.out.println("2.Exit");
            int choice11;
            do {
                System.out.println("Please enter the number 1-2 to execute the program:");
                choice11 = Integer.parseInt(sc.nextLine());
            } while (choice11 < 1 && choice11 > 2);
            switch (choice11) {
                case 1:
                    // search item in product lst and add product
                    System.out.println("\n-----------------------------");
                    System.out.println("Products in The WareHouse include:");
                    productsList.browsing();
                    System.out.println("\nEnter the name of product you need to add to cart");
                    String addName = sc.nextLine();
                    Product buyP = productsList.searchProduct(addName);
                    if (buyP == null) {
                        System.out.println("Your Product you want to edit is not inside our warehouse!");
                    } else {
                        cart.addItemToCart(buyP.getName());
                    }
                    break;
                    // exit
                case 2:
                    esc = false;
                    break;

            }
        }
    }

    public static void checkProductListAndSearchProduct(int choice) {
        //work with product list
        if (productsList.getProductsList().isEmpty()) {
            System.out.println("\nOur Warehouse is current empty!");
        } else {
            if (choice == 4) {
                //doing shopping
                shoppingList();
            }
            if (choice == 5) {
                //display all the products in the product list
                productsList.browsing();
            }
            if (choice != 4 && choice != 5) {
                System.out.println("\n-----------------------------");
                System.out.println("Products in The WareHouse include:");
                productsList.browsing();
                System.out.println("Enter the name of product you need");
                String name1 = sc.nextLine();
                Product pr = productsList.searchProduct(name1);
                if (pr == null) {
                    System.out.println("Your Product you want to remove is not inside our warehouse!");
                } else {
                    if (choice == 3) {
                        //remove product
                        shoppingCartList.listenToRemove(pr);
                        productsList.removeItem(pr);
                    } else if (choice == 2) {
                        //edit product
                        editProduct(pr, name1);
                    }
                }
            }
        }

    }

    public static void checkShoppingListAndSearchCart(int choice) {
        // work with shopping cart list
        if (shoppingCartList.shoppingCarts.isEmpty()) {
            System.out.println("Shopping Cart List is current empty!");
        } else {
            if (choice == 68) {
                //display the sorted list of shopping cart
                shoppingCartList.displayCartBasedOnWeight();
            } else if (choice == 88) {
                // calculate the total price of all cart and print it
                System.out.println(shoppingCartList.buy());
            } else if (choice != 68 && choice != 88) {
                System.out.println("\n-----------------------------");
                System.out.println("All The Current Cart");
                shoppingCartList.displayCarts();
                System.out.println("Enter the id of cart you need");
                String cartId = sc.nextLine();
                ShoppingCart shopc = shoppingCartList.search(cartId);
                if (shopc == null) {
                    System.out.println("Your Cart you want is not inside our system");
                } else {
                    if (choice == 99) {
                        //display a cart information based on its searched id
                        System.out.println(shopc.cartAmount());
                    } else if (choice == 5) {
                        //execute shopping activity
                        shopping(shopc);
                    } else if (choice != 99 && choice != 5) {
                        shopc.displayAllProducts();
                        System.out.println("Enter the name of item you need:");
                        String pName = sc.nextLine();
                        Product sProduct = shopc.searchForProductItem(pName);
                        if (sProduct == null) {
                            System.out.println("Your Product you want to add Message is not inside your current cart");
                        } else {
                            if (choice == 2) {
                                //remove items in cart
                                shopc.removeItem(sProduct.getName());

                            } else if (choice == 3) {
                                //add message based on product name in cart
                                System.out.println("Enter the message you want to add to the gift!");
                                String msg = sc.nextLine();
                                shopc.addMessage(pName, msg);
                            } else if (choice == 4) {
                                //look message based on product name in cart
                                shopc.lookMessage(pName);
                            }
                        }
                    }
                }
            }

        }

    }

}
