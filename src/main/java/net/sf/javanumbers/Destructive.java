package net.sf.javanumbers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to annotate methods that are destructive in nature, meaning they diverge 
 * from 
 * @author nathanlane
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Destructive {
  
  String value() default "";

}
