package com.vicpin.butcherknife.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindBool {
    /**
     * View id
     */
    int id();

    /**
     * Sync changes in widget with this field
     */
    boolean observeChanges() default false;

    /**
     * Update check when main view is clicked
     */
    boolean toggleOnClick() default false;

}
