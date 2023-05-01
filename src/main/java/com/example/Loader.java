package com.example;
/**
 * @author Group14
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Collection;
import java.util.HashSet;

public class Loader {

    static String fileName = "products.txt";
    static String fileName2 = "carts.txt";

    /**
     * <p>
     * read the products.txt file then initiate a new product manager model object,
     * which contains products
     * </p>
     */
    public static ProductsManager retriveProductFromDatabase(String file) {
        ProductsManager productsManager = new ProductsManager();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader in = new BufferedReader(fileReader);
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {
                Product p = createProduct(line, "product");
                productsManager.addProduct(p);
            }
            in.close();
            fileReader.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productsManager;
    }

    /**
     * <p>
     * read the carts.txt file then initiate a new cart manager model object,
     * which contains carts and products
     * </p>
     */
    public static CartsManager retriveCartsFromDatabase(ProductsManager store, String file) {
        CartsManager cartsManager = new CartsManager();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader in = new BufferedReader(fileReader);
            String line;
            in.readLine();
            ShoppingCart sc;
            while ((line = in.readLine()) != null) {
                String[] elements = line.split(",");
                String cartId = elements[0];

                String couponName = "";
                double couponValue = 0.0;
                String couponType = "";
                Coupon cartAppliedCoupon = null;
                if (!elements[3].equals("x-x-0.00000")) {
                    String[] couponInfos = elements[3].split("-");
                    couponName = couponInfos[1];
                    couponType = couponInfos[0];
                    couponValue = Double.parseDouble(couponInfos[2]);
                    // System.out.println(couponName);
                    // System.out.println(couponValue);
                    // System.out.println(couponType);
                    cartAppliedCoupon = new Coupon(couponName, couponType, couponValue);
                }
                sc = new ShoppingCart(cartId, store);
                String line2 = in.readLine();
                String[] len = line2.split(":");
                int length = Integer.parseInt(len[1]);
                // System.out.println(length);
                String line3 = in.readLine();
                String productsLines;
                for (int index = 0; index < length; index++) {
                    productsLines = in.readLine();
                    // System.out.println(productsLines);
                    Product p = createProduct(productsLines, "cart");
                    sc.addItem(p);
                    // System.out.println(sc.addItem(p));
                }
                sc.setAppliedCoupon(cartAppliedCoupon);
                cartsManager.addCart(sc);
            }
            in.close();
            fileReader.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cartsManager;
    }

    /**
     * <p>
     * function that utilizes File Stream API
     * to write data of objects and convert it into lines of text in file
     * </p>
     */
    public static void writeProductsIntoFile(HashSet<Product> store) {
        try (FileWriter fw = new FileWriter(fileName)) {
            String weight = "";
            String couponInfo;
            String productTitle = "Type,Name,Description,Quantity,Price,Weight,Gift,Coupon,TaxType";
            fw.write(productTitle + "\n");
            for (Product product : store) {
                if (product instanceof PhysicalProducts) {
                    PhysicalProducts phy = (PhysicalProducts) product;
                    weight = String.format("%.5f", phy.getWeight());
                }

                Coupon coupon = product.getCoupon();
                if (coupon != null) {
                    couponInfo = String.format("%s-%s-%.5f", coupon.getType(), coupon.getProduct(),
                            coupon.getValue());
                } else {
                    couponInfo = String.format("%s-%s-%.5f", "x", "x",
                            0.0);
                }
                String msg = "false";
                if (product.getCreateGift()) {
                    msg = "true";
                }

                fw.write(product.getProductType() + "," + product.getName() + "," + product.getDescription() + "," +
                        product.getAvailableQuantity() + "," + product.getPrice() + ","
                        + weight + "," + msg + ","
                        + couponInfo + "," + product.getTaxType() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * function that utilizes File Stream API
     * to write data of objects and convert it into lines of text in file
     * </p>
     */
    public static void writeCartsIntoFile(List<ShoppingCart> shoppingCarts) {
        String couponn;
        LocalDateTime createId = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        String msg;
        String cartTitle = "CartId,TotalWeight,TotalPrice,Coupon,Date";
        String productTitle = "Type,Name,Description,Quantity,Price,Weight,Gift,Coupon,TaxType";
        try (FileWriter fw = new FileWriter(fileName2)) {
            fw.write(cartTitle + "\n");
            for (ShoppingCart cart : shoppingCarts) {
                Coupon coupon = cart.getAppliedCoupon();
                if (coupon != null) {
                    couponn = String.format("%s-%s-%.5f", coupon.getType(), coupon.getProduct(),
                            coupon.getValue());
                } else {
                    couponn = String.format("%s-%s-%.5f", "x", "x",
                            0.0);
                }
                fw.write(cart.getCartId() + "," + cart.getTotalWeight() + "," + cart.getTotalPrice() + "," + couponn
                        + "," + createId.format(myFormatObj).toString() + "\n");
                fw.write("Products:" + cart.countItem() + "\n");
                fw.write(productTitle + "\n");
                String weight = "";
                String cp;
                Collection<Product> items = cart.getItemsList().values();

                // System.out.println(cart.getItemsList().get(1).getCoupon());
                // System.out.println(cart.getItemsList().get(2).getCoupon());

                for (Product p : items) {
                    if (p instanceof PhysicalProducts) {
                        PhysicalProducts phy = (PhysicalProducts) p;
                        weight = String.format("%.5f", phy.getWeight());
                    }
                    // System.out.println(p);
                    Coupon c = p.getCoupon();
                    // System.out.println(c);
                    if (c != null) {
                        cp = String.format("%s-%s-%.5f", coupon.getType(), coupon.getProduct(),
                                coupon.getValue());
                    } else {
                        cp = String.format("%s-%s-%.5f", "x", "x",
                                0.0);
                    }

                    fw.write(p.getProductType() + "," + p.getName() + "," + p.getDescription() + "," +
                            p.getAvailableQuantity() + "," + p.getPrice() + ","
                            + weight + "," + p.getMessage() + ","
                            + cp + "," + p.getTaxType() + "\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * create Product object based on all information stored in files
     * </p>
     */
    public static Product createProduct(String line, String choices) {
        String[] pInfos = line.split(",");
        String type = pInfos[0];
        // System.out.println(type.equalsIgnoreCase("PHYSICAL"));
        String name = pInfos[1];
        String description = pInfos[2];
        int quantity = Integer.parseInt(pInfos[3]);
        double price = Double.parseDouble(pInfos[4]);
        double weight;
        // System.out.println(pInfos[5]);
        if (type.equalsIgnoreCase("PHYSICAL")) {
            // System.out.println(pInfos[5]);
            // System.out.println("i");
            weight = Double.parseDouble(pInfos[5]);
        } else {
            weight = 0;
        }
        String message = pInfos[6];

        Coupon coupon = null;
        if (!pInfos[7].equals("x-x-0.00000")) {
            String[] cInfos = pInfos[7].split("-");
            String cType = cInfos[0];
            String cName = cInfos[1];
            double cPrice = Double.parseDouble(cInfos[2]);
            coupon = new Coupon(cName, cType, cPrice);
        }
        // System.out.println(coupon);
        String taxType = pInfos[8];
        Product p;
        if (weight != 0) {
            p = new PhysicalProducts(name, price, description, quantity, taxType);
            PhysicalProducts phy = (PhysicalProducts) p;
            phy.setWeight(weight);
        } else {
            p = new DigitalProduct(name, price, description, quantity, taxType);
        }
        if (choices.equalsIgnoreCase("product")) {
            if (message.equals("true")) {
                p.setCreateGift(true);
            }
            p.setMessage(message);
        } else {
            p.setCreateGift(true);
            p.setMessage(message);
        }
        p.setCoupon(coupon);
        // System.out.println(p.getCoupon());
        return p;
    }
}
