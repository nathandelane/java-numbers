package net.sf.javanumbers;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class TestFactors {
  
  @Test
  public void testGreatestCommonFactor() {
    final BigInteger gcf = Factors.greatestCommonFactor(BigInteger.valueOf(24), BigInteger.valueOf(6));
    
    assertTrue(gcf.equals(BigInteger.valueOf(6)));
  }

}
