package com.example;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductsManager store = Loader.retriveProductFromDatabase("products.txt");
        CartsManager cartsManager = Loader.retriveCartsFromDatabase(store, "carts.txt");
        View view = new View();
        Controllerr controllerr = new Controllerr(store, cartsManager, view);
        controllerr.menu(sc);
        sc.close();
    }
}
