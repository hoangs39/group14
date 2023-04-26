package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhysicalProductsTest {
    @Test
    void testGetDisplayInfo() {
        PhysicalProducts p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p2.setWeight(10.00);
        assertTrue(p2.getDisplayInfo().equals("car  [type= PHYSICAL, description= new, quantity= 4, price= 40.00, weight= 10.00, message= notSupport!, taxType= luxuryTax, tax= 8.00]"));
    }

    @Test
    void testGetProductType() {
        PhysicalProducts p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        assertTrue(p2.getProductType().equals("PHYSICAL"));
    }

    @Test
    void testGetWeight() {
        PhysicalProducts p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p2.setWeight(10.00);
        assertTrue(p2.getWeight() == 10.000);
    }


    @Test
    void testToString() {
        PhysicalProducts p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p2.setWeight(10.00);
        assertTrue(p2.toString().equals("PHYSICAL - car"));
    }
}
