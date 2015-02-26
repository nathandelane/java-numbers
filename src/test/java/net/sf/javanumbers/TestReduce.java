package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestReduce {

  @Test
  public void testReduce() {
    final Rational r = Rational.valueOf(0.125);
    final Rational reducedR = r.reduce();
    
    assertTrue(reducedR.equals(new Rational(1, 8)));
  }
  
  @Test
  public void testZero() {
    final Rational r = new Rational(0, 2);
    
    assertTrue(r.reduce().equals(0));
  }
  
  @Test
  public void testReduceDecimalNumerator() {
    final Rational r = new Rational(0.125, 1.0);
    
    assertTrue(r.reduce().equals(0.125));
  }
  
  @Test
  public void testReduceDecimalDenominator() {
    final Rational r = new Rational(1, 0.25);
    
    assertTrue(r.reduce().equals(Rational.valueOf(4)));
  }
  
}
