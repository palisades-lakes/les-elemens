package palisades.lakes.elements.java.sets;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import palisades.lakes.elements.java.numbers.DoubleInterval;
import palisades.lakes.elements.java.numbers.IntegerInterval;
import palisades.lakes.elements.java.sets.IntersectsStatic;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-29
 * @version 2017-06-02
 */

@SuppressWarnings("unchecked")
public final class IntersectsStatic extends Object {

  //--------------------------------------------------------------
  // methods
  //--------------------------------------------------------------

  public static final boolean intersects (final DoubleInterval s0,
                                          final DoubleInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; }

  public static final boolean intersects (final DoubleInterval s0,
                                          final IntegerInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; }

  public static final boolean intersects (final IntegerInterval s0,
                                          final DoubleInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; }

  public static final boolean intersects (final IntegerInterval s0,
                                          final IntegerInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; }

  public static final boolean intersects (final DoubleInterval s0,
                                          final Set s1) {
    return s0.intersects(s1); }

  public static final boolean intersects (final IntegerInterval s0,
                                          final Set s1) {
    return s0.intersects(s1); }

  public static final boolean intersects (final Set s0,
                                          final DoubleInterval s1) {
    return s1.intersects(s0); }

  public static final boolean intersects (final Set s0,
                                          final IntegerInterval s1) {
    return s1.intersects(s0); }

  public static final boolean intersects (final Set s0,
                                          final Set s1) {
    return (! Collections.disjoint(s0,s1)); }

  //--------------------------------------------------------------
  // lookup
  //--------------------------------------------------------------

  public static final boolean inline (final Object s0,
                                      final Object s1) {

    if (s0 instanceof DoubleInterval) {
      if (s1 instanceof DoubleInterval) {
        final DoubleInterval t0 = (DoubleInterval) s0;
        final DoubleInterval t1 = (DoubleInterval) s1;
        if (t0.max <= t1.min) { return false; }
        if (t1.max <= t0.min) { return false; }
        return true; }
      if (s1 instanceof IntegerInterval) {
        final DoubleInterval t0 = (DoubleInterval) s0;
        final IntegerInterval t1 = (IntegerInterval) s1;
        if (t0.max <= t1.min) { return false; }
        if (t1.max <= t0.min) { return false; }
        return true; }
      if (s1 instanceof Set) {
        return intersects((DoubleInterval) s0, (Set) s1); }
      return ((DoubleInterval) s0).intersects(s1); }

    if (s0 instanceof IntegerInterval) {
      if (s1 instanceof IntegerInterval) {
        final IntegerInterval t0 = (IntegerInterval) s0;
        final IntegerInterval t1 = (IntegerInterval) s1;
        if (t0.max <= t1.min) { return false; }
        if (t1.max <= t0.min) { return false; }
        return true; }
      if (s1 instanceof DoubleInterval) {
        final IntegerInterval t0 = (IntegerInterval) s0;
        final DoubleInterval t1 = (DoubleInterval) s1;
        if (t0.max <= t1.min) { return false; }
        if (t1.max <= t0.min) { return false; }
        return true; }
      if (s1 instanceof Set) {
        return intersects((IntegerInterval) s0, (Set) s1); }
      return ((IntegerInterval) s0).intersects(s1); }

    if (s0 instanceof Set) {
      if (s1 instanceof DoubleInterval) {
        return intersects((Set) s0, (DoubleInterval) s1); }
      if (s1 instanceof IntegerInterval) {
        return intersects((Set) s0, (IntegerInterval) s1); }
      if (s1 instanceof Set) {
        return intersects((Set) s0, (Set) s1); } }

    throw new UnsupportedOperationException(
      "Can't test for interesection of " +
        s0.getClass().getSimpleName() +
        " and " +
        s1.getClass().getSimpleName()); }

  //--------------------------------------------------------------

