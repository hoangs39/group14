package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;  

public class ProductListTest {
    @Test
    public void emptyProductList() {
        ProductList l=new ProductList();
        assertEquals(0, l.countProduct());
    }
    @Test
    public void twoProductList(){
        ProductList l=new ProductList();
        l.addProduct(new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12));
        l.addProduct(new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning"));
        assertEquals(2, l.countProduct());
    }
    @Test
    public void sameProductNameStillCount(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Apple", "A cute apple", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        assertEquals(2, l.countProduct());
    }
    @Test
    public void searchForProductTest(){
        ProductList l=new ProductList();
        PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
        DigitalProduct pro2 = new DigitalProduct("Apple", "A cute apple", 20, 25, "Good morning");
        l.addProduct(pro1);
        l.addProduct(pro2);
        Product searchResult=l.searchForProduct("Apple");
        assertTrue(searchResult.equals(pro1));
    }
}
