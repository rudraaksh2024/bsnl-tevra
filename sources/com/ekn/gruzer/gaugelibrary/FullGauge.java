package com.ekn.gruzer.gaugelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;
import java.util.List;

public class FullGauge extends AbstractGauge {
    private boolean displayValuePoint = false;
    protected boolean drawValueText = true;
    private float gaugeBGWidth = 20.0f;
    private float startAngle = 270.0f;
    private float sweepAngle = 360.0f;

    public /* bridge */ /* synthetic */ void addRange(Range range) {
        super.addRange(range);
    }

    public /* bridge */ /* synthetic */ int getGaugeBackgroundColor() {
        return super.getGaugeBackgroundColor();
    }

    public /* bridge */ /* synthetic */ double getMaxValue() {
        return super.getMaxValue();
    }

    public /* bridge */ /* synthetic */ double getMinValue() {
        return super.getMinValue();
    }

    public /* bridge */ /* synthetic */ float getPadding() {
        return super.getPadding();
    }

    public /* bridge */ /* synthetic */ List getRanges() {
        return super.getRanges();
    }

    public /* bridge */ /* synthetic */ double getValue() {
        return super.getValue();
    }

    public /* bridge */ /* synthetic */ int getValueColor() {
        return super.getValueColor();
    }

    public /* bridge */ /* synthetic */ boolean isUseRangeBGColor() {
        return super.isUseRangeBGColor();
    }

    public /* bridge */ /* synthetic */ void setFormatter(ValueFormatter valueFormatter) {
        super.setFormatter(valueFormatter);
    }

    public /* bridge */ /* synthetic */ void setGaugeBackGroundColor(int i) {
        super.setGaugeBackGroundColor(i);
    }

    public /* bridge */ /* synthetic */ void setMaxValue(double d) {
        super.setMaxValue(d);
    }

    public /* bridge */ /* synthetic */ void setMinValue(double d) {
        super.setMinValue(d);
    }

    public /* bridge */ /* synthetic */ void setNeedleColor(int i) {
        super.setNeedleColor(i);
    }

    public /* bridge */ /* synthetic */ void setPadding(float f) {
        super.setPadding(f);
    }

    public /* bridge */ /* synthetic */ void setRanges(List list) {
        super.setRanges(list);
    }

    public /* bridge */ /* synthetic */ void setUseRangeBGColor(boolean z) {
        super.setUseRangeBGColor(z);
    }

    public /* bridge */ /* synthetic */ void setValue(double d) {
        super.setValue(d);
    }

    public /* bridge */ /* synthetic */ void setValueColor(int i) {
        super.setValueColor(i);
    }

    public FullGauge(Context context) {
        super(context);
        init();
    }

    public FullGauge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FullGauge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public FullGauge(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        getGaugeBackGround().setStrokeWidth(this.gaugeBGWidth);
        getTextPaint().setTextSize(35.0f);
        setPadding(20.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBaseArc(canvas);
        drawValueArcOnCanvas(canvas);
        drawValueText(canvas);
        drawValuePoint(canvas);
    }

    private void drawBaseArc(Canvas canvas) {
        drawBaseArc(canvas, getRectF(), this.startAngle, this.sweepAngle, getGaugeBackGround(getValue()));
    }

    /* access modifiers changed from: protected */
    public void drawBaseArc(Canvas canvas, RectF rectF, float f, float f2, Paint paint) {
        prepareCanvas(canvas);
        canvas.drawArc(rectF, f, f2, false, paint);
        finishCanvas(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawValuePoint(Canvas canvas) {
        if (this.displayValuePoint) {
            prepareCanvas(canvas);
            canvas.rotate(calculateSweepAngle(getValue(), getMinValue(), getMaxValue()), getRectRight() / 2.0f, getRectBottom() / 2.0f);
            canvas.drawCircle(200.0f, getPadding(), 8.0f, getRangePaintForValue(getValue(), getRanges()));
            Canvas canvas2 = canvas;
            canvas2.drawLine(197.0f, 11.0f, 206.0f, 19.0f, getArrowPaint());
            canvas2.drawLine(206.0f, 20.0f, 197.0f, 27.0f, getArrowPaint());
            finishCanvas(canvas);
        }
    }

    private Paint getArrowPaint() {
        Paint paint = new Paint(1);
        paint.setStrokeWidth(4.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(-1);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    /* access modifiers changed from: protected */
    public void prepareCanvas(Canvas canvas) {
        canvas.save();
        canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 200.0f));
        canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
    }

    /* access modifiers changed from: protected */
    public void finishCanvas(Canvas canvas) {
        canvas.restore();
    }

    private void drawValueText(Canvas canvas) {
        if (this.drawValueText) {
            canvas.save();
            canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 220.0f));
            canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
            canvas.drawText(getFormattedValue() + "", 200.0f, 240.0f, getTextPaint());
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public Paint getRangePaintForValue(double d, List<Range> list) {
        Paint paint = new Paint(1);
        paint.setStrokeWidth(this.gaugeBGWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(getRangeColorForValue(d, list));
        return paint;
    }

    private void drawValueArcOnCanvas(Canvas canvas) {
        Canvas canvas2 = canvas;
        drawValueArcOnCanvas(canvas2, getRectF(), getStartAngle(), calculateSweepAngle(getValue(), getMinValue(), getMaxValue()), getValue(), getRanges());
    }

    /* access modifiers changed from: protected */
    public void drawValueArcOnCanvas(Canvas canvas, RectF rectF, float f, float f2, double d, List<Range> list) {
        prepareCanvas(canvas);
        canvas.drawArc(rectF, f, f2, false, getRangePaintForValue(d, list));
        finishCanvas(canvas);
    }

    /* access modifiers changed from: protected */
    public float calculateSweepAngle(double d, double d2, double d3) {
        return (this.sweepAngle / 100.0f) * ((float) getCalculateValuePercentage(d2, d3, d));
    }

    /* access modifiers changed from: protected */
    public float getSweepAngle() {
        return this.sweepAngle;
    }

    /* access modifiers changed from: protected */
    public void setSweepAngle(float f) {
        this.sweepAngle = f;
    }

    /* access modifiers changed from: protected */
    public float getStartAngle() {
        return this.startAngle;
    }

    /* access modifiers changed from: protected */
    public void setStartAngle(float f) {
        this.startAngle = f;
    }

    /* access modifiers changed from: protected */
    public float getGaugeBGWidth() {
        return this.gaugeBGWidth;
    }

    /* access modifiers changed from: protected */
    public void setGaugeBGWidth(float f) {
        this.gaugeBGWidth = f;
    }

    public boolean isDisplayValuePoint() {
        return this.displayValuePoint;
    }

    public void setDisplayValuePoint(boolean z) {
        this.displayValuePoint = z;
    }

    /* access modifiers changed from: protected */
    public boolean isDrawValueText() {
        return this.drawValueText;
    }

    /* access modifiers changed from: protected */
    public void setDrawValueText(boolean z) {
        this.drawValueText = z;
    }
}
