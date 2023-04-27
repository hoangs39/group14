// package com.example;
// import org.junit.jupiter.api.*;
// import static org.junit.jupiter.api.Assertions.*;

// public class CartsManagerTest {
//     @Test
//     void testAddCart() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         c2.addItem(p1);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         assertTrue(!cm.addCart(c1));
//     }

//     @Test
//     void testBuy() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         c2.addItem(p1);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         cm.buy();
//         assertTrue(cm.countCarts() == 0);
//     }

//     @Test
//     void testChange() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         c2.addItem(p1);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         cm.buy();
//         assertTrue(cm.countCarts() == 0);
//     }

//     @Test
//     void testCountCarts() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         c2.addItem(p1);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         assertTrue(cm.countCarts() == 2);
//     }


//     @Test
//     void testGetCartById() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         c2.addItem(p1);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         assertTrue(cm.getCartById("C1").equals(c1));
//     }

//     @Test
//     void testRemoveItemInCart() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         cm.removeItemInCart(p1);
//         assertTrue(c1.searchItem(p1.getName()).getAvailableQuantity() == 1);
//     }

//     @Test
//     void testSpeedUpShipping() {
//         PhysicalProducts p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
//         PhysicalProducts p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
//         p1.setWeight(10.00);
//         p2.setWeight(5);
//         ProductsManager productManager = new ProductsManager();
//         productManager.addProduct(p1);
//         productManager.addProduct(p2);
//         ShoppingCart c1 = new ShoppingCart("C1",productManager);
//         c1.addItem(p1);
//         c1.addItem(p1);
//         ShoppingCart c2 = new ShoppingCart("C2",productManager);
//         c2.addItem(p2);
//         CartsManager cm = new CartsManager();
//         cm.addCart(c1);
//         cm.addCart(c2);
//         cm.speedUpShipping();
//         assertTrue(cm.getShoppingCarts().get(0).equals(c2));
//     }
// }
