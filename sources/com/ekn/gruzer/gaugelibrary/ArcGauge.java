package com.ekn.gruzer.gaugelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class ArcGauge extends FullGauge {
    private float gaugeBGWidth = 20.0f;
    private float startAngle = 150.0f;
    private float sweepAngle = 240.0f;

    /* access modifiers changed from: protected */
    public void drawValuePoint(Canvas canvas) {
    }

    public ArcGauge(Context context) {
        super(context);
        init();
    }

    public ArcGauge(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ArcGauge(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public ArcGauge(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        getGaugeBackGround().setStrokeWidth(this.gaugeBGWidth);
        getGaugeBackGround().setStrokeCap(Paint.Cap.ROUND);
        getGaugeBackGround().setColor(Color.parseColor("#D6D6D6"));
        getTextPaint().setTextSize(35.0f);
        setPadding(20.0f);
        setSweepAngle(this.sweepAngle);
        setStartAngle(this.startAngle);
    }
}
