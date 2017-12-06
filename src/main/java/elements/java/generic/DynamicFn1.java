/**
 * Copyright (c) Rich Hickey. All rights reserved.
 * The use and distribution terms for this software are covered by
 * the
 * Eclipse Public License 1.0
 * (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this
 * distribution.
 * By using this software in any fashion, you are agreeing to be
 * bound by
 * the terms of this license.
 * You must not remove this notice, or any other, from this
 * software.
 **/

/* rich Sep 13, 2007 */

package elements.java.generic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import clojure.lang.AFn;
import clojure.lang.IFn;
import clojure.lang.IPersistentCollection;
import clojure.lang.IPersistentMap;
import clojure.lang.IPersistentSet;
import clojure.lang.ISeq;
import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentHashSet;
import clojure.lang.RT;
import clojure.lang.Util;
import clojure.lang.Var;

/**
 * Fork of clojure.lang.MultiFn, for performance experiments.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-06-02
 * @version 2017-06-06
 */

@SuppressWarnings("unchecked")
public final class DynamicFn1 extends AFn {

  public final IFn dispatchFn;
  public final Object defaultDispatchVal;
  private final String name;
  private final ReentrantReadWriteLock rw;
  private volatile IPersistentMap methodTable;
  private volatile IPersistentMap preferTable;
  private volatile Map methodCache;

  // private static final Var assoc = RT.var("clojure.core",
  // "assoc");
  // private static final Var dissoc = RT.var("clojure.core",
  // "dissoc");
  private static final Var isa = RT.var("clojure.core","isa?");
  private static final Var parents =
    RT.var("clojure.core","parents");

  public DynamicFn1 (final String name0, final IFn dispatchFn0,
                  final Object defaultDispatchVal0) {
    this.rw = new ReentrantReadWriteLock();
    this.name = name0;
    this.dispatchFn = dispatchFn0;
    this.defaultDispatchVal = defaultDispatchVal0;
    this.methodTable = PersistentHashMap.EMPTY;
    this.methodCache = new HashMap((Map) getMethodTable());
    this.preferTable = PersistentHashMap.EMPTY;
  }

  public final DynamicFn1 reset () {
    rw.writeLock().lock();
    try {
      methodTable = PersistentHashMap.EMPTY;
      methodCache = Collections.emptyMap();
      preferTable = PersistentHashMap.EMPTY;
      return this;
    }
    finally {
      rw.writeLock().unlock();
    }
  }

  public final DynamicFn1 addMethod (final Object dispatchVal,
                                  final IFn method) {
    rw.writeLock().lock();
    try {
      methodTable = getMethodTable().assoc(dispatchVal,method);
      resetCache();
      return this;
    }
    finally {
      rw.writeLock().unlock();
    }
  }

  public final DynamicFn1 removeMethod (final Object dispatchVal) {
    rw.writeLock().lock();
    try {
      methodTable = getMethodTable().without(dispatchVal);
      resetCache();
      return this;
    }
    finally {
      rw.writeLock().unlock();
    }
  }

  public final DynamicFn1 preferMethod (final Object dispatchValX,
                                     final Object dispatchValY) {
    rw.writeLock().lock();
    try {
      if (prefers(dispatchValY,
        dispatchValX)) { throw new IllegalStateException(
          String.format(
            "Preference conflict in multimethod '%s': %s is already preferred to %s",
            name,dispatchValY,dispatchValX)); }
      preferTable =
        getPreferTable().assoc(dispatchValX,
          RT.conj(
            (IPersistentCollection) RT.get(getPreferTable(),
              dispatchValX,PersistentHashSet.EMPTY),
            dispatchValY));
      resetCache();
      return this;
    }
    finally {
      rw.writeLock().unlock();
    }
  }

  private final boolean prefers (final Object x, final Object y) {
    final IPersistentSet xprefs =
      (IPersistentSet) getPreferTable().valAt(x);
    if ((xprefs != null) && xprefs.contains(y)) { return true; }
    for (ISeq ps = RT.seq(parents.invoke(y)); ps != null;
      ps = ps.next()) {
      if (prefers(x,ps.first())) { return true; }
    }
    for (ISeq ps = RT.seq(parents.invoke(x)); ps != null;
      ps = ps.next()) {
      if (prefers(ps.first(),y)) { return true; }
    }
    return false;
  }

  private static final boolean isA (final Object x, 
                                    final Object y) {
    if (x instanceof Signature2) {
      if (y instanceof Signature2) {
        return ((Signature2) y).isAssignableFrom((Signature2) x); }
      return false; }
    if (y instanceof Signature2) { return false; }
    return RT.booleanCast(isa.invoke(x,y)); }

  private final boolean dominates (final Object x,
                                   final Object y) {
    return prefers(x,y) || isA(x,y); }

  private Object lastSignature;
  private IFn lastFn;
  
  private final Map resetCache () {
    rw.writeLock().lock();
    try {
      lastSignature = null;
      lastFn = null;
      methodCache = new HashMap((Map) getMethodTable());
      return methodCache; }
    finally { rw.writeLock().unlock(); } }

