package com.vicpin.butcherknife.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindImage {
    /**
     * View id
     */
    int id();

    /**
     * Placeholder to use while image is loading
     */
    int placeholder() default 0;

    /**
     * Crop image, default true
     */
    boolean cropped() default true;

    /**
     * True if image resource is located in a file, default false
     */
    boolean isFile() default false;

    /**
     * True if image should be transform into a circle, default false
     */
    boolean isCircle() default false;

    /**
     * Corner to be rounded if cornerRadios is set, default ALL
     */
    CornerType cornerType() default CornerType.ALL;

    /**
     * Dimension resource used for corners rounding, default 0
     */
    int cornerRadius() default 0;

    /**
     * Dimension resource used for image margin, default 0
     */
    int cornerMargin() default 0;

    /**
     *  Visibility when image url is empty, default VISIBLE
     */
    int visibilityIfEmpty() default 0; //Default View.VISIBLE, usually image shows placeholder if empty

}
