package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.lang.Comparable;
public class ShoppingCart implements Comparable<ShoppingCart>{
final double baseFee=0.1;
protected double fee=0;
protected double total_weight = 0.0;
protected double totalPrice=0;
protected int countCartAmount=0;
private int id;
Set<String> shoppingSet;
List<Product> shoppingList;
ProductList lst;
public ShoppingCart(ProductList lst,int id){
    this.shoppingSet=new HashSet<String>();
    this.shoppingList=new ArrayList<Product>();
    this.lst=lst;
    this.id=id;
}
public int countShoppingCartSet(){
    return shoppingSet.size();
}   
public int countShoppingList(){
    return shoppingList.size();
}   
boolean addItem(String productName){
    boolean able=true;
    if(shoppingSet.contains(productName)){
        return false;
    }
    for(Product p : lst.getProductsList()){
        if(productName.equals(p.getName())){
            if(p.getQuantityAvailable()==0){
                able=false;
                break;
            }
            p.setQuantityAvailable(p.getQuantityAvailable()-1);
            shoppingSet.add(productName);
            shoppingList.add(p);
            break;
    }
}
return able;
}
boolean removeItem(String productName){
    Product result = null;
    Product result1 = null;
    for (Product product : shoppingList) {
        if(product.getName().equals(productName)){
            result = product;
            break;
        }
    }
    for (Product product : lst.getProductsList()) {
        if(product.getName().equals(productName)){
            result1 = product;
            break;
        }
    }
if(result!=null && result1!=null){
    if(shoppingSet.contains(productName) && shoppingList.contains(result)){
        result1.setQuantityAvailable(result1.getQuantityAvailable()+1);
        shoppingSet.remove(productName);
        shoppingList.remove(result);
        return true;
    }
}
    return false;
}
double cartAmount(){
    this.countCartAmount+=1;
    if(countCartAmount>=2){
        totalPrice=0;
        total_weight=0;
    }
    for(Product p : shoppingList){
        if(p instanceof PhysicalProduct){
            PhysicalProduct ph= ((PhysicalProduct) p) ;
            total_weight+=ph.getWeight();
        }
            totalPrice+=p.getPrice();
        }
    fee=baseFee*total_weight;
    totalPrice+=fee;
    return totalPrice;
}
public Set<String> getProductSet(){
    return shoppingSet;
}
public void displayShoppingCartSet(){
    System.out.println(shoppingSet);
}
public void displayShoppingCartList(){
    System.out.println(shoppingList);
}
public Product searchForProductInCart(String name){
    for (Product product : shoppingList) {
        if(product.getName().equals(name)){
            return product;
        }
    }
    return null;
}

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
@Override
public int compareTo(ShoppingCart shoppingCart) {
    if (this.total_weight > shoppingCart.total_weight){
        return 1;
    }
    else if(this.total_weight < shoppingCart.total_weight){
        return -1;
    }
    return 0;
    }
}
