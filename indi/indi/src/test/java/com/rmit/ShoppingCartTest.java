package com.rmit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

public class ShoppingCartTest {
    @Test
    public void testAdd() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p1.getName());
        assertEquals(1, p1.getQuantity());
        assertEquals(1, sc.countItem());
        assertTrue(sc.searchForProductItem(p1.getName()) != null);
    }
    @Test
    public void testAddDigitalItemToCart() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        assertTrue(sc.lst.equals(pl));
        sc.addItemToCart(p1.getName());
        assertEquals(200, sc.total);
        assertEquals(0, 0);
    }

    @Test
    public void testAddPhysicalItemToCart() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p2.getName());
        assertEquals(3.3, sc.totalWeight);
    }
    @Test
    public void testAdd2ItemsToCartNoDulicate() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p1.getName());
        sc.addItemToCart(p2.getName());
        assertEquals(2, sc.countItem());
    }
    @Test
    public void testAdd2ItemsToCartDulicate() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p1.getName());
        sc.addItemToCart(p1.getName());
        assertEquals(1, sc.countItem());
    }

    @Test
    public void testAddMessage() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p1.getName());
        sc.addMessage("TV", "Hi");
        assertTrue(sc.searchForProductItem("TV").getMessage().equals("Hi"));
    }

    @Test
    public void testCartAmount() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p2.getName());
        sc.addItemToCart(p1.getName());
        assertEquals(3200.33, sc.total);
        assertEquals(3.3, sc.totalWeight);
    }

    @Test
    public void testCatchUpdate() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);

        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p1.getName());
        sc.catchUpdate(sc.searchForProductItem(p1.getName()), 20, "a");
        assertEquals(20, sc.searchForProductItem(p1.getName()).getPrice());
        assertTrue(sc.searchForProductItem(p1.getName()).getDescription().equals("a"));
    }

    @Test
    public void testCatchUpdate2() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);

        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p2.getName());
        sc.catchUpdate(sc.searchForProductItem(p2.getName()), 20, "a",20);
        Product p = sc.searchForProductItem(p2.getName());
        PhysicalProducts phy = (PhysicalProducts) p;
        assertEquals(20, p.getPrice());
        assertEquals(20, phy.getWeight());
        assertTrue(p.getDescription().equals("a"));
    }

    @Test
    public void testCountItem() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        assertTrue(sc.lst.equals(pl));
        sc.addItemToCart(p2.getName());
        sc.addItemToCart(p1.getName());
        assertEquals(2, sc.countItem());
    }

    @Test
    public void testRemoveItem() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        assertTrue(sc.lst.equals(pl));
        sc.addItemToCart(p2.getName());
        sc.addItemToCart(p1.getName());
        sc.removeItem(p1.getName());
        assertEquals(1, sc.countItem());
        assertEquals(2, p1.getQuantity());
        assertEquals(3000.33, sc.total);
    }

    @Test
    public void testSearchForProduct() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        assertTrue(sc.lst.equals(pl));
        sc.addItemToCart(p2.getName());
        assertTrue(sc.searchForProductItem("Car").getName().equals("Car"));
    }

    @Test
    public void testDisplayCartInfo() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc = new ShoppingCart("cart1", pl);
        sc.addItemToCart(p2.getName());
        assertTrue(sc.displayCartInfo().equals("CartID: cart1, Total Weight: 3.30000"));
    }
}
