package palisades.lakes.elements.java.sets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class IntersectsAsm implements Opcodes {

  //--------------------------------------------------------------
  private static final void doubleDouble (final ClassWriter cw) {
    
    final MethodVisitor mv =
      cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
        "intersects",
        "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/DoubleInterval;)Z",
        null,null);
    mv.visitCode();
    final Label l0 = new Label();
    mv.visitLabel(l0);
    mv.visitLineNumber(25,l0);
    mv.visitVarInsn(ALOAD,0);
    mv.visitFieldInsn(GETFIELD,
      "mthcmp/java/numbers/DoubleInterval","max","D");
    mv.visitVarInsn(ALOAD,1);
    mv.visitFieldInsn(GETFIELD,
      "mthcmp/java/numbers/DoubleInterval","min","D");
    mv.visitInsn(DCMPG);
    final Label l1 = new Label();
    mv.visitJumpInsn(IFGT,l1);
    mv.visitInsn(ICONST_0);
    mv.visitInsn(IRETURN);
    mv.visitLabel(l1);
    mv.visitLineNumber(26,l1);
    mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
    mv.visitVarInsn(ALOAD,1);
    mv.visitFieldInsn(GETFIELD,
      "mthcmp/java/numbers/DoubleInterval","max","D");
    mv.visitVarInsn(ALOAD,0);
    mv.visitFieldInsn(GETFIELD,
      "mthcmp/java/numbers/DoubleInterval","min","D");
    mv.visitInsn(DCMPG);
    final Label l2 = new Label();
    mv.visitJumpInsn(IFGT,l2);
    mv.visitInsn(ICONST_0);
    mv.visitInsn(IRETURN);
    mv.visitLabel(l2);
    mv.visitLineNumber(27,l2);
    mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
    mv.visitInsn(ICONST_1);
    mv.visitInsn(IRETURN);
    final Label l3 = new Label();
    mv.visitLabel(l3);
    mv.visitLocalVariable("s0",
      "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,0);
    mv.visitLocalVariable("s1",
      "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,1);
    mv.visitMaxs(4,2);
    mv.visitEnd();
  }
  //--------------------------------------------------------------
  
  public static final byte[] dump () {

    final ClassWriter cw = new ClassWriter(
      ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    
    
    cw.visit(52,ACC_PUBLIC + ACC_FINAL + ACC_SUPER,
      "mthcmp/java/sets/IntersectsBytes",
      null,
      "clojure/lang/AFn",
      null);

    cw.visitSource("IntersectsAsm.java",null);

    doubleDouble(cw);
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/IntegerInterval;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(31,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      mv.visitInsn(I2D);
      mv.visitInsn(DCMPG);
      final Label l1 = new Label();
      mv.visitJumpInsn(IFGT,l1);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(32,l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitInsn(I2D);
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l2 = new Label();
      mv.visitJumpInsn(IFGT,l2);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l2);
      mv.visitLineNumber(33,l2);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLocalVariable("s0",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,0);
      mv.visitLocalVariable("s1",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l3,1);
      mv.visitMaxs(4,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/DoubleInterval;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(37,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitInsn(I2D);
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l1 = new Label();
      mv.visitJumpInsn(IFGT,l1);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(38,l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      mv.visitInsn(I2D);
      mv.visitInsn(DCMPG);
      final Label l2 = new Label();
      mv.visitJumpInsn(IFGT,l2);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l2);
      mv.visitLineNumber(39,l2);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLocalVariable("s0",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l3,0);
      mv.visitLocalVariable("s1",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,1);
      mv.visitMaxs(4,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/IntegerInterval;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(43,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      final Label l1 = new Label();
      mv.visitJumpInsn(IF_ICMPGT,l1);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(44,l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitVarInsn(ALOAD,0);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      final Label l2 = new Label();
      mv.visitJumpInsn(IF_ICMPGT,l2);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l2);
      mv.visitLineNumber(45,l2);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLocalVariable("s0",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l3,0);
      mv.visitLocalVariable("s1",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l3,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Lmthcmp/java/numbers/DoubleInterval;Ljava/util/Set;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(49,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/DoubleInterval","intersects",
        "(Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("s0",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l1,0);
      mv.visitLocalVariable("s1","Ljava/util/Set;",null,l0,l1,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Lmthcmp/java/numbers/IntegerInterval;Ljava/util/Set;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(53,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/IntegerInterval","intersects",
        "(Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("s0",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l1,0);
      mv.visitLocalVariable("s1","Ljava/util/Set;",null,l0,l1,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Ljava/util/Set;Lmthcmp/java/numbers/DoubleInterval;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(57,l0);
      mv.visitVarInsn(ALOAD,1);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/DoubleInterval","intersects",
        "(Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("s0","Ljava/util/Set;",null,l0,l1,0);
      mv.visitLocalVariable("s1",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l1,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects",
          "(Ljava/util/Set;Lmthcmp/java/numbers/IntegerInterval;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(61,l0);
      mv.visitVarInsn(ALOAD,1);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/IntegerInterval","intersects",
        "(Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("s0","Ljava/util/Set;",null,l0,l1,0);
      mv.visitLocalVariable("s1",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l0,l1,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects","(Ljava/util/Set;Ljava/util/Set;)Z",null,
          null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(65,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKESTATIC,"java/util/Collections",
        "disjoint",
        "(Ljava/util/Collection;Ljava/util/Collection;)Z",false);
      final Label l1 = new Label();
      mv.visitJumpInsn(IFEQ,l1);
      mv.visitInsn(ICONST_0);
      final Label l2 = new Label();
      mv.visitJumpInsn(GOTO,l2);
      mv.visitLabel(l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitLabel(l2);
      mv.visitFrame(Opcodes.F_SAME1,0,null,1,
        new Object[] { Opcodes.INTEGER });
      mv.visitInsn(IRETURN);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLocalVariable("s0","Ljava/util/Set;",null,l0,l3,0);
      mv.visitLocalVariable("s1","Ljava/util/Set;",null,l0,l3,1);
      mv.visitMaxs(2,2);
      mv.visitEnd();
    }
    {
      final MethodVisitor mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "intersects","(Ljava/lang/Object;Ljava/lang/Object;)Z",
          null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(69,l0);
      mv.visitTypeInsn(NEW,
        "java/lang/UnsupportedOperationException");
      mv.visitInsn(DUP);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLineNumber(70,l1);
      mv.visitTypeInsn(NEW,"java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn(
        "don't know how to test for intersections of ");
      mv.visitMethodInsn(INVOKESPECIAL,"java/lang/StringBuilder",
        "<init>","(Ljava/lang/String;)V",false);
      final Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(71,l2);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLineNumber(72,l3);
      mv.visitLdcInsn(" and ");
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLineNumber(73,l4);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l5 = new Label();
      mv.visitLabel(l5);
      mv.visitLineNumber(70,l5);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "toString","()Ljava/lang/String;",false);
      final Label l6 = new Label();
      mv.visitLabel(l6);
      mv.visitLineNumber(69,l6);
      mv.visitMethodInsn(INVOKESPECIAL,
        "java/lang/UnsupportedOperationException","<init>",
        "(Ljava/lang/String;)V",false);
      mv.visitInsn(ATHROW);
      final Label l7 = new Label();
      mv.visitLabel(l7);
      mv.visitLocalVariable("s0","Ljava/lang/Object;",null,l0,l7,
        0);
      mv.visitLocalVariable("s1","Ljava/lang/Object;",null,l0,l7,
        1);
      mv.visitMaxs(5,2);
      mv.visitEnd();
    }
    
    {
      final MethodVisitor mv = 
        cw.visitMethod(
          ACC_PUBLIC + ACC_FINAL, 
          "invoke", 
          "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 
          null, 
          null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(135, l0);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/DoubleInterval");
      Label l1 = new Label();
      mv.visitJumpInsn(IFEQ, l1);
      Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(136, l2);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/DoubleInterval");
      Label l3 = new Label();
      mv.visitJumpInsn(IFEQ, l3);
      Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLineNumber(138, l4);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      Label l5 = new Label();
      mv.visitLabel(l5);
      mv.visitLineNumber(137, l5);
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/DoubleInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l3);
      mv.visitLineNumber(139, l3);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/IntegerInterval");
      Label l6 = new Label();
      mv.visitJumpInsn(IFEQ, l6);
      Label l7 = new Label();
      mv.visitLabel(l7);
      mv.visitLineNumber(141, l7);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      Label l8 = new Label();
      mv.visitLabel(l8);
      mv.visitLineNumber(140, l8);
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/IntegerInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l6);
      mv.visitLineNumber(142, l6);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "java/util/Set");
      Label l9 = new Label();
      mv.visitJumpInsn(IFEQ, l9);
      Label l10 = new Label();
      mv.visitLabel(l10);
      mv.visitLineNumber(143, l10);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/DoubleInterval;Ljava/util/Set;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l9);
      mv.visitLineNumber(144, l9);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitMethodInsn(INVOKEVIRTUAL, "mthcmp/java/numbers/DoubleInterval", "intersects", "(Ljava/lang/Object;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(146, l1);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/IntegerInterval");
      Label l11 = new Label();
      mv.visitJumpInsn(IFEQ, l11);
      Label l12 = new Label();
      mv.visitLabel(l12);
      mv.visitLineNumber(147, l12);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/IntegerInterval");
      Label l13 = new Label();
      mv.visitJumpInsn(IFEQ, l13);
      Label l14 = new Label();
      mv.visitLabel(l14);
      mv.visitLineNumber(149, l14);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      Label l15 = new Label();
      mv.visitLabel(l15);
      mv.visitLineNumber(148, l15);
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/IntegerInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l13);
      mv.visitLineNumber(150, l13);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/DoubleInterval");
      Label l16 = new Label();
      mv.visitJumpInsn(IFEQ, l16);
      Label l17 = new Label();
      mv.visitLabel(l17);
      mv.visitLineNumber(152, l17);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      Label l18 = new Label();
      mv.visitLabel(l18);
      mv.visitLineNumber(151, l18);
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/DoubleInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l16);
      mv.visitLineNumber(153, l16);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "java/util/Set");
      Label l19 = new Label();
      mv.visitJumpInsn(IFEQ, l19);
      Label l20 = new Label();
      mv.visitLabel(l20);
      mv.visitLineNumber(154, l20);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Lmthcmp/java/numbers/IntegerInterval;Ljava/util/Set;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l19);
      mv.visitLineNumber(155, l19);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitMethodInsn(INVOKEVIRTUAL, "mthcmp/java/numbers/IntegerInterval", "intersects", "(Ljava/lang/Object;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l11);
      mv.visitLineNumber(157, l11);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(INSTANCEOF, "java/util/Set");
      Label l21 = new Label();
      mv.visitJumpInsn(IFEQ, l21);
      Label l22 = new Label();
      mv.visitLabel(l22);
      mv.visitLineNumber(158, l22);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/DoubleInterval");
      Label l23 = new Label();
      mv.visitJumpInsn(IFEQ, l23);
      Label l24 = new Label();
      mv.visitLabel(l24);
      mv.visitLineNumber(159, l24);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/DoubleInterval");
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Ljava/util/Set;Lmthcmp/java/numbers/DoubleInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l23);
      mv.visitLineNumber(160, l23);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "mthcmp/java/numbers/IntegerInterval");
      Label l25 = new Label();
      mv.visitJumpInsn(IFEQ, l25);
      Label l26 = new Label();
      mv.visitLabel(l26);
      mv.visitLineNumber(161, l26);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "mthcmp/java/numbers/IntegerInterval");
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Ljava/util/Set;Lmthcmp/java/numbers/IntegerInterval;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l25);
      mv.visitLineNumber(162, l25);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(INSTANCEOF, "java/util/Set");
      mv.visitJumpInsn(IFEQ, l21);
      Label l27 = new Label();
      mv.visitLabel(l27);
      mv.visitLineNumber(163, l27);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitVarInsn(ALOAD, 2);
      mv.visitTypeInsn(CHECKCAST, "java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC, "mthcmp/java/sets/Intersects", "intersects", "(Ljava/util/Set;Ljava/util/Set;)Z", false);
      mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
      mv.visitInsn(ARETURN);
      mv.visitLabel(l21);
      mv.visitLineNumber(165, l21);
      mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
      mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
      mv.visitInsn(DUP);
      Label l28 = new Label();
      mv.visitLabel(l28);
      mv.visitLineNumber(166, l28);
      mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("Can't test for interesection of ");
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
      Label l29 = new Label();
      mv.visitLabel(l29);
      mv.visitLineNumber(167, l29);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l30 = new Label();
      mv.visitLabel(l30);
      mv.visitLineNumber(168, l30);
      mv.visitLdcInsn(" and ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l31 = new Label();
      mv.visitLabel(l31);
      mv.visitLineNumber(169, l31);
      mv.visitVarInsn(ALOAD, 2);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l32 = new Label();
      mv.visitLabel(l32);
      mv.visitLineNumber(166, l32);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
      Label l33 = new Label();
      mv.visitLabel(l33);
      mv.visitLineNumber(165, l33);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V", false);
      mv.visitInsn(ATHROW);
      Label l34 = new Label();
      mv.visitLabel(l34);
      mv.visitLocalVariable("this", "Lmthcmp/java/sets/Intersects;", null, l0, l34, 0);
      mv.visitLocalVariable("s0", "Ljava/lang/Object;", null, l0, l34, 1);
      mv.visitLocalVariable("s1", "Ljava/lang/Object;", null, l0, l34, 2);
      mv.visitMaxs(5, 3);
      mv.visitEnd();
      }

    {
      final MethodVisitor mv = 
        cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(178, l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "clojure/lang/AFn", "<init>", "()V", false);
      mv.visitInsn(RETURN);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("this", "Lmthcmp/java/sets/IntersectsBytes;", null, l0, l1, 0);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
      }

    cw.visitEnd();

    return cw.toByteArray();
  }
  //--------------------------------------------------------------

//  public static final Class load () {
//    try {
//      final ClassLoader cl = ClassLoader.getSystemClassLoader();
//      assert (cl instanceof ClassLoader);
//      final Method m = 
//        ClassLoader.class
//        .getDeclaredMethod(
//        "defineClass", 
//        String.class, 
//        byte[].class, 
//        int.class, 
//        int.class);
//      assert (null != m);
//      m.setAccessible(true);
//      
//      final byte[] bytes = dump();
//      
//      return (Class) m.invoke(
//        cl, 
//        "mthcmp.java.sets.IntersectsBytes", 
//        bytes, 
//        Integer.valueOf(0), 
//        Integer.valueOf(bytes.length));
//    }
//    catch (NoSuchMethodException | 
//      SecurityException | 
//      IllegalAccessException | 
//      IllegalArgumentException | 
//      InvocationTargetException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//      throw new RuntimeException(e); } }


  public static final Class load () {
    try {
      final ClassLoader cl = ClassLoader.getSystemClassLoader();
      //assert (cl instanceof ClassLoader);
      final Method m = 
        ClassLoader.class
        .getDeclaredMethod(
        "defineClass", 
        String.class, 
        byte[].class, 
        int.class, 
        int.class);
      assert (null != m);
      m.setAccessible(true);
      
      final byte[] bytes = dump();
      
      return (Class) m.invoke(
        cl, 
        "mthcmp.java.sets.IntersectsBytes", 
        bytes, 
        Integer.valueOf(0), 
        Integer.valueOf(bytes.length));
    }
    catch (NoSuchMethodException | 
      SecurityException | 
      IllegalAccessException | 
      IllegalArgumentException | 
      InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new RuntimeException(e); } }

  //--------------------------------------------------------------
}
//--------------------------------------------------------------
