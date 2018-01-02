package com.vicpin.butcherknife.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindText {
    /**
     * View id
     */
    int id();

    /**
     * Parametrized string resource
     */
    int template() default 0;

    /**
     * Visibility when text is empty or number is 0, default VISIBLE
     */
    int visibilityIfEmpty() default 0; //Default View.VISIBLE

    /**
     * Parse html tags with Html.fromHtml
     */
    boolean isHtml() default false;

}
