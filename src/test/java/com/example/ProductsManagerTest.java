package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class ProductsManagerTest {
    @Test
    void testAddDuplicatedProduct() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");

        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        assertTrue( !productManager.addProduct(p1));
    }
    @Test
    void testAddProduct() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        assertTrue( productManager.addProduct(p1));
    }

    @Test
    void testCountProducts() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        assertTrue( productManager.countProducts() == 1);
    }

    @Test
    void testGetProductByName() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        assertTrue( productManager.getProductByName(p2.getName()).equals(p2));
    }


    @Test
    void testRemoveProduct() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        assertTrue( productManager.removeProduct(p2));
        assertTrue( productManager.getProductByName(p2.getName()) == null);

    }
}
