package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class ProductTest {
    @Test
    void testCalculateTax() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        // check accuracy of tax calculation function
        assertTrue(p1.calculateTax(p1.getTaxType()) == 8.00);
    }

    @Test
    void testDecreaseQuantity1() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.decreaseQuantity(1);
        // check the qunatity of the product after having used this function
        assertTrue(p1.getAvailableQuantity() == 3);
    }

    @Test
    void testEquals() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Product p2 = new PhysicalProducts("car", 4, "n", 4, "normalTax");
        // test equality of 2 simillar products
        assertTrue(p1.equals(p2));
    }



    @Test
    void testGetCoupon() {
        Product p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        p2.setCoupon(cp2);
        // test the accuracy of getter setter function
        // check the output coupon of a getter and the real coupon
        assertTrue(p2.getCoupon().equals(cp2));
    }

    @Test
    void testGetCreateGift() {
        Product p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        // test the accuracy of getter setter function
        p2.setCreateGift(true);
        assertTrue(p2.getCreateGift());
    }


    @Test
    void testIncreaseQuantity() {
        Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p1.increaseQuantity(1);
         // check the qunatity of the product after having used this function
        assertTrue(p1.getAvailableQuantity() == 5);
    }

    @Test
    void testUnableToSetMessage() {
        Product p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p2.setCreateGift(false);
        p2.setMessage("Hi");
        // test the ability of adding message if we didnt enable the gift functionality to this product initially
        assertTrue(p2.getMessage().equals("notSupport!"));
    }

    @Test
    void testSetMessage() {
        Product p2 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        p2.setCreateGift(true);
        p2.setMessage("Hi");
        // test the ability of adding message if we did enable the gift functionality to this product initially
        assertTrue(p2.getMessage().equals("Hi"));
    }
}
