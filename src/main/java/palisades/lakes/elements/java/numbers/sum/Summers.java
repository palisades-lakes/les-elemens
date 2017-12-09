package palisades.lakes.elements.java.numbers.sum;

import java.util.DoubleSummaryStatistics;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.function.DoubleBinaryOperator;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedSummaryStatistics;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import com.google.common.math.Stats;
import com.google.common.math.StatsAccumulator;

//----------------------------------------------------------------
/** Accumulators from various libraries.
 *
 * @author mcdonald dot john dot alan at gmail dot com
 * @since 2017-05-18
 * @version 2017-05-18
 */

public final class Summers {

  //--------------------------------------------------------------

  private static final DoubleBinaryOperator
  plus = (final double z0, final double z1) -> z0 + z1;

  public static final double
  doubleAccumulator (final double[] z) {
    final DoubleAccumulator a = new DoubleAccumulator(plus,0.0);
    for (final double zi : z) { a.accumulate(zi); }
    return a.doubleValue();  }

  //--------------------------------------------------------------

  public static final double
  doubleAdder (final double[] z) {
    final DoubleAdder a = new DoubleAdder();
    for (final double zi : z) { a.add(zi); }
    return a.doubleValue();  }

  //--------------------------------------------------------------

  public static final double
  doubleSummaryStatistics (final double[] z) {
    final DoubleSummaryStatistics a =
      new DoubleSummaryStatistics();
    for (final double zi : z) { a.accept(zi); }
    return a.getSum();  }

  //--------------------------------------------------------------

  public static final double
  stats (final double[] z) { return Stats.of(z).sum();  }

  //--------------------------------------------------------------

  public static final double
  statsAccumulator (final double[] z) {
    final StatsAccumulator a = new StatsAccumulator();
    a.addAll(z);
    return a.sum();  }

  //--------------------------------------------------------------

  public static final double
  descriptiveStatistics (final double[] z) {
    final DescriptiveStatistics a = new DescriptiveStatistics(z);
    return a.getSum();  }

  //--------------------------------------------------------------

  public static final double
  statUtils (final double[] z) {
    return StatUtils.sum(z);  }

  //--------------------------------------------------------------

  public static final double
  math3Sum (final double[] z) { return new Sum().evaluate(z);  }

  //--------------------------------------------------------------

  public static final double
  summaryStatistics (final double[] z) {
    final SummaryStatistics a = new SummaryStatistics();
    for (final double zi : z) { a.addValue(zi); }
    return a.getSum();  }

  //--------------------------------------------------------------

  public static final double
  synchronizedSummaryStatistics (final double[] z) {
    final SynchronizedSummaryStatistics a =
      new SynchronizedSummaryStatistics();
    for (final double zi : z) { a.addValue(zi); }
    return a.getSum();  }

  //--------------------------------------------------------------
  // construction
  //--------------------------------------------------------------

  private Summers () {
    throw new UnsupportedOperationException("can't instantiate" +
      getClass().getSimpleName()); }

  //--------------------------------------------------------------
} // end of class
//--------------------------------------------------------------
