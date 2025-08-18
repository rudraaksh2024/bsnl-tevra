package com.ekn.gruzer.gaugelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;
import java.util.List;

public class HalfGauge extends AbstractGauge {
    private float currentAngle = 30.0f;
    private boolean enableAnimation = true;
    private boolean enableBackGroundShadow = true;
    private boolean enableNeedleShadow = true;
    private Handler handler = new Handler();
    private int maxValueTextColor = -7829368;
    private int minValueTextColor = -7829368;
    private Integer needleAngleNext;
    private float needleEnd = 150.0f;
    private float needleStart = 30.0f;
    private Runnable runnable = new Runnable() {
        public void run() {
            HalfGauge.this.invalidate();
        }
    };
    private float startAngle = 210.0f;
    private float sweepAngle = 120.0f;

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

    public /* bridge */ /* synthetic */ void setValueColor(int i) {
        super.setValueColor(i);
    }

    public HalfGauge(Context context) {
        super(context);
        init();
    }

    public HalfGauge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public HalfGauge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public HalfGauge(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        getGaugeBackGround().setStrokeWidth(100.0f);
        setPadding(20.0f);
    }

    private void drawShadow() {
        if (this.enableBackGroundShadow) {
            getGaugeBackGround().setShadowLayer(15.0f, 0.0f, 5.0f, 1342177280);
            setLayerType(2, getGaugeBackGround());
        }
        if (this.enableNeedleShadow) {
            getNeedlePaint().setShadowLayer(10.0f, 0.0f, 5.0f, 1342177280);
            setLayerType(2, getNeedlePaint());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            drawShadow();
            canvas.save();
            canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
            canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
            canvas.drawArc(getRectF(), this.startAngle, this.sweepAngle, false, getGaugeBackGround());
            drawRange(canvas);
            canvas.restore();
            canvas.save();
            canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
            canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
            canvas.rotate((float) getNeedleAngle(), getRectRight() / 2.0f, getRectBottom() / 2.0f);
            Canvas canvas2 = canvas;
            canvas2.drawLine(-30.0f, 200.0f, 200.0f, 200.0f, getNeedlePaint());
            canvas2.drawOval(190.0f, 190.0f, 210.0f, 210.0f, getNeedlePaint());
            canvas.restore();
            canvas.save();
            canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
            canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
            canvas.drawText(getFormattedValue() + "", 200.0f, 240.0f, getTextPaint());
            canvas.restore();
            drawValueText(canvas);
            drawMinValue(canvas);
            drawMaxValue(canvas);
        }
    }

    private void drawValueText(Canvas canvas) {
        canvas.save();
        canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
        canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
        canvas.drawText(getFormattedValue() + "", 200.0f, 240.0f, getTextPaint());
        canvas.restore();
    }

    private void drawMinValue(Canvas canvas) {
        canvas.save();
        canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
        canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
        canvas.rotate(26.0f, 10.0f, 130.0f);
        canvas.drawText(getFormattedValue(getMinValue()) + "", getPadding() + 10.0f, 130.0f, getRangeValue(getMinValueTextColor()));
        canvas.restore();
    }

    private void drawMaxValue(Canvas canvas) {
        canvas.save();
        canvas.translate((((float) getWidth()) / 2.0f) - ((getRectRight() / 2.0f) * getScaleRatio().floatValue()), (((float) getHeight()) / 2.0f) - (getScaleRatio().floatValue() * 50.0f));
        canvas.scale(getScaleRatio().floatValue(), getScaleRatio().floatValue());
        canvas.rotate(-26.0f, 390.0f, 130.0f);
        canvas.drawText(getFormattedValue(getMaxValue()) + "", 390.0f - getPadding(), 130.0f, getRangeValue(getMaxValueTextColor()));
        canvas.restore();
    }

    private void drawRange(Canvas canvas) {
        for (Range range : getRanges()) {
            Canvas canvas2 = canvas;
            canvas2.drawArc(getRectF(), calculateStartAngle(range.getFrom()), calculateSweepAngle(range.getFrom(), range.getTo()), false, getRangePaint(range.getColor()));
        }
    }

    private float calculateStartAngle(double d) {
        return ((this.sweepAngle / 100.0f) * ((float) getCalculateValuePercentage(d))) + this.startAngle;
    }

    private float calculateSweepAngle(double d, double d2) {
        return (((this.sweepAngle / 100.0f) * ((float) getCalculateValuePercentage(d2))) - ((this.sweepAngle / 100.0f) * ((float) getCalculateValuePercentage(d)))) + 0.5f;
    }

    public int getNeedleAngle() {
        Integer num = this.needleAngleNext;
        if (num == null || !this.enableAnimation) {
            this.currentAngle = (((this.needleEnd - this.needleStart) / 100.0f) * ((float) getCalculateValuePercentage())) + this.needleStart;
        } else if (((float) num.intValue()) != this.currentAngle) {
            float f = this.currentAngle;
            if (((float) this.needleAngleNext.intValue()) < f) {
                this.currentAngle = f - 1.0f;
            } else {
                this.currentAngle = f + 1.0f;
            }
            this.handler.postDelayed(this.runnable, 5);
        }
        return (int) this.currentAngle;
    }

    public void setValue(double d) {
        super.setValue(d);
        this.needleAngleNext = Integer.valueOf((int) ((((this.needleEnd - this.needleStart) / 100.0f) * ((float) getCalculateValuePercentage())) + this.needleStart));
    }

    /* access modifiers changed from: protected */
    public Paint getRangePaint(int i) {
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getGaugeBackGround().getStrokeWidth());
        return paint;
    }

    /* access modifiers changed from: protected */
    public Paint getRangeValue(int i) {
        Paint paint = new Paint(1);
        paint.setColor(i);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(15.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    public boolean isEnableBackGroundShadow() {
        return this.enableBackGroundShadow;
    }

    public void setEnableBackGroundShadow(boolean z) {
        this.enableBackGroundShadow = z;
    }

    public boolean isEnableNeedleShadow() {
        return this.enableNeedleShadow;
    }

    public void setEnableNeedleShadow(boolean z) {
        this.enableNeedleShadow = z;
    }

    public void enableAnimation(boolean z) {
        this.enableAnimation = z;
    }

    public boolean isEnableAnimation() {
        return this.enableAnimation;
    }

    public void setMinValueTextColor(int i) {
        this.minValueTextColor = i;
    }

    public void setMaxValueTextColor(int i) {
        this.maxValueTextColor = i;
    }

    public int getMinValueTextColor() {
        return this.minValueTextColor;
    }

    public int getMaxValueTextColor() {
        return this.maxValueTextColor;
    }
}
