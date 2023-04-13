package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;  

public class PhysicalProductTest {
    @Test
  public void equalSamePhysicalProduct() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    PhysicalProduct pro2 = pro1;
    assertTrue(pro1.equals(pro2));
  }
  @Test
  public void equalSamePhysicalProductObject() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    Object obj = pro1;
    assertTrue(pro1.equals(obj));
  }
  @Test
  public void physicalProductCompareWithWrongType() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    String smth = "New physical product";
    assertFalse(pro1.equals(smth));
  }
  @Test
  public void equalName() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    PhysicalProduct pro2 = new PhysicalProduct("Applee", "An apple", 12, 25, "Hi", 12);
    pro1.setProductName(pro1.getName()+"e");
    assertTrue(pro1.getName().equals(pro2.getName()));
  }
  @Test
  public void differentPhysicalProductName() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    PhysicalProduct pro2 = new PhysicalProduct("Applee", "An apple", 12, 25, "Hi", 12);
    assertFalse(pro1.getName().equals(pro2.getName()));
  }
  @Test
  public void equalWeight() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 10);
    PhysicalProduct pro2 = new PhysicalProduct("Applee", "An apple", 12, 25, "Hi", 12);
    pro1.setWeight(pro1.getWeight()+2);
    assertTrue(pro1.getWeight()==pro2.getWeight());
  }
  @Test
  public void toStringPhysicalProductOutput() {
    PhysicalProduct pro1 = new PhysicalProduct("Apple", "An apple", 12, 25, "Hi", 12);
    String stringResult ="PHYSICAL - "+"<"+pro1.getName()+">";
    assertTrue(stringResult.equals(pro1.toString()));
  }
}
