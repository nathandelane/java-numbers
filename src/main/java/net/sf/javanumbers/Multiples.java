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
 * Internal class used to find the least common multiples and multiples of two values.
 *
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
abstract class Multiples {

  /**
   * Finds least common multiple of two values.
   * @param left {@link BigInteger} value
   * @param right {@link BigInteger} value
   * @return {@link BigInteger} value representing the least common multiple
   */
  public static BigInteger leastCommonMultiple(BigInteger left, BigInteger right) {
    return left.multiply(right).divide(Factors.greatestCommonFactor(left, right));
  }
  
}
