package com.rmit;

public class Test2 {
    public static void main(String[] args) {
        // Product p1 = new PhysicalProducts("car", 40.0, "new", 4, "luxuryTax");
        // Coupon cp1 = new Coupon(p1.getName(), "percent", 10);
        // p1.setCoupon(cp1);
        // PhysicalProducts phy1 = (PhysicalProducts) p1;
        // phy1.setWeight(10.0);
        // Product p2 = new PhysicalProducts("bike", 4.0, "new", 4, "normalTax");
        // Coupon cp2 = new Coupon(p2.getName(), "price", 10);
        // p2.setCoupon(cp2);
        // p2.setCreateGift(true);
        // Product p3 = new DigitalProduct("game", 40.0, "new", 4, "normalTax");
        // Product p4 = new DigitalProduct("xx", 40.0, "new", 4, "normalTax");
        
        // System.out.println(p1.getDisplayInfo());
        // System.out.println(p2.getDisplayInfo());
        // System.out.println(p3.getDisplayInfo());
        // System.out.println(p4.getDisplayInfo());

        // ProductsManager productManager = new ProductsManager();
        // productManager.addProduct(p1);
        // System.out.println(productManager.addProduct(p1));
        // System.out.println(productManager.addProduct(p2));
        // System.out.println(productManager.getProductByName(p2.getName()).getCreateGift());
        // productManager.addProduct(p3);
        // Loader.writeProductsToFile(productManager.getProductList());
        // ProductsManager store = Loader.readProductFromFile();
        // store.getAllProducts();
        // Loader.writeProductsToFile(store.getProductList());
        // System.out.println(productManager.addProduct(p4));
        // System.out.println(productManager.countProducts() == 3);

        // System.out.println(productManager.removeProduct(p4));
        // System.out.println(productManager.removeProduct(p4));
        
        // productManager.getAllProducts();
        // System.out.println(productManager.getProductByName(p1.getName()).equals(p1));
        // System.out.println(productManager.countProducts() == 3);
        
        // ShoppingCart c1 = new ShoppingCart("C1",store);
        // c1.addItem(p1);
        // c1.addItem(p1);
        // System.out.println(c1.addItem(p1));
        // System.out.println(c1.addItem(productManager.getProductByName(p1.getName())));
        // System.out.println(c1.addItem(p1));
        // System.out.println(p1.getAvailableQuantity());
        // c1.addItem(p2);
        // System.out.println(c1.addMessage(p2.getName(), "gift!"));
        // System.out.println(c1.getMessage(p2.getName()));

        // System.out.println(c1.addItem(p2));
        // System.out.println(c1.addItem(p2));
        // System.out.println(cp2.getValue());
        // System.out.println(c1.searchCoupon(p2.getName()));
        // System.out.println(c1.applyCoupon(p2.getName()));
        // System.out.println(c1.applyCoupon(p1.getName()));
        // System.out.println(c1.getAppliedCoupon());
        // System.out.println(c1.removeItem(p1.getName()));
        // System.out.println(c1.countItem());
        // System.out.println(c1.cartAmount());

        // System.out.println(c1.searchItem(p1.getName()).getAvailableQuantity());
        // p2.setPrice(10.0);
        // System.out.println(p1.calculateTax(p1.getTaxType()));
        // System.out.println(p1.getDisplayInfo());
        // System.out.println(c1.changeItemAll(p2));
        // System.out.println(c1.searchItem(p1.getName()).getPrice());
        // c1.cartAmount();
        // System.out.println(c1.cartAmount());
        // System.out.println(c1.removeItemAll(p1.getName()));
        // System.out.println(c1.cartAmount());

        // ShoppingCart c2 = new ShoppingCart("C2",productManager);
        // c2.addItem(productManager.getProductByName(p2.getName()));
        // System.out.println(c2.addItem(productManager.getProductByName(p2.getName())));

        // CartsManager cm = new CartsManager();
        // cm.addCart(c1);
        // cm.addCart(c2);
        // System.out.println(cm.RemoveItemInCart(p1));
        // System.out.println(cm.RemoveItemInCart(p1));
        // System.out.println(c1.getItemsList());
        // System.out.println(cm.countCarts());
        // p2.setPrice(10.00000);
        // cm.Change(p2);
        // p2.calculateTax(p2.getTaxType());
        // System.out.println(c1.cartAmount());
        // cm.Change(p2);
        // System.out.println(c1.searchItem(p2.getName()).getPrice());
        // cm.RemoveItemInCart(p1);
        // System.out.println(c1.searchItem(p1.getName()));
        
        // System.out.println(cm.countCarts());

        // CartsManager cartsManager = Loader.createCartsObject(store);
        // cartsManager.displayCarts();
        // System.out.println(cartsManager.buy());
        // cm.displayCarts();
        // Loader.writeProductsToFile();
    }
}
