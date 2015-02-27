package net.sf.javanumbers;

import java.math.BigInteger;

abstract class Multiples {

  public static BigInteger leastCommonMultiple(BigInteger left, BigInteger right) {
    return left.multiply(right).divide(Factors.greatestCommonFactor(left, right));
  }
  
}
