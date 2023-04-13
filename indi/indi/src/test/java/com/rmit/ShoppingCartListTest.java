package com.rmit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

public class ShoppingCartListTest {
    @Test
    public void testAddCart() {
        ShoppingCart sc1 = new ShoppingCart("cart1", null);
        ShoppingCartList scl = new ShoppingCartList();
        scl.addCart(sc1);
        assertEquals(1, scl.countCarts());
        assertTrue(scl.search("cart1").equals(sc1));
    }

    @Test
    public void testCountCart() {
        ShoppingCart sc1 = new ShoppingCart("cart1", null);
        ShoppingCartList scl = new ShoppingCartList();
        scl.addCart(sc1);
        assertEquals(1, scl.countCarts());
    }

    @Test
    public void testBuy() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc1 = new ShoppingCart("cart1", pl);
        ShoppingCart sc2 = new ShoppingCart("cart2", pl);
        sc1.addItemToCart(p1.getName());
        sc2.addItemToCart(p1.getName());
        sc2.addItemToCart(p2.getName());
        ShoppingCartList scl = new ShoppingCartList();
        scl.addCart(sc2);
        scl.addCart(sc1);
        scl.speedUpShipping();
        assertEquals("You Have Just Done Your Shopping! and This is Your Bill: 3400.33000", scl.buy());
    }

    @Test
    public void testSort() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        PhysicalProducts p2 = new PhysicalProducts("Car", 3000, "New Powerful", 3);
        p2.setWeight(3.3);
        ProductsList pl = new ProductsList();
        pl.addItem(p2);
        pl.addItem(p1);
        ShoppingCart sc1 = new ShoppingCart("cart1", pl);
        ShoppingCart sc2 = new ShoppingCart("cart2", pl);
        sc1.addItemToCart(p1.getName());
        sc2.addItemToCart(p1.getName());
        sc2.addItemToCart(p2.getName());
        ShoppingCartList scl = new ShoppingCartList();
        scl.addCart(sc2);
        scl.addCart(sc1);
        scl.speedUpShipping();
        assertTrue(scl.shoppingCarts.get(0).equals(sc1));
    }


    @Test
    public void testSearch() {
        ShoppingCart sc = new ShoppingCart("cart1", null);
        ShoppingCartList scl = new ShoppingCartList();
        scl.addCart(sc);
        assertTrue(scl.search("cart1").equals(sc));
    }
}
