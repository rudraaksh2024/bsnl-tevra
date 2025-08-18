package com.ekn.gruzer.gaugelibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractGauge extends View {
    private ValueFormatter formatter = new ValueFormatterImpl();
    private int gaugeBGColor = Color.parseColor("#EAEAEA");
    private Paint gaugeBackGround;
    private double maxValue = 100.0d;
    private double minValue = 0.0d;
    private Paint needleColor;
    private float padding = 0.0f;
    private List<Range> ranges = new ArrayList();
    private float rectBottom = 400.0f;
    private RectF rectF;
    private float rectLeft = 0.0f;
    private float rectRight = 400.0f;
    private float rectTop = 0.0f;
    private Paint textPaint;
    private boolean useRangeBGColor = false;
    private double value = 0.0d;

    /* access modifiers changed from: protected */
    public int getCalculateValuePercentageOld(double d, double d2, double d3) {
        if (d >= d3) {
            return 0;
        }
        if (d2 <= d3) {
            return 100;
        }
        return (int) (((d3 - d) / (d2 - d)) * 100.0d);
    }

    public AbstractGauge(Context context) {
        super(context);
    }

    public AbstractGauge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AbstractGauge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AbstractGauge(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        getScaleRatio();
    }

    /* access modifiers changed from: protected */
    public RectF getRectF() {
        if (this.rectF == null) {
            float f = this.rectLeft;
            float f2 = this.padding;
            this.rectF = new RectF(f + f2, this.rectTop + f2, this.rectRight - f2, this.rectBottom - f2);
        }
        return this.rectF;
    }

    /* access modifiers changed from: protected */
    public Float getScaleRatio() {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        float min = ((float) Math.min(measuredHeight, measuredWidth)) / 1.0f;
        float max = ((float) Math.max(measuredHeight, measuredWidth)) / 1.0f;
        float f = min / 400.0f;
        float f2 = min / 200.0f;
        if (measuredWidth <= measuredHeight) {
            return Float.valueOf(f);
        }
        if (f2 > f) {
            return Float.valueOf(f);
        }
        return Float.valueOf(max / 400.0f);
    }

    public void addRange(Range range) {
        if (range != null) {
            this.ranges.add(range);
        }
    }

    public List<Range> getRanges() {
        return this.ranges;
    }

    public void setRanges(List<Range> list) {
        this.ranges = list;
    }

    /* access modifiers changed from: protected */
    public Paint getNeedlePaint() {
        if (this.needleColor == null) {
            Paint paint = new Paint();
            this.needleColor = paint;
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.needleColor.setAntiAlias(true);
            this.needleColor.setStyle(Paint.Style.FILL_AND_STROKE);
            this.needleColor.setStrokeWidth(5.0f);
        }
        return this.needleColor;
    }

    /* access modifiers changed from: protected */
    public Paint getGaugeBackGround() {
        if (this.gaugeBackGround == null) {
            Paint paint = new Paint();
            this.gaugeBackGround = paint;
            paint.setColor(this.gaugeBGColor);
            this.gaugeBackGround.setAntiAlias(true);
            this.gaugeBackGround.setStyle(Paint.Style.STROKE);
        }
        return this.gaugeBackGround;
    }

    /* access modifiers changed from: protected */
    public Paint getGaugeBackGround(double d) {
        if (this.useRangeBGColor) {
            getGaugeBackGround().setColor(getRangeColorForValue(d));
            getGaugeBackGround().setAlpha(20);
        }
        return getGaugeBackGround();
    }

    /* access modifiers changed from: protected */
    public int getRangeColorForValue(double d) {
        return getRangeColorForValue(d, this.ranges);
    }

    /* access modifiers changed from: protected */
    public int getRangeColorForValue(double d, List<Range> list) {
        int i = -7829368;
        for (Range next : list) {
            if (next.getTo() <= d) {
                i = next.getColor();
            }
            if (next.getFrom() <= d && next.getTo() >= d) {
                i = next.getColor();
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int getCalculateValuePercentage() {
        return getCalculateValuePercentage(getValue());
    }

    /* access modifiers changed from: protected */
    public int getCalculateValuePercentage(double d) {
        return getCalculateValuePercentage(getMinValue(), getMaxValue(), d);
    }

    /* access modifiers changed from: protected */
    public int getCalculateValuePercentage(double d, double d2, double d3) {
        int i = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i < 0 && d2 < 0.0d && d < d2) {
            return getCalculateValuePercentageUseCaseOne(d, d2, d3);
        }
        if (i < 0 && d2 < 0.0d && d > d2) {
            return getCalculateValuePercentageUseCaseTwo(d, d2, d3);
        }
        if ((d >= 0.0d && d2 < 0.0d) || (i < 0 && d2 >= 0.0d)) {
            if (d > d2) {
                return getCalculateValuePercentageUseCaseThree(d, d2, d3);
            }
            if (d < d2) {
                return getCalculateValuePercentageUseCaseFoure(d, d2, d3);
            }
        }
        return getCalculateValuePercentageOld(d, d2, d3);
    }

    private int getCalculateValuePercentageUseCaseOne(double d, double d2, double d3) {
        if (d3 <= Math.min(d, d2)) {
            return 0;
        }
        if (d3 >= Math.max(d, d2)) {
            return 100;
        }
        return (int) Math.abs(((Math.min(d, d2) - d3) / (Math.abs(Math.min(d, d2)) - Math.abs(Math.max(d, d2)))) * 100.0d);
    }

    private int getCalculateValuePercentageUseCaseTwo(double d, double d2, double d3) {
        if (d3 <= Math.min(d, d2)) {
            return 100;
        }
        if (d3 >= Math.max(d, d2)) {
            return 0;
        }
        return (int) Math.abs(((Math.max(d, d2) - d3) / (Math.abs(Math.min(d, d2)) - Math.abs(Math.max(d, d2)))) * 100.0d);
    }

    private int getCalculateValuePercentageUseCaseThree(double d, double d2, double d3) {
        double abs = Math.abs(d) + Math.abs(d2);
        if (d3 <= Math.min(d, d2)) {
            return 100;
        }
        if (d3 >= Math.max(d, d2)) {
            return 0;
        }
        return (int) Math.abs(((Math.max(d, d2) - d3) / abs) * 100.0d);
    }

    private int getCalculateValuePercentageUseCaseFoure(double d, double d2, double d3) {
        double abs = Math.abs(d) + Math.abs(d2);
        if (d3 <= Math.min(d, d2)) {
            return 0;
        }
        if (d3 >= Math.max(d, d2)) {
            return 100;
        }
        return (int) Math.abs(((Math.abs(Math.min(d, d2)) + d3) / abs) * 100.0d);
    }

    public void setValueColor(int i) {
        getTextPaint().setColor(i);
    }

    public int getValueColor() {
        return getTextPaint().getColor();
    }

    /* access modifiers changed from: protected */
    public Paint getTextPaint() {
        if (this.textPaint == null) {
            Paint paint = new Paint(1);
            this.textPaint = paint;
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.textPaint.setStyle(Paint.Style.FILL);
            this.textPaint.setTextSize(25.0f);
            this.textPaint.setTextAlign(Paint.Align.CENTER);
        }
        return this.textPaint;
    }

    public double getMinValue() {
        return this.minValue;
    }

    public void setMinValue(double d) {
        this.minValue = d;
    }

    public double getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(double d) {
        this.maxValue = d;
    }

    public double getValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public String getFormattedValue(double d) {
        String formattedValue = this.formatter.getFormattedValue(d);
        return formattedValue == null ? new ValueFormatterImpl().getFormattedValue(d) : formattedValue;
    }

    /* access modifiers changed from: protected */
    public String getFormattedValue() {
        return getFormattedValue(getValue());
    }

    public void setValue(double d) {
        this.value = d;
        invalidate();
    }

    public void setFormatter(ValueFormatter valueFormatter) {
        this.formatter = valueFormatter;
    }

    public void setNeedleColor(int i) {
        getNeedlePaint().setColor(i);
    }

    /* access modifiers changed from: protected */
    public float getRectTop() {
        return this.rectTop;
    }

    /* access modifiers changed from: protected */
    public void setRectTop(float f) {
        this.rectTop = f;
    }

    /* access modifiers changed from: protected */
    public float getRectLeft() {
        return this.rectLeft;
    }

    /* access modifiers changed from: protected */
    public void setRectLeft(float f) {
        this.rectLeft = f;
    }

    /* access modifiers changed from: protected */
    public float getRectRight() {
        return this.rectRight;
    }

    /* access modifiers changed from: protected */
    public void setRectRight(float f) {
        this.rectRight = f;
    }

    /* access modifiers changed from: protected */
    public float getRectBottom() {
        return this.rectBottom;
    }

    /* access modifiers changed from: protected */
    public void setRectBottom(float f) {
        this.rectBottom = f;
    }

    public float getPadding() {
        return this.padding;
    }

    public void setPadding(float f) {
        this.padding = f;
    }

    public boolean isUseRangeBGColor() {
        return this.useRangeBGColor;
    }

    public void setUseRangeBGColor(boolean z) {
        this.useRangeBGColor = z;
    }

    public void setGaugeBackGroundColor(int i) {
        this.gaugeBackGround.setColor(i);
        this.gaugeBGColor = i;
    }

    public int getGaugeBackgroundColor() {
        return this.gaugeBGColor;
    }
}
