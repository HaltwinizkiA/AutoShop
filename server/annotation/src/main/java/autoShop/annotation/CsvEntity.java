package autoShop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvEntity {
String  fileName()default "AutoShop.csv" ;
String separator() default ";";
}
