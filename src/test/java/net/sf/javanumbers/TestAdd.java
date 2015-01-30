package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAdd {
  
  @Test
  public void testAddToWhole() {
    final Rational r1 = new Rational(2, 3);
    final Rational r2 = new Rational(1, 3);
    
    assertTrue(r1.add(r2).equals(1));
  }

  @Test
  public void testAddToWhole2() {
    final Rational r1 = new Rational(7, 8);
    final Rational r2 = Rational.valueOf(0.125);
    
    assertTrue(r1.add(r2).equals(1));
  }
  
  @Test
  public void testAddRationals() {
    final Rational r1 = new Rational(1, 16);
    final Rational r2 = new Rational(2, 16);
    
    assertTrue(r1.add(r2).equals(new Rational(3, 16)));
  }
  
  @Test
  public void testAddZero() {
    final Rational r1 = new Rational(15, 67);
    
    assertTrue(r1.add(Rational.ZERO).equals(r1));
  }
  
  @Test
  public void testAddOne() {
    final Rational r1 = Rational.valueOf(-0.5);
    
    assertTrue(r1.add(Rational.ONE).equals(r1.negate()));
  }

}
