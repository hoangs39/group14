package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class ShoppingCartList {
    List<ShoppingCart> shoppingCartList;
    public ShoppingCartList(){
        shoppingCartList= new ArrayList<>();
    }
    public void addShoppingCart(ShoppingCart s){
        shoppingCartList.add(s);
        s.cartAmount();
    }
    public int countShoppingCart(){
        return shoppingCartList.size();
    }   
    public List<ShoppingCart> getShoppingCartList(){
        return shoppingCartList;
    }
    public void sortBasedOnTotalWeights(){
        Collections.sort(shoppingCartList);
    }
    public ShoppingCart searchForCartInCartList(int id){
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if(shoppingCart.getId()==id){
                return shoppingCart;
            }
        }
        return null;
    }
}
