package com.vicpin.butcherknife.annotation.processor.entity

import com.vicpin.butcherknife.annotation.BindDate
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityDateProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName = "TextView"
    override val id: Int
    override val visibilityIfEmpty: Int

    val formatRes: Int
    val template : Int

    init {
        val annotation = annotatedField.getAnnotation(BindDate::class.java)
        id = annotation.id
        formatRes = annotation.format
        template = annotation.template
        visibilityIfEmpty = annotation.visibilityIfEmpty
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.LONG, Type.DATE)
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