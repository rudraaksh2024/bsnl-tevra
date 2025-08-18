package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

class FourthMoment extends ThirdMoment implements Serializable {
    private static final long serialVersionUID = 4763990447117157611L;
    private double m4;

    FourthMoment() {
        this.m4 = Double.NaN;
    }

    FourthMoment(FourthMoment fourthMoment) throws NullArgumentException {
        copy(fourthMoment, this);
    }

    public void increment(double d) {
        if (this.n < 1) {
            this.m4 = 0.0d;
            this.m3 = 0.0d;
            this.m2 = 0.0d;
            this.m1 = 0.0d;
        }
        double d2 = this.m3;
        double d3 = this.m2;
        super.increment(d);
        double d4 = (double) this.n;
        double d5 = d4 - 1.0d;
        this.m4 = (this.m4 - ((this.nDev * 4.0d) * d2)) + (this.nDevSq * 6.0d * d3) + (((d4 * d4) - (3.0d * d5)) * this.nDevSq * this.nDevSq * d5 * d4);
    }

    public double getResult() {
        return this.m4;
    }

    public void clear() {
        super.clear();
        this.m4 = Double.NaN;
    }

    public FourthMoment copy() {
        FourthMoment fourthMoment = new FourthMoment();
        copy(this, fourthMoment);
        return fourthMoment;
    }

    public static void copy(FourthMoment fourthMoment, FourthMoment fourthMoment2) throws NullArgumentException {
        MathUtils.checkNotNull(fourthMoment);
        MathUtils.checkNotNull(fourthMoment2);
        ThirdMoment.copy(fourthMoment, fourthMoment2);
        fourthMoment2.m4 = fourthMoment.m4;
    }
}
