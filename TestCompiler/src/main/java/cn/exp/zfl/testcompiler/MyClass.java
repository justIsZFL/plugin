package cn.exp.zfl.testcompiler;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import cn.zfl.aptlib.Test;
import cn.zfl.aptlib.BindView;

/**
 * AutoService 自动注册
 * SupportedSourceVersion jdk版本
 * SupportedAnnotationTypes 指定处理注解
 *
 * @author zfl
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"cn.zfl.aptlib.Test"})
public class MyClass extends AbstractProcessor {
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("======================APT启动=========================");
        for (TypeElement next : annotations) {
            System.out.println("key1:" + next.getSimpleName());
        }
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Test.class);
        for (Element next : elementsAnnotatedWith) {
            Test annotation = next.getAnnotation(Test.class);
            String path = annotation.path();


            System.out.println("key2:" + next.getSimpleName());
            ClassName logClass = ClassName.get("android.util", "Log");
            ClassName activityClass = ClassName.get("android.app", "Activity");
            //创建参数  Map<String,Class<? extends IRouteGroup>>> routes
            ParameterSpec altlas = ParameterSpec
                    .builder(ClassName.get(next.asType()), "activity")
                    .build();
            //参数名
            MethodSpec.Builder method = MethodSpec.methodBuilder("initLog")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(altlas)
                    .addStatement("$T.e($S,$S+\"Inject\"+$L+\"---\"+$S)", logClass, "zfl", next.getSimpleName(), "activity", path);
            //获取TypeElement的所有成员变量和成员方法
            TypeElement classElement = (TypeElement) next;//获取节点的具体类型
            List<? extends Element> allMembers = elementUtils.getAllMembers(classElement);
            //遍历成员变量
            for (Element member : allMembers) {
                //找到被BYView注解的成员变量
                BindView byView = member.getAnnotation(BindView.class);
                if (byView == null) {
                    continue;
                }
                Set<Modifier> modifiers = member.getModifiers();
                boolean isPrivate = false;
                for (Modifier modifier : modifiers) {
                    isPrivate = Modifier.PRIVATE.equals(modifier);
                }
                if (isPrivate) {
//                    throw new IllegalArgumentException(ClassName.get(next.asType()).toString() + member.getSimpleName() + "注解变量类型必须为public");
                }
                //构建函数体
//                method.addStatement("try{activity.$L = activity.findViewById($L);}catch ($T e){throw new NullPointerException($S);}",
//                        member.getSimpleName(),//注解节点变量的名称
//                        byView.value(),
//                        NullPointerException.class,
//                        ClassName.get(next.asType()).toString() + member.getSimpleName() + "注解变量类型为null");
                method.beginControlFlow("try")
                        .addStatement("activity.$L = activity.findViewById($L)", member.getSimpleName(), byView.value())
                        .nextControlFlow("catch ($T e)", NullPointerException.class)
                        .addStatement("throw new NullPointerException($S)", ClassName.get(next.asType()).toString() +"中的变量"+ member.getSimpleName() + "获取失败或者注解变量类型为null")
                        .endControlFlow();
//                try {
//
//                }catch (NullPointerException e){
//                    throw new NullPointerException("");
//                }
            }

            //创建类
            TypeSpec typeSpec = TypeSpec.classBuilder(next.getSimpleName() + "Inject")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(method.build())
                    .build();
            System.out.println("name:" + typeSpec.name);
            //创建Java文件
            JavaFile file = JavaFile.builder("cn.exp.zfl.aptdemo", typeSpec)
                    .build();
            try {
                file.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("======================APT结束=========================");
        return true;
    }

}
