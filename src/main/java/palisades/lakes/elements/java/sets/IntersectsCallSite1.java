package palisades.lakes.elements.java.sets;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.Objects;

import palisades.lakes.elements.java.sets.Intersects;
import palisades.lakes.elements.java.sets.IntersectsCallSite1;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2017-12-13
 */

@SuppressWarnings("unchecked")
public final class IntersectsCallSite1 extends Object {

  //--------------------------------------------------------------

  private final MethodHandles.Lookup lookup = 
    MethodHandles.lookup();

  private final MethodHandle 
  getHandle (final Class c0, final Class c1) 
    throws NoSuchMethodException, IllegalAccessException {

    return 
      lookup
      .findStatic(
        Intersects.class, 
        "intersects", 
        MethodType.methodType(
          boolean.class, c0,  c1))
      .asType(
        MethodType.methodType(
          boolean.class, Object.class,  Object.class)); } 

  //--------------------------------------------------------------

  private Class class0;
  private Class class1;
  private final MutableCallSite callSite;
  private MethodHandle invoker;

  private final void 
  updateTarget (final Object s0, final Object s1) 
    throws NoSuchMethodException, IllegalAccessException {
    final Class c0 = s0.getClass();
    final Class c1 = s1.getClass();
    if (! (Objects.equals(c0,class0) && Objects.equals(c1,class1))) {
      class0 = c0;
      class1 = c1;
      callSite.setTarget(getHandle(c0,c1)); }  }

  //--------------------------------------------------------------

  public final boolean intersects (final Object s0,
                                   final Object s1) {
    try {
      updateTarget(s0,s1);
      return (boolean) invoker.invokeExact(s0,s1); }

    catch (final Error e) {
      System.err.println("method handle invoke failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "method handle invoke failed", e); } }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private IntersectsCallSite1 () { 

    try { 
      callSite = 
        new MutableCallSite(
          MethodType.methodType(
            boolean.class, Object.class,  Object.class));
      invoker = callSite.dynamicInvoker(); }
    catch (final Error e) {
      System.err.println("constructor failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "constructor failed", e); } }

  public static final IntersectsCallSite1 make () {
    return new IntersectsCallSite1(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
