package org.apache.poi.sl.draw.geom;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Context {
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.(\\p{Digit}+)([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
    private final Rectangle2D _anchor;
    private final Map<String, Double> _ctx = new HashMap();
    private final IAdjustableShape _props;

    public Context(CustomGeometry customGeometry, Rectangle2D rectangle2D, IAdjustableShape iAdjustableShape) {
        this._props = iAdjustableShape;
        this._anchor = rectangle2D;
        for (AdjustValueIf evaluate : customGeometry.adjusts) {
            evaluate(evaluate);
        }
        for (GuideIf evaluate2 : customGeometry.guides) {
            evaluate(evaluate2);
        }
    }

    /* access modifiers changed from: package-private */
    public Rectangle2D getShapeAnchor() {
        return this._anchor;
    }

    /* access modifiers changed from: package-private */
    public GuideIf getAdjustValue(String str) {
        return this._props.getAdjustValue(str);
    }

    public double getValue(String str) {
        if (DOUBLE_PATTERN.matcher(str).matches()) {
            return Double.parseDouble(str);
        }
        return this._ctx.containsKey(str) ? this._ctx.get(str).doubleValue() : evaluate(BuiltInGuide.valueOf("_" + str));
    }

    public double evaluate(Formula formula) {
        String name;
        double evaluate = formula.evaluate(this);
        if ((formula instanceof GuideIf) && (name = ((GuideIf) formula).getName()) != null) {
            this._ctx.put(name, Double.valueOf(evaluate));
        }
        return evaluate;
    }
}
