package com.rmit;

public class Coupon {
    private String name;
    private String type;
    private double value;

    public Coupon(String name, String type, double value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return this.name;
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
    return this.name.equals(otherCoupon.getName());
  }

  @Override
  public int hashCode() {
    return this.name.length();
  }
}
