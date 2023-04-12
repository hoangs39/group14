// package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

// import java.util.Scanner;

// public class MainBeforeRefactor {
//     static Scanner sc = new Scanner(System.in);
//     static ProductsList productsList = new ProductsList();
//     static ShoppingCartList shoppingCartList = new ShoppingCartList();
//     static Product p;

//     public static void main(String[] args) {
//         start();
//     }

//     public static void start(){
//         boolean active = true;
//         while (active) {
//             menu();
//             int choice;
//             do {
//                 System.out.println("Please enter the number 1-8 to execute the program:");
//                 choice = Integer.parseInt(sc.nextLine());
//             } while (choice < 1 && choice > 9);

//             switch (choice) {
//                 case 1:
//                     System.out.println("Please enter type you want for the item:");
//                     String type = sc.nextLine();
//                     System.out.println("Please enter the name you want for the item:");
//                     String name = sc.nextLine();
//                     double price;
//                     do {
//                         System.out.println("Enter its price:");
//                         price  = Double.parseDouble(sc.nextLine());
//                     } while (price  < 0.0);
//                     int quantity;
//                     do {
//                         System.out.println("Enter its available quantity:");
//                         quantity  = Integer.parseInt(sc.nextLine());
//                     } while (quantity  < 0);
//                     System.out.println("Please enter the description you want for the item:");
//                     String description = sc.nextLine();
//                     if (type.equalsIgnoreCase("physical")) {
//                         System.out.println("Please enter weight of the item:");
//                         double weight = Double.parseDouble(sc.nextLine());
//                         p = new PhysicalProducts(name,price,description,quantity);
//                         PhysicalProducts phy = (PhysicalProducts) p;
//                         phy.setWeight(weight);
//                         productsList.addItem(p);
//                     } else {
//                         Product p = new DigitalProduct(name, price, description,quantity);
//                         productsList.addItem(p);
//                     }
//                     break;
//                 case 2:
//                 if(productsList.getProductsList().isEmpty()){
//                     System.out.println("Our Warehouse is current empty!");
//                 }else{
//                     productsList.browsing();
//                     System.out.println("Enter the name of product you need to edit");
//                     name = sc.nextLine();
//                     p = productsList.searchProduct(name);
//                     if (p == null) {
//                         System.out.println("Your Product you want to edit is not inside our warehouse!");
//                     } else {
//                         editProduct(p);
//                     }
//                 }
//                     break;
//                 case 3:
//                 if(productsList.getProductsList().isEmpty()){
//                     System.out.println("Our Warehouse is current empty!");
//                 }else{
//                     productsList.browsing();
//                     System.out.println("Enter the name of product you need to remove!");
//                     name = sc.nextLine();
//                     p = productsList.searchProduct(name);
//                     if (p == null) {
//                         System.out.println("Your Product you want to remove is not inside our warehouse!");
//                     } else {
//                         shoppingCartList.listenToRemove(p);
//                         productsList.removeItem(p);
//                     }
//                 }
//                     break;
//                 case 4:
//                 if(productsList.getProductsList().isEmpty()){
//                     System.out.println("Our Warehouse is current empty!");
//                 }else{
//                     shoppingList();
//                 }
//                     break;
//                 case 5:
//                 if(productsList.getProductsList().isEmpty()){
//                     System.out.println("Our Warehouse is current empty!");
//                 }else{
//                     productsList.browsing();
//                 }
//                     break;
//                 case 6:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart list is current empty!");
//                 }else{
//                     shoppingCartList.displayCartBasedOnWeight();
//                 }
//                     break;
//                 case 7:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart list is current empty!");
//                 }else{
//                     System.out.println(shoppingCartList.buy());
//                 }
//                     break;
//                 case 8:
//                     displayCartAmount();
//                     break;
//                 case 9:
//                     active = false;
//                     break;

//             }

//         }
//     }

//     public static void menu() {
//         System.out.println("1.Add an item into Our Product Warehouse:");
//         System.out.println("2.Edit items into Our Product Warehouse:");
//         System.out.println("3.Remove an item into Our Product Warehouse:");
//         System.out.println("4.Shopping!");
//         System.out.println("5.Browse Our Product Warehouse:");
//         System.out.println("6.Display Carts:");
//         System.out.println("7.Display Cart Amount:");
//         System.out.println("8.Payment!");
//         System.out.println("9.Exit!");
//     }

//     public static void editProduct(Product p) {
//         double price;
//         do {
//             System.out.println("Enter its price:");
//             price  = Double.parseDouble(sc.nextLine());
//         } while (price  < 0.0);
//         int quantity;
//         do {
//             System.out.println("Enter its available quantity:");
//             quantity  = Integer.parseInt(sc.nextLine());
//         } while (quantity  < 0);
//         System.out.println("Please enter the description you want for the item:");
//         String description = sc.nextLine();
//         p.setDescription(description);
//         p.setPrice(price);
//         p.setQuantity(quantity);
//         if (p instanceof PhysicalProducts) {
//             System.out.println("Please enter weight of the item:");
//             double weight = Double.parseDouble(sc.nextLine());
//             PhysicalProducts phy = (PhysicalProducts) p;
//             phy.setWeight(weight);
//             shoppingCartList.listenToChange(phy, description, price, weight);
//         }else{
//             shoppingCartList.listenToChange(p, description, price);
//         }
//     }


//     public static void shoppingMenu() {
//         System.out.println("1.Add New Cart and start to shop");
//         System.out.println("2.Remove item from Cart");
//         System.out.println("3.Add Message to Product");
//         System.out.println("4.Show The Message of Your Product:");
//         System.out.println("5.Add additional items to cart!");
//         System.out.println("6.Exit!");
//     }

//     public static void displayCartAmount() {
//         if(shoppingCartList.shoppingCarts.isEmpty()){
//             System.out.println("Shopping Cart List is current empty!");
//         }else{
//             shoppingCartList.displayCartBasedOnWeight();
//             System.out.println("Enter the id of cart you need");
//             String cartId3 = sc.nextLine();
//             ShoppingCart shopc3 = shoppingCartList.search(cartId3);
//             if (shopc3 == null) {
//                 System.out.println("Your Cart you want is not inside our system");
//             } else {
//                 System.out.println(shopc3.cartAmount());
//             }
//         }
//     }

//     public static void shoppingList() {
//         boolean active1 = true;
//         while (active1) {
//             productsList.browsing();
//             shoppingMenu();
//             int choice1;
//             do {
//                 System.out.println("Please enter the number 1-6 to execute the program:");
//                 choice1 = Integer.parseInt(sc.nextLine());
//             } while (choice1 < 1 && choice1 > 6);

//             switch (choice1) {
//                 case 1:
//                     System.out.println("Please enter the id you want for the cart:");
//                     String id = sc.nextLine();
//                     ShoppingCart cart = new ShoppingCart(id,productsList);
//                     shopping(cart);
//                     shoppingCartList.addCart(cart);
//                     break;
//                 case 2:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart List is current empty!");
//                 }else{
//                     shoppingCartList.displayCartBasedOnWeight();
//                     System.out.println("Enter the id of cart you need");
//                     String cartId = sc.nextLine();
//                     ShoppingCart shopc = shoppingCartList.search(cartId);
//                     if (shopc == null) {
//                         System.out.println("Your Cart you want to edit is not inside our system");
//                     } else {
//                         shopc.displayAllProducts();
//                         System.out.println("Enter the name of item you need to remove");
//                         String pName = sc.nextLine();
//                         Product remP = shopc.searchForProduct(pName);
//                         if (remP == null) {
//                             System.out.println("Your Product you want to remove is not inside your current cart");
//                         } else {
//                             shopc.removeItem(remP.getName());
//                         }
//                     }
//                 }
//                     break;
//                 case 3:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart List is current empty!");
//                 }else{
//                     shoppingCartList.displayCartBasedOnWeight();
//                     System.out.println("Enter the id of cart you need");
//                     String cartId1 = sc.nextLine();
//                     ShoppingCart shopc1 = shoppingCartList.search(cartId1);
//                     if (shopc1 == null) {
//                         System.out.println("Your Cart you want is not inside our system");
//                     } else {
//                         shopc1.displayAllProducts();
//                         System.out.println("Enter the name of item you need to add message ");
//                         String addName = sc.nextLine();
//                         Product addMsgGift = shopc1.searchForProduct(addName);
//                         if (addMsgGift == null) {
//                             System.out.println("Your Product you want to add Message is not inside your current cart");
//                         } else {
//                             System.out.println("Enter the message you want to add to the gift!");
//                             String msg = sc.nextLine();
//                             shopc1.addMessage(addName, msg);
//                         }
//                     }
//                 }
//                     break;
//                 case 4:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart List is current empty!");
//                 }else{
//                     shoppingCartList.displayCartBasedOnWeight();
//                     System.out.println("Enter the id of cart you need");
//                     String cartId2 = sc.nextLine();
//                     ShoppingCart shopc2 = shoppingCartList.search(cartId2);
//                     if (shopc2 == null) {
//                         System.out.println("Your Cart you want to edit is not inside our system");
//                     } else {
//                         shopc2.displayAllProducts();
//                         System.out.println("Enter the name of item you need to show message ");
//                         String showName = sc.nextLine();
//                         Product showMsgGift = shopc2.searchForProduct(showName);
//                         if (showMsgGift == null) {
//                             System.out.println("Your Product you want to add Message is not inside your current cart");
//                         } else {
//                             shopc2.lookMessage(showName);
//                         }
//                     }
//                 }
//                     break;
//                 case 5:
//                 if(shoppingCartList.shoppingCarts.isEmpty()){
//                     System.out.println("Shopping Cart List is current empty!");
//                 }else{
//                     shoppingCartList.displayCartBasedOnWeight();
//                     System.out.println("Enter the id of cart you need");
//                     String cartId2 = sc.nextLine();
//                     ShoppingCart shopc2 = shoppingCartList.search(cartId2);
//                     if (shopc2 == null) {
//                         System.out.println("Your Cart you want is not inside our system");
//                     } else {
//                         shopping(shopc2);
//                     }
//                 }
//                     break;
//                 case 6:
//                 active1 = false;
//                 break;

//             }

//         }

//     }


//     public static void shopping(ShoppingCart cart) {
//         boolean esc = true;
//         while (esc) {
//             System.out.println("1.Buying");
//             System.out.println("2.Exit");
//             int choice11;
//             do {
//                 System.out.println("Please enter the number 1-2 to execute the program:");
//                 choice11 = Integer.parseInt(sc.nextLine());
//             } while (choice11 < 1 && choice11 > 2);
//             switch (choice11) {
//                 case 1:
//                     productsList.browsing();
//                     System.out.println("Enter the name of product you need to add to cart");
//                     String name = sc.nextLine();
//                     Product buyP = productsList.searchProduct(name);
//                     if (p == null) {
//                         System.out.println("Your Product you want to edit is not inside our warehouse!");
//                     } else {
//                         cart.addItemToCart(buyP.getName());
//                     }
//                     break;
//                 case 2:
//                     esc = false;
//                     break;

//             }
//         }
//     }


// }
