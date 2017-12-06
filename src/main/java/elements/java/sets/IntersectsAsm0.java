package elements.java.sets;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class IntersectsAsm0 implements Opcodes {

  //--------------------------------------------------------------
//  private static final void doubleDouble (final ClassWriter cw) {
//    
//    final MethodVisitor mv =
//      cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
//        "intersects",
//        "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/DoubleInterval;)Z",
//        null,null);
//    mv.visitCode();
//    final Label l0 = new Label();
//    mv.visitLabel(l0);
//    mv.visitLineNumber(25,l0);
//    mv.visitVarInsn(ALOAD,0);
//    mv.visitFieldInsn(GETFIELD,
//      "mthcmp/java/numbers/DoubleInterval","max","D");
//    mv.visitVarInsn(ALOAD,1);
//    mv.visitFieldInsn(GETFIELD,
//      "mthcmp/java/numbers/DoubleInterval","min","D");
//    mv.visitInsn(DCMPG);
//    final Label l1 = new Label();
//    mv.visitJumpInsn(IFGT,l1);
//    mv.visitInsn(ICONST_0);
//    mv.visitInsn(IRETURN);
//    mv.visitLabel(l1);
//    mv.visitLineNumber(26,l1);
//    mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
//    mv.visitVarInsn(ALOAD,1);
//    mv.visitFieldInsn(GETFIELD,
//      "mthcmp/java/numbers/DoubleInterval","max","D");
//    mv.visitVarInsn(ALOAD,0);
//    mv.visitFieldInsn(GETFIELD,
//      "mthcmp/java/numbers/DoubleInterval","min","D");
//    mv.visitInsn(DCMPG);
//    final Label l2 = new Label();
//    mv.visitJumpInsn(IFGT,l2);
//    mv.visitInsn(ICONST_0);
//    mv.visitInsn(IRETURN);
//    mv.visitLabel(l2);
//    mv.visitLineNumber(27,l2);
//    mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
//    mv.visitInsn(ICONST_1);
//    mv.visitInsn(IRETURN);
//    final Label l3 = new Label();
//    mv.visitLabel(l3);
//    mv.visitLocalVariable("s0",
//      "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,0);
//    mv.visitLocalVariable("s1",
//      "Lmthcmp/java/numbers/DoubleInterval;",null,l0,l3,1);
//    mv.visitMaxs(4,2);
//    mv.visitEnd();
//  }
//  
  public static final byte[] dump ()
    throws Exception {

    final ClassWriter cw = new ClassWriter(
      ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    
    //final FieldVisitor fv;
    MethodVisitor mv;
    //final AnnotationVisitor av0;

    cw.visit(52,ACC_PUBLIC + ACC_FINAL + ACC_SUPER,
      "mthcmp/java/sets/IntersectsAsm",null,"java/lang/Object",
      null);

    cw.visitSource("IntersectsAsm.java",null);

    
    {
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
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
      mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "inline","(Ljava/lang/Object;Ljava/lang/Object;)Z",null,
          null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(82,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l1 = new Label();
      mv.visitJumpInsn(IFEQ,l1);
      final Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(83,l2);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l3 = new Label();
      mv.visitJumpInsn(IFEQ,l3);
      final Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLineNumber(84,l4);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ASTORE,2);
      final Label l5 = new Label();
      mv.visitLabel(l5);
      mv.visitLineNumber(85,l5);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ASTORE,3);
      final Label l6 = new Label();
      mv.visitLabel(l6);
      mv.visitLineNumber(86,l6);
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l7 = new Label();
      mv.visitJumpInsn(IFGT,l7);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l7);
      mv.visitLineNumber(87,l7);
      mv.visitFrame(Opcodes.F_APPEND,2,
        new Object[] { "mthcmp/java/numbers/DoubleInterval",
      "mthcmp/java/numbers/DoubleInterval" },
        0,null);
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l8 = new Label();
      mv.visitJumpInsn(IFGT,l8);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l8);
      mv.visitLineNumber(88,l8);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l3);
      mv.visitLineNumber(89,l3);
      mv.visitFrame(Opcodes.F_CHOP,2,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l9 = new Label();
      mv.visitJumpInsn(IFEQ,l9);
      final Label l10 = new Label();
      mv.visitLabel(l10);
      mv.visitLineNumber(90,l10);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ASTORE,2);
      final Label l11 = new Label();
      mv.visitLabel(l11);
      mv.visitLineNumber(91,l11);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ASTORE,3);
      final Label l12 = new Label();
      mv.visitLabel(l12);
      mv.visitLineNumber(92,l12);
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      mv.visitInsn(I2D);
      mv.visitInsn(DCMPG);
      final Label l13 = new Label();
      mv.visitJumpInsn(IFGT,l13);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l13);
      mv.visitLineNumber(93,l13);
      mv.visitFrame(Opcodes.F_APPEND,2,
        new Object[] { "mthcmp/java/numbers/DoubleInterval",
      "mthcmp/java/numbers/IntegerInterval" },
        0,null);
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitInsn(I2D);
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l14 = new Label();
      mv.visitJumpInsn(IFGT,l14);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l14);
      mv.visitLineNumber(94,l14);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l9);
      mv.visitLineNumber(95,l9);
      mv.visitFrame(Opcodes.F_CHOP,2,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l15 = new Label();
      mv.visitJumpInsn(IFEQ,l15);
      final Label l16 = new Label();
      mv.visitLabel(l16);
      mv.visitLineNumber(96,l16);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/DoubleInterval;Ljava/util/Set;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l15);
      mv.visitLineNumber(97,l15);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/DoubleInterval","intersects",
        "(Ljava/lang/Object;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(99,l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l17 = new Label();
      mv.visitJumpInsn(IFEQ,l17);
      final Label l18 = new Label();
      mv.visitLabel(l18);
      mv.visitLineNumber(100,l18);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l19 = new Label();
      mv.visitJumpInsn(IFEQ,l19);
      final Label l20 = new Label();
      mv.visitLabel(l20);
      mv.visitLineNumber(101,l20);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ASTORE,2);
      final Label l21 = new Label();
      mv.visitLabel(l21);
      mv.visitLineNumber(102,l21);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ASTORE,3);
      final Label l22 = new Label();
      mv.visitLabel(l22);
      mv.visitLineNumber(103,l22);
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      final Label l23 = new Label();
      mv.visitJumpInsn(IF_ICMPGT,l23);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l23);
      mv.visitLineNumber(104,l23);
      mv.visitFrame(Opcodes.F_APPEND,2,
        new Object[] { "mthcmp/java/numbers/IntegerInterval",
      "mthcmp/java/numbers/IntegerInterval" },
        0,null);
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      final Label l24 = new Label();
      mv.visitJumpInsn(IF_ICMPGT,l24);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l24);
      mv.visitLineNumber(105,l24);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l19);
      mv.visitLineNumber(106,l19);
      mv.visitFrame(Opcodes.F_CHOP,2,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l25 = new Label();
      mv.visitJumpInsn(IFEQ,l25);
      final Label l26 = new Label();
      mv.visitLabel(l26);
      mv.visitLineNumber(107,l26);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ASTORE,2);
      final Label l27 = new Label();
      mv.visitLabel(l27);
      mv.visitLineNumber(108,l27);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ASTORE,3);
      final Label l28 = new Label();
      mv.visitLabel(l28);
      mv.visitLineNumber(109,l28);
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","max","I");
      mv.visitInsn(I2D);
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","min","D");
      mv.visitInsn(DCMPG);
      final Label l29 = new Label();
      mv.visitJumpInsn(IFGT,l29);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l29);
      mv.visitLineNumber(110,l29);
      mv.visitFrame(Opcodes.F_APPEND,2,
        new Object[] { "mthcmp/java/numbers/IntegerInterval",
      "mthcmp/java/numbers/DoubleInterval" },
        0,null);
      mv.visitVarInsn(ALOAD,3);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/DoubleInterval","max","D");
      mv.visitVarInsn(ALOAD,2);
      mv.visitFieldInsn(GETFIELD,
        "mthcmp/java/numbers/IntegerInterval","min","I");
      mv.visitInsn(I2D);
      mv.visitInsn(DCMPG);
      final Label l30 = new Label();
      mv.visitJumpInsn(IFGT,l30);
      mv.visitInsn(ICONST_0);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l30);
      mv.visitLineNumber(111,l30);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitInsn(ICONST_1);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l25);
      mv.visitLineNumber(112,l25);
      mv.visitFrame(Opcodes.F_CHOP,2,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l31 = new Label();
      mv.visitJumpInsn(IFEQ,l31);
      final Label l32 = new Label();
      mv.visitLabel(l32);
      mv.visitLineNumber(113,l32);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/IntegerInterval;Ljava/util/Set;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l31);
      mv.visitLineNumber(114,l31);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/IntegerInterval","intersects",
        "(Ljava/lang/Object;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l17);
      mv.visitLineNumber(116,l17);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l33 = new Label();
      mv.visitJumpInsn(IFEQ,l33);
      final Label l34 = new Label();
      mv.visitLabel(l34);
      mv.visitLineNumber(117,l34);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l35 = new Label();
      mv.visitJumpInsn(IFEQ,l35);
      final Label l36 = new Label();
      mv.visitLabel(l36);
      mv.visitLineNumber(118,l36);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Lmthcmp/java/numbers/DoubleInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l35);
      mv.visitLineNumber(119,l35);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l37 = new Label();
      mv.visitJumpInsn(IFEQ,l37);
      final Label l38 = new Label();
      mv.visitLabel(l38);
      mv.visitLineNumber(120,l38);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Lmthcmp/java/numbers/IntegerInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l37);
      mv.visitLineNumber(121,l37);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      mv.visitJumpInsn(IFEQ,l33);
      final Label l39 = new Label();
      mv.visitLabel(l39);
      mv.visitLineNumber(122,l39);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l33);
      mv.visitLineNumber(124,l33);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitTypeInsn(NEW,
        "java/lang/UnsupportedOperationException");
      mv.visitInsn(DUP);
      final Label l40 = new Label();
      mv.visitLabel(l40);
      mv.visitLineNumber(125,l40);
      mv.visitTypeInsn(NEW,"java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("Can't test for interesection of ");
      mv.visitMethodInsn(INVOKESPECIAL,"java/lang/StringBuilder",
        "<init>","(Ljava/lang/String;)V",false);
      final Label l41 = new Label();
      mv.visitLabel(l41);
      mv.visitLineNumber(126,l41);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l42 = new Label();
      mv.visitLabel(l42);
      mv.visitLineNumber(127,l42);
      mv.visitLdcInsn(" and ");
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l43 = new Label();
      mv.visitLabel(l43);
      mv.visitLineNumber(128,l43);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l44 = new Label();
      mv.visitLabel(l44);
      mv.visitLineNumber(125,l44);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "toString","()Ljava/lang/String;",false);
      final Label l45 = new Label();
      mv.visitLabel(l45);
      mv.visitLineNumber(124,l45);
      mv.visitMethodInsn(INVOKESPECIAL,
        "java/lang/UnsupportedOperationException","<init>",
        "(Ljava/lang/String;)V",false);
      mv.visitInsn(ATHROW);
      final Label l46 = new Label();
      mv.visitLabel(l46);
      mv.visitLocalVariable("s0","Ljava/lang/Object;",null,l0,l46,
        0);
      mv.visitLocalVariable("s1","Ljava/lang/Object;",null,l0,l46,
        1);
      mv.visitLocalVariable("t0",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l5,l3,2);
      mv.visitLocalVariable("t1",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l6,l3,3);
      mv.visitLocalVariable("t0",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l11,l9,2);
      mv.visitLocalVariable("t1",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l12,l9,3);
      mv.visitLocalVariable("t0",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l21,l19,2);
      mv.visitLocalVariable("t1",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l22,l19,3);
      mv.visitLocalVariable("t0",
        "Lmthcmp/java/numbers/IntegerInterval;",null,l27,l25,2);
      mv.visitLocalVariable("t1",
        "Lmthcmp/java/numbers/DoubleInterval;",null,l28,l25,3);
      mv.visitMaxs(5,4);
      mv.visitEnd();
    }
    {
      mv =
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC,
          "manual","(Ljava/lang/Object;Ljava/lang/Object;)Z",null,
          null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(135,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l1 = new Label();
      mv.visitJumpInsn(IFEQ,l1);
      final Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(136,l2);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l3 = new Label();
      mv.visitJumpInsn(IFEQ,l3);
      final Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLineNumber(138,l4);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l5 = new Label();
      mv.visitLabel(l5);
      mv.visitLineNumber(137,l5);
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/DoubleInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l3);
      mv.visitLineNumber(139,l3);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l6 = new Label();
      mv.visitJumpInsn(IFEQ,l6);
      final Label l7 = new Label();
      mv.visitLabel(l7);
      mv.visitLineNumber(141,l7);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l8 = new Label();
      mv.visitLabel(l8);
      mv.visitLineNumber(140,l8);
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/DoubleInterval;Lmthcmp/java/numbers/IntegerInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l6);
      mv.visitLineNumber(142,l6);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l9 = new Label();
      mv.visitJumpInsn(IFEQ,l9);
      final Label l10 = new Label();
      mv.visitLabel(l10);
      mv.visitLineNumber(143,l10);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/DoubleInterval;Ljava/util/Set;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l9);
      mv.visitLineNumber(144,l9);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/DoubleInterval","intersects",
        "(Ljava/lang/Object;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l1);
      mv.visitLineNumber(146,l1);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l11 = new Label();
      mv.visitJumpInsn(IFEQ,l11);
      final Label l12 = new Label();
      mv.visitLabel(l12);
      mv.visitLineNumber(147,l12);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l13 = new Label();
      mv.visitJumpInsn(IFEQ,l13);
      final Label l14 = new Label();
      mv.visitLabel(l14);
      mv.visitLineNumber(149,l14);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l15 = new Label();
      mv.visitLabel(l15);
      mv.visitLineNumber(148,l15);
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/IntegerInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l13);
      mv.visitLineNumber(150,l13);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l16 = new Label();
      mv.visitJumpInsn(IFEQ,l16);
      final Label l17 = new Label();
      mv.visitLabel(l17);
      mv.visitLineNumber(152,l17);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l18 = new Label();
      mv.visitLabel(l18);
      mv.visitLineNumber(151,l18);
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/IntegerInterval;Lmthcmp/java/numbers/DoubleInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l16);
      mv.visitLineNumber(153,l16);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l19 = new Label();
      mv.visitJumpInsn(IFEQ,l19);
      final Label l20 = new Label();
      mv.visitLabel(l20);
      mv.visitLineNumber(154,l20);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Lmthcmp/java/numbers/IntegerInterval;Ljava/util/Set;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l19);
      mv.visitLineNumber(155,l19);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,
        "mthcmp/java/numbers/IntegerInterval","intersects",
        "(Ljava/lang/Object;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l11);
      mv.visitLineNumber(157,l11);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      final Label l21 = new Label();
      mv.visitJumpInsn(IFEQ,l21);
      final Label l22 = new Label();
      mv.visitLabel(l22);
      mv.visitLineNumber(158,l22);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/DoubleInterval");
      final Label l23 = new Label();
      mv.visitJumpInsn(IFEQ,l23);
      final Label l24 = new Label();
      mv.visitLabel(l24);
      mv.visitLineNumber(159,l24);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/DoubleInterval");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Lmthcmp/java/numbers/DoubleInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l23);
      mv.visitLineNumber(160,l23);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,
        "mthcmp/java/numbers/IntegerInterval");
      final Label l25 = new Label();
      mv.visitJumpInsn(IFEQ,l25);
      final Label l26 = new Label();
      mv.visitLabel(l26);
      mv.visitLineNumber(161,l26);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,
        "mthcmp/java/numbers/IntegerInterval");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Lmthcmp/java/numbers/IntegerInterval;)Z",
        false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l25);
      mv.visitLineNumber(162,l25);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(INSTANCEOF,"java/util/Set");
      mv.visitJumpInsn(IFEQ,l21);
      final Label l27 = new Label();
      mv.visitLabel(l27);
      mv.visitLineNumber(163,l27);
      mv.visitVarInsn(ALOAD,0);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitVarInsn(ALOAD,1);
      mv.visitTypeInsn(CHECKCAST,"java/util/Set");
      mv.visitMethodInsn(INVOKESTATIC,
        "mthcmp/java/sets/IntersectsAsm","intersects",
        "(Ljava/util/Set;Ljava/util/Set;)Z",false);
      mv.visitInsn(IRETURN);
      mv.visitLabel(l21);
      mv.visitLineNumber(165,l21);
      mv.visitFrame(Opcodes.F_SAME,0,null,0,null);
      mv.visitTypeInsn(NEW,
        "java/lang/UnsupportedOperationException");
      mv.visitInsn(DUP);
      final Label l28 = new Label();
      mv.visitLabel(l28);
      mv.visitLineNumber(166,l28);
      mv.visitTypeInsn(NEW,"java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("Can't test for interesection of ");
      mv.visitMethodInsn(INVOKESPECIAL,"java/lang/StringBuilder",
        "<init>","(Ljava/lang/String;)V",false);
      final Label l29 = new Label();
      mv.visitLabel(l29);
      mv.visitLineNumber(167,l29);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l30 = new Label();
      mv.visitLabel(l30);
      mv.visitLineNumber(168,l30);
      mv.visitLdcInsn(" and ");
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l31 = new Label();
      mv.visitLabel(l31);
      mv.visitLineNumber(169,l31);
      mv.visitVarInsn(ALOAD,1);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Class",
        "getSimpleName","()Ljava/lang/String;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/String;)Ljava/lang/StringBuilder;",
        false);
      final Label l32 = new Label();
      mv.visitLabel(l32);
      mv.visitLineNumber(166,l32);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "toString","()Ljava/lang/String;",false);
      final Label l33 = new Label();
      mv.visitLabel(l33);
      mv.visitLineNumber(165,l33);
      mv.visitMethodInsn(INVOKESPECIAL,
        "java/lang/UnsupportedOperationException","<init>",
        "(Ljava/lang/String;)V",false);
      mv.visitInsn(ATHROW);
      final Label l34 = new Label();
      mv.visitLabel(l34);
      mv.visitLocalVariable("s0","Ljava/lang/Object;",null,l0,l34,
        0);
      mv.visitLocalVariable("s1","Ljava/lang/Object;",null,l0,l34,
        1);
      mv.visitMaxs(5,2);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PRIVATE,"<init>","()V",null,null);
      mv.visitCode();
      final Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(175,l0);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKESPECIAL,"java/lang/Object",
        "<init>","()V",false);
      final Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLineNumber(177,l1);
      mv.visitTypeInsn(NEW,
        "java/lang/UnsupportedOperationException");
      mv.visitInsn(DUP);
      final Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLineNumber(178,l2);
      mv.visitTypeInsn(NEW,"java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("can't instantiate ");
      mv.visitMethodInsn(INVOKESPECIAL,"java/lang/StringBuilder",
        "<init>","(Ljava/lang/String;)V",false);
      mv.visitVarInsn(ALOAD,0);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/Object",
        "getClass","()Ljava/lang/Class;",false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "append","(Ljava/lang/Object;)Ljava/lang/StringBuilder;",
        false);
      mv.visitMethodInsn(INVOKEVIRTUAL,"java/lang/StringBuilder",
        "toString","()Ljava/lang/String;",false);
      final Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLineNumber(177,l3);
      mv.visitMethodInsn(INVOKESPECIAL,
        "java/lang/UnsupportedOperationException","<init>",
        "(Ljava/lang/String;)V",false);
      mv.visitInsn(ATHROW);
      final Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLocalVariable("this",
        "Lmthcmp/java/sets/IntersectsAsm;",null,l0,l4,0);
      mv.visitMaxs(5,1);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
