// package com.rmit;

// import java.util.Map;
// import java.util.Set;
// // import java.util.Map.Entry;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Iterator;

// public class ShoppingCart {
//     // list of product name inside the cart
//     private Map<Product, Integer> itemsList;
//     // list of coupon inside the cart
//     private Set<Coupon> coupons;
//     // applied coupon
//     private Coupon appliedCoupon;
//     // id of the cart
//     private String cartId;
//     private double totalWeight;
//     private final double BASE_FEE = 0.1;
//     private double totalPrice;

//     public ShoppingCart() {
//         LocalDateTime createId = LocalDateTime.now();
//         DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
//         this.cartId = "C" + createId.format(myFormatObj);
//         // this.store = store;
//         this.itemsList = new HashMap<>();
//         this.coupons = new HashSet<>();
//         totalWeight = 0.0;
//         totalPrice = 0.0;
//         System.out.println();
//     }

//     /**
//      * String representation
//      */
//     @Override
//     public String toString() {
//         if (getItemsList().isEmpty()) {
//             return String.format("Cart %s: There are no products in this cart!", cartId);
//         }
//         return String.format("Cart %s: weight= %,.2f, price= %,.2f",
//                 cartId, getTotalWeight(), cartAmount());
//     }

//     public String printItemsList() {
//         String products = "";
//         StringBuilder sb = new StringBuilder();
//         Iterator<Map.Entry<Product, Integer>> iterator = itemsList.entrySet().iterator();
//         while (iterator.hasNext()) {
//             Map.Entry<Product, Integer> entry = iterator.next();
//             Product product = entry.getKey();
//             int amount = entry.getValue();
//             String productInfo = String.format("%s-$.5f-%d-%.5f", product.getName(), product.getPrice(), amount,
//                     product.getTaxAmount());
//             products = sb.append(productInfo).append(",").toString();
//         }
//         return products;
//     }

//     /**
//      * search Product in the cart based on the name
//      * 
//      * @return Product object for further convinences
//      */
//     public Product searchItem(String p) {
//         Iterator<Map.Entry<Product, Integer>> iterator = itemsList.entrySet().iterator();
//         while (iterator.hasNext()) {
//             Map.Entry<Product, Integer> entry = iterator.next();
//             Product product = entry.getKey();
//             if (product.getName().equals(p)) {
//                 return product;
//             }
//         }
//         return null;
//     }

//     /**
//      * add item to item list
//      * 
//      * @param p the product used for adding
//      *          <p>
//      *          add product to items map
//      *          then add coupon to copouns list if product has a copoun
//      *          and substract the quantity of product
//      *          </p>
//      * @return boolean true if success / false if not work
//      */
//     public boolean addItem(Product p) {
//         // check if items in the store
//         Product item = ProductsManager.getProductByName(p.getName());
//         if (item.getAvailableQuantity() != 0 && item != null) {

//             if (itemsList.containsKey(item)) {
//                 itemsList.put(item, itemsList.get(item) + 1);
//             } else {
//                 itemsList.put(item, 1);
//                 Coupon coupon = item.getCoupon();
//                 if (coupon != null) {
//                     coupons.add(coupon);
//                 }
//             }
//             p.decreaseQuantity(1);
//             cartAmount();
//             return true;
//         }
//         return false;
//     }

//     // calculate the size of the map
//     public int countItem() {
//         return itemsList.size();
//     }

//     /**
//      * remove item to item list
//      * 
//      * @param p the product used for removing
//      *          <p>
//      *          remove product from items map
//      *          then add the quatity of the product
//      *          and remove coupon to copouns list
//      *          if there are no left coressponding product in the list
//      *          </p>
//      * @return boolean true if success / false if not work
//      */
//     public boolean removeItem(Product p) {
//         Iterator<Map.Entry<Product, Integer>> iterator = itemsList.entrySet().iterator();
//         while (iterator.hasNext()) {
//             Map.Entry<Product, Integer> entry = iterator.next();
//             Product product = entry.getKey();
//             if (product.equals(p) && product != null) {
//                 iterator.remove();
//                 product.increaseQuantity(1);
//                 if (getAppliedCoupon().equals(p)) {
//                     setAppliedCoupon(null);
//                 }
//                 removeCoupon(p);
//                 cartAmount();
//                 return true;
//             }
//         }
//         return false;
//     }

//     /**
//      * Search For item in the item list and then get/set message for the Product
//      * item
//      * 
//      * @param p       the product name used for searching
//      * @param message the new message of the Product item
//      * @return boolean if found / null if not found
//      */
//     public boolean addMessage(String p, String message) {
//         Product product = searchItem(p);
//         if (product != null) {
//             product.setMessage(message);
//             return true;
//         }
//         return false;
//     }

