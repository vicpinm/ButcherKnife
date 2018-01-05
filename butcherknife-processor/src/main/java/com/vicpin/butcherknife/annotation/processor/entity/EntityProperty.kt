package com.vicpin.butcherknife.annotation.processor.entity

import com.vicpin.butcherknife.annotation.processor.EnvironmentUtil
import java.util.*
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

abstract class EntityProperty(annotatedField: Element) {

    enum class Type { INT, LONG, FLOAT, DOUBLE, DATE, STRING, BOOL, OTHER }

    abstract val widgetClassName: String
    val name: String
    val type: Type
    abstract val id: Int
    abstract val visibilityIfEmpty: Int
    open var toggleOnViewClick = false

    abstract fun getSupportedTypes(): List<Type>

    abstract fun getDataBindingBlock(propertyName: String): String

    open fun getDataObserverBlock(propertyName: String): String? = null

    abstract fun getIsEmptyCondition(propertyName: String): String?

    init {
        name = annotatedField.toString()
        val typeString = annotatedField.asType().toString()
        type = when (typeString) {
            Long::class.java.name -> Type.LONG
            "long" -> Type.LONG
            Integer::class.java.name -> Type.INT
            "int" -> Type.INT
            Float::class.java.name -> Type.FLOAT
            "float" -> Type.FLOAT
            Double::class.java.name -> Type.DOUBLE
            "double" -> Type.DOUBLE
            Date::class.java.name -> Type.DATE
            String::class.java.name -> Type.STRING
            Boolean::class.java.name -> Type.BOOL
            else -> Type.OTHER
        }

    }

    fun validate() {
        if (id == 0) {
            EnvironmentUtil.logError("Error processing field $name: id value is 0")
        }

        if(!getSupportedTypes().contains(type)) {
            EnvironmentUtil.logError("Error processing field $name: type $type is not valid (supported types: ${getSupportedTypes().joinToString()})")

        }
    }

    val setterMethod: String
        get() =  if (name.length > 1) {
            "set${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}"
        } else {
            "set${name.substring(0, 1).toUpperCase()}"
        }


    val getterMethod: String
        get() = if (name.length > 1) {
            "get${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}()"
        } else {
            "get${name.substring(0, 1).toUpperCase()}()"
        }




}
