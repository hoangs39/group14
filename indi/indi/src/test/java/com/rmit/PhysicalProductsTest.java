package com.rmit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

public class PhysicalProductsTest {
    @Test
    public void testDisplayAllAttributes() {
        Product p2 = new PhysicalProducts("Car", 3000, "Powerful", 3);
        PhysicalProducts p22 = (PhysicalProducts) p2;
        p22.setWeight(10.0);
        assertTrue(p2.displayAll().equals("Car|3000.0|Powerful|3|10.00000"));  
    }

    @Test
    public void testView() {
        Product p2 = new PhysicalProducts("Car", 3000, "Powerful", 3);
        PhysicalProducts p22 = (PhysicalProducts) p2;
        p22.setWeight(10.0);
        assertTrue(p2.view().equals("PHYSICAL - <Car>"));
    }

    @Test
    public void testSetWeight() {
        Product p2 = new PhysicalProducts("Car", 3000, "Powerful", 3);
        PhysicalProducts p22 = (PhysicalProducts) p2;
        p22.setWeight(10.0);
        assertEquals(10.0, p22.getWeight());
    }



    @Test
    public void testAddMessage() {
        Product p2 = new PhysicalProducts("Car", 3000, "Powerful", 3);
        PhysicalProducts p22 = (PhysicalProducts) p2;
        p22.setWeight(10.0);
        p2.setMessage("Hello");
        assertTrue(p2.getMessage().equals("Hello"));
    }
}