//     /**
//      * Search For item in the item list and then get/set message for the Product
//      * item
//      * 
//      * @param p the product name used for searching
//      * @return String if found / null if not found
//      */
//     public String getMessage(String p) {
//         Product product = searchItem(p);
//         if (product != null && product.getMessage() != null) {
//             return product.getMessage();
//         }
//         return "";
//     }

//     /**
//      * calculate the cart price according to price and shipping fee
//      * <p>
//      * cart price = product price + shipping fee
//      * shipping fee = total weight of all physical products * base fee
//      * base fee = 0.1
//      * if there is applied coupon, calculate the price based on its type:
//      * price = price * (1 - percent);
//      * or
//      * price = price - discount's value
//      * </p>
//      * 
//      * @return total price of the cart
//      */
//     public double cartAmount() {
//         resetAll();
//         Iterator<Map.Entry<Product, Integer>> iterator = itemsList.entrySet().iterator();
//         while (iterator.hasNext()) {
//             double productPrice = 0;
//             Map.Entry<Product, Integer> entry = iterator.next();
//             Product product = entry.getKey();
//             int amount = entry.getValue();
//             productPrice = (product.getPrice() + product.getTaxAmount()) * amount;
//             // calculate the weight
//             if (product instanceof PhysicalProducts) {
//                 PhysicalProducts phy = (PhysicalProducts) product;
//                 totalWeight += (phy.getWeight() * amount);
//             }
//             // if there any apply coupon then apply it to corresponding products
//             if (this.appliedCoupon != null && this.appliedCoupon.getProduct().equals(product.getName())) {
//                 if (this.appliedCoupon.getType().equals("percent")) {
//                     productPrice *= (1 - (appliedCoupon.getValue() / 100));
//                 } else if (this.appliedCoupon.getType().equals("price")) {
//                     productPrice -= this.appliedCoupon.getValue();
//                 }
//             }
//             totalPrice += productPrice;
//         }
//         totalPrice += (totalWeight * BASE_FEE);
//         return totalPrice;
//     }

//     // reset the price and weight of cart
//     private void resetAll() {
//         totalPrice = 0;
//         totalWeight = 0;
//     }

//     /**
//      * assign the Coupon based on the name
//      * <p>
//      * if there is any the coupon that has the product name in the coupons list
//      * then assign it to appiled coupon and re-calculate the price
//      * </p>
//      * 
//      * @return applied copoun
//      */
//     public Coupon applyCoupon(Product p) {
//         if (getItemsList().containsKey(p)) {
//             if (getAppliedCoupon().getProduct().equals(p.getName())) {
//                 this.appliedCoupon = searchCoupon(p.getName());
//             }
//         }
//         cartAmount();
//         return this.appliedCoupon;
//     }

//     /**
//      * search copoun based on the name
//      * 
//      * @return searched copoun
//      */
//     public Coupon searchCoupon(String p) {
//         for (Coupon coupon : coupons) {
//             if (coupon.getProduct().equals(p)) {
//                 return coupon;
//             }
//         }
//         return null;
//     }

//     /**
//      * remove the Coupon based on the name
//      * <p>
//      * if there is any the coupon that has the product name in the coupons list
//      * then remove it and re-calculate the price
//      * </p>
//      * 
//      * @return boolean
//      */
//     private boolean removeCoupon(Product p) {
//         Coupon coupon = searchCoupon(p.getName());
//         if (coupon != null) {
//             getCoupons().remove(coupon);
//             cartAmount();
//             return true;
//         }
//         return false;
//     }

//     // private void displayAllCoupons() {
//     // for (Coupon coupon : coupons) {
//     // System.out.println(coupon.getType() + ":" + coupon.getValue() + ":" +
//     // coupon.getProduct());
//     // }
//     // }

//     // getters, setters
//     public Map<Product, Integer> getItemsList() {
//         return itemsList;
//     }

//     public void setItemsList(Map<Product, Integer> itemsList) {
//         this.itemsList = itemsList;
//     }

//     public Set<Coupon> getCoupons() {
//         return coupons;
//     }

//     public void setCoupons(Set<Coupon> coupons) {
//         this.coupons = coupons;
//     }

//     public Coupon getAppliedCoupon() {
//         return appliedCoupon;
//     }

//     public void setAppliedCoupon(Coupon appliedCoupon) {
//         this.appliedCoupon = appliedCoupon;
//     }

//     public String getCartId() {
//         return cartId;
//     }

//     public void setCartId(String cartId) {
//         this.cartId = cartId;
//     }

//     public double getTotalWeight() {
//         return totalWeight;
//     }

//     public double getTotalPrice() {
//         return totalPrice;
//     }

// }
