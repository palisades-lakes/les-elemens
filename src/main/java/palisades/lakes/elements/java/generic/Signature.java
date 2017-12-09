package palisades.lakes.elements.java.generic;

import palisades.lakes.elements.java.generic.Signature;

/** A pair of classes, for optimizing multimethod dispatch 
 * functions.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-05
 * @version 2017-06-05
 */

public interface Signature {

  
  //--------------------------------------------------------------
  // inheritance partial ordering
  //--------------------------------------------------------------

  boolean isAssignableFrom (Signature that);
  boolean isAssignableFrom (Class k);
  boolean isAssignableFrom (Class k0, Class k1);
  boolean isAssignableFrom (Class k0, Class k1, Class k2);
  boolean isAssignableFrom (Class... ks);
  
  //--------------------------------------------------------------
  // short cut signature instantiation
  //--------------------------------------------------------------

  boolean equiv (Class k0, Class k1);
  boolean equiv (Class k0, Class k1, Class k2);
  boolean equiv (Class... ks);
  
  //--------------------------------------------------------------
}
//--------------------------------------------------------------
