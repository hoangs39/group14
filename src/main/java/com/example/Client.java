package com.example;

import java.util.Scanner;
/**
 * <p>
 * The class functions as the executor that uses all view , model and controller classes.
 * </p>
 */
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
