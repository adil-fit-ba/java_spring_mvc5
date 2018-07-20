package ba.fit.java.spring.mvc.helper.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAutorizationAttribute {
    boolean isNastavnik();
    boolean isUcenik();

}
