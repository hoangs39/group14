package com.rmit;

public abstract class Product implements Gift<Product>,Tax{
    /**
     * Product attributes
     */
    private String name;
    private String description;
    private int availableQuantity;
    private double price;
    private String message = "noMsg";
    private String taxType;
    private double taxAmount;
    private Coupon coupon;
    //decide to create a gift
    private boolean createGift = false;


    private final double TAXFREE = 0;
    private final double TAXNORMAL = 0.1;
    private final double TAXLUXURY = 0.2;

    // constructors
    /**
     * @param name 
     * @param description
     * @param availableQuantity non-negative
     * @param price non-negative 
     */
    public Product(String name, double price,String description, int availableQuantity,String taxType){
        this.name = name;
        this.price = price;
        this.description = description;
        this.availableQuantity = availableQuantity;
        this.taxType = taxType;
        calculateTax(this.taxType);
    }

    // override functions
    /**
     * @param taxType String represent the tax type of a product
     * @return taxAmount: the calucated amount of tax of this product
     * calculate the tax amount based on the product's tax type
     */
    @Override
    public double calculateTax(String taxType) {
        double taxRate;
        switch (this.taxType) {
            case "freeTax":
                taxRate = TAXFREE;
                break;
            case "normalTax":
                taxRate = TAXNORMAL;
                break;
            case "luxuryTax":
                taxRate = TAXLUXURY;
                break;
            default:
                throw new IllegalArgumentException("Invalid tax type: " + this.taxType);
        }
        this.taxAmount = this.price * taxRate;
        return this.taxAmount;
    }

    // getters and setter for message for gift 
    @Override
    public String getMessage() {
        if(createGift){
            return this.message;
        }else{
            return "notSupport!";
        }
    
    }

    @Override
    public void setMessage(String message) {
        if(createGift){
            this.message = message;
        }else{
            this.message = "noSupport";
        }
    }
    // check the equality of products based on thier name and the length of thier name.
    @Override
    public boolean equals(Object other) {
      if (other == this) {
        return true;
      }
      if (!(other instanceof Product)) {
        return false;
      }
      Product otherProduct = (Product) other;
      return this.name.equals(otherProduct.getName()) ;
    }
  
    @Override
    public int hashCode() {
      return this.name.length();
    }

    // abstract functions
    /**
     * String representation
     */
    @Override
    public abstract String toString();
    /**
     * get the product type (PHYSICAL / DIGITAL)
     */
    public abstract String getProductType();
    /**
     * get the formatted information String of a product
     */
    public abstract String getDisplayInfo();


    // functions
    /**
     * increase product quantity by an amount 
     * <p>
     * Given an amount integer -> increase the product available quanity by that amount
     * </p>
     * @param amount amount to increase
     */
    public void increaseQuantity(int amount) {
        availableQuantity += amount;
    }

    /**
     * decrease product quantity by an amount 
     * <p>
     * Given an amount integer -> decrease the product available quanity by that amount
     * </p>
     * @param amount amount to decrease
     */
    public void decreaseQuantity(int amount) {
        availableQuantity -= amount;
    }

    //getters, setters
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
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
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
    public boolean getCreateGift() {
        return createGift;
    }

    public void setCreateGift(boolean createGift) {
        this.createGift = createGift;
    }


}
