package palisades.lakes.elements.java.sets;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Objects;

import palisades.lakes.elements.java.sets.Intersects;
import palisades.lakes.elements.java.sets.IntersectsHandle1;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2017-12-13
 */

@SuppressWarnings("unchecked")
public final class IntersectsHandle1 extends Object {

  //--------------------------------------------------------------

  private final MethodHandles.Lookup lookup = 
    MethodHandles.lookup();

  private final MethodHandle 
  getHandle (final Class c0, final Class c1) 
    throws NoSuchMethodException, IllegalAccessException {

    final MethodType t0 = 
      MethodType.methodType(boolean.class, c0,  c1);
    //assert (null != t0);
    final MethodType t1 = MethodType.methodType(
      boolean.class, Object.class,  Object.class);
    //assert (null != t1);
    final MethodHandle h0 = 
      lookup.findStatic(Intersects.class, "intersects", t0);
    //assert (null != h0);
    return h0.asType(t1); } 

  //--------------------------------------------------------------

  private Class class0;
  private Class class1;
  private MethodHandle handle;

  private final MethodHandle 
  updateHandle (final Object s0, final Object s1) 
    throws NoSuchMethodException, IllegalAccessException {
    final Class c0 = s0.getClass();
    final Class c1 = s1.getClass();
    if (! (Objects.equals(c0,class0) && Objects.equals(c1,class1))) {
      class0 = c0;
      class1 = c1;
      handle = getHandle(c0,c1); } 
    //assert (null != handle);
    return handle; }

  //--------------------------------------------------------------

  public final boolean intersects (final Object s0,
                                   final Object s1) {
    try {

      return (boolean) updateHandle(s0,s1).invokeExact(s0,s1); }

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

  private IntersectsHandle1 () { 

    try { updateHandle(new Object(), new Object()); }
    catch (final Error e) {
      System.err.println("constructor failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "constructor failed", e); } }

  public static final IntersectsHandle1 make () {
    return new IntersectsHandle1(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
