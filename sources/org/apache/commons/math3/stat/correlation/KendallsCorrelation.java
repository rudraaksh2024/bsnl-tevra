package org.apache.commons.math3.stat.correlation;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

public class KendallsCorrelation {
    private final RealMatrix correlationMatrix;

    public KendallsCorrelation() {
        this.correlationMatrix = null;
    }

    public KendallsCorrelation(double[][] dArr) {
        this(MatrixUtils.createRealMatrix(dArr));
    }

    public KendallsCorrelation(RealMatrix realMatrix) {
        this.correlationMatrix = computeCorrelationMatrix(realMatrix);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.correlationMatrix;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix realMatrix) {
        int columnDimension = realMatrix.getColumnDimension();
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(columnDimension, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < i; i2++) {
                double correlation = correlation(realMatrix.getColumn(i), realMatrix.getColumn(i2));
                blockRealMatrix.setEntry(i, i2, correlation);
                blockRealMatrix.setEntry(i2, i, correlation);
            }
            blockRealMatrix.setEntry(i, i, 1.0d);
        }
        return blockRealMatrix;
    }

    public RealMatrix computeCorrelationMatrix(double[][] dArr) {
        return computeCorrelationMatrix((RealMatrix) new BlockRealMatrix(dArr));
    }

    public double correlation(double[] dArr, double[] dArr2) throws DimensionMismatchException {
        int i;
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (dArr3.length == dArr4.length) {
            int length = dArr3.length;
            long sum = sum((long) (length - 1));
            Pair[] pairArr = new Pair[length];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                pairArr[i3] = new Pair(Double.valueOf(dArr3[i3]), Double.valueOf(dArr4[i3]));
            }
            Arrays.sort(pairArr, new Comparator<Pair<Double, Double>>() {
                public int compare(Pair<Double, Double> pair, Pair<Double, Double> pair2) {
                    int compareTo = pair.getFirst().compareTo(pair2.getFirst());
                    return compareTo != 0 ? compareTo : pair.getSecond().compareTo(pair2.getSecond());
                }
            });
            Pair pair = pairArr[0];
            long j = 1;
            long j2 = 1;
            int i4 = 1;
            long j3 = 0;
            long j4 = 0;
            while (i4 < length) {
                Pair pair2 = pairArr[i4];
                if (((Double) pair2.getFirst()).equals(pair.getFirst())) {
                    j++;
                    if (((Double) pair2.getSecond()).equals(pair.getSecond())) {
                        j2++;
                    } else {
                        j4 += sum(j2 - 1);
                        j2 = 1;
                    }
                } else {
                    j3 += sum(j - 1);
                    j4 += sum(j2 - 1);
                    j = 1;
                    j2 = 1;
                }
                i4++;
                pair = pair2;
            }
            long sum2 = j3 + sum(j - 1);
            long sum3 = j4 + sum(j2 - 1);
            Pair[] pairArr2 = new Pair[length];
            int i5 = 1;
            long j5 = 0;
            while (i5 < length) {
                int i6 = i2;
                while (i6 < length) {
                    int min = FastMath.min(i6 + i5, length);
                    int min2 = FastMath.min(min + i5, length);
                    int i7 = i6;
                    int i8 = i7;
                    int i9 = min;
                    while (true) {
                        if (i7 >= min && i9 >= min2) {
                            break;
                        }
                        if (i7 < min) {
                            if (i9 < min2) {
                                i = i6;
                                if (((Double) pairArr[i7].getSecond()).compareTo((Double) pairArr[i9].getSecond()) <= 0) {
                                    pairArr2[i8] = pairArr[i7];
                                } else {
                                    pairArr2[i8] = pairArr[i9];
                                    j5 += (long) (min - i7);
                                    i9++;
                                }
                            } else {
                                i = i6;
                                pairArr2[i8] = pairArr[i7];
                            }
                            i7++;
                        } else {
                            i = i6;
                            pairArr2[i8] = pairArr[i9];
                            i9++;
                        }
                        i8++;
                        i6 = i;
                    }
                    i6 += i5 * 2;
                }
                i5 <<= 1;
                i2 = 0;
                Pair[] pairArr3 = pairArr;
                pairArr = pairArr2;
                pairArr2 = pairArr3;
            }
            Pair pair3 = pairArr[i2];
            int i10 = 1;
            long j6 = 0;
            long j7 = 1;
            while (i10 < length) {
                Pair pair4 = pairArr[i10];
                if (((Double) pair4.getSecond()).equals(pair3.getSecond())) {
                    j7++;
                } else {
                    j6 += sum(j7 - 1);
                    j7 = 1;
                }
                i10++;
                pair3 = pair4;
            }
            long sum4 = j6 + sum(j7 - 1);
            long j8 = sum - sum2;
            return ((double) (((j8 - sum4) + sum3) - (j5 * 2))) / FastMath.sqrt(((double) j8) * ((double) (sum - sum4)));
        }
        throw new DimensionMismatchException(dArr3.length, dArr4.length);
    }

    private static long sum(long j) {
        return (j * (1 + j)) / 2;
    }
}
