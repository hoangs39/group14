package vn.edu.rmit;
/**
 * @author <Doan Duy Bach - S3926953>
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;  

public class DigitalProductTest {
    @Test
  public void equalSameDigitalProduct() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    DigitalProduct pro2 = pro1;
    assertTrue(pro1.equals(pro2));
  }
  @Test
  public void equalSameDigitalProductObject() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    Object obj = pro1;
    assertTrue(pro1.equals(obj));
  }
  @Test
  public void digitalProductCompareWithWrongType() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    String smth = "New digita; product";
    assertFalse(pro1.equals(smth));
  }
  public void digitalEqualName() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    DigitalProduct pro2 = new DigitalProduct("Miku Emojie", "A cute miku emoji", 20, 25, "Good morning");
    pro1.setProductName(pro1.getName()+"e");
    assertTrue(pro1.getName().equals(pro2.getName()));
  }
  @Test
  public void differentPhysicalProductName() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    DigitalProduct pro2 = new DigitalProduct("Miku Emojie", "A cute miku emoji", 20, 25, "Good morning");
    assertFalse(pro1.getName().equals(pro2.getName()));
  }
  @Test
  public void toStringDigitalProductOutput() {
    DigitalProduct pro1 = new DigitalProduct("Miku Emoji", "A cute miku emoji", 20, 25, "Good morning");
    String stringResult = "DIGITAL - "+"<"+pro1.getName()+">";
    assertTrue(stringResult.equals(pro1.toString()));
  }
}
