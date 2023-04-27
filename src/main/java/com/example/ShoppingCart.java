package com.example;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    // list of product name inside the cart
    private Map<Integer, Product> itemsList;
    private Map<String, Integer> countItems;
    private ProductsManager store;
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

    public ShoppingCart(String id, ProductsManager store) {
        this.cartId = id;
        this.store = store;
        this.itemsList = new HashMap<>();
        this.coupons = new HashSet<>();
        this.countItems = new HashMap<>();
        totalWeight = 0.0;
        totalPrice = 0.0;
        itemId = 1;
    }

    /**
     * String representation
     */
    @Override
    public String toString() {

        if (!getItemsList().isEmpty()) {
            double amount = cartAmount();
            return String.format("Cart %s: weight= %,.2f, price= %,.2f",
                    cartId, getTotalWeight(), amount);

        }
        return String.format("Cart %s: There are no products in this cart!", cartId);
    }

    public void printItemsList() {
        Set<Map.Entry<Integer,Product>> entries = itemsList.entrySet();
        Stream<Map.Entry<Integer,Product>> entriesStream = entries.stream();
        entriesStream
        .forEach((m) -> {
            System.out.printf("id: %d Name: %s Price: %.2f Tax:%.5f Message: %s\n",m.getKey()
            ,m.getValue().getName()
            ,m.getValue().getPrice()
            ,m.getValue().getTaxAmount(),m.getValue().getMessage());});
        return;
    }

    /**
     * search Product in the cart based on the name and id
     * 
     * @param p  name of the product
     * @param id of the product
     * @return Product object for further convinences
     */
    public Product searchItem(String p, int id) {
        Iterator<Map.Entry<Integer, Product>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            if (entry.getKey() == Integer.valueOf(id) && entry.getValue().getName().equals(p)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * add item to item list
     * 
     * @param p the product name used for adding
     *          <p>
     *          add product to items map
     *          then add coupon to copouns list if product has a copoun
     *          and substract the quantity of product
     *          </p>
     * @return boolean true if success / false if not work
     */
    public boolean addItem(Product p) {
        Product product = store.getProductByName(p.getName());
        // create a single product that can be modified its msg indepently
        // to others product with the same name in the Product manager
        Product item = createItem(product);
        item.setCreateGift(product.getCreateGift());
        if (product.getAvailableQuantity() != 0) {
            itemsList.put(Integer.valueOf(itemId++), item);
            product.decreaseQuantity(1);
            countTheNumberOfItems(item.getName());
            // System.out.println(item);
            if (product.getCoupon() != null) {
                Coupon coupon = product.getCoupon();
                coupons.add(coupon);
            }
            cartAmount();
            return true;
        }
        return false;
    }

    private void countTheNumberOfItems(String product) {
        if (!countItems.containsKey(product)) {
            countItems.put(product, 1);
        } else {
            countItems.put(product, countItems.get(product) + 1);
        }
    }

    /**
     * create an unique item
     * 
     * @param p the product name that used for create item
     *          <p>
     *          create an unique Product item in cart
     *          that takes data from the product in the product manager,
     *          where just only store the data of products with same name, not a
     *          single unique product
     *          </p>
     * @return Product item that is unique
     */
    private Product createItem(Product p) {
        if (p instanceof PhysicalProducts) {
            PhysicalProducts phy = (PhysicalProducts) p;
            PhysicalProducts item = new PhysicalProducts(p.getName(), p.getPrice(), p.getDescription(), 1,
                    p.getTaxType());
            item.setWeight(phy.getWeight());
            return item;
        } else {
            DigitalProduct digitalProduct = new DigitalProduct(p.getName(), p.getPrice(), p.getDescription(), 1,
                    p.getTaxType());
            return digitalProduct;
        }
    }

    // calculate the size of the map
    public int countItem() {
        return itemsList.size();
    }

    /**
     * remove item to item list based on id
     * 
     * @param p  the product name used for removing
     * @param id the id of a specific item
     *           <p>
     *           remove product from items map based on its name and id
     *           then add the quatity of the product
     *           and remove coupon to copouns list
     *           if there are no left coressponding product in the list
     *           </p>
     * @return boolean true if success / false if not work
     */
    public boolean removeItemById(int id) {
        if (itemsList.containsKey(id)) {
            Product product = store.getProductByName(itemsList.get(id).getName());
            if (product != null) {
                product.increaseQuantity(1);
            }
            if (countItems.get(itemsList.get(id).getName()) > 0) {
                countItems.put(itemsList.get(id).getName(), countItems.get(itemsList.get(id).getName()) - 1);
            }
            if (countItems.get(itemsList.get(id).getName()) == 0) {
                countItems.remove(itemsList.get(id).getName());
                if(getAppliedCoupon() != null){
                    if (getAppliedCoupon().getProduct().equals(itemsList.get(id).getName())) {
                        setAppliedCoupon(null);
                    }
                }
                removeCoupon(itemsList.get(id).getName());
            }
            itemsList.remove(id);
            cartAmount();
            return true;
        }
        return false;
    }

    /**
     * remove item to item list
     * 
     * @param p the product name used for removing
     *          <p>
     *          remove product from items map
     *          then add the quatity of the product
     *          and remove coupon to copouns list
     *          if there are no left coressponding product in the list
     *          </p>
     * @return boolean true if success / false if not work
     */

    public boolean removeItem(String p) {
        Iterator<Map.Entry<Integer, Product>> iterator = itemsList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            if (entry.getValue().getName().equals(p)) {
                if (countItems.get(p) > 0) {
                    countItems.put(p, countItems.get(p) - 1);
                } else {
                    countItems.remove(p);
                }
                Product product = store.getProductByName(p);
                if (product != null) {
                    product.increaseQuantity(1);
                }
                if (countItems.get(p) == 0) {
                    Coupon c = getAppliedCoupon();
                    if (c != null && c.getProduct().equals(p)) {
                        setAppliedCoupon(null);
                    }
                    removeCoupon(p);
                }
                cartAmount();
                iterator.remove();
            }
        }
        return true;
    }

    // change all items, whics have that name
    public boolean changeItemAll(Product p) {
        Collection<Product> items = itemsList.values();
        for (Product product : items) {
            if (product.getName().equals(p.getName())) {
                product.setPrice(p.getPrice());
                product.setDescription(p.getDescription());
                product.setTaxType(p.getTaxType());
                product.calculateTax(p.getTaxType());
            }
        }
        cartAmount();
        return true;
    }

    /**
     * Search For item in the item list and then get/set message for the Product
     * item
     * 
     * @param p       the product name used for searching
     * @param message the new message of the Product item
     * @return boolean if found / null if not found
     */
    public boolean addMessage(String p, int id, String message) {
        Product product = searchItem(p, id);
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
     * @param p  the product name used for searching
     * @param id id of product
     * @return String if found / null if not found
     */
    public String getMessage(String p, int id) {
        Product product = searchItem(p, id);
        if (product != null && product.getMessage() != null && product.getCreateGift()) {
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
        if (!getItemsList().isEmpty()) {
            Iterator<Map.Entry<Integer, Product>> iterator = itemsList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Product> entry = iterator.next();
                Product product = entry.getValue();
                productPrice = product.getPrice() + product.getTaxAmount();
                if (product instanceof PhysicalProducts) {
                    PhysicalProducts phy = (PhysicalProducts) product;
                    totalWeight += phy.getWeight();
                }
                if (this.appliedCoupon != null && this.appliedCoupon.getProduct().equals(product.getName())) {
                    if (this.appliedCoupon.getType().equals("percent")) {
                        productPrice *= (1 - (appliedCoupon.getValue() / 100));
                    } else if (this.appliedCoupon.getType().equals("price")) {
                        productPrice -= this.appliedCoupon.getValue();
                    }
                }
                totalPrice += productPrice;
            }
            totalPrice += (totalWeight *BASE_FEE); 
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
     * 
     * @param p Product that might be inside the cart
     *          <p>
     *          if there is any the coupon that has the product name in the coupons
     *          list
     *          then assign it to appiled coupon and re-calculate the price
     *          </p>
     * @return applied copoun
     */
    public Coupon applyCoupon(String p) {
        if(countItems.containsKey(p)){
            if (countItems.get(p) > 0) {
                this.appliedCoupon = searchCoupon(p);
                // System.out.println(this.appliedCoupon.getProduct());
            }
        }
        cartAmount();
        return this.appliedCoupon;
    }

    /**
     * search copoun based on the name
     * 
     * @param p name of the product that owns the coupon
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
    public boolean removeCoupon(String p) {
        Coupon coupon = searchCoupon(p);
        if (coupon != null) {
            getCoupons().remove(coupon);
            setAppliedCoupon(null);
            cartAmount();
            return true;
        }
        return false;
    }

    public void displayAllCoupons() {
        for (Coupon coupon : coupons) {
            System.out.println("Type:" + coupon.getType() + "|" + "Value:" + coupon.getValue() + "|" + "Product:" +
                    coupon.getProduct());
        }
    }

    // getters, setters
    public Map<Integer, Product> getItemsList() {
        return itemsList;
    }

    public void setItemsList(Map<Integer, Product> itemsList) {
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
