package net.sf.javanumbers.java;

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
 * <p>
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * </p>
 * <p>
 * This class can be used to work with rational numbers, or fractions, in a non-destructive deterministic manner. It is
 * based on {@link BigInteger} and {@link BigDecimal}.
 * </p>
 *
 * Factory class for consistently creating new {@link BigInteger} objects.
 * 
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
public class BigIntegerFactory {

  public static BigInteger create(String value) {
    return new BigInteger(value);
  }

  public static BigInteger create(BigInteger value) {
    return new BigInteger(value.toString());
  }

  public static BigInteger create(long value) {
    return BigInteger.valueOf(value);
  }

  public static BigInteger create(int value) {
    return BigInteger.valueOf(value);
  }

  public static BigInteger create(short value) {
    return BigInteger.valueOf(value);
  }

  public static BigInteger create(byte value) {
    return BigInteger.valueOf(value);
  }

}
