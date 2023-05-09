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
         // check the ability of adding the same cart to collection
        assertTrue( !productManager.addProduct(p1));
    }
    @Test
    void testAddProduct() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
         // check the ability of adding carts to collection
        assertTrue( productManager.addProduct(p1));
    }

    @Test
    void testCountProducts() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        // check size of the collection inside the collection after having added products to the it.
        assertTrue( productManager.countProducts() == 1);
    }

    @Test
    void testGetProductByName() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        // check the equality of output product of searching function and the searched product
        assertTrue( productManager.getProductByName(p2.getName()).equals(p2));
    }


    @Test
    void testRemoveProduct() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p2);
        assertTrue( productManager.removeProduct(p2));
        // check the size after having deleted the only product in the product manager's collection
        assertTrue( productManager.getProductByName(p2.getName()) == null);

    }
}
