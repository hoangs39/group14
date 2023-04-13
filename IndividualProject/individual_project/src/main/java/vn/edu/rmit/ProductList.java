package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import java.util.List;
import java.util.ArrayList;
public class ProductList {
    List<Product> products;
    public ProductList(){
        products=new ArrayList<>();
    }
    public void addProduct(Product p){
        products.add(p);
    }
    public int countProduct(){
        return products.size();
    }   
    public List<Product> getProductsList(){
        return products;
    }
    public Product searchForProduct(String name){
        for (Product product : products) {
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
}
