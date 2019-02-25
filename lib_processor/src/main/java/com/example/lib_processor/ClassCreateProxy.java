package com.example.lib_processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;


import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class ClassCreateProxy {

    private String mBindingClassName;

    private String mPackageName;

    private TypeElement mTypeElement;

    private Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();

    public ClassCreateProxy(Elements elementUtils, TypeElement classElement) {

        this.mTypeElement = classElement;

        PackageElement packageElement = elementUtils.getPackageOf(mTypeElement);

        String packageName = packageElement.getQualifiedName().toString();

        String className = mTypeElement.getSimpleName().toString();

        this.mPackageName = packageName;

        this.mBindingClassName = className + "_ViewBinding";
    }

    //public TypeSpec generateJavaCode() {
    //    TypeSpec bindingClass =
    //        TypeSpec.classBuilder(mBindingClassName).addModifiers(Modifier.PUBLIC).addMethod(generateMethods()).build();
    //    return bindingClass;
    //}

    public void putElement(int id, VariableElement element) {

        mVariableElementMap.put(id, element);
    }

    //private MethodSpec generateMethods() {
    //
    //    ClassName host = ClassName.bestGuess(mTypeElement.getQualifiedName().toString());
    //
    //    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
    //
    //        .addModifiers(Modifier.PUBLIC)
    //
    //        .returns(void.class)
    //
    //        .addParameter(host, "host");
    //
    //    for (int id : mVariableElementMap.keySet()) {
    //
    //        VariableElement element = mVariableElementMap.get(id);
    //
    //        String name = element.getSimpleName().toString();
    //
    //        String type = element.asType().toString();
    //
    //        methodBuilder.addCode(
    //            "host." + name + " = " + "(" + type + ")(((android.app.Activity)host).findViewById( " + id + "));");
    //    }
    //
    //    return methodBuilder.build();
    //}

    public String getPackageName() {
        return mPackageName;
    }

    public String getProxyClassFullName() {
        return mPackageName + "." + mBindingClassName;
    }

    public TypeElement getTypeElement() {
        return mTypeElement;
    }



    /**

     * 创建Java代码

     * @return

     */

    public String generateJavaCode1() {

        StringBuilder builder = new StringBuilder();

        builder.append("package ").append(mPackageName).append(";\n");

        builder.append("import com.example.lib_butterknife.*;\n");

        builder.append('\n');

        builder.append("public class ").append(mBindingClassName);

        builder.append(" {\n");


        generateMethods1(builder);

        builder.append('\n');

        builder.append("}\n");

        return builder.toString();

    }

    /**

     * 加入Method

     * @param builder

     */

    private void generateMethods1(StringBuilder builder) {

        builder.append("public void bind(" + mTypeElement.getQualifiedName() + " host ) {\n");

        for (int id : mVariableElementMap.keySet()) {

            VariableElement element = mVariableElementMap.get(id);

            String name = element.getSimpleName().toString();

            String type = element.asType().toString();

            builder.append("host." + name).append(" = ");

            builder.append("(" + type + ")(((android.app.Activity)host).findViewById( " + id + "));\n");

        }

        builder.append("  }\n");

    }
}
