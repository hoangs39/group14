package com.rmit;

import java.util.Map;
import java.util.Set;
// import java.util.Map.Entry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class ShoppingCart {
    // list of product name inside the cart
    private Map<Integer, String> itemsList;
    // list of coupon inside the cart
    private Set<Coupon> coupons;
    // applied coupon
    private Coupon appliedCoupon;
    // id of the cart
    private String cartId;
    // private ProductsManager store;
    private double totalWeight;
    private final double BASE_FEE = 0.1;
    private double totalPrice;
    private int itemId;


    public ShoppingCart() {
        LocalDateTime createId = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        this.cartId = "C" + createId.format(myFormatObj);
        // this.store = store;
        this.itemsList = new HashMap<>();
        this.coupons = new HashSet<>();
        totalWeight = 0.0;
        totalPrice = 0.0;
        itemId = 1;
    }

    /**
     * String representation
     */
    @Override
    public String toString() {
        if (getItemsList().isEmpty()) {
            return String.format("Cart %s: There are no products in this cart!", cartId);
        }
        return String.format("Cart %s: items= [%s], weight= %,.2f, price= %,.2f", 
        cartId, getItemsList().toString(), getTotalWeight(), cartAmount());
    }

    /**
     * search Product in the cart based on the name and id
     * @return Product object for further convinences
     */
    public Product searchItem(String p, int id) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == Integer.valueOf(id) && entry.getValue().equals(p)) {
                Product product = ProductsManager.getProductByName(p);
                return product;
            }
        }
        return null;
    }

    //remove all the products that have the same name in the items list and then re-calculate the price
    public void RemoveAllProductByName(String p) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getValue().equals(p)) {
                iterator.remove();
            }
        }
        cartAmount();
        return;
    }
    
    /**
     * add item to item list 
     * @param p    the product name used for adding
     * <p>
     * add product to items map
     * then add coupon to copouns list if product has a copoun 
     * and substract the quantity of product
     * </p>
     * @return boolean true if success / false if not work
     */
    public boolean addItem(String p) {
        Product product = ProductsManager.getProductByName(p);
        if (product.getAvailableQuantity() != 0) {
            itemsList.put(Integer.valueOf(itemId++),p);
            product.decreaseQuantity(1);
            cartAmount();

            if(product.getCoupon() != null){
                Coupon coupon = product.getCoupon();
                coupons.add(coupon);
            }
            return true;
        }
        return false;
    }

    // calculate the size of the map
    public int countItem() {
        return itemsList.size();
    }

    /**
     * remove item to item list 
     * @param p    the product name used for removing
     * <p>
     * remove product from items map
     * then add the quatity of the product
     * and remove coupon to copouns list 
     * if there are no left coressponding product in the list
     * </p>
     * @return boolean true if success / false if not work
     */
    public boolean removeItem(String p) {
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getValue().equals(p)) {
                iterator.remove();
                Product product = ProductsManager.getProductByName(p);
                if(product != null){
                    product.increaseQuantity(1);
                }

                if (!getItemsList().containsValue(p)){
                    if(getAppliedCoupon().equals(p)){
                        setAppliedCoupon(null);
                    }
                    removeCoupon(p);
                }
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
     * @param p    the product name used for searching
     * @param message the new message of the Product item
     * @return boolean if found / null if not found
     */
    public boolean addMessage(String p,int id, String message) {
        Product product = searchItem(p, id);
        if (product != null) {
            product.setMessage(message);
            return true;
        }
        return false;
    }

    /**
     * Search For item in the item list and then get/set message for the Product
     * item
     * @param p    the product name used for searching
     * @return String if found / null if not found
     */
    public String getMessage(String p) {
        Product product = ProductsManager.getProductByName(p);
        if (product != null && product.getMessage()!= null) {
            return product.getMessage();
        }
        return "";
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
     * @return total price of the cart
     */
    public double cartAmount() {
        resetAll();
        Iterator<Map.Entry<Integer, String>> iterator = itemsList.entrySet().iterator();
        double productPrice;
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Product product = ProductsManager.getProductByName(entry.getValue());
            productPrice = product.getPrice() + product.getTaxAmount();
            // calculate the weight
            if (product instanceof PhysicalProducts) {
                PhysicalProducts phy = (PhysicalProducts) product;
                totalWeight += phy.getWeight();
            }
            // if there any apply coupon
            if(this.appliedCoupon != null){
                if (this.appliedCoupon.getType().equals("percent")) {
                    productPrice *= (1 - (appliedCoupon.getValue()/100));
                } else if (this.appliedCoupon.getType().equals("price")) {
                    productPrice -= this.appliedCoupon.getValue();
                }
            }

            totalPrice += productPrice;
        }
        totalPrice += (totalWeight * BASE_FEE);

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
     * @return applied copoun
     */
    public Coupon applyCoupon(String p) {
        if (getItemsList().containsValue(p)){
            if(getAppliedCoupon().equals(p)){
                this.appliedCoupon = searchCoupon(p);
            }
        }
        cartAmount();
        return this.appliedCoupon;
    }

    /**
     * search copoun based on the name
     * @return searched copoun
     */
    public Coupon searchCoupon(String p) {
        for (Coupon coupon : coupons) {
            if (coupon.getProduct().equals(p) && getItemsList().containsValue(p)) {
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
     * @return boolean
     */
    private boolean removeCoupon(String p){
        Coupon coupon = searchCoupon(p);
        if (coupon != null) {
            getCoupons().remove(coupon);
            cartAmount();
            return true;
        }
        return false;
    }

    // private void displayAllCoupons() {
    //     for (Coupon coupon : coupons) {
    //         System.out.println(coupon.getType() + ":" + coupon.getValue() + ":" + coupon.getProduct());
    //     }
    // }


    // getters, setters
    public Map<Integer, String> getItemsList() {
        return itemsList;
    }

    public void setItemsList(Map<Integer, String> itemsList) {
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
