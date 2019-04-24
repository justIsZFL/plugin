package com.pluginjava.cn;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import kotlin.reflect.jvm.internal.impl.serialization.ProtoBuf;

/**
 * @author zfl
 */
public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;
    private boolean needInject = false;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("LifecycleClassVisitor : visitAnnotation -----> started :"+("Lcn/zfl/aptlib/Test;".equals(desc)) + desc);
        if ("Lcn/zfl/aptlib/Test;".equals(desc)){
            needInject = true;
            System.out.println("LifecycleClassVisitor : visitAnnotation -----> needInject :");
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        System.out.println("LifecycleClassVisitor : visit -----> started :" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //匹配 使用注解的class 并且属于activity
        System.out.println("LifecycleClassVisitor : visitMethod : " + needInject);
        if (needInject) {
            if ("onCreate".equals(name) ) {
                //处理onCreate
                System.out.println("do : onCreate : " + mClassName);
                return new LifecycleOnCreateMethodVisitor(mv,mClassName);
            } else if ("onDestroy".equals(name)) {
                //处理onDestroy
                System.out.println("do : onDestroy : " + mClassName);
                return new LifecycleOnDestroyMethodVisitor(mv,mClassName);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}