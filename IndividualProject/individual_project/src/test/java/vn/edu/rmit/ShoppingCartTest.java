package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;  

public class ShoppingCartTest {
    @Test
    public void testAddItemsToCart(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        assertEquals(2, sc.countShoppingCartSet());
        assertEquals(2, sc.countShoppingList());
    }
    @Test 
    public void testRemoveItem(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        sc.removeItem(pro1.getName());
        assertEquals(1, sc.countShoppingCartSet());
        assertEquals(1, sc.countShoppingList());
    }
    @Test
    public void testCartAmount(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        assertEquals(sc.cartAmount(), 51.2);
    }
    @Test
    public void testSearchForProductInCart(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        Product searchResult=sc.searchForProductInCart("Apple");
        assertTrue(searchResult.equals(pro1));
    }
    @Test
    public void testShoppingCartOutput(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Hastune Miku Emoji", "A cute emoji", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        ShoppingCart sc= new ShoppingCart(l, 1);
        sc.addItem(pro1.getName());
        sc.addItem(pro2.getName());
        String result = String.join(",", sc.getProductSet());
        String expectResult="Apple,Hastune Miku Emoji";
        assertTrue(expectResult.equals(result));
    }
}
