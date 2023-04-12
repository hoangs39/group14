package com.rmit;
import java.util.HashSet;
import java.util.Set;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

/**
 * ShoppingCart take a instance of a ProductList as a reference variable.
 * Whenever a product was add to cart, it automatically add to both 2 set: set of String and set of Product 
 * for more convenience when using below functions.
 * When a change was happened in the product list, the shopping cart 
 * will reset all the attribute in the product in the item list(set of Product) 
 * and re call cartAmount() again.
 */
public class ShoppingCart {
    Set<Product> itemsList;
    Set<String> items;
    String id;
    ProductsList lst;
    double totalWeight = 0.0;
    final double BASE_FEE = 0.1;
    double total = 0.0;

    public ShoppingCart(String id, ProductsList productList){
        this.id = id;
        this.lst = productList;
        this.itemsList = new HashSet<>();
        this.items = new HashSet<>();
    }
    public void catchUpdate(Product p, double price, String description){
        Product res = searchForProductItem(p.getName());
        if(res != null){
            res.setDescription(description);
            res.setPrice(price);
        }
        cartAmount();
        return;
    }

    public String displayCartInfo(){
        return String.format("CartID: %s, Total Weight: %.5f",id,totalWeight);
    }
    
    /**
   * Whenever the ChangeListener methods in the ShoppingCartList class is passed these value
   * The CatchUpdate will execute automatically and reset all the product containing in cart!
   * 
   * @param p  the product used for searching 
   * @param price the new price of the product p in the Product List in cart
   * @param description the new description of the product p
   * @param weight the new of physical product p
   */
    public void catchUpdate(Product p, double price, String description, double weight){
        Product res = searchForProductItem(p.getName());
        if(res != null && res instanceof PhysicalProducts){
            res.setDescription(description);
            res.setPrice(price);
            PhysicalProducts pp = (PhysicalProducts) res;
            pp.setWeight(weight);
        }
        cartAmount();
        return;
    }


    public boolean addItemToCart(String p){
        if(items.contains(p)){
            return false;
        }
        for (Product product : lst.getProductsList()) {
            if(product.getName().equals(p)){
                if(product.getQuantity() == 0){
                    return false;
                };
                product.setQuantity(product.getQuantity() - 1);
                items.add(p);
                itemsList.add(product);
                cartAmount();
                // Product setP = searchForProduct(p);
                // setP.setQuantity(1);
                return true;
            }
        }
        return false;
    }

    public int countItem(){
        return items.size();
    }



    public boolean removeItem(String p){
        Product pr = searchForProductItem(p);
        Product pro = lst.searchProduct(p);
        // System.out.println(pro.getName());
        boolean result = items.contains(p) && (pr != null) && (pro != null);
        if(result){
            itemsList.remove(pr);
            items.remove(p);
            // System.out.println(pro.getQuantity());
            pro.setQuantity(pro.getQuantity() + 1);
            // System.out.println(pro.getQuantity());
            cartAmount();
            return true;
        }
        return false;
    }


    public double cartAmount(){
        resetAll();
        for (Product p : itemsList) {
            if(p instanceof PhysicalProducts){
                PhysicalProducts phy = ((PhysicalProducts) p);
                totalWeight += phy.getWeight();
            }
            total += p.getPrice();
        }
        double fee = totalWeight * BASE_FEE;
        total += fee;
        return total;
    }
    
    private void resetAll(){
        this.total = 0;
        this.totalWeight = 0;
    }

    public void displayAllProducts(){
        for (Product p : itemsList) {
            System.out.println(p.view());
        }
    }

    /**
   * Search For item in the item list and then get/set message for the Product item
   * 
   * @param name  the product name used for searching 
   * @param message the new message of the Product item
   */
    public void addMessage(String name, String message){
        Product gift = searchForProductItem(name);
        if(gift == null){
            System.out.println("Cant add message | product is not available in the cart!");
        }
        gift.setMessage(message);
    }

    public void lookMessage(String name){
        Product gift = searchForProductItem(name);
        if(gift == null){
            System.out.println("Cant add message | product is not available in the cart!");
        }
        System.out.println(gift.getMessage());
    }

    public Product searchForProductItem(String name){
        for (Product p : itemsList) {
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
}
