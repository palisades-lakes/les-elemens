package palisades.lakes.elements.java.sets;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import palisades.lakes.elements.java.sets.Intersects;
import palisades.lakes.elements.java.sets.IntersectsMethod;

/** Implementations of a generic intersection test.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-29
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class IntersectsMethod extends Object {

  //--------------------------------------------------------------

  private final Map methods = new HashMap(2);

  private final Method
  getMethod (final Class c0, final Class c1)
    throws NoSuchMethodException, SecurityException  {

    Map inner = (Map) methods.get(c0);
    if (null == inner)  {
      inner = new HashMap(2);
      methods.put(c0,inner); }

    Method m = (Method) inner.get(c1);
    if (null == m) {
      m = Intersects.class.getMethod("intersects", c0, c1);
      inner.put(c1,m); }

    return m; }

  //--------------------------------------------------------------

  private Class class0;
  private Class class1;
  private Method method;

  private final Method
  updateMethod (final Object s0, final Object s1)
    throws NoSuchMethodException, SecurityException  {

    final Class c0 = s0.getClass();
    final Class c1 = s1.getClass();
    if (! ((c0.equals(class0) && c1.equals(class1)))) {
      class0 = c0;
      class1 = c1;
      method = getMethod(c0, c1); }
    assert (null != method);
    return method; }

  //--------------------------------------------------------------

  public final boolean intersects (final Object s0,
                                   final Object s1) {
    try {
      return
        ((Boolean) updateMethod(s0,s1).invoke(this,s0,s1))
        .booleanValue(); }

    catch (final Error e) {
      System.err.println("reflectionCached failed");
      e.printStackTrace();
      throw e; }

    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException("reflectionCached failed", e); } }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private IntersectsMethod () {

    try { updateMethod(new Object(), new Object()); }
    catch (final Error e) {
      System.err.println("constructor failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "constructor failed", e); } }

  public static final IntersectsMethod make () {
    return new IntersectsMethod(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
