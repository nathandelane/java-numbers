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
 * Factory class for consistently creating new {@link BigDecimal} objects.
 *
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
public abstract class BigDecimalFactory {
  
  public static BigDecimal create(String value) {
    return new BigDecimal(value);
  }
  
  public static BigDecimal create(BigInteger value) {
    return new BigDecimal(value);
  }
  
  public static BigDecimal create(double value) {
    return BigDecimal.valueOf(value);
  }
  
  public static BigDecimal create(long value) {
    return BigDecimal.valueOf(value);
  }
  
  public static BigDecimal create(float value) {
    return new BigDecimal(value);
  }
  
  public static BigDecimal create(int value) {
    return new BigDecimal(value);
  }
  
  public static BigDecimal create(short value) {
    return new BigDecimal(value);
  }
  
  public static BigDecimal create(byte value) {
    return new BigDecimal(value);
  }
  
}
