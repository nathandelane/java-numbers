package net.sf.javanumbers;

import java.math.BigInteger;

abstract class Factors {
  
  /**
   * Determine whether left and right values are factors of each other.
   * @param left
   * @param right
   * @return
   */
  public static boolean areFactors(BigInteger left, BigInteger right) {
    return left.abs().mod(right.abs()).compareTo(BigInteger.ZERO) == 0 || right.abs().mod(left.abs()).compareTo(BigInteger.ZERO) == 0;
  }
  
  /**
   * Calculate the greatest common factor of two values.
   * @param left
   * @param right
   * @return
   */
  public static BigInteger greatestCommonFactor(BigInteger left, BigInteger right) {
    BigInteger first = left.compareTo(right) < 0 ? right : left;
    BigInteger second = left.compareTo(right) < 0 ? left : right;
    BigInteger remainder = BigInteger.ZERO; 

    while ((remainder = first.mod(second)) != BigInteger.ZERO) {
      first = new BigInteger(second.toString());
      second = new BigInteger(remainder.toString());
    }

    return new BigInteger(second.toString());
  }

}
