package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DigitalProductTest {
    @Test
    public void testDisplayAllAttributes() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        assertTrue(p1.displayAll().equals("TV|200.0|brandnew|2"));  
    }

    @Test
    public void testView() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        assertTrue(p1.view().equals("DIGITAL - <TV>"));
    }
    @Test
    public void testAddMessage() {
        Product p1 = new DigitalProduct("TV",200,"brandnew",2);
        p1.setMessage("Hello");
        assertTrue(p1.getMessage().equals("Hello"));
    }
}
