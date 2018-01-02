package com.vicpin.butcherknife.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by victor on 15/12/17.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindDate {

    /**
     * View id
     */
    int id();

    /**
     * String resource with date format
     */
    int format();

    /**
     * Parametrized string resource
     */
    int template() default 0;

    /**
     *  Visibility when image url is empty, default VISIBLE
     */
    int visibilityIfEmpty() default 0; //Default View.VISIBLE

}
