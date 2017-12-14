package palisades.lakes.elements.java.sets;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import palisades.lakes.elements.java.sets.Intersects;
import palisades.lakes.elements.java.sets.IntersectsHandle4;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @version 2017-12-13
 */

@SuppressWarnings("unchecked")
public final class IntersectsHandle4 extends Object {

  //--------------------------------------------------------------

  private Class class0;
  private Class class1;
  private MethodHandle handle;

  private final Map types;
  private Map inner;
  
  private final MethodType
  getType (final Class c0, final Class c1)  {

    class1 = c1;
    if (! Objects.equals(c0,class0)) {
      class0 = c0;
      inner = (Map) types.get(c0);
      if (null == inner)  { 
        inner = new HashMap(2); 
        types.put(c0,inner); } }
    
    MethodType t = (MethodType) inner.get(c1);
    if (null == t) {
      t = MethodType.methodType(boolean.class, c0,  c1);
      inner.put(c1,t); }
    
    return t; } 

  //--------------------------------------------------------------

  private final MethodHandles.Lookup lookup = 
    MethodHandles.lookup();

  private final Map handles = new HashMap(4); 
  
  private final MethodType t1 = MethodType.methodType(
    boolean.class, Object.class,  Object.class);
  
  private final MethodHandle 
  getHandle (final Class c0, final Class c1) 
    throws NoSuchMethodException, IllegalAccessException {

    final MethodType t0 = getType(c0,c1);

    MethodHandle h = (MethodHandle) handles.get(t0);
    if (null == h) {
      h = lookup
        .findStatic(Intersects.class, "intersects", t0)
        .asType(t1);
      handles.put(t0,h); }
    
    return h; } 

  //--------------------------------------------------------------

  private final MethodHandle 
  updateHandle (final Object s0, final Object s1) 
    throws NoSuchMethodException, IllegalAccessException {
    final Class c0 = s0.getClass();
    final Class c1 = s1.getClass();
    if (! (Objects.equals(c0,class0) && Objects.equals(c1,class1))) {
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
      System.err.println(s0);
      System.err.println(s1);
      System.err.println(handle);
      System.err.println(class0);
      System.err.println(class1);
      System.err.println(types);
      System.err.println(inner);
      throw new RuntimeException(
        "method handle invoke failed", e); } }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private IntersectsHandle4 () { 

    types = new HashMap(3);
    inner = new HashMap(3);
    types.put(Object.class,inner); 
    try { updateHandle(new Object(), new Object()); }
    catch (final Error e) {
      System.err.println("constructor failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "constructor failed", e); } }

  public static final IntersectsHandle4 make () {
    return new IntersectsHandle4(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
