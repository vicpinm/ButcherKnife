package com.vicpin.butcherknife.annotation.processor;

import com.vicpin.butcherknife.annotation.processor.model.Model;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@SupportedAnnotationTypes({
        "com.vicpin.butcherknife.annotation.BindDate",
        "com.vicpin.butcherknife.annotation.BindText",
        "com.vicpin.butcherknife.annotation.BindImage",
})
public class ButcherKnifeProcessor extends AbstractProcessor {

    Elements utils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        utils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        EnvironmentUtil.init(processingEnv, utils);
        Model model = buildModel(roundEnv);

        for (Model.EntityModel entity : model.getEntities()) {
            createBindingClassFor(entity);
        }

        return true;
    }

    private void createBindingClassFor(Model.EntityModel entity) {

        BindingWritter writter = new BindingWritter(entity);
        writter.generateFile(processingEnv);

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Model buildModel(RoundEnvironment env) {
        return Model.Companion.buildFrom(env, getSupportedAnnotationTypes());
    }
}
