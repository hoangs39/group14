package com.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Group14
 */
public class CouponTest {
    @Test
    void testEquals() {
        Coupon c1 = new Coupon("car", "price", 12);
        Coupon c2 = new Coupon("car", "price", 12);
        // check the equality of 2 simillar coupons
        assertTrue(c1.equals(c2));
    }

    @Test
    void testNotEquals() {
        Coupon c1 = new Coupon("car", "price", 12);
        Coupon c2 = new Coupon("car", "percent", 2);
        // check the equality of 2 different coupons
        assertTrue(!c1.equals(c2));
    }


}
