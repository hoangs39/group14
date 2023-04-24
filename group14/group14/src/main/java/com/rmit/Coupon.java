package com.rmit;

public class Coupon {
    //Coupon's attributes
    private String product;
    private String type;
    private double value;

    //Constructors
    public Coupon(String product, String type, double value) {
        this.product = product;
        this.type = type;
        this.value = value;
    }

    // getters setters
    public String getProduct() {
        return this.product;
    }

    public String getType() {
        return this.type;
    }

    public double getValue() {
        return this.value;
    }
    
  // ovverride functions
  // check the equality of coupons based on thier product and the length of thier product.
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Coupon)) {
      return false;
    }
    Coupon otherCoupon = (Coupon) other;
    return this.product.equals(otherCoupon.getProduct()) && this.getType().equals(otherCoupon.getType()) && this.getValue() == otherCoupon.getValue();
  }

  @Override
  public int hashCode() {
    return this.product.length();
  }
}
