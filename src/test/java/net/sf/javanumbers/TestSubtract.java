package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSubtract {
  
  @Test
  public void testSubtractToZero() {
    final Rational r1 = new Rational(3, 20);
    final Rational r2 = new Rational(1, 20);
    final Rational r3 = new Rational(1, 10);
    
    assertTrue(r1.subtract(r2).subtract(r3).equals(0));
  }
  
  @Test
  public void testSubtractFromWhole() {
    final Rational r1 = Rational.valueOf(1);
    final Rational r2 = new Rational(1, 3);
    
    assertTrue(r1.subtract(r2).equals(new Rational(2, 3)));
  }

}
