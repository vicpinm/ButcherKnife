package com.vicpin.butcherknife.annotation.processor.entity

import com.vicpin.butcherknife.annotation.BindImage
import com.vicpin.butcherknife.annotation.CornerType
import javax.lang.model.element.Element

/**
 * Created by victor on 15/12/17.
 */

class EntityImageProperty(annotatedField: Element) : EntityProperty(annotatedField) {

    override val widgetClassName = "ImageView"
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
        val annotation = annotatedField.getAnnotation(BindImage::class.java)
        id = annotation.id
        cornerMargin = annotation.cornerMargin
        cornerRadiusRes = annotation.cornerRadius
        cornerType = annotation.cornerType
        cropped = annotation.cropped
        isCircle = annotation.isCircle
        isFile = annotation.isFile
        placeholderRes = annotation.placeholder
        visibilityIfEmpty = annotation.visibilityIfEmpty
    }

    override fun getSupportedTypes(): List<Type> {
        return listOf(Type.STRING)
    }


    override fun getDataBindingBlock(propertyName: String): String {
        return "loadWithPicasso($propertyName, entity.$getterMethod, $placeholderRes, $cropped, $isFile, $isCircle, $cornerType, $cornerRadiusRes, $cornerMargin)"
    }

    override fun getIsEmptyCondition(propertyName: String): String {
        return "if(TextUtils.isEmpty(entity.$getterMethod)) $propertyName.setVisibility($visibilityIfEmpty)"
    }



}