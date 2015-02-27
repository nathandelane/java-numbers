package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class TestValueOf {
  
  @Test
  public void testValueOfDouble() {
    assertTrue(Rational.valueOf(Double.valueOf(0.123456)).equals(0.123456));
  }
  
  @Test
  public void testValueOfDoubleMax() {
    assertTrue(Rational.valueOf(Double.MAX_VALUE).equals(Double.MAX_VALUE));
  }
  
  @Test
  public void testValueOfDoubleMin() {
    assertTrue(Rational.valueOf(Double.MIN_VALUE).equals(Double.MIN_VALUE));
  }
  
  @Test
  public void testValueOfStringWholeNumber() {
    final String s = "123";
    
    assertTrue(Rational.valueOf(s).equals(new BigDecimal(s)));
  }
  
  @Test
  public void testValueOfStringFloatingPointNumber() {
    final String s = "0.968";
    
    assertTrue(Rational.valueOf(s).equals(new BigDecimal(s)));
  }

}
