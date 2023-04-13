package com.rmit;
/**
 * @author <Dang Tran Huy Hoang - s3927241>
 */

 /**
 * A general Class that manage all common attributes and methods of a product.
 */
public abstract class Product implements Gift<Product>,Tax{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String message;
    private String taxType;
    private double taxAmount;
    private Coupon coupon;

    final double TAXFREE = 0;
    final double TAXNORMAL = 0.1;
    final double TAXLUXURY = 0.2;

    public Product(String name, double price,String description, int quantity,String taxType){
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.taxType = taxType;
    }

    @Override
    public double calculateTax(String taxType) {
        double taxRate;
        switch (this.taxType) {
            case "tax-free":
                taxRate = TAXFREE;
                break;
            case "normal tax":
                taxRate = TAXNORMAL;
                break;
            case "luxury tax":
                taxRate = TAXLUXURY;
                break;
            default:
                throw new IllegalArgumentException("Invalid tax type: " + this.taxType);
        }
        this.taxAmount = this.price * taxRate;
        return this.taxAmount;
    }

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object other) {
      if (other == this) {
        return true;
      }
      if (!(other instanceof Product)) {
        return false;
      }
      Product otherProduct = (Product) other;
      return this.name.equals(otherProduct.getName()) && this.getMessage().equals(otherProduct.getMessage()) ;
    }
  
    @Override
    public int hashCode() {
      return this.name.length();
    }

    public abstract String view();
    public abstract String displayAll();


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getTaxAmount() {
        return taxAmount;
    }
}
