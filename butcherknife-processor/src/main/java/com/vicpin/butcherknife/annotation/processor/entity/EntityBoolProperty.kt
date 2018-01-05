package com.vicpin.butcherknife.annotation.processor.entity

import com.vicpin.butcherknife.annotation.BindBool
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityBoolProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName = "CompoundButton"
    override val id : Int
    override val visibilityIfEmpty = 0
    val observeChanges: Boolean
    override var toggleOnViewClick: Boolean = false

    init {
        val annotation = annotatedField.getAnnotation(BindBool::class.java)
        id = annotation.id
        observeChanges = annotation.observeChanges
        toggleOnViewClick = annotation.toggleOnClick
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.BOOL)
    }


    override fun getDataBindingBlock(propertyName: String): String {
        return "$propertyName.setChecked(entity.$getterMethod)"
    }

    override fun getIsEmptyCondition(propertyName: String): String? {
        return null
    }

    override fun getDataObserverBlock(propertyName: String): String? {
        if(!observeChanges) return null

        return "    $propertyName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {\n" +
                "               @Override\n" +
                "               public void onCheckedChanged(CompoundButton c, boolean checked) {\n" +
                "                   entity.$setterMethod(checked);\n" +
                "               }\n" +
                "           })"
    }


}