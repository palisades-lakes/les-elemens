package elements.java.generic;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;

import clojure.lang.AFn;
import clojure.lang.IFn;

/**
 * A dynamic function of 2 arguments.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-02
 * @version 2017-06-06
 */

@SuppressWarnings("unchecked")
public final class DynamicFn2 extends AFn {

  private final String name;
  private final ReentrantReadWriteLock rw;

  private volatile ImmutableMap methodTable;
  public final ImmutableMap getMethodTable () {
    return methodTable; }

  private volatile ImmutableSetMultimap preferTable;
  public final ImmutableSetMultimap getPreferTable () {
    return preferTable; }

  private volatile ImmutableMap methodCache;

  //--------------------------------------------------------------

  public DynamicFn2 (final String name0) {
    this.rw = new ReentrantReadWriteLock();
    this.name = name0;
    this.methodTable = ImmutableMap.of();
    this.methodCache = getMethodTable();
    this.preferTable = ImmutableSetMultimap.of(); }

  //--------------------------------------------------------------
  // TODO: check if same value already there

  private static final ImmutableMap assoc (final ImmutableMap m,
                                           final Signature2 k,
                                           final Object v) {
    final ImmutableMap.Builder b = ImmutableMap.builder();
    if (null != m) { b.putAll(m); }
    return b.put(k,v).build(); }

  //--------------------------------------------------------------

  private static final ImmutableMap dissoc (final ImmutableMap m,
                                            final Signature2 k) {
    final ImmutableMap.Builder b = ImmutableMap.builder();
    for (final Object xi : m.keySet()) {
      final Map.Entry ei = (Map.Entry) xi;
      final Object ki = ei.getKey();
      if (! k.equals(ki)) { b.put(ki,ei.getValue()); } }
    return b.build(); }

  //--------------------------------------------------------------

  private Class class0;
  private Class class1;
  private IFn lastFn;

  private final Map resetCache () {
    rw.writeLock().lock();
    try {
      class0 = null;
      class1 = null;
      lastFn = null;
      methodCache = getMethodTable();
      return methodCache; }
    finally { rw.writeLock().unlock(); } }

  //--------------------------------------------------------------

  public final DynamicFn2 reset () {
    rw.writeLock().lock();
    try {
      methodTable = ImmutableMap.of();
      preferTable = ImmutableSetMultimap.of(); 
      resetCache();
      return this; }
    finally { rw.writeLock().unlock(); } }

  //--------------------------------------------------------------

  public final DynamicFn2 addMethod (final Signature2 k,
                                     final IFn method) {
    rw.writeLock().lock();
    try {
      methodTable = assoc(
        getMethodTable(), k, method);
      resetCache();
      return this; }
    finally { rw.writeLock().unlock(); } }

  public final DynamicFn2 addMethod (final Class c0,
                                     final Class c1,
                                     final IFn method) {
    return addMethod(Signature2.get(c0,c1), method); }

  //--------------------------------------------------------------

  public final DynamicFn2 removeMethod (final Signature2 k) {
    rw.writeLock().lock();
    try {
      methodTable = dissoc(getMethodTable(),k);
      resetCache();
      return this; }
    finally { rw.writeLock().unlock(); } }

  public final DynamicFn2 removeMethod (final Class c0,
                                        final Class c1) {
    return removeMethod(Signature2.get(c0,c1)); }

  //--------------------------------------------------------------

  private final boolean prefers (final Signature2 k0,
                                 final Signature2 k1) {
    final ImmutableSet s = getPreferTable().get(k0);
    if ((s != null) && s.contains(k1)) { return true; }
    return false; }

  //--------------------------------------------------------------

  private final boolean dominates (final Signature2 k0,
                                   final Signature2 k1) {
    return k1.isAssignableFrom(k0) || prefers(k0,k1); }

  //--------------------------------------------------------------

  private static final ImmutableSetMultimap 
  add (final ImmutableSetMultimap m,
       final Signature2 k,
       final Signature2 v) {
    return 
      ImmutableSetMultimap.builder()
      .putAll(m)
      .put(k,v)
      .build(); }

  //--------------------------------------------------------------

  public final DynamicFn2 preferMethod (final Signature2 k0,
                                        final Signature2 k1) {
    rw.writeLock().lock();
    try {
      if (prefers(k0,k1)) { 
        throw new IllegalStateException(
          String.format(
            "Preference conflict in multimethod '%s': "
              + "%s is already preferred to %s",
              name,k0,k1)); }
      preferTable = add(getPreferTable(),k0,k1);
      resetCache();
      return this; }
    finally { rw.writeLock().unlock(); } }

  public final DynamicFn2 preferMethod (final Class c00,
                                        final Class c01,
                                        final Class c10,
                                        final Class c11) {
    return 
      preferMethod(
        Signature2.get(c00,c01),
        Signature2.get(c10,c11)); }

  //--------------------------------------------------------------

  private final IFn findAndCacheBestMethod (final Signature2 k0) {
    rw.readLock().lock();
    IFn bestMethod;
    final ImmutableMap mt = methodTable;
    final ImmutableSetMultimap pt = preferTable;
    try {
      Map.Entry bestEntry = null;
      for (final Object o : getMethodTable().entrySet()) {
        final Map.Entry e = (Map.Entry) o;
        final Signature2 k1 = (Signature2) e.getKey();
        if (k1.isAssignableFrom(k0)) {
          if ((bestEntry == null)
            || dominates(k1,(Signature2) bestEntry.getKey())) {
            bestEntry = e; }
          if (! dominates((Signature2) bestEntry.getKey(),k1)) { 
            throw new IllegalArgumentException(
              String.format(
                "Multiple methods in multimethod '%s' " +
                  "match dispatch value: %s -> %s and %s, " + 
                  "and neither is preferred",
                  name, k0, k1, bestEntry.getKey())); } } }
      if (bestEntry == null) { return null; }
      bestMethod = (IFn) bestEntry.getValue(); }
    finally { rw.readLock().unlock(); }

    // ensure basis has stayed stable throughout, else redo
    rw.writeLock().lock();
    try {
      if ((mt == methodTable) && (pt == preferTable)) {
        // place in cache
        methodCache = assoc(methodCache,k0,bestMethod);
        return bestMethod; }
      resetCache();
      return findAndCacheBestMethod(k0); }
    finally { rw.writeLock().unlock(); } }

  //--------------------------------------------------------------

  public final IFn getMethod (final Class c0,
                              final Class c1) {
    final Signature2 k = Signature2.get(c0,c1);
    final IFn f = (IFn) methodCache.get(k);
    if (f != null) { return f; }
    return findAndCacheBestMethod(k); }

  //--------------------------------------------------------------

  @Override
  synchronized public final Object invoke (final Object x0, 
                                           final Object x1) {
    final Class c0 = x0.getClass();
    final Class c1 = x1.getClass();
    if (c0.equals(class0) && c1.equals(class1)) {
      return lastFn.invoke(x0,x1); }

    final IFn f = getMethod(c0,c1);
    if (null == f) {
      throw new UnsupportedOperationException(
        "no method for (" + name + " " 
          + c0.getName() + " "
          + c1.getName() + ")"); }
    class0 = c0;
    class1 = c1;
    lastFn = f;
    return f.invoke(x0,x1); }

  //--------------------------------------------------------------
}
//--------------------------------------------------------------

