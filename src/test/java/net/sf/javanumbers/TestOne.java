package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestOne {

  @Test
  public void testOneEqualsOne() {
    assertTrue(Rational.ONE.equals(1));
  }
  
  @Test
  public void testIntegerMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Integer.MAX_VALUE, Integer.MAX_VALUE)));
  }
  
  @Test
  public void testLongMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Long.MAX_VALUE, Long.MAX_VALUE)));
  }
  
  @Test
  public void testDoubleMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Double.MAX_VALUE, Double.MAX_VALUE)));
  }
  
  @Test
  public void testFloatMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Float.MAX_VALUE, Float.MAX_VALUE)));
  }
  
  @Test
  public void testShortMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Short.MAX_VALUE, Short.MAX_VALUE)));
  }
  
  @Test
  public void testCharMaxValueEqualsOne() {
    assertTrue(Rational.ONE.equals(new Rational(Character.MAX_VALUE, Character.MAX_VALUE)));
  }
  
  @Test
  public void testNegativeNormalizedEqualsOne() {
    final Rational r = new Rational(-1, -1);
    
    assertTrue(r.equals(1));
  }
  
}
