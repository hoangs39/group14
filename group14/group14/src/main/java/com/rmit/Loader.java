package com.rmit;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Loader {
    static String fileName = "products.txt";
    static String fileName2 = "carts.txt";
    public static ProductsList readProductFromFile() {
        ProductsList productList = new ProductsList();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");
                String type = elements[0];
                String name = elements[1];
                String description = elements[2];
                int quantity = Integer.parseInt(elements[3]);
                double price = Double.parseDouble(elements[4]);

                double weight;
                if (type.equals("physical")) {
                    weight = 0;
                } else {
                    weight = Double.parseDouble(elements[5]);
                }
                String message = elements[6];
                Coupon coupon = null;
                if(!elements[7].equals("x-x-0.00000")){
                    String[]couponInfos = elements[7].split("-");
                    String couponType = couponInfos[0];
                    String couponName = couponInfos[1];
                    double couponPrice = Double.parseDouble(couponInfos[2]);
                    coupon = new Coupon(couponName, couponType, couponPrice);
                }
                String taxType = elements[8];
                Product p;
                if (weight != 0) {
                    p = new PhysicalProducts(name, price, description, quantity, taxType);
                    PhysicalProducts phy = (PhysicalProducts) p;
                    phy.setWeight(weight);
                }
                p = new DigitalProduct(name, price, description, quantity, taxType);
                p.setMessage(message);
                p.setCoupon(coupon);
                p.calculateTax(taxType);
                productList.getProductsList().add(p);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in file: " + fileName);
        }
        return productList;
    }

    public static void writeProductsToFile(List<Product> store) {
        try (FileWriter fw = new FileWriter(fileName)) {
            String weight = "";
            String msg = "";
            String couponInfo;

            for (Product product : store) {
                if(product instanceof PhysicalProducts){
                    PhysicalProducts phy = (PhysicalProducts) product;
                    weight = String.format("%.5f", phy.getWeight());
                }
                if(product.getMessage() != null && !product.getMessage().equals("")){
                        msg = product.getMessage();             
                }

                Coupon coupon = product.getCoupon();
                if(coupon != null){
                    couponInfo = String.format("%s-%s-%.5f", coupon.getType(),coupon.getProduct(),
                    coupon.getValue());
                }else{
                    couponInfo = String.format("%s-%s-%.5f", "x","x",
                    0);
                }

                fw.write(product.getName() + "," + product.getDescription() + "," +
                         product.getQuantity() + "," + product.getPrice() + "," 
                         +weight + "," + msg + "," 
                         +couponInfo + "," + product.getTaxType() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCartsToFile(List<ShoppingCart> shoppingCarts) {
        String couponI;
        Date date = new Date();
        try (FileWriter fw = new FileWriter(fileName2)) {
            for (ShoppingCart cart : shoppingCarts) {
                Coupon coupon = cart.appliedCoupon;
                if(coupon != null){
                    couponI = String.format("%s-%s-%.5f", coupon.getType(),coupon.getProduct(),
                    coupon.getValue());
                }else{
                    couponI = String.format("%s-%s-%.5f", "x","x",
                    0);    
                }
                fw.write(cart.id + "," + cart.totalWeight + "," + cart.totalPrice + "," + couponI +"," + date.toString() +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}