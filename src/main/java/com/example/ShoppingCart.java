package com.example;

import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;

public class ShoppingCart {
    // list of product name inside the cart
    private Set<Product> itemsList;
    // list of coupon inside the cart
    private Set<Coupon> coupons;
    // applied coupon
    private Coupon appliedCoupon;
    private ProductsManager store;
    // id of the cart
    private String cartId;
    private double totalWeight;
    private final double BASE_FEE = 0.1;
    private double totalPrice;

    // constructor
    public ShoppingCart(String id, ProductsManager store) {
        this.cartId = id;
        this.itemsList = new HashSet<>();
        this.coupons = new HashSet<>();
        totalWeight = 0.0;
        totalPrice = 0.0;
        this.store = store;
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        if (getItemsList().isEmpty()) {
            return String.format("Cart %s: There are no products in this cart!", cartId);
        }
        return String.format("Cart %s: weight= %,.2f, price= %,.2f",
                cartId, this.getTotalWeight(), cartAmount());
    }

    public String printItemsList() {
        String productsInfo = "";
        StringBuilder sb = new StringBuilder();
        for (Product product : itemsList) {
            String productInfo = String.format("%s-%.5f-%d-%.5f", product.getName(), product.getPrice(),
                    product.getAvailableQuantity(),
                    product.getTaxAmount());
            productsInfo = sb.append(productInfo).append(",").toString();
        }
        return productsInfo;
    }

    /**
     * search Product in the cart based on the name
     * 
     * @return Product object for further convinences
     */
    public Product searchItem(String p) {
        for (Product product : itemsList) {
            if (product.getName().equals(p)) {
                return product;
            }
        }
        return null;
    }

    /**
     * add item to item list
     * 
     * @param p the product used for adding
     *          <p>
     *          add product to items map
     *          then add coupon to copouns list if product has a copoun
     *          and substract the quantity of product
     *          </p>
     * @return boolean true if success / false if not work
     */
    public boolean addItem(Product p) {
        // check if items in the store
        Product item = store.getProductByName(p.getName());
        // System.out.println(item);
        if (item.getAvailableQuantity() != 0) {
            if (!itemsList.contains(item)) {
                Product product = null;
                if (item instanceof PhysicalProducts) {

                    if (!itemsList.contains(item)) {
                        product = new PhysicalProducts(item.getName(), item.getPrice(), item.getDescription(), 1,
                                item.getTaxType());

                        PhysicalProducts phy = (PhysicalProducts) product;
                        PhysicalProducts phyItem = (PhysicalProducts) item;

                        phy.setWeight(phyItem.getWeight());
                        product.setCreateGift(item.getCreateGift());
                        Coupon coupon = item.getCoupon();
                        if (coupon != null) {
                            coupons.add(coupon);
                        }
                        itemsList.add(product);
                        p.decreaseQuantity(1);
                        cartAmount();
                        return true;
                    }
                } else {
                    product = new DigitalProduct(item.getName(), item.getPrice(), item.getDescription(), 1,
                            item.getTaxType());
                    product.setCreateGift(item.getCreateGift());
                    itemsList.add(product);
                    p.decreaseQuantity(1);
                    Coupon coupon = item.getCoupon();
                    if (coupon != null) {
                        coupons.add(coupon);
                    }
                    cartAmount();
                    return true;
                }
            } else {
                Product prt = searchItem(item.getName());
                prt.setAvailableQuantity(prt.getAvailableQuantity() + 1);
                Coupon coupon = item.getCoupon();
                if (coupon != null) {
                    coupons.add(coupon);
                }
                p.decreaseQuantity(1);
                cartAmount();
                return true;
            }
        }
        return false;
    }

    // calculate the size of the map
    public int countItem() {
        return itemsList.size();
    }

    /**
     * remove item to item list
     * 
     * @param p the product used for removing
     *          <p>
     *          remove product from items set
     *          then add the quatity of the product
     *          and remove coupon to copouns list
     *          if there are no left coressponding product in the list
     *          </p>
     * @return boolean true if success / false if not work
     */
    public boolean removeItem(String p) {
        for (Product product : itemsList) {
            if (product.getName().equals(p)) {
                Product pr = store.getProductByName(p);
                pr.increaseQuantity(1);
                product.decreaseQuantity(1);
                if (product.getAvailableQuantity() == 0) {
                    removeCoupon(product);
                    itemsList.remove(product);
                }
                cartAmount();
                return true;
            }
        }
        return false;
    }

