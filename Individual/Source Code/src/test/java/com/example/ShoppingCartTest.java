package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    @Test
    void testAddItem() {
        Product product1 = new DigitalProduct("Ebook", "This is a book", 1, 2.99);
        Product product2 = new PhysicalProduct("Keyboard", "This is a keyboard",1 ,2.00 ,1 );
        Product product3 = new PhysicalProduct("Football", "This is a football", 1, 5.99, 0.4);

        ProductService ps = ProductService.getpServiceInstance();

        ps.addProduct(product1);
        ps.addProduct(product2);
        ps.addProduct(product3);

        ShoppingCart cart = new ShoppingCart();
        assertTrue(cart.addItem("Ebook"));
        assertTrue(cart.addItem("Keyboard"));

        cart.addItem("Football");
        assertEquals(0, ps.getProduct("Football").getQuantity());
    }

    @Test
    void testRemoveItem() {
        Product product1 = new DigitalProduct("Ebook", "This is a book", 1, 2.99);
        Product product2 = new PhysicalProduct("Keyboard", "This is a keyboard",1 ,2.00 ,1 );
        Product product3 = new PhysicalProduct("Football", "This is a football", 1, 5.99, 0.4);

        ProductService ps = ProductService.getpServiceInstance();

        ps.addProduct(product1);
        ps.addProduct(product2);
        ps.addProduct(product3);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Ebook");
        cart.addItem("Keyboard");
        cart.addItem("Football");

        assertTrue(cart.removeItem("Ebook"));
        assertTrue(cart.removeItem("Keyboard"));

        cart.removeItem("Football");
        assertEquals(1, ps.getProduct("Football").getQuantity());
    }
}
