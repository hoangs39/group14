package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
public class DigitalProduct extends Product {
    public DigitalProduct(String productName,String description,int quantityAvailable,double price,String msg){
        super(productName,description,quantityAvailable,price,msg);
    }
    @Override
    public String toString() {
        return "DIGITAL - "+"<"+getName()+">";
    }
}
