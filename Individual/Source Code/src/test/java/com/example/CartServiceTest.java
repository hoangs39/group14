package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CartServiceTest {
    @Test
    void testGetCartWithMatchingItem() {
        CartService cs = CartService.getcServiceInstance();
        ProductService ps = ProductService.getpServiceInstance();

        Product product1 = new PhysicalProduct("T-Shirt", "This is a T-Shirt", 1, 1.99, 0.1);
        Product product2 = new PhysicalProduct("Coat", "This is a Coat", 1, 2.99, 0.5);
        Product product3 = new PhysicalProduct("Milk Tea", "Delicious", 1, 0.49, 0.2);
        Product product4 = new DigitalProduct("CS2 game", "This is the CS2 game", 1, 2.50);
        
        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = new ShoppingCart();

        ps.addProduct(product1);
        ps.addProduct(product2);
        ps.addProduct(product3);
        ps.addProduct(product4);

        cs.addCart(cart1);
        cs.addCart(cart2);

        cart1.addItem("T-Shirt");
        cart1.addItem("Coat");

        cart2.addItem("Milk Tea");
        cart2.addItem("CS2 game");

        assertEquals(cart1, cs.getCartWithMatchingItem("T-Shirt"));
        assertEquals(cart1, cs.getCartWithMatchingItem("Coat"));
        assertEquals(cart2, cs.getCartWithMatchingItem("Milk Tea"));
        assertEquals(cart2, cs.getCartWithMatchingItem("CS2 game"));
    }
}
