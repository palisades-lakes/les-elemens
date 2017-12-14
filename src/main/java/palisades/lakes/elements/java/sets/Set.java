package palisades.lakes.elements.java.sets;


/**
 * All methods are optional. Throwing an exception is not the same
 * as returning false.
 *
 * @author palisades dot lakes at gmail dot com
 * @since 2017-05-22
 * @version 2017-05-22
 */
public interface Set {

  /** How many palisades.lakes.elements does this set have?
   */
  public boolean isEmpty ();

  /** How many palisades.lakes.elements does this set have?
   */
  public Object cardinality ();

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (boolean x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (byte x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (char x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (double x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (float x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (int x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (long x);

  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (short x);


  /** Is <code>x</code> an element of <code>this</code> set?
   */
  public boolean contains (Object x);

  //--------------------------------------------------------------
  /** Does <code>this</code> share any palisades.lakes.elements with
   * <code>set</code>?
   */
  public boolean intersects (Object set);

  /** Is this a strict subset of <code>set</code>?
   * That is, <code>set</code> contains something in <
   * code>this</code>.
   */
  public boolean strictSubsetOf (Object set);

  /** Is this a subset of <code>set</code>?
   * Must return true if <code>this.strictSubsetOf(set}</code>
   * does.
   */
  public boolean subsetOf (Object set);

  public Object intersection (Object set);
  public Object union (Object set);
  public Object difference (Object set);
}