  public static final boolean manual (final Object s0,
                                      final Object s1) {

    if (s0 instanceof DoubleInterval) {
      if (s1 instanceof DoubleInterval) {
        return intersects((DoubleInterval) s0, (DoubleInterval) s1); }
      if (s1 instanceof IntegerInterval) {
        return intersects((DoubleInterval) s0, (IntegerInterval) s1); }
      if (s1 instanceof Set) {
        return intersects((DoubleInterval) s0, (Set) s1); }
      return ((DoubleInterval) s0).intersects(s1); }

    if (s0 instanceof IntegerInterval) {
      if (s1 instanceof IntegerInterval) {
        return intersects((IntegerInterval) s0, (IntegerInterval) s1); }
      if (s1 instanceof DoubleInterval) {
        return intersects((IntegerInterval) s0, (DoubleInterval) s1); }
      if (s1 instanceof Set) {
        return intersects((IntegerInterval) s0, (Set) s1); }
      return ((IntegerInterval) s0).intersects(s1); }

    if (s0 instanceof Set) {
      if (s1 instanceof DoubleInterval) {
        return intersects((Set) s0, (DoubleInterval) s1); }
      if (s1 instanceof IntegerInterval) {
        return intersects((Set) s0, (IntegerInterval) s1); }
      if (s1 instanceof Set) {
        return intersects((Set) s0, (Set) s1); } }

    throw new UnsupportedOperationException(
      "Can't test for interesection of " +
        s0.getClass().getSimpleName() +
        " and " +
        s1.getClass().getSimpleName()); }

  //--------------------------------------------------------------

  public static final boolean reflection (final Object s0,
                                          final Object s1) {
    try {
      final Method m =
        IntersectsStatic.class.getDeclaredMethod(
          "intersects", s0.getClass(), s1.getClass());
      return ((Boolean) m.invoke(null,s0,s1)).booleanValue(); }
    catch (final Exception e) {
      e.printStackTrace(System.err);
      throw new RuntimeException(
        "reflection method lookup failed", e); } }

  //--------------------------------------------------------------

  public static final boolean handle (final Object s0,
                                      final Object s1) {
    try {
      final MethodType mType =
        MethodType.methodType(
          boolean.class,
          new Class[] { s0.getClass(), s1.getClass(), });
      final MethodHandle mHandle =
        MethodHandles.lookup().findStatic(
          IntersectsStatic.class, "intersects", mType);
      return (boolean) mHandle.invoke(s0,s1); }
    catch (final Error e) {
      System.err.println("method handle lookup failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "method handle lookup failed", e); }}

  //--------------------------------------------------------------

  private static Class class0;
  private static Class class1;

  private static Method method;
  private static MethodType methodType;
  private static MethodHandle methodHandle;

  static {
    class0 = DoubleInterval.class;
    class1 = IntegerInterval.class;
    try {
      method =
        IntersectsStatic.class.getMethod("intersects", class0, class1);
      assert (null != method);
      methodType = MethodType.methodType(
        boolean.class, new Class[] { class0,  class1, });
      assert (null != methodType);
      methodHandle = MethodHandles.lookup().findStatic(
        IntersectsStatic.class, "intersects", methodType);
      assert (null != methodHandle); }
    catch (NoSuchMethodException
      | SecurityException
      | IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException(
        "static reflection method lookup failed", e); } }

  //--------------------------------------------------------------

  public static final boolean reflectionCached (final Object s0,
                                                final Object s1) {
    try {
      final Class c0 = s0.getClass();
      final Class c1 = s1.getClass();
      if ((null == method) ||
        (! (Objects.equals(c0,class0) && Objects.equals(c1,class1)))) {
        class0 = c0;
        class1 = c1;
        method = IntersectsStatic.class.getMethod("intersects", c0, c1); }
      assert (null != method);
      return ((Boolean) method.invoke(null,s0,s1)).booleanValue(); }
    catch (final Exception e) {
      e.printStackTrace(System.err);
      throw new RuntimeException(
        "reflection method lookup failed", e); } }

  //--------------------------------------------------------------


  public static final boolean handleCached (final Object s0,
                                            final Object s1) {
    try {
      final Class c0 = s0.getClass();
      final Class c1 = s1.getClass();
      if (! (Objects.equals(c0,class0) && Objects.equals(c1,class1))) {
        class0 = c0;
        class1 = c1;
        methodType = MethodType.methodType(
          boolean.class, new Class[] { c0,  c1, });
        assert (null != methodType);
        methodHandle = MethodHandles.lookup().findStatic(
          IntersectsStatic.class, "intersects", methodType);
        assert (null != methodHandle); }
      return (boolean) methodHandle.invoke(s0,s1); }
    catch (final Error e) {
      System.err.println("method handle lookup failed");
      e.printStackTrace();
      throw e; }
    catch (final Throwable e) {
      e.printStackTrace();
      throw new RuntimeException(
        "method handle lookup failed", e); }}

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private IntersectsStatic () { }

  public static final IntersectsStatic make () {
    return new IntersectsStatic(); }

  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
