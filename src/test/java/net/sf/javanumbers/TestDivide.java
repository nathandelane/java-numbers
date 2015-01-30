package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestDivide {
  
  @Test
  public void testDivideFromWhole() {
    final Rational r1 = Rational.valueOf(1);
    final Rational r2 = Rational.valueOf(4);
    
    assertTrue(r1.divide(r2).equals(new Rational(1, 4)));
  }
  
  @Test
  public void testNonDeterminateDivision() {
    final Rational r1 = Rational.valueOf(1);
    final Rational r2 = Rational.valueOf(3);
    
    assertTrue(r1.divide(r2).equals(new Rational(1, 3)));
  }
  
  @Test
  public void testMultiWholes() {
    final Rational r1 = Rational.valueOf(2);
    final Rational r2 = Rational.valueOf(3);
    
    assertTrue(r1.divide(r2).equals(new Rational(2, 3)));
  }

  @Test
  public void testMultiWholes2() {
    final Rational r1 = Rational.valueOf(5);
    final Rational r2 = Rational.valueOf(4);
    
    assertTrue(r1.divide(r2).equals(new Rational(5, 4)));
  }

}
