package net.sf.javanumbers;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>
 * Copyright (C) 2013 Nathan Lane, nathandelane &lt;nathan.david.lane@gmail.com&gt;
 * </p>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see &lt;http://www.gnu.org/licenses/&gt;.
 * </p>
 * <p>
 * This class can be used to work with rational numbers, or fractions, in a non-destructive deterministic manner. It is
 * based on {@link BigInteger} and {@link BigDecimal}.
 * </p>
 * 
 * Internal class used to determine whether two values, a numerator and a denominator, are factors, and to find greatest common factors.
 * 
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
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
