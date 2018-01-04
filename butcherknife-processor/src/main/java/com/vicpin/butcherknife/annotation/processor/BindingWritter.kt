package com.vicpin.butcherknife.annotation.processor

import com.vicpin.butcherknife.annotation.processor.model.Model
import java.io.IOException
import javax.annotation.processing.ProcessingEnvironment

/**
 * Created by victor on 10/12/17.
 */

class BindingWritter(private val entity: Model.EntityModel) {

    private var text: String = ""
    private val className: String

    init {
        this.className = entity.name + CLASSNAME_SUFIX
    }

    private fun generateClass(): String {

        appendPackpage()
        appendImports()
        appendClassName()
        appendClassProperties()
        appendWithViewMethod()
        appendWithActivityMethod()
        appendBindMethod()
        appendClassEnding()


        return text
    }

    private fun appendPackpage() {
        newLine("package ${entity.pkg}", newLine = true)
    }

    private fun appendImports() {
        newLine("import ${entity.modelClass}")
        newLine("import com.vicpin.butcherknife.Binding")
        newLine("import android.widget.TextView")
        newLine("import android.widget.ImageView")
        newLine("import android.widget.CompoundButton")
        newLine("import java.text.SimpleDateFormat")
        newLine("import java.util.Date")
        newLine("import static com.vicpin.butcherknife.BindingUtils.*")
        newLine("import static com.vicpin.butcherknife.annotation.CornerType.*")
        newLine("import android.text.TextUtils")
        newLine("import android.view.View")
        newLine("import android.text.Html", newLine = true)
    }

    private fun appendClassName() {
        newLine("public class $className implements Binding<${entity.name}> {", newLine = true)
    }

    private fun appendClassProperties() {
        for (property in entity.properties) {
            val propertyName = entity.getPropertyName(property)
            newLine("${property.widgetClassName} $propertyName", level = 1)
        }
        newLine()
    }

    private fun appendWithViewMethod() {
        newLine("public static $className with(android.view.View view) {", level = 1)
        newLine("$className binding = new $className()", level = 2)
        for (property in entity.properties) {
            val propertyName = entity.getPropertyName(property)
            newLine("binding.$propertyName = (${property.widgetClassName}) view.findViewById(${property.id})", level = 2)
        }
        newLine("return binding", level = 2)
        newLine("}", level = 1, newLine = true)
    }

    private fun appendWithActivityMethod() {
        newLine("public static $className with(android.app.Activity act) {", level = 1)
        newLine("return with(act.getWindow().getDecorView())", level = 2)
        newLine("}", level = 1, newLine = true)
    }

    private fun appendBindMethod() {
        newLine("@Override public void bind(${entity.name} entity) {", level = 1)
        for (property in entity.properties) {
            val propertyName = entity.getPropertyName(property)

            newLine("if($propertyName != null) {" , level = 2)

            if(property.getIsEmptyCondition(propertyName) != null) {
                newLine("${property.getIsEmptyCondition(propertyName)}", level = 3)
                newLine("else {", level = 3)
                newLine("$propertyName.setVisibility(View.VISIBLE)", level = 4)
                newLine(property.getDataBindingBlock(propertyName = propertyName), level = 4)
                newLine("}", level = 2)
            }
            else{
                newLine(property.getDataBindingBlock(propertyName = propertyName), level = 4)
            }

            newLine("}", level = 2)
        }
        newLine("}", level = 1, newLine = true)
    }


    private fun appendClassEnding() {
        newLine("}")
    }

    fun generateFile(env: ProcessingEnvironment) {

        try { // write the file
            val source = env.filer.createSourceFile("${entity.pkg}.$className")

            val writer = source.openWriter()
            writer.write(generateClass())
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }

    }

    fun newLine(line: String = "", level: Int = 0, newLine: Boolean = false) {
        var indentation = ""
        var semicolon = if (!line.isEmpty() && !line.endsWith("}") && !line.endsWith("{")) ";" else ""

        (1..level).forEach { indentation += "\t" }

        text += if (newLine) {
            "$indentation$line$semicolon\n\n"
        } else {
            "$indentation$line$semicolon\n"
        }
    }

    companion object {

        private val CLASSNAME_SUFIX = "Binding"
    }
}
