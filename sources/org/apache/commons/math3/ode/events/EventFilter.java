package org.apache.commons.math3.ode.events;

import java.util.Arrays;
import org.apache.commons.math3.ode.events.EventHandler;

public class EventFilter implements EventHandler {
    private static final int HISTORY_SIZE = 100;
    private double extremeT;
    private final FilterType filter;
    private boolean forward;
    private final EventHandler rawHandler;
    private final Transformer[] transformers = new Transformer[100];
    private final double[] updates = new double[100];

    public EventFilter(EventHandler eventHandler, FilterType filterType) {
        this.rawHandler = eventHandler;
        this.filter = filterType;
    }

    public void init(double d, double[] dArr, double d2) {
        this.rawHandler.init(d, dArr, d2);
        boolean z = d2 >= d;
        this.forward = z;
        this.extremeT = z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        Arrays.fill(this.transformers, Transformer.UNINITIALIZED);
        Arrays.fill(this.updates, this.extremeT);
    }

    public double g(double d, double[] dArr) {
        double g = this.rawHandler.g(d, dArr);
        boolean z = this.forward;
        int i = 0;
        if (z) {
            Transformer[] transformerArr = this.transformers;
            int length = transformerArr.length - 1;
            if (this.extremeT < d) {
                Transformer transformer = transformerArr[length];
                Transformer selectTransformer = this.filter.selectTransformer(transformer, g, z);
                if (selectTransformer != transformer) {
                    double[] dArr2 = this.updates;
                    System.arraycopy(dArr2, 1, dArr2, 0, length);
                    Transformer[] transformerArr2 = this.transformers;
                    System.arraycopy(transformerArr2, 1, transformerArr2, 0, length);
                    this.updates[length] = this.extremeT;
                    this.transformers[length] = selectTransformer;
                }
                this.extremeT = d;
                return selectTransformer.transformed(g);
            }
            while (length > 0) {
                if (this.updates[length] <= d) {
                    return this.transformers[length].transformed(g);
                }
                length--;
            }
            return this.transformers[0].transformed(g);
        } else if (d < this.extremeT) {
            Transformer transformer2 = this.transformers[0];
            Transformer selectTransformer2 = this.filter.selectTransformer(transformer2, g, z);
            if (selectTransformer2 != transformer2) {
                double[] dArr3 = this.updates;
                System.arraycopy(dArr3, 0, dArr3, 1, dArr3.length - 1);
                Transformer[] transformerArr3 = this.transformers;
                System.arraycopy(transformerArr3, 0, transformerArr3, 1, transformerArr3.length - 1);
                this.updates[0] = this.extremeT;
                this.transformers[0] = selectTransformer2;
            }
            this.extremeT = d;
            return selectTransformer2.transformed(g);
        } else {
            while (true) {
                double[] dArr4 = this.updates;
                if (i >= dArr4.length - 1) {
                    return this.transformers[dArr4.length - 1].transformed(g);
                }
                if (d <= dArr4[i]) {
                    return this.transformers[i].transformed(g);
                }
                i++;
            }
        }
    }

    public EventHandler.Action eventOccurred(double d, double[] dArr, boolean z) {
        return this.rawHandler.eventOccurred(d, dArr, this.filter.getTriggeredIncreasing());
    }

    public void resetState(double d, double[] dArr) {
        this.rawHandler.resetState(d, dArr);
    }
}
