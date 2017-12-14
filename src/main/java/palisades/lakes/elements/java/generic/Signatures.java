package palisades.lakes.elements.java.generic;

import palisades.lakes.elements.java.generic.Signature;
import palisades.lakes.elements.java.generic.Signature2;
import palisades.lakes.elements.java.generic.Signature3;
import palisades.lakes.elements.java.generic.SignatureN;

/** Utilities for signatures.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-06-05
 * @version 2017-06-05
 */

public final class Signatures {

  //--------------------------------------------------------------
  
  public static final Class get (final Class c0) {
    return c0; }
  
  public static final Class get (final Object x0) {
    return get(x0.getClass()); }
  
  //--------------------------------------------------------------
  
  public static final Signature get (final Class c0,
                                     final Class c1) {
    return Signature2.get(c0,c1); }
  
  public static final Signature get (final Object x0,
                                      final Object x1) {
    return get(x0.getClass(),x1.getClass()); }
  
  //--------------------------------------------------------------
  
  public static final Signature get (final Class c0,
                                     final Class c1,
                                     final Class c2) {
    return Signature3.get(c0,c1,c2); }
  
  public static final Signature get (final Object x0,
                                      final Object x1,
                                      final Object x2) {
    return get(x0.getClass(),x1.getClass(),x2.getClass()); }
  
  //--------------------------------------------------------------
  
  public static final Signature get (final Class... cs) {
    return SignatureN.get(cs); }
  
  public static final Signature get (final Object... xs) {
    final Class[] cs = new Class[xs.length];
    for (int i=0;i<xs.length;i++) {
      cs[i] = xs[i].getClass(); }
    return get(cs); }
  
  //--------------------------------------------------------------

  private Signatures () {
    throw new UnsupportedOperationException(
      "can't instantiate " + getClass().getName()); }


  //--------------------------------------------------------------
}
//--------------------------------------------------------------
