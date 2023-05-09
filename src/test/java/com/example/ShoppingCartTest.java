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
    //check qunatity of the item in the cart after having added 3 products with 2 items are simillar.
    assertTrue(c1.countItem() == 3);
    // check equality of the searched product and the input product
    // they must be the same because they have the same name and the equal function is modified 
    // to accepts that 2 products having the same name are equal.
    assertTrue(c1.searchItem(p1.getName(),1).equals(p1));
    // check the quantiy of products inside cart after having added products to cart
    assertTrue(c1.getCountItems().get(p1.getName()) == 2);
    //check the quantity of the item is product manager after having added products to cart
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
        // check the msg received when the gift feature of the added product is not enabled
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
        // check what would happen when enabling gift feature but not adding any msg to the product
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
        //check output msg after having updated msg and enabled gift feature.
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
        // check price after haivng applied coupon
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
        //check price of cart after having applied 2 coupons
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
        //check the accuracy of the price calculation algorithm
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
        //check the accuracy of the price after having changed price data of a product in the product manager's collection
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
        // test count size of the cart after having added 2 products
        assertTrue(c1.countItem() == 2);
    }



    @Test
    void testGetCartId() {
        ProductsManager productManager = new ProductsManager();
        ShoppingCart c1 = new ShoppingCart("C1",productManager);
        // check the accuracy of getter and setter function
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
         // check the accuracy of getter and setter function
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
        // check whether the coupon have been deleted completedly
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
        // check the existence of the deleted product in collection
        // to examine the accuracy of the alogrithm and show the difference between this and the removebyid function that only 
        // delete a single item
        assertTrue(p1.getAvailableQuantity() == 4);
        // check whether the same name items have been deleted completedly
        assertTrue(c1.getCountItems().get(p1.getName()) == null);
        // re-check the price of the cart
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
        //check whether item exists in the collection after having deleted it with the remove by id fucntion
        Product p = c1.searchItem(p1.getName(),1);
        assertTrue(p == null);
        assertTrue(c1.cartAmount() == 48.000);
    }

    @Test
    void testRemoveItemByIdAndNoLeftProduct() {
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
        //check what would happen if we use the item's id many times to remove all items in the collection.
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
         // check the equality of copuon inside collection and the added copuon
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
        // check whether the cart contains the product after having added it to the items collection
        assertTrue(c1.getItemsList().containsValue(c1.searchItem(p1.getName(),1)));
        // check the equality of an item inside collection and the added product
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
        //check the format of toString function if there is nothing in the carts collection
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
        //check the format of toString function
        assertTrue(c1.toString().equals("Cart C1: weight= 10.00, price= 49.00"));
    }
}