    // remove all items, whics have that name
    public boolean removeItemAll(String p) {
        for (Product product : itemsList) {
            if (product.getName().equals(p)) {
                Product pr = store.getProductByName(p);
                pr.increaseQuantity(product.getAvailableQuantity());
                itemsList.remove(product);
                removeCoupon(pr);
                cartAmount();
                return true;
            }
        }
        return false;
    }

    // change all items, whics have that name
    public boolean changeItemAll(Product p) {
        for (Product product : itemsList) {
            if (product.getName().equals(p.getName())) {
                product.setPrice(p.getPrice());
                product.setDescription(p.getDescription());
                product.setTaxType(p.getTaxType());
                product.calculateTax(p.getTaxType());
                cartAmount();
                return true;
            }
        }
        return false;
    }

    /**
     * Search For item in the item list and then get/set message for the Product
     * item
     * 
     * @param p       the product name used for searching
     * @param message the new message of the Product item
     * @return boolean if found / null if not found
     */
    public boolean addMessage(String p, String message) {
        Product product = searchItem(p);
        if (product != null && product.getCreateGift()) {
            product.setMessage(message);
            return true;
        }
        return false;
    }

    /**
     * Search For item in the item list and then get/set message for the Product
     * item
     * 
     * @param p the product name used for searching
     * @return String if found / null if not found
     */
    public String getMessage(String p) {
        Product product = searchItem(p);
        if (product != null && product.getCreateGift() && product.getMessage() != null) {
            return product.getMessage();
        }
        return "Empty!";
    }

    /**
     * calculate the cart price according to price and shipping fee
     * <p>
     * cart price = product price + shipping fee
     * shipping fee = total weight of all physical products * base fee
     * base fee = 0.1
     * if there is applied coupon, calculate the price based on its type:
     * price = price * (1 - percent);
     * or
     * price = price - discount's value
     * </p>
     * 
     * @return total price of the cart
     */
    public double cartAmount() {
        resetAll();
        double productPrice = 0;
        double productWeight = 0;
        for (Product product : itemsList) {
            int amount = product.getAvailableQuantity();
            productPrice = (product.getPrice() + product.getTaxAmount()) * amount;
            // calculate the weight
            if (product instanceof PhysicalProducts) {
                PhysicalProducts phy = (PhysicalProducts) product;
                productWeight += (phy.getWeight() * amount);
                totalWeight += productWeight;
            }
            productPrice += (productWeight * BASE_FEE);
            // if there any apply coupon then apply it to corresponding products
            if (this.appliedCoupon != null && this.appliedCoupon.getProduct().equals(product.getName())) {
                if (this.appliedCoupon.getType().equals("percent")) {
                    productPrice *= (1 - (appliedCoupon.getValue() / 100));
                } else if (this.appliedCoupon.getType().equals("price")) {
                    productPrice -= this.appliedCoupon.getValue() * amount;
                }
            }
            totalPrice += productPrice;
            productWeight = 0;
        }
        return totalPrice;
    }

    // reset the price and weight of cart
    private void resetAll() {
        totalPrice = 0;
        totalWeight = 0;
    }

    /**
     * assign the Coupon based on the name
     * <p>
     * if there is any the coupon that has the product name in the coupons list
     * then assign it to appiled coupon and re-calculate the price
     * </p>
     * 
     * @return applied copoun
     */
    public Coupon applyCoupon(String p) {
        Product product = searchItem(p);
        if (product != null) {
            this.appliedCoupon = searchCoupon(p);
        }
        cartAmount();
        return this.appliedCoupon;
    }

    /**
     * search copoun based on the name
     * 
     * @return searched copoun
     */
    public Coupon searchCoupon(String p) {
        for (Coupon coupon : coupons) {
            if (coupon.getProduct().equals(p)) {
                return coupon;
            }
        }
        return null;
    }

    /**
     * remove the Coupon based on the name
     * <p>
     * if there is any the coupon that has the product name in the coupons list
     * then remove it and re-calculate the price
     * </p>
     * 
     * @return boolean
     */
    public boolean removeCoupon(Product p) {
        Coupon coupon = searchCoupon(p.getName());
        if (coupon != null) {
            getCoupons().remove(coupon);
            cartAmount();
            return true;
        }
        return false;
    }

    public void displayAllCoupons() {
        for (Coupon coupon : coupons) {
            System.out.println(coupon.getType() + ":" + coupon.getValue() + ":" +
                    coupon.getProduct());
        }
    }

    // getters, setters
    public Set<Product> getItemsList() {
        return itemsList;
    }

    public void setItemsList(Set<Product> itemsList) {
        this.itemsList = itemsList;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Coupon getAppliedCoupon() {
        return appliedCoupon;
    }

    public void setAppliedCoupon(Coupon appliedCoupon) {
        this.appliedCoupon = appliedCoupon;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
