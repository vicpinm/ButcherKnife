package com.vicpin.butcherknife.annotation.processor.model

import com.vicpin.butcherknife.annotation.BindImage
import com.vicpin.butcherknife.annotation.CornerType
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityImageProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName: String
    override val id: Int
    override val visibilityIfEmpty: Int

    val cornerMargin : Int
    val cornerRadiusRes: Int
    val cornerType: CornerType
    val cropped: Boolean
    val isCircle: Boolean
    val isFile: Boolean
    val placeholderRes: Int


    init {
        widgetClassName = "ImageView"
        id = annotatedField.getAnnotation(BindImage::class.java).id
        cornerMargin = annotatedField.getAnnotation(BindImage::class.java).cornerMargin
        cornerRadiusRes = annotatedField.getAnnotation(BindImage::class.java).cornerRadius
        cornerType = annotatedField.getAnnotation(BindImage::class.java).cornerType
        cropped = annotatedField.getAnnotation(BindImage::class.java).cropped
        isCircle = annotatedField.getAnnotation(BindImage::class.java).isCircle
        isFile = annotatedField.getAnnotation(BindImage::class.java).isFile
        placeholderRes = annotatedField.getAnnotation(BindImage::class.java).placeholder
        visibilityIfEmpty = annotatedField.getAnnotation(BindImage::class.java).visibilityIfEmpty
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.STRING)
    }

    val getterMethod: String
        get() = if (name.length > 1) {
            "get${name.substring(0, 1).toUpperCase()}${name.substring(1, name.length)}()"
        } else {
            "get${name.substring(0, 1).toUpperCase()}()"
        }


    override fun getDataBindingBlock(propertyName: String): String {
        return "loadWithPicasso($propertyName, entity.$getterMethod, $placeholderRes, $cropped, $isFile, $isCircle, $cornerType, $cornerRadiusRes, $cornerMargin)"
    }

    override fun getIsEmptyCondition(propertyName: String): String {
        return "if(TextUtils.isEmpty(entity.$getterMethod)) $propertyName.setVisibility($visibilityIfEmpty)"
    }



}