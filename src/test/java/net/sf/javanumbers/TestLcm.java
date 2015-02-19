package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class TestLcm {
  
  @Test
  public void testHappy() {
    final BigInteger lcm = Rational.lcm(BigInteger.valueOf(3), BigInteger.valueOf(9));
    
    assertTrue(lcm.equals(BigInteger.valueOf(9)));
  }
  
  @Test
  public void testAdditionImplOfLcm() {
    final Rational r1 = new Rational(1, 3);
    final Rational r2 = new Rational(6, 9);
    
    assertTrue(Rational.ONE.equals(r1.add(r2)));
  }
  
  @Test
  public void testDestructiveness() {
    final Rational r1 = Rational.valueOf(BigDecimal.valueOf(0.31942));
    final Rational r2 = Rational.valueOf(BigDecimal.valueOf(0.1));
    final Rational r3 = r1.add(r2);
    
    assertTrue(r3.bigDecimalValue().equals(BigDecimal.valueOf(0.41942)));
  }

}
