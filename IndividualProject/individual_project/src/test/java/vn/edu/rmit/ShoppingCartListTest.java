package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;  
public class ShoppingCartListTest {

    @Test
    void testAddShoppingCart() {
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        PhysicalProduct pro3 = new PhysicalProduct("Orange", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro4 = new DigitalProduct("Lily Emoji", "A cute Lily emoji", 20, 25, "Good night");
        l.addProduct(pro1);
        l.addProduct(pro2);
        l.addProduct(pro3);
        l.addProduct(pro4);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        ShoppingCart sc1= new ShoppingCart(l, 1);
        sc1.addItem(pro3.getName());
        sc1.addItem(pro4.getName());
        ShoppingCartList scl1=new ShoppingCartList();
        scl1.addShoppingCart(sc);
        scl1.addShoppingCart(sc1);
        assertEquals(2, scl1.countShoppingCart());
    }
    @Test
    void testSearchForCartInCartList() {
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        PhysicalProduct pro3 = new PhysicalProduct("Orange", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro4 = new DigitalProduct("Lily Emoji", "A cute Lily emoji", 20, 25, "Good night");
        l.addProduct(pro1);
        l.addProduct(pro2);
        l.addProduct(pro3);
        l.addProduct(pro4);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        ShoppingCart sc1= new ShoppingCart(l, 1);
        sc1.addItem(pro3.getName());
        sc1.addItem(pro4.getName());
        ShoppingCartList scl1=new ShoppingCartList();
        scl1.addShoppingCart(sc);
        scl1.addShoppingCart(sc1);
        String result = String.join(",", scl1.searchForCartInCartList(1).getProductSet());
        String expectResult="Apple,Hastune Miku Emoji";
        assertTrue(expectResult.equals(result));
    }

    @Test
    void testSortBasedOnTotalWeights() {
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        PhysicalProduct pro3 = new PhysicalProduct("Orange", "An apple", 12, 25, "Hi", 13);
        DigitalProduct pro4 = new DigitalProduct("Lily Emoji", "A cute Lily emoji", 20, 25, "Good night");
        l.addProduct(pro1);
        l.addProduct(pro2);
        l.addProduct(pro3);
        l.addProduct(pro4);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        ShoppingCart sc1= new ShoppingCart(l, 1);
        sc1.addItem(pro3.getName());
        sc1.addItem(pro4.getName());
        ShoppingCartList scl1=new ShoppingCartList();
        scl1.addShoppingCart(sc);
        scl1.addShoppingCart(sc1);
        scl1.sortBasedOnTotalWeights();
        assertTrue(scl1.getShoppingCartList().get(0).equals(sc));
    }
}
