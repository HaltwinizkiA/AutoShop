package autoShop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
    Property property() default Property.SimpleProperty;

    int colomnNuber() default 0;

    String keyField() default "id";

}
