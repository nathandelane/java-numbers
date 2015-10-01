package net.sf.javanumbers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to annotate methods that are destructive in nature, meaning they diverge 
 * from the whole point of this library which is to maintain value consistency of rational numbers.
 * For example 1/3 is the infinitely repeating number 0.33333... Methods marked with this might 
 * truncate inifinitely repeating values, resulting in a number that is not reusable as a {@link Rational}.
 * 
 * @author nathandelane &lt;nathan.david.lane@gmail.com&gt;
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Destructive {
  
  String value() default "";

}
