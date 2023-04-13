package com.rmit;

public class Coupon {
    private String product;
    private String type;
    private double value;

    public Coupon(String product, String type, double value) {
        this.product = product;
        this.type = type;
        this.value = value;
    }

    public String getProduct() {
        return this.product;
    }

    public String getType() {
        return this.type;
    }

    public double getValue() {
        return this.value;
    }
    
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Coupon)) {
      return false;
    }
    Coupon otherCoupon = (Coupon) other;
    return this.product.equals(otherCoupon.getProduct());
  }

  @Override
  public int hashCode() {
    return this.product.length();
  }
}
