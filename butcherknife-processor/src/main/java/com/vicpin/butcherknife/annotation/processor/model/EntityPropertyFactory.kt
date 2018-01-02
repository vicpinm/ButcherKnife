package com.vicpin.butcherknife.annotation.processor.model

import com.vicpin.butcherknife.annotation.BindDate
import com.vicpin.butcherknife.annotation.BindImage
import com.vicpin.butcherknife.annotation.BindText
import com.vicpin.butcherknife.annotation.processor.EnvironmentUtil
import javax.lang.model.element.Element

/**
 * Created by victor on 16/12/17.
 */
class EntityPropertyFactory {
    companion object {
        fun createProperty(annotatedField : Element, annotation : Class<Annotation>) : EntityProperty {
            val property = when(annotation.name) {
                BindText::class.java.name -> {
                    EntityTextProperty(annotatedField)
                }
                BindDate::class.java.name -> {
                    EntityDateProperty(annotatedField)
                }
                BindImage::class.java.name -> {
                    EntityImageProperty(annotatedField)
                }
                else -> {
                    EnvironmentUtil.logError("${annotation.name} is not a supported annotation type")
                    EntityTextProperty(annotatedField)

                }
            }

            property.validate()
            return property
        }
    }

}