package com.example;
/**
 * @author <Hoang Vinh Khue - s3927474>
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ProductServiceTest {
    /**
     * Since both the getProduct() and removeProduct() methods take a String as their parameter,
     * I test put both of the methods in this test 
     */
    @Test
    void testGetAndRemoveProduct() {
        Product product1 = new DigitalProduct("Ebook", "This is a book", 1, 2.99);
        Product product2 = new PhysicalProduct("Keyboard", "This is a keyboard",1 ,2.00 ,1 );
        Product product3 = new PhysicalProduct("Football", "This is a football", 1, 5.99, 0.4);
        ProductService ps = ProductService.getpServiceInstance();

        ps.addProduct(product1);
        ps.addProduct(product2);
        ps.addProduct(product3);

        assertEquals(product1, ps.getProduct("Ebook"));
        assertEquals(product2, ps.getProduct("Keyboard"));
        assertEquals(product3, ps.getProduct("Football"));

        ps.removeProduct("Ebook");

        assertNotEquals(product1, ps.getProduct("Ebook"));
    }
}
