package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class DigitalProductTest {
    @Test
    void testGetDisplayInfo() {
        DigitalProduct p2 = new DigitalProduct("r", 40.0, "new", 4, "luxuryTax");
        assertTrue(p2.getDisplayInfo().equals("r  [type= DIGITAL, description= new, quantity= 4, price= 40.00, message= notSupport!, taxType= luxuryTax, tax= 8.00]"));
    }

    @Test
    void testGetProductType() {
        DigitalProduct p2 = new DigitalProduct("r", 40.0, "new", 4, "luxuryTax");
        assertTrue(p2.getProductType().equals("DIGITAL"));
    }

    @Test
    void testToString() {
        DigitalProduct p2 = new DigitalProduct("r", 40.0, "new", 4, "luxuryTax");
        assertTrue(p2.toString().equals("DIGITAL - r"));
    }
}
