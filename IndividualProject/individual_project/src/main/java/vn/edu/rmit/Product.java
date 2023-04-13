package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
public class Product 
{
    private String productName;
    private String description;
    private int quantityAvailable;
    private double price;
    private String message;
    public Product(String name,String description,int quantityAvailable,double price,String msg){
        this.productName=name;
        this.description=description;
        this.quantityAvailable=quantityAvailable;
        this.price=price;
        this.message=msg;
    }
    protected String getName(){
        return productName;
    }
    protected String getDescription(){
        return description;
    }
    protected int getQuantityAvailable(){
        return quantityAvailable;
    }
    protected double getPrice(){
        return price;
    }
    protected String getMessage(){
        return message;
    }
    protected void setMessage(String message){
        this.message=message;
    }
    protected void setQuantityAvailable(int quantityAvailable){
        this.quantityAvailable=quantityAvailable;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
