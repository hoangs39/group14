package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class CartsManagerTest {
    @Test
    void testAddCart() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
        // create a product manger or store for 2 below carts.
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        // create 2 carts
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        ShoppingCart c2 = new ShoppingCart("C2",productManager);
        c2.addItem(p2);
        c2.addItem(p1);
        // adding carts to carts collection
        CartsManager cm = new CartsManager();
        cm.addCart(c1);
        cm.addCart(c2);
        // check the ability of adding the same cart to collection
        assertTrue(!cm.addCart(c1));
    }


    @Test
    void testCountCarts() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
        // create a product manger or store for 2 below carts.
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        // create 2 carts
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        ShoppingCart c2 = new ShoppingCart("C2",productManager);
        c2.addItem(p2);
        c2.addItem(p1);
         // adding carts to carts collection
        CartsManager cm = new CartsManager();
        cm.addCart(c1);
        cm.addCart(c2);
        // check size of the collection inside the collection after having added carts to the it.
        assertTrue(cm.countCarts() == 2);
    }


    @Test
    void testGetCartById() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
         // create a product manger or store for 2 below carts.
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        // create 2 carts
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        ShoppingCart c2 = new ShoppingCart("C2",productManager);
        c2.addItem(p2);
        c2.addItem(p1);
         // adding carts to carts collection
        CartsManager cm = new CartsManager();
        cm.addCart(c1);
        cm.addCart(c2);
        //check if cart equality of input cart instance and the output instance got by using this function.
        assertTrue(cm.getCartById("C1").equals(c1));
    }

    @Test
    void testRemoveItemInCart() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
         // create a product manger or store for 2 below carts.
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
         // create 2 carts
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        ShoppingCart c2 = new ShoppingCart("C2",productManager);
        c2.addItem(p2);
         // adding carts to carts collection
        CartsManager cm = new CartsManager();
        cm.addCart(c1);
        cm.addCart(c2);
        cm.removeItemInCart(p1);
        // check whether the deleted is still avaiable in cart or not?
        assertTrue(c1.searchItem(p1.getName(),1) == null);
        assertTrue(c1.searchItem(p1.getName(),2) == null);
        assertTrue(c1.getCountItems().get(p1.getName()) == null);
    }

    @Test
    void testSpeedUpShipping() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        PhysicalProducts p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
        p2.setWeight(5);
         // create a product manger or store for 2 below carts.
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
         // create 2 carts
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        ShoppingCart c2 = new ShoppingCart("C2",productManager);
        c2.addItem(p2);
         // adding carts to carts collection
        CartsManager cm = new CartsManager();
        cm.addCart(c1);
        cm.addCart(c2);
        cm.speedUpShipping();
        // check the accuracy of sorting alogrithms by examining the equality of the first element 
        // and the lightest cart in the collection
        assertTrue(cm.getShoppingCarts().get(0).equals(c2));
    }
}
