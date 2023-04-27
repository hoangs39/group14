package com.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    static String fileName = "products.txt";
    static String fileName2 = "carts.txt";

    public static ProductsManager readProductFromFile() {
        ProductsManager productsManager = new ProductsManager();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fileReader);
            // read the first title Line;
            in.readLine();
            String line;
            while ((line = in.readLine()) != null) {
                String[] elements = line.split(",");
                String type = elements[0];
                String name = elements[1];
                String description = elements[2];
                int quantity = Integer.parseInt(elements[3]);
                double price = Double.parseDouble(elements[4]);

                double weight;
                if (type.equals("PHYSICAL")) {
                    weight = Double.parseDouble(elements[5]);
                } else {
                    weight = 0.0;
                }
                String message = elements[6];
                boolean gift = false;
                if(message.equals("true")){
                    gift = true;
                }
                Coupon coupon = null;
                if (!elements[7].equals("x-x-0.00000")) {
                    String[] couponInfos = elements[7].split("-");
                    String couponType = couponInfos[0];
                    String couponName = couponInfos[1];
                    double couponPrice = Double.parseDouble(couponInfos[2]);
                    coupon = new Coupon(couponName, couponType, couponPrice);
                }
                String taxType = elements[8];
                Product p;
                if (type.equals("PHYSICAL")) {
                    p = new PhysicalProducts(name, price, description, quantity, taxType);
                    PhysicalProducts phy = (PhysicalProducts) p;
                    phy.setWeight(weight);
                }else{
                    p = new DigitalProduct(name, price, description, quantity, taxType);
                }
                p.setMessage(message);
                p.setCoupon(coupon);
                p.setCreateGift(gift);
                productsManager.addProduct(p);
                // String lineSkip = in.readLine();
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

    public static void writeProductsToFile(HashSet<Product> store) {
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
                if(product.getCreateGift()){
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

    public static void writeCartsToFile(List<ShoppingCart> shoppingCarts) {
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
                System.out.println(items);
                for (Product p : items) {
                    if (p instanceof PhysicalProducts) {
                        PhysicalProducts phy = (PhysicalProducts) p;
                        weight = String.format("%.5f", phy.getWeight());
                    }
                    Coupon c = p.getCoupon();
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

    public static CartsManager createCartsObject(ProductsManager store) {
        CartsManager cartsManager = new CartsManager();
        try {
            FileReader fileReader = new FileReader(fileName2);
            BufferedReader in = new BufferedReader(fileReader);
            String line;
            in.readLine();
            ShoppingCart sc;
            while ((line = in.readLine()) != null) {
                String[] elements = line.split(",");
                String cartId = elements[0];
                // System.out.println(elements[4]);
                String couponName = "";
                if (!elements[3].equals("x-x-0.00000")) {
                    String[] couponInfos = elements[3].split("-");
                    couponName = couponInfos[1];
                }

                sc = new ShoppingCart(cartId, store);
                // System.out.println(sc);
                String line2 = in.readLine();
                String[] len = line2.split(":");
                int length = Integer.parseInt(len[1]);
                // System.out.println(length);

                String line3 = in.readLine();
                String productsLines;
                for (int index = 0; index < length; index++) {
                    productsLines = in.readLine();
                    String[] pInfos = productsLines.split(",");
                    String type = pInfos[0];
                    String name = pInfos[1];
                    String description = pInfos[2];
                    int quantity = Integer.parseInt(pInfos[3]);
                    // System.out.println(quantity);
                    double price = Double.parseDouble(pInfos[4]);
                    double weight;
                    if (type.equals("physical")) {
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
                    String taxType = pInfos[8];
                    Product p;
                    if (weight != 0) {
                        p = new PhysicalProducts(name, price, description, quantity, taxType);
                        PhysicalProducts phy = (PhysicalProducts) p;
                        phy.setWeight(weight);
                    } else {
                        p = new DigitalProduct(name, price, description, quantity, taxType);
                        p.setMessage(message);
                        p.setCoupon(coupon);
                    }
                    // System.out.println(p);
                    for (int i = 0; i < quantity; i++) {
                        // System.out.println(i);
                        sc.addItem(p);
                        // System.out.println("hi");
                    }

                }
                // System.out.println(couponName);
                if(!couponName.equals("")){
                    sc.applyCoupon(couponName);
                }
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
}