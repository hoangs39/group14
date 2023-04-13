package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean exit = true;
        ProductList l=new ProductList();
        List<Product> products=l.getProductsList();
        ShoppingCartList scl=new ShoppingCartList();
        while(exit){
            int option;
            do {
                System.out.println("Please enter option number 0-8: ");
                System.out.println("0: Create a new product");
                System.out.println("1: Display all products");
                System.out.println("2: Create a new shopping cart and add products to the current shopping cart");
                System.out.println("3: Edit a product in the product list and the shopping cart");
                System.out.println("4: Remove a product from the current shopping cart");
                System.out.println("5: Display the cart amount");
                System.out.println("6: Display all shopping carts based on their total weight");
                System.out.println("7: Exit");
                System.out.print("Your option: ");
                option=Integer.parseInt(sc.nextLine());
            } while (option<0&&option>7);
            switch(option){
                case 0:
                    Scanner zero= new Scanner(System.in);
                    System.out.println();
                    String productType;
                    System.out.println("Enter the type of the product:(physical or digital): ");
                    productType=zero.nextLine();
                    System.out.println("Enter the name of the product: ");
                    String name=zero.nextLine();
                    System.out.println("Enter the description of the product: ");
                    String description=zero.nextLine();
                    int quantityAvailable;
                    System.out.println("Enter the message of the product: ");
                    String message = zero.nextLine();
                    do{
                    System.out.println("Enter the available quantity of the product: ");
                    quantityAvailable=Integer.parseInt(zero.nextLine());
                    }
                    while(quantityAvailable<0);
                    double price;
                    do{
                        System.out.println("Enter the price of the product: ");
                        price=Double.parseDouble(zero.nextLine());
                    }
                    while(price<0.0);
                    if(productType.equals("physical")){
                        double weight;
                    do{
                        System.out.println("Enter the weight of the product: ");
                        weight=Double.parseDouble(zero.nextLine());
                    }
                    while(weight<0.0);
                        l.addProduct(new PhysicalProduct(name,description,quantityAvailable,price,message,weight));
                    }
                    if(productType.equals("digital")){
                        l.addProduct(new DigitalProduct(name,description,quantityAvailable,price,message));
                    }
                    break;
                case 1:
                for(Product p: products){
                    System.out.println(p);
                }  
                    break;
                case 2:
                Scanner two=new Scanner(System.in);
                boolean e=true;
                String productName;
                String choice;
                int id;
                System.out.println("Enter the id of the shopping cart: ");
                id=Integer.parseInt(two.nextLine());
                ShoppingCart s=new ShoppingCart(l,id);
                do{
                System.out.println("Enter the name of the product you want to add to the shopping cart:");
                productName= two.nextLine();
                s.addItem(productName);
                System.out.println("Do you want to quit adding product to the shopping cart?(y or n):");
                choice= two.nextLine();
                if(choice.equals("y")){
                    e=false;
                }
            }
                while(e);
                scl.addShoppingCart(s);
                    break;
                case 3:
                Scanner three=new Scanner(System.in);
                    if(scl.getShoppingCartList().isEmpty()){
                        System.out.println("The shopping cart is currently empty");
                    }
                    else{
                    System.out.println("Enter the name of the product that you want to edit:");
                    String pName=three.nextLine();
                    Product pro=l.searchForProduct(pName);

                    System.out.println("Enter the new description:");
                    String newDewscription=three.nextLine();
                    System.out.println("Enter the new avaiable quantity:");
                    int newQuantityAvailable=Integer.parseInt(three.nextLine());
                    System.out.println("Enter the new price:");
                    double newPrice=Double.parseDouble(three.nextLine());
                    System.out.println("Enter the new message:");
                    String newMessage=three.nextLine();
                    double newWeight = 0;
                    if(pro instanceof PhysicalProduct){
                    PhysicalProduct phy= (PhysicalProduct) pro;
                    System.out.println("Enter the new weight:");
                    newWeight=Double.parseDouble(three.nextLine());
                    phy.setWeight(newWeight);
                    }

                    pro.setDescription(newDewscription);
                    pro.setQuantityAvailable(newQuantityAvailable);
                    pro.setPrice(newPrice);
                    pro.setMessage(newMessage);

                    for (ShoppingCart shoppingCart : scl.getShoppingCartList()) {
                    Product proInCart=shoppingCart.searchForProductInCart(pName);
                    if(proInCart!=null){
                    proInCart.setDescription(newDewscription);
                    proInCart.setQuantityAvailable(newQuantityAvailable);
                    proInCart.setPrice(newPrice);
                    proInCart.setMessage(newMessage);
                    if(proInCart instanceof PhysicalProduct){
                    PhysicalProduct phyCart= (PhysicalProduct) proInCart;
                    phyCart.setWeight(newWeight);
                    }
                    shoppingCart.cartAmount();
                    }
                    }
                    }
                    break;
                case 4:
                    Scanner four= new Scanner(System.in);
                    if(scl.getShoppingCartList().isEmpty()){
                        System.out.println("The shopping cart is currently empty");
                    }
                    else{
                    System.out.println("Enter the id of the shopping cart: ");
                    int idRemove=Integer.parseInt(four.nextLine());
                    ShoppingCart shoppingCartRemove=scl.searchForCartInCartList(idRemove);
                    System.out.println("Enter the name of the product that you want to remove:");
                    String pNameRemove=four.nextLine();
                    Product proInCartRemove=shoppingCartRemove.searchForProductInCart(pNameRemove);
                    shoppingCartRemove.removeItem(proInCartRemove.getName());
                    shoppingCartRemove.cartAmount();
                    }
                    break;
                case 5:
                Scanner five= new Scanner(System.in);
                if(scl.getShoppingCartList().isEmpty()){
                    System.out.println("The shopping cart is currently empty");
                }
                else{
                    System.out.println("Enter the id of the shopping cart: ");
                    int idCartAmount=Integer.parseInt(five.nextLine());
                    ShoppingCart shoppingCartAmount=scl.searchForCartInCartList(idCartAmount);
                    System.out.println("The cart amount of the shopping cart is:");
                    System.out.println(shoppingCartAmount.cartAmount());
                }
                    break;
                case 6:
                    scl.sortBasedOnTotalWeights();
                    for(ShoppingCart shoppingCartWeight: scl.getShoppingCartList()){
                        shoppingCartWeight.displayShoppingCartSet();
                    }
                    break;
                case 7:
                exit=false;
                    break;
            }
        }
    }
}
