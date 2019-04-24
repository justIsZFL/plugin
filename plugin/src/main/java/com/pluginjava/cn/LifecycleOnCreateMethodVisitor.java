package com.pluginjava.cn;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class LifecycleOnCreateMethodVisitor extends MethodVisitor {
    private String name;

    public LifecycleOnCreateMethodVisitor(MethodVisitor mv, String name) {
        super(Opcodes.ASM4, mv);
        this.name = name;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        System.out.println("OnCreate: do  visitMethodInsn===opcode:"+ opcode+"==owner:"+owner+"====name:"+name+"====desc:"+desc+"======itf:"+itf);
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if ("setContentView".equals(name)){
            mv.visitVarInsn(Opcodes.ALOAD, 0);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, this.name+"Inject", "initLog", "(Ljava/lang/String;)V", false);
            System.out.println(this.name+"Inject");
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, this.name+"Inject", "initLog", "(L"+this.name+";)V", false);
        }

    }

    @Override
    public void visitCode() {
//        System.out.println("LifecycleClassVisitor : visitMethod : OnCreate" );
//        mv.visitLdcInsn("TAG");
//        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
//        mv.visitInsn(Opcodes.DUP);
//        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//        mv.visitLdcInsn("-------> onCreate : ");
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitVarInsn(Opcodes.ALOAD, 0);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//        mv.visitInsn(Opcodes.POP);
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
//        System.out.println("do : onCreate : start");
//        mv.visitLdcInsn("zfl");
//        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
//        mv.visitInsn(Opcodes.DUP);
//        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//        mv.visitLdcInsn("onCreate: ");
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitVarInsn(Opcodes.ALOAD, 0);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//        mv.visitInsn(Opcodes.POP);
//        System.out.println("do : onCreate : end");


//            mv.visitVarInsn(Opcodes.ALOAD, 0);
//            mv.visitLdcInsn(new Integer(16908290));
//            System.out.println("this Name :" + name);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, name, "findViewById", "(I)Landroid/view/View;", false);
//            mv.visitTypeInsn(Opcodes.CHECKCAST, "android/view/ViewGroup");
//            mv.visitVarInsn(Opcodes.ASTORE, 2);
//
//            mv.visitVarInsn(Opcodes.ALOAD, 2);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/view/ViewGroup", "removeAllViews", "()V", false);
//
//            mv.visitTypeInsn(Opcodes.NEW, "android/widget/TextView");
//            mv.visitInsn(Opcodes.DUP);
//            mv.visitVarInsn(Opcodes.ALOAD, 0);
//            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
//            mv.visitVarInsn(Opcodes.ASTORE, 3);
//
//
//            mv.visitVarInsn(Opcodes.ALOAD, 3);
//            mv.visitLdcInsn("\u5566\u5566\u5566\uff0c\u5c0f\u9f99\u9f99\uff0c\u4f60\u597d\u554a");
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
//
//            mv.visitVarInsn(Opcodes.ALOAD, 2);
//            mv.visitVarInsn(Opcodes.ALOAD, 3);
//            mv.visitInsn(Opcodes.ICONST_M1);
//            mv.visitInsn(Opcodes.ICONST_M1);
//            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "android/view/ViewGroup", "addView", "(Landroid/view/View;II)V", false);
        }
        super.visitInsn(opcode);
    }
}
