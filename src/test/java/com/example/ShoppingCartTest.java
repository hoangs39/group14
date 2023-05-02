package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class ShoppingCartTest {


    @Test
    void testAddItem() {
    Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
    Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
    ProductsManager productManager = new ProductsManager();
    productManager.addProduct(p1);
    productManager.addProduct(p2);
    ShoppingCart c1 = new ShoppingCart("C1",productManager);
    c1.addItem(p1);
    c1.addItem(p1);
    c1.addItem(p2);
    //check qunatity of the item in the cart
    assertTrue(c1.countItem() == 3);
    // check name
    assertTrue(c1.searchItem(p1.getName(),1).equals(p1));
    // check quantiy of products inside cart
    assertTrue(c1.getCountItems().get(p1.getName()) == 2);
    //check qunatity of the item is product list
    assertTrue(p1.getAvailableQuantity() == 2);
    }

    @Test
    void testAddMessageUnsucessfully() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        // check the msg received when the gift feature is not enabled
        c1.addMessage(p1.getName(),1, "hi");
        assertTrue(c1.searchItem(p1.getName(),1).getMessage().equals("notSupport!"));
    }
    @Test
    void testNotAddMessage() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setCreateGift(true);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        // enable gift feature but not add msg
        assertTrue(c1.searchItem(p1.getName(),1).getMessage().equals("Empty!"));
    }

    @Test
    void testAddMessage() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setCreateGift(true);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addMessage(p1.getName(),1, "Hello");
        //check msg
        assertTrue(c1.searchItem(p1.getName(),1).getMessage().equals("Hello"));
    }

    @Test
    void testApplyCoupon() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        c1.applyCoupon(p2.getName());
        // check price after applying coupon
        assertTrue(c1.cartAmount() == 42.4000);
    }

    @Test
    void testApply2Coupons() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        p1.setCoupon(cp1);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        c1.applyCoupon(p2.getName());
        c1.applyCoupon(p1.getName());
        //check price of cart after applying 2 coupons
        assertTrue(c1.cartAmount() == 47.6000);
    }

    @Test
    void testCartAmount() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.setWeight(10.00);
        PhysicalProducts p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        //check the accuracy of the price
        assertTrue(c1.cartAmount() == 53.400);
    }

    @Test
    void testChangeItemAll() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.setWeight(10.00);
        PhysicalProducts p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p2.setWeight(10.00);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        p2.setPrice(1);
        c1.changeItemAll(p2);
        //check the accuracy of the price after changed data
        assertTrue(c1.cartAmount() == 51.100);
    }

    @Test
    void testCountItem() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.setWeight(10.00);
        PhysicalProducts p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setWeight(10.00);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        // test count size of the cart
        assertTrue(c1.countItem() == 2);
    }



    @Test
    void testGetCartId() {
        ProductsManager productManager = new ProductsManager();
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        assertTrue(c1.getCartId().equals("C1"));
    }

    @Test
    void testGetMessage() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        p1.setCreateGift(true);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        c1.addMessage(p1.getName(),1, "Hello");
        assertTrue(c1.getMessage(p1.getName(),1).equals("Hello"));
    }

    @Test
    void testRemoveCoupon() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        p1.setCoupon(cp1);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        Product p = c1.searchItem(p1.getName(),1);
        c1.removeCoupon(p.getName());
        // find the deleted item
        assertTrue(c1.searchCoupon(p1.getName()) == null);
    }

    @Test
    void testRemoveItemAll() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        c1.removeItemAll(p1.getName());
        // chekc the number of deleted items
        assertTrue(p1.getAvailableQuantity() == 4);
        // find the deleted item
        assertTrue(c1.getCountItems().get(p1.getName()) == null);
        // re-check the price
        assertTrue(c1.cartAmount() == 0.000);
    }

    @Test
    void testRemoveItemById() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        c1.removeItemById(1);
        // find the item by id
        Product p = c1.searchItem(p1.getName(),1);
        assertTrue(p == null);
        assertTrue(c1.cartAmount() == 48.000);
    }

    @Test
    void testRemoveItemByIdAndNoProductLeft() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        p1.setCoupon(cp1);
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p1);
        c1.applyCoupon(cp1.getProduct());
        c1.removeItemById(1);
        c1.removeItemById(2);
        Product p = c1.searchItem(p1.getName(),1);
        assertTrue(p == null);
        assertTrue(c1.cartAmount() == 0.000);
    }

    @Test
    void testSearchCoupon() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        p1.setCoupon(cp1);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        // find coupon in coupons list
        assertTrue(c1.searchCoupon(p1.getName()).equals(cp1));
    }

    @Test
    void testSearchItem() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        p1.setCoupon(cp1);
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        c1.addItem(p2);
        // test find an item in cart
        assertTrue(c1.getItemsList().containsValue(c1.searchItem(p1.getName(),1)));
        assertTrue(c1.searchItem(p1.getName(),1).equals(p1));
    }

    @Test
    void testToStringEmpty() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        assertTrue(c1.toString().equals("Cart C1: There are no products in this cart!"));
    }
    
    @Test
    void testToString() {
        PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.setWeight(10.000);
        Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        ProductsManager productManager = new ProductsManager();
        productManager.addProduct(p1);
        productManager.addProduct(p2);
        
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        c1.addItem(p1);
        assertTrue(c1.toString().equals("Cart C1: weight= 10.00, price= 49.00"));
    }
}
