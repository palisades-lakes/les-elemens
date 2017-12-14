package palisades.lakes.elements.java.sets;

import java.util.Collections;
import java.util.Set;

import palisades.lakes.elements.java.numbers.DoubleInterval;
import palisades.lakes.elements.java.numbers.IntegerInterval;

/** Implementations of a generic intersection test.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-29
 * @version 2017-06-06
 */

@SuppressWarnings("unchecked")
public final class Intersects extends Object {

  //--------------------------------------------------------------
  // methods
  //--------------------------------------------------------------

  public final static boolean intersects (final DoubleInterval s0,
                                          final DoubleInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; } 

  public final static boolean intersects (final DoubleInterval s0,
                                          final IntegerInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; } 

  public final static boolean intersects (final IntegerInterval s0,
                                          final DoubleInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; } 

  public final static boolean intersects (final IntegerInterval s0,
                                          final IntegerInterval s1) {
    if (s0.max <= s1.min) { return false; }
    if (s1.max <= s0.min) { return false; }
    return true; } 

  public final static boolean intersects (final DoubleInterval s0,
                                          final Set s1) {
    return s0.intersects(s1); } 

  public final static boolean intersects (final IntegerInterval s0,
                                          final Set s1) {
    return s0.intersects(s1); } 

  public final static boolean intersects (final Set s0,
                                          final DoubleInterval s1) {
    return s1.intersects(s0); } 

  public final static boolean intersects (final Set s0,
                                          final IntegerInterval s1) {
    return s1.intersects(s0); } 

  public final static boolean intersects (final Set s0,
                                          final Set s1) {
    return (! Collections.disjoint(s0,s1)); } 

  public final static boolean intersects (final Object s0,
                                          final Object s1) {
    throw new UnsupportedOperationException(
      "don't know how to test for intersections of " +
        s0.getClass().getSimpleName() + 
        " and " +
        s1.getClass().getSimpleName()); } 

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
        return intersects(
          (DoubleInterval) s0, (DoubleInterval) s1); }
      if (s1 instanceof IntegerInterval) {
        return intersects(
          (DoubleInterval) s0, (IntegerInterval) s1); }
      if (s1 instanceof Set) { 
        return intersects((DoubleInterval) s0, (Set) s1); }
      return ((DoubleInterval) s0).intersects(s1); }

    if (s0 instanceof IntegerInterval) {
      if (s1 instanceof IntegerInterval) {
        return intersects(
          (IntegerInterval) s0, (IntegerInterval) s1); }
      if (s1 instanceof DoubleInterval) {
        return intersects(
          (IntegerInterval) s0, (DoubleInterval) s1); }
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
  // construction
  //--------------------------------------------------------------

//  private Intersects () { 
//    throw new UnsupportedOperationException(
//      "can't instantiate " + getClass()); }
  //public Intersects () { super(); }
  ///--------------------------------------------------------------
} // end class
//--------------------------------------------------------------
