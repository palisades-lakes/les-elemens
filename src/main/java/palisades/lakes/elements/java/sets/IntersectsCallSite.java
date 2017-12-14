package palisades.lakes.elements.java.sets;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import palisades.lakes.elements.java.sets.Intersects;
import palisades.lakes.elements.java.sets.IntersectsCallSite;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-31
 * @version 2017-06-01
 */

@SuppressWarnings("unchecked")
public final class IntersectsCallSite extends Object {

  //--------------------------------------------------------------

  private final Map types = new HashMap(2);
  
  private final MethodType
  getType (final Class c0, final Class c1)  {

    Map inner = (Map) types.get(c0);
    if (null == inner)  { 
      inner = new HashMap(2); 
      types.put(c0,inner); }
    
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

  private IntersectsCallSite () { 

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

  public static final IntersectsCallSite make () {
    return new IntersectsCallSite(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
