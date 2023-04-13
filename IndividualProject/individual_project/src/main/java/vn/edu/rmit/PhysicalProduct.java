package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
public class PhysicalProduct extends Product{
    private double weight;
    public PhysicalProduct(String productName,String description,int quantityAvailable,double price,String msg,double weight){
        super(productName,description,quantityAvailable,price,msg);
        this.weight=weight;
    }
    
    public double getWeight() {
        return weight;
    }
    

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PHYSICAL - "+"<"+getName()+">";
    }
}
