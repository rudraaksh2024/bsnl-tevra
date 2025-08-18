package com.ekn.gruzer.gaugelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

public class MultiGauge extends FullGauge {
    private float distance = 30.0f;
    private float gaugeBGWidth = 20.0f;
    private double secondMaxValue = 100.0d;
    private double secondMinValue = 0.0d;
    private List<Range> secondRanges = new ArrayList();
    private double secondValue = 0.0d;
    private double thirdMaxValue = 100.0d;
    private double thirdMinValue = 0.0d;
    private List<Range> thirdRanges = new ArrayList();
    private double thirdValue = 0.0d;

    /* access modifiers changed from: protected */
    public void drawValuePoint(Canvas canvas) {
    }

    private RectF getSecondRect() {
        return new RectF(getRectLeft() + getPadding() + this.distance, getRectTop() + getPadding() + this.distance, (getRectRight() - getPadding()) - this.distance, (getRectBottom() - getPadding()) - this.distance);
    }

    private RectF getThirdRect() {
        return new RectF(getRectLeft() + getPadding() + (this.distance * 2.0f), getRectTop() + getPadding() + (this.distance * 2.0f), (getRectRight() - getPadding()) - (this.distance * 2.0f), (getRectBottom() - getPadding()) - (this.distance * 2.0f));
    }

    public MultiGauge(Context context) {
        super(context);
        init();
    }

    public MultiGauge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MultiGauge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public MultiGauge(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public void init() {
        getGaugeBackGround().setStrokeWidth(this.gaugeBGWidth);
        getGaugeBackGround().setColor(Color.parseColor("#EAEAEA"));
        getTextPaint().setTextSize(35.0f);
        setPadding(20.0f);
        setDrawValueText(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBaseArc(canvas, getSecondRect(), getStartAngle(), getSweepAngle(), getGaugeBackGround(getSecondValue()));
        drawBaseArc(canvas, getThirdRect(), getStartAngle(), getSweepAngle(), getGaugeBackGround(getThirdValue()));
        RectF secondRect = getSecondRect();
        drawValueArcOnCanvas(canvas, secondRect, getStartAngle(), calculateSweepAngle(getSecondValue(), getSecondMinValue(), getSecondMaxValue()), getSecondValue(), getSecondRanges());
        RectF thirdRect = getThirdRect();
        drawValueArcOnCanvas(canvas, thirdRect, getStartAngle(), calculateSweepAngle(getThirdValue(), getThirdMinValue(), getThirdMaxValue()), getThirdValue(), getThirdRanges());
    }

    private Paint getRangePaint(double d, List<Range> list) {
        Paint paint = new Paint(1);
        paint.setStrokeWidth(this.gaugeBGWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getGaugeBackGround().getColor());
        paint.setStrokeCap(Paint.Cap.ROUND);
        for (Range next : list) {
            if (next.getTo() <= d) {
                paint.setColor(next.getColor());
            }
            if (next.getFrom() <= d && next.getTo() >= d) {
                paint.setColor(next.getColor());
            }
        }
        return paint;
    }

    public double getSecondValue() {
        return this.secondValue;
    }

    public void setSecondValue(double d) {
        this.secondValue = d;
        invalidate();
    }

    public double getThirdValue() {
        return this.thirdValue;
    }

    public void setThirdValue(double d) {
        this.thirdValue = d;
        invalidate();
    }

    public double getSecondMinValue() {
        return this.secondMinValue;
    }

    public void setSecondMinValue(double d) {
        this.secondMinValue = d;
    }

    public double getThirdMinValue() {
        return this.thirdMinValue;
    }

    public void setThirdMinValue(double d) {
        this.thirdMinValue = d;
    }

    public double getSecondMaxValue() {
        return this.secondMaxValue;
    }

    public void setSecondMaxValue(double d) {
        this.secondMaxValue = d;
    }

    public double getThirdMaxValue() {
        return this.thirdMaxValue;
    }

    public void setThirdMaxValue(double d) {
        this.thirdMaxValue = d;
    }

    public void addSecondRange(Range range) {
        this.secondRanges.add(range);
    }

    public void addThirdRange(Range range) {
        this.thirdRanges.add(range);
    }

    public List<Range> getSecondRanges() {
        return this.secondRanges;
    }

    public void setSeconRanges(List<Range> list) {
        this.secondRanges = list;
    }

    public List<Range> getThirdRanges() {
        return this.thirdRanges;
    }

    public void setThirdRanges(List<Range> list) {
        this.thirdRanges = list;
    }
}
