package com.vicpin.butcherknife.annotation.processor.model

import com.vicpin.butcherknife.annotation.BindText
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityTextProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName : String
    override val id : Int
    override val visibilityIfEmpty: Int
    val template : Int
    val isHtml : Boolean

    init {
        widgetClassName = "TextView"
        id = annotatedField.getAnnotation(BindText::class.java).id
        template = annotatedField.getAnnotation(BindText::class.java).template
        visibilityIfEmpty = annotatedField.getAnnotation(BindText::class.java).visibilityIfEmpty
        isHtml = annotatedField.getAnnotation(BindText::class.java).isHtml
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.STRING, Type.INT, Type.LONG, Type.FLOAT)
    }

    val getterMethod: String
        get() = if (name.length > 1) {
            "get${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}()"
        } else {
            "get${name.substring(0, 1).toUpperCase()}()"
        }



    override fun getDataBindingBlock(propertyName: String): String {
        val text = if(template > 0) {
            "$propertyName.getResources().getString($template, entity.$getterMethod)"
        }
        else{
            "String.valueOf(entity.$getterMethod)"
        }

        return if(isHtml) {
            "$propertyName.setText(Html.fromHtml($text))"
        } else {
            "$propertyName.setText($text)"
        }
    }

    override fun getIsEmptyCondition(propertyName: String): String {
        if(type == Type.STRING) {
            return "if(TextUtils.isEmpty(entity.$getterMethod)) $propertyName.setVisibility($visibilityIfEmpty)"
        }
        else {
            return "if(entity.$getterMethod == 0) $propertyName.setVisibility($visibilityIfEmpty)"
        }

    }


}