  private final IFn findAndCacheBestMethod (final Object dispatchVal) {
    rw.readLock().lock();
    Object bestValue;
    final IPersistentMap mt = methodTable;
    final IPersistentMap pt = preferTable;
    try {
      Map.Entry bestEntry = null;
      for (final Object o : getMethodTable()) {
        final Map.Entry e = (Map.Entry) o;
        if (isA(dispatchVal,e.getKey())) {
          if ((bestEntry == null)
            || dominates(e.getKey(),bestEntry.getKey())) {
            bestEntry = e; }
          if (!dominates(bestEntry.getKey(),
            e.getKey())) { throw new IllegalArgumentException(
              String.format(
                "Multiple methods in multimethod '%s' " +
                  "match dispatch value: %s -> %s and %s, " + 
                  "and neither is preferred",
                name,dispatchVal,e.getKey(),
                bestEntry.getKey())); } } }
      if (bestEntry == null) {
        bestValue = methodTable.valAt(defaultDispatchVal);
        if (bestValue == null) { return null; } }
      else { bestValue = bestEntry.getValue(); } }
    finally { rw.readLock().unlock(); }

    // ensure basis has stayed stable throughout, else redo
    rw.writeLock().lock();
    try {
      if ((mt == methodTable) && (pt == preferTable)) {
        // place in cache
        methodCache.put(dispatchVal,bestValue);
        return (IFn) bestValue; }
      resetCache();
      return findAndCacheBestMethod(dispatchVal); }
    finally { rw.writeLock().unlock(); } }
  
  public final IFn getMethod (final Object dispatchVal) {
    final IFn targetFn = (IFn) methodCache.get(dispatchVal);
    if (targetFn != null) { return targetFn; }
    return findAndCacheBestMethod(dispatchVal); }

  
  synchronized private final IFn getFn (final Object signature) {
    if (signature.equals(lastSignature)) { return lastFn; }
    final IFn targetFn = getMethod(signature);
    lastSignature = signature;
    lastFn = targetFn;
    if (targetFn == null) { throw new IllegalArgumentException(
      String.format(
        "No method in multimethod '%s' for dispatch value: %s",
        name,signature)); }
    return targetFn; }


  @Override
  public final Object invoke () {
    return getFn(dispatchFn.invoke()).invoke();
  }

  @Override
  public final Object invoke (Object arg1) {
    return getFn(dispatchFn.invoke(arg1))
      .invoke(Util.ret1(arg1,arg1 = null));
  }

  @Override
  public final Object invoke (final Object arg1, 
                              final Object arg2) {
    final Object signature = dispatchFn.invoke(arg1,arg2);
    final IFn f = getFn(signature);
    return f.invoke(arg1,arg2); }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3)).invoke(
      Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
      Util.ret1(arg3,arg3 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4)).invoke(
      Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
      Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5))
      .invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6))
      .invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7) {
    return getFn(
      dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,arg7))
      .invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8) {
    return getFn(
      dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,arg7,arg8))
      .invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9)).invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10)).invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null),
        Util.ret1(arg10,arg10 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16))
      .invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null),
        Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16,
                              Object arg17) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,
      arg17)).invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null),
        Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null),
        Util.ret1(arg17,arg17 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16,
                              Object arg17, Object arg18) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,
      arg17,arg18)).invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null),
        Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null),
        Util.ret1(arg17,arg17 = null),
        Util.ret1(arg18,arg18 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16,
                              Object arg17, Object arg18,
                              Object arg19) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,
      arg17,arg18,arg19)).invoke(Util.ret1(arg1,arg1 = null),
        Util.ret1(arg2,arg2 = null),Util.ret1(arg3,arg3 = null),
        Util.ret1(arg4,arg4 = null),Util.ret1(arg5,arg5 = null),
        Util.ret1(arg6,arg6 = null),Util.ret1(arg7,arg7 = null),
        Util.ret1(arg8,arg8 = null),Util.ret1(arg9,arg9 = null),
        Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null),
        Util.ret1(arg17,arg17 = null),
        Util.ret1(arg18,arg18 = null),
        Util.ret1(arg19,arg19 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16,
                              Object arg17, Object arg18,
                              Object arg19, Object arg20) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,
      arg17,arg18,arg19,arg20)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null),
        Util.ret1(arg17,arg17 = null),
        Util.ret1(arg18,arg18 = null),
        Util.ret1(arg19,arg19 = null),
        Util.ret1(arg20,arg20 = null));
  }

  @Override
  public final Object invoke (Object arg1, Object arg2,
                              Object arg3, Object arg4,
                              Object arg5, Object arg6,
                              Object arg7, Object arg8,
                              Object arg9, Object arg10,
                              Object arg11, Object arg12,
                              Object arg13, Object arg14,
                              Object arg15, Object arg16,
                              Object arg17, Object arg18,
                              Object arg19, Object arg20,
                              final Object... args) {
    return getFn(dispatchFn.invoke(arg1,arg2,arg3,arg4,arg5,arg6,
      arg7,arg8,arg9,arg10,arg11,arg12,arg13,arg14,arg15,arg16,
      arg17,arg18,arg19,arg20,args)).invoke(
        Util.ret1(arg1,arg1 = null),Util.ret1(arg2,arg2 = null),
        Util.ret1(arg3,arg3 = null),Util.ret1(arg4,arg4 = null),
        Util.ret1(arg5,arg5 = null),Util.ret1(arg6,arg6 = null),
        Util.ret1(arg7,arg7 = null),Util.ret1(arg8,arg8 = null),
        Util.ret1(arg9,arg9 = null),Util.ret1(arg10,arg10 = null),
        Util.ret1(arg11,arg11 = null),
        Util.ret1(arg12,arg12 = null),
        Util.ret1(arg13,arg13 = null),
        Util.ret1(arg14,arg14 = null),
        Util.ret1(arg15,arg15 = null),
        Util.ret1(arg16,arg16 = null),
        Util.ret1(arg17,arg17 = null),
        Util.ret1(arg18,arg18 = null),
        Util.ret1(arg19,arg19 = null),
        Util.ret1(arg20,arg20 = null),args);
  }

  public final IPersistentMap getMethodTable () {
    return methodTable;
  }

  public final IPersistentMap getPreferTable () {
    return preferTable;
  }
}
