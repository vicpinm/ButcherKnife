package com.vicpin.butcherknife.annotation.processor.entity

import com.vicpin.butcherknife.annotation.BindText
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityTextProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName = "TextView"
    override val id: Int
    override val visibilityIfEmpty: Int
    val template: Int
    val isHtml: Boolean
    val observeChanges: Boolean

    init {
        val annotation = annotatedField.getAnnotation(BindText::class.java)
        id = annotation.id
        template = annotation.template
        visibilityIfEmpty = annotation.visibilityIfEmpty
        isHtml = annotation.isHtml
        observeChanges = annotation.observeChanges
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.STRING, Type.INT, Type.LONG, Type.FLOAT, Type.DOUBLE)
    }


    override fun getDataBindingBlock(propertyName: String): String {
        val text = if (template > 0) {
            "$propertyName.getResources().getString($template, entity.$getterMethod)"
        } else {
            "String.valueOf(entity.$getterMethod)"
        }

        return if (isHtml) {
            "$propertyName.setText(Html.fromHtml($text))"
        } else {
            "$propertyName.setText($text)"
        }

    }

    override fun getDataObserverBlock(propertyName: String) : String? {
        if(!observeChanges) return null

        val textParsed = when(type) {
            Type.INT -> "parseInteger(text.toString())"
            Type.DOUBLE -> "parseDouble(text.toString())"
            Type.FLOAT -> "parseFloat(text.toString())"
            Type.LONG -> "parseLong(text.toString())"
            else -> "text.toString()"
        }

        return "$propertyName.addTextChangedListener(new SimpleTextWatcher() {\n" +
                "             @Override\n" +
                "             public void onTextChanged(CharSequence text, int i, int i1, int i2) {\n" +
                "                 entity.$setterMethod($textParsed);\n" +
                "             }\n" +
                "         })"
    }


    override fun getIsEmptyCondition(propertyName: String): String {
        if (type == Type.STRING) {
            return "if(TextUtils.isEmpty(entity.$getterMethod)) $propertyName.setVisibility($visibilityIfEmpty)"
        } else {
            return "if(entity.$getterMethod == 0) $propertyName.setVisibility($visibilityIfEmpty)"
        }

    }


}