package com.vicpin.butcherknife.annotation.processor.model

import com.vicpin.butcherknife.annotation.BindBool
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityBoolProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName : String
    override val id : Int
    override val visibilityIfEmpty = 0

    init {
        widgetClassName = "CompoundButton"
        id = annotatedField.getAnnotation(BindBool::class.java).id
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.BOOL)
    }

    val getterMethod: String
        get() = if (name.length > 1) {
            "get${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}()"
        } else {
            "get${name.substring(0, 1).toUpperCase()}()"
        }



    override fun getDataBindingBlock(propertyName: String): String {
        return "$propertyName.setChecked(entity.$getterMethod)"
    }

    override fun getIsEmptyCondition(propertyName: String): String? {
        return null


    }


}