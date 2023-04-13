package com.example;
/**
 * The Main class will be the starting point of the program
 * Running this class will create a UI (User Interface) for the user to interact with
 * @author <Hoang Vinh Khue - s3927474>
 */

import java.util.Scanner;

public class Main {
    private static ProductService ps = ProductService.getpServiceInstance();
    private static CartService cs = CartService.getcServiceInstance();

    public static void main(String[] args) {
        Product pp1 = new PhysicalProduct("T-Shirt", "This is a T-Shirt", 1, 1.99, 0.1);
        Product pp2 = new PhysicalProduct("Coat", "This is a Coat", 1, 2.99, 0.5);
        Product pp3 = new PhysicalProduct("Keyboard", "This is a keyboard",1 ,2.00 ,1 );
        Product pp4 = new PhysicalProduct("Football", "This is a football", 1, 5.99, 0.4);
        Product pp5 = new PhysicalProduct("Milk Tea", "Delicious", 1, 0.49, 0.2);

        Product dp1 = new DigitalProduct("CS2 game", "This is the CS2 game", 1, 2.50);
        Product dp2 = new DigitalProduct("Ebook", "This is an Ebook", 1, 2.00);
        Product dp3 = new DigitalProduct("Programming course", "This course teaches Java", 1, 4.99);
        Product dp4 = new DigitalProduct("LoL", "This is a game", 1, 1.00);

        ps.addProduct(pp1);
        ps.addProduct(pp2);
        ps.addProduct(pp3);
        ps.addProduct(pp4);
        ps.addProduct(pp5);

        ps.addProduct(dp1);
        ps.addProduct(dp2);
        ps.addProduct(dp3);
        ps.addProduct(dp4);

        ShoppingCart cart1 = new ShoppingCart();
        cart1.addItem("T-Shirt");
        cart1.addItem("Ebook");
        cart1.addItem("Coat");

        ShoppingCart cart2 = new ShoppingCart();
        cart2.addItem("Football");

        cs.addCart(cart1);
        cs.addCart(cart2);

        Scanner scanner = new Scanner(System.in);

        // Clear the console
        System.out.print("\033[H\033[2J");  
        System.out.flush();    

        // Printing the UI
        System.out.println("Welcome to the online shopping service. What would you like to do?");
        while (true) {
            System.out.println("Choose an action: ");
            System.out.println("1. Create a new product");
            System.out.println("2. Edit a product");
            System.out.println("3. Create a new shopping cart");
            System.out.println("4. Add a product to the current shopping cart");
            System.out.println("5. Remove a product from the current shopping cart");
            System.out.println("6. List all available products");
            System.out.println("7. List all shopping carts based on their total weight");
            System.out.println("0. Exit");
            System.out.println();

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createNewProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    createNewShoppingCart();
                    break;
                case 4:
                    addProductToCart();
                    break;
                case 5:
                    removeProductFromCart();
                    break;
                case 6:
                    listAvailableProducts();
                    break;
                case 7:
                    listShoppingCartsByWeight();
                    break;
                case 0:
                    System.out.println("Thank you for using our service. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * This function will create a new product by taking user input
     */
    private static void createNewProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which type of product do you want to create");
        System.out.println("1. Digital");
        System.out.println("2. Physical");       
        System.out.println();
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Product name: ");
                String dname = scanner.nextLine();
                System.out.println("Description: ");
                String ddes = scanner.nextLine();
                System.out.println("Quantity: ");
                int dquan = scanner.nextInt();
                System.out.println("Price: ");
                double dprice = scanner.nextDouble();

                Product newDigitalProduct = new DigitalProduct(dname, ddes, dquan, dprice);
                ps.addProduct(newDigitalProduct);

                System.out.println("Product added sucessfully!");
                System.out.println("\nReturning to menu... \n");
                break;
            case 2:
                System.out.println("Product name: ");
                String pname = scanner.nextLine();
                System.out.println("Description: ");
                String pdes = scanner.nextLine();
                System.out.println("Quantity: ");
                int pquan = scanner.nextInt();
                System.out.println("Price: ");
                double pprice = scanner.nextDouble();
                System.out.println("Weight: ");
                double pweight = scanner.nextDouble();

                Product newPhysicalProduct = new PhysicalProduct(pname, pdes, pquan, pprice, pweight);
                ps.addProduct(newPhysicalProduct);

                System.out.println("Product added sucessfully!");
                System.out.println("\nReturning to menu... \n");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
    } 

    /**
     * This function will edit the information of an existing product by 
     * deleting that product and adding a new product
     */
    private static void editProduct() {
        Scanner scanner = new Scanner(System.in);

        // Printing the products and let user choose the product they want to edit
        System.out.println();
        System.out.println("All products available: ");
        System.out.println(ps.listProducts());
        System.out.println("Enter the name of the product that you want to edit: ");

        String name = scanner.nextLine();
        ShoppingCart currentCart = cs.getCartWithMatchingItem(name);

        Product chosenProduct = ps.getProduct(name);
        Product newProduct = null;

        // if there are any cart that contain the chosen product, it will remove the product
        if (currentCart != null) {
            currentCart.removeItem(name);
        }
        ps.removeProduct(name);

        System.out.println("New name: ");
        String newName = scanner.nextLine();
        System.out.println("New description: ");
        String newDesc = scanner.nextLine();
        System.out.println("New quantity: ");
        int newQuan = scanner.nextInt();
        System.out.println("New price: ");
        double newPrice = scanner.nextDouble();

        if (chosenProduct.isPhysical()) {
            System.out.println("New weight");
            double newWeight = scanner.nextDouble();
            newProduct = new PhysicalProduct(newName, newDesc, newQuan, newPrice, newWeight);
            ps.addProduct(newProduct);
        } else {
            newProduct = new DigitalProduct(newName, newDesc, newQuan, newPrice);
            ps.addProduct(newProduct);
        }

        // Adding the new product to the current cart
        if (currentCart != null) {
            currentCart.addItem(newProduct.getName());
        }
        System.out.println("Product editted succesfully! \n");
    }

    /**
     * This function will create a new shopping cart for the user
     */
    private static void createNewShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cs.addCart(cart);
        System.out.println("Shopping cart created successfully!");
        System.out.println("\nReturning to menu... \n");
    }

    /**
     * This function will allow user to add any product to their shopping carts
     */
    private static void addProductToCart() {
        Scanner scanner = new Scanner(System.in);

        // Let user choose a cart
        System.out.println("Which cart do you want to choose: ");
        cs.listShoppingCartbyIndex();
        ShoppingCart currentCart = cs.getCart(scanner.nextInt());
        scanner.nextLine();
        
        // Adding the item
        System.out.println("Which item do you want to add: ");
        System.out.println(ps.listProducts());
        String pName = scanner.nextLine();
        if (ps.getProduct(pName).getQuantity() > 0) {
            currentCart.addItem(pName);
            System.out.println("Item " + pName + " added successfully!");
            System.out.println("\nReturning to menu... \n");
        } else {
            System.out.println("Cannot add this item!");
            System.out.println("\nReturning to menu... \n");
        }
    }

    /**
     * This function will allow user to remove any product from their shopping carts
     */
    private static void removeProductFromCart() {
        Scanner scanner = new Scanner(System.in);

        // Let user choose a cart
        System.out.println("Which cart do you want to choose: ");
        cs.listShoppingCartbyIndex();
        ShoppingCart currentCart = cs.getCart(scanner.nextInt());
        scanner.nextLine();

        // Removing the item
        System.out.println("Type the name of the item that you want to remove: ");
        cs.printCart(currentCart);
        String pName = scanner.nextLine();
        currentCart.removeItem(pName);

        System.out.println("Item " + pName + " removed successfully!");
        System.out.println("\nReturning to menu... \n");
    }

    /**
     * This function will display all of the products in the store
     */
    private static void listAvailableProducts() {
        System.out.println();
        System.out.println("All products available: ");
        System.out.println(ps.listProducts());
        System.out.println("Returning to menu... \n");
    }

    /**
     * This function will display all shopping carts based on their total weight
     */
    private static void listShoppingCartsByWeight() {
       System.out.println("Listing shopping cart by weight: ");
       cs.sortShoppingCartByWeight();
       System.out.println("\nReturning to menu... \n");
    }
}
