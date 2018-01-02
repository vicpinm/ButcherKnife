package com.vicpin.butcherknife.annotation.processor.model

import com.vicpin.butcherknife.annotation.BindDate
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityDateProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName: String
    override val id: Int
    override val visibilityIfEmpty: Int

    val formatRes: Int
    val template : Int

    init {
        widgetClassName = "TextView"
        id = annotatedField.getAnnotation(BindDate::class.java).id
        formatRes = annotatedField.getAnnotation(BindDate::class.java).format
        template = annotatedField.getAnnotation(BindDate::class.java).template
        visibilityIfEmpty = annotatedField.getAnnotation(BindDate::class.java).visibilityIfEmpty
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.LONG, Type.DATE)
    }

    val getterMethod: String
        get() = if (name.length > 1) {
            "get${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}()"
        } else {
            "get${name.substring(0, 1).toUpperCase()}()"
        }


    override fun getDataBindingBlock(propertyName: String): String {
        return if(template > 0) {
            "$propertyName.setText($propertyName.getResources().getString($template, formatDate(entity.$getterMethod, $propertyName.getResources().getString($formatRes))))"
        }
        else{
            return "$propertyName.setText(formatDate(entity.$getterMethod, $propertyName.getResources().getString($formatRes)))"
        }
    }

    override fun getIsEmptyCondition(propertyName: String): String {
        return if(type == Type.DATE) {
            "if(entity.$getterMethod == null) $propertyName.setVisibility($visibilityIfEmpty)"
        }
        else {
            "if(entity.$getterMethod == 0) $propertyName.setVisibility($visibilityIfEmpty)"
        }
    }




}