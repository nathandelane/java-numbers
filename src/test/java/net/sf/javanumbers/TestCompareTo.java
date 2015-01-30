package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCompareTo {
  
  @Test
  public void testCompareToEqual() {
    final Rational r1 = new Rational(1, 8);
    final Rational r2 = Rational.valueOf(0.125);
    
    assertTrue(r1.compareTo(r2) == 0);
  }
  
  @Test
  public void testCompareToLeftIsLessThan() {
    final Rational r1 = new Rational(1, 8);
    final Rational r2 = new Rational(1, 4);
    
    assertTrue(r1.compareTo(r2) < 0);
  }

  @Test
  public void testCompareToRightIsLessThan() {
    final Rational r1 = new Rational(1, 8);
    final Rational r2 = new Rational(1, 4);
    
    assertTrue(r2.compareTo(r1) > 0);
  }

}
