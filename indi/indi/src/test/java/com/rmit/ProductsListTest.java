package com.rmit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

public class ProductsListTest {
    @Test
    public void testAddItem() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        assertTrue(pl.getProductsList().get(0).getName().equals("Car"));
        assertTrue(pl.getProductsList().get(1).getName().equals("TV"));
    }


    @Test
    public void testCountItem() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        assertEquals(2, pl.countItem());
    }

    @Test
    public void testRemoveItem() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        pl.removeItem(p2);
        assertTrue(pl.getProductsList().get(0).getName().equals("TV"));
    }

    @Test
    public void testSearchProduct() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        Product p = pl.searchProduct("TV");
        assertTrue(p.equals(p1));
    }
}
