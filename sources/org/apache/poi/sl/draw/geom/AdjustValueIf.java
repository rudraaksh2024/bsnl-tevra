package org.apache.poi.sl.draw.geom;

public interface AdjustValueIf extends GuideIf {
    double evaluate(Context context) {
        return evaluateAdjustValue(context);
    }

    double evaluateAdjustValue(Context context) {
        GuideIf adjustValue = context.getAdjustValue(getName());
        return adjustValue != null ? adjustValue.evaluate(context) : evaluateGuide(context);
    }
}
