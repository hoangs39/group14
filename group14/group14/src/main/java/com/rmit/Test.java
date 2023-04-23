package com.rmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

interface Giftt {
    public String getMessage();

    public void setMessage(String message);
}

class Productt implements Giftt {
    int price;
    String name;
    String msg;
    int quanity;

    boolean gift = false;

    public Productt(String name, int price) {
        this.name = name;
        this.price = price;
    };

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public void setMessage(String message) {
        if (gift) {
            this.msg = message;
        }
    }
}

class Store {
    // list of products
    private static HashSet<Productt> productList;

    // constructor
    public Store() {
        this.productList = new HashSet<>();
    }

    // product list functions
    public void addItem(Productt p) {
        this.productList.add(p);
    }

    public HashSet<Productt> getList() {
        return this.productList;
    }

}

class Test {
    public static void main(String args[]) throws IOException {
        Map<Productt, Integer> words = new HashMap<>();
        List<Productt> list = new ArrayList<>();
        Productt p1 = new Productt("map", 2);
        Productt p2 = new Productt("ma", 4);
        list.add(p2);
        Productt p = list.get(0);
        Productt p3 = new Productt(p2.name, p2.price);
        p3.quanity = 1;
        System.out.println(list.contains(p2));
        p3.setGift(true);
        p3.setMessage("me!");
        words.put(p3, 1);
        System.out.println(words.get(p3));
        words.put(p3, words.get(p3) + 1);
        System.out.println(words.get(p3));

        // words.put(p3, 1);
        // p3 = new Productt(p1.name, p2.price);
        // words.put(p3, words.get(p3) + 1);

        // Productt p4 = new Productt(p1.name, p1.price);
        p2.setPrice(5);
        Iterator<Map.Entry<Productt, Integer>> iterator = words.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Productt, Integer> entry = iterator.next();
            Productt productt = entry.getKey();
            if (productt.name.equals(p2.name)) {
                productt.setPrice(p2.price);
            }
            // System.out.println(productt.price);
            // System.out.println(productt.msg);
            System.out.println(entry.getValue());
        }
        // System.out.println(p2.msg);

        // String fileName = "products.txt";
        // Scanner scanner = new Scanner(new File(fileName));
        // while (scanner.hasNextLine()) {
        // String line1 = scanner.nextLine();
        // String[] elements = line1.split(",");
        // String id = elements[0];
        // System.out.println(id);
        // String weight = elements[1];
        // System.out.println(weight);
        // String price = elements[2];
        // System.out.println(price);
        // String line2 = scanner.nextLine();
        // int length = Integer.parseInt(line2);
        // for (int i = 0; i < length; i++) {
        // // if(scanner.hasNextLine()){
        // String line3 = scanner.nextLine();
        // String[] product = line3.split("-");
        // String pName = product[0];
        // String pPrice = elements[1];
        // System.out.println(pName);
        // System.out.println(pPrice);
        // // }
        // }

        // }

        // try (FileWriter fw = new FileWriter(fileName)) {
        // fw.write("cart-1"+ "," +"3.00000" +"," + "40.00000" + "\n");
        // fw.write("2"+"\n");
        // fw.write("p1"+ "-" +"3.00000" +"\n");
        // fw.write("p2"+ "-" +"4.00000" + "\n");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // Scanner scanner = new Scanner(new File(fileName));
        // while (scanner.hasNextLine()) {
        // String line = scanner.nextLine();
        // String[] elements = line.split(",");
        // // System.out.println(elements);
        // String type = elements[0];
        // System.out.println(type);
        // String name = elements[1];
        // System.out.println(name);
        // String description = elements[2];
        // System.out.println(description);
        // int quantity = Integer.parseInt(elements[3]);
        // System.out.println(quantity);
        // double price = Double.parseDouble(elements[4]);
        // System.out.println(price);
        // double weight;
        // if (!type.equals("physical")) {
        // weight = 0;
        // } else {
        // weight = Double.parseDouble(elements[5]);
        // }
        // System.out.println(weight);

        // String message = elements[6];
        // System.out.println(message);

        // Coupon coupon = null;
        // if (!elements[7].equals("x-x-0.00000")) {
        // String[] couponInfos = elements[7].split("-");
        // // System.out.println(couponInfos.length);
        // String couponType = couponInfos[0];
        // System.out.println(couponType);
        // String couponName = couponInfos[1];
        // System.out.println(couponName);
        // double couponPrice = Double.parseDouble(couponInfos[2]);
        // System.out.println(couponPrice);
        // coupon = new Coupon(couponName, couponType, couponPrice);
        // }
        // String taxType = elements[8];
        // System.out.println(taxType);
        // switch (taxType) {
        // case "normalTax":
        // int taxAmount = 9;
        // System.out.println(taxAmount);
        // break;
        // }

        // }
        // File file = new File(fileName);
        // FileReader fileReader = new FileReader(file);
        // BufferedReader in = new BufferedReader(fileReader);
        // String line;
        // while((line = in.readLine()) != null){
        // String[] elements = line.split(",");
        // String type = elements[0];
        // System.out.println(type);
        // String line2 = in.readLine();
        // int len = Integer.parseInt(line2);
        // for (int index = 0; index < len; index++) {
        // String line3 = in.readLine();
        // System.out.println(line3);
        // }
        // }

        // String name = elements[1];
        // System.out.println(name);
        // String description = elements[2];
        // System.out.println(description);
        // int quantity = Integer.parseInt(elements[3]);
        // System.out.println(quantity);
        // double price = Double.parseDouble(elements[4]);
        // System.out.println(price);
        // double weight;
        // if (!type.equals("physical")) {
        // weight = 0;
        // } else {
        // weight = Double.parseDouble(elements[5]);
        // }
        // System.out.println(weight);

        // String message = elements[6];
        // System.out.println(message);

    }
}

