package org.example.anno;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("org.example.anno.IMA")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class IMAProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(IMA.class)) {
            if (e.getKind() == ElementKind.CLASS) {
                generateClass(e);
            }
        }
        return true;
    }

    private void generateClass(Element element) {
        String className = element.getSimpleName() + "Generated";
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);

        generateGettersAndSetters(element, classBuilder);

        generateAllArgsConstructor(element, classBuilder);

        TypeSpec generatedClass = classBuilder.build();

        writeToFile(generatedClass);
    }

    private void generateGettersAndSetters(Element element, TypeSpec.Builder classBuilder) {
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) enclosedElement;
                String fieldName = variableElement.getSimpleName().toString();
                TypeMirror fieldType = variableElement.asType();

                MethodSpec getter = MethodSpec.methodBuilder("get" + capitalize(fieldName))
                        .addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.get(fieldType))
                        .addStatement("return this.$N", fieldName)
                        .build();

                MethodSpec setter = MethodSpec.methodBuilder("set" + capitalize(fieldName))
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(TypeName.get(fieldType), fieldName)
                        .addStatement("this.$N = $N", fieldName, fieldName)
                        .build();

                classBuilder.addMethod(getter);
                classBuilder.addMethod(setter);
            }
        }
    }

    private void generateAllArgsConstructor(Element element, TypeSpec.Builder classBuilder) {
        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC);

        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) enclosedElement;
                String fieldName = variableElement.getSimpleName().toString();
                TypeMirror fieldType = variableElement.asType();

                constructorBuilder.addParameter(TypeName.get(fieldType), fieldName);
                constructorBuilder.addStatement("this.$N = $N", fieldName, fieldName);
            }
        }

        classBuilder.addMethod(constructorBuilder.build());
    }

    private void writeToFile(TypeSpec generatedClass) {
        try {
            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(generatedClass.name);
            try (Writer writer = javaFileObject.openWriter()) {
                writer.write(generatedClass.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
