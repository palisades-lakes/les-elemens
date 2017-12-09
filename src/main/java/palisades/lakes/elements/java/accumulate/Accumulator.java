package palisades.lakes.elements.java.accumulate;


/** Convenience interface for mutable, <em>non-</em>thread safe
 * objects used for general kinds of reductions of data sets,
 * typically online. Supports both updating (adding items),
 *  and downdating (removing items) from the accumulation.
 * <p>
 * All methods are optional.
 * <p>
 * TODO: more kinds of items to add and values to extract. <br>
 * TODO: immutable version that returns a new Accumulator on
 * add/remove?
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-16
 * @version 2017-05-17
 */
public interface Accumulator {

  //--------------------------------------------------------------
  // general Object interface
  //--------------------------------------------------------------

  Object value ();

  /** {@link #addAll(Object) addAll} and
   * {@link #removeAll(Object) removeAll} treat their arguments
   * as collections of individual items to be added or removed;
   * {@link #add(Object) add} and
   * {@link #remove(Object) remove} treat the argument as a single
   * thing to be added or removed.
   */

  void add (final Object z);

  void remove (final Object z);

  /** {@link #addAll(Object) addAll} and
   * {@link #removeAll(Object) removeAll} treat their arguments
   * as collections of individual items to be added or removed;
   * {@link #add(Object) add} and
   * {@link #remove(Object) remove} treat the argument as a single
   * thing to be added or removed.
   */

  void addAll (final Object z,
               final int start,
               final int end);

  /** {@link #addAll(double[]) addAll} and
   * {@link #removeAll(double[]) removeAll} treat their arguments
   * as collections of individual items to be added or removed;
   * {@link #add(Object) add} and
   * {@link #remove(Object) remove} treat the argument as a single
   * thing to be added or removed.
   */

  void removeAll (final Object z,
                  final int start,
                  final int end);

  /** {@link #addAll(Object) addAll} and
   * {@link #removeAll(Object) removeAll} treat their arguments
   * as collections of individual items to be added or removed;
   * {@link #add(Object) add} and
   * {@link #remove(Object) remove} treat the argument as a single
   * thing to be added or removed.
   */

  void addAll (final Object z);

  /** {@link #addAll(double[]) addAll} and
   * {@link #removeAll(double[]) removeAll} treat their arguments
   * as collections of individual items to be added or removed;
   * {@link #add(Object) add} and
   * {@link #remove(Object) remove} treat the argument as a single
   * thing to be added or removed.
   */

  void removeAll (final Object z);

  //--------------------------------------------------------------
  // primitives
  //--------------------------------------------------------------

  double doubleValue ();
  void add (final double z);
  void remove (final double z);
  void addAll (final double[] z);
  void removeAll (final double[] z);
  void addAll (final double[] z,
               final int start,
               final int end);
  void removeAll (final double[] z,
                  final int start,
                  final int end);

  // for dot products, distances, etc.

  void add (final double z0, final double z1);
  void addAll (final double[] z0, final double[] z1);
  void addAll (final double[] z0,
               final double[] z1,
               final int start,
               final int end);
}
