package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF();
    private RectF mBarShadowRectBuffer = new RectF();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public void drawExtras(Canvas canvas) {
    }

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = barDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint = new Paint(1);
        this.mShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mBarBorderPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        IBarDataSet iBarDataSet2 = iBarDataSet;
        int i2 = i;
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        int i3 = 0;
        boolean z = true;
        boolean z2 = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil((double) (((float) iBarDataSet.getEntryCount()) * phaseX)), iBarDataSet.getEntryCount());
            for (int i4 = 0; i4 < min; i4++) {
                float x = ((BarEntry) iBarDataSet2.getEntryForIndex(i4)).getX();
                this.mBarShadowRectBuffer.left = x - barWidth;
                this.mBarShadowRectBuffer.right = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (!this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    Canvas canvas2 = canvas;
                } else if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                    break;
                } else {
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        Canvas canvas3 = canvas;
        BarBuffer barBuffer = this.mBarBuffers[i2];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i2);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet2);
        transformer.pointValuesToPixel(barBuffer.buffer);
        if (iBarDataSet.getColors().size() != 1) {
            z = false;
        }
        if (z) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        while (i3 < barBuffer.size()) {
            int i5 = i3 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i5])) {
                if (this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i3])) {
                    if (!z) {
                        this.mRenderPaint.setColor(iBarDataSet2.getColor(i3 / 4));
                    }
                    if (iBarDataSet.getGradientColor() != null) {
                        GradientColor gradientColor = iBarDataSet.getGradientColor();
                        this.mRenderPaint.setShader(new LinearGradient(barBuffer.buffer[i3], barBuffer.buffer[i3 + 3], barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                    }
                    if (iBarDataSet.getGradientColors() != null) {
                        int i6 = i3 / 4;
                        this.mRenderPaint.setShader(new LinearGradient(barBuffer.buffer[i3], barBuffer.buffer[i3 + 3], barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], iBarDataSet2.getGradientColor(i6).getStartColor(), iBarDataSet2.getGradientColor(i6).getEndColor(), Shader.TileMode.MIRROR));
                    }
                    int i7 = i3 + 1;
                    int i8 = i3 + 3;
                    canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i7], barBuffer.buffer[i5], barBuffer.buffer[i8], this.mRenderPaint);
                    if (z2) {
                        canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i7], barBuffer.buffer[i5], barBuffer.buffer[i8], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
            i3 += 4;
            Canvas canvas4 = canvas;
        }
    }

    /* access modifiers changed from: protected */
    public void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void drawValues(Canvas canvas) {
        boolean z;
        float f;
        float f2;
        List list;
        boolean z2;
        MPPointF mPPointF;
        int i;
        float f3;
        boolean z3;
        Transformer transformer;
        float[] fArr;
        float f4;
        int i2;
        int i3;
        BarEntry barEntry;
        float[] fArr2;
        float f5;
        float f6;
        float f7;
        BarEntry barEntry2;
        List list2;
        int i4;
        ValueFormatter valueFormatter;
        MPPointF mPPointF2;
        float f8;
        BarEntry barEntry3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i5 = 0;
            while (i5 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i5);
                if (!shouldDrawValues(iBarDataSet)) {
                    list = dataSets;
                    f2 = f;
                    z2 = z;
                } else {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, "8");
                    float f9 = z ? -f : calcTextHeight + f;
                    float f10 = z ? calcTextHeight + f : -f;
                    if (isInverted) {
                        f9 = (-f9) - calcTextHeight;
                        f10 = (-f10) - calcTextHeight;
                    }
                    float f11 = f9;
                    float f12 = f10;
                    BarBuffer barBuffer = this.mBarBuffers[i5];
                    float phaseY = this.mAnimator.getPhaseY();
                    ValueFormatter valueFormatter2 = iBarDataSet.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    if (!iBarDataSet.isStacked()) {
                        int i6 = 0;
                        while (((float) i6) < ((float) barBuffer.buffer.length) * this.mAnimator.getPhaseX()) {
                            float f13 = (barBuffer.buffer[i6] + barBuffer.buffer[i6 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f13)) {
                                break;
                            }
                            int i7 = i6 + 1;
                            if (!this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i7]) || !this.mViewPortHandler.isInBoundsLeft(f13)) {
                                i4 = i6;
                                valueFormatter = valueFormatter2;
                                list2 = dataSets;
                                mPPointF2 = instance;
                            } else {
                                int i8 = i6 / 4;
                                BarEntry barEntry4 = (BarEntry) iBarDataSet.getEntryForIndex(i8);
                                float y = barEntry4.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    String barLabel = valueFormatter2.getBarLabel(barEntry4);
                                    int i9 = (y > 0.0f ? 1 : (y == 0.0f ? 0 : -1));
                                    float[] fArr3 = barBuffer.buffer;
                                    barEntry3 = barEntry4;
                                    f8 = f13;
                                    String str = barLabel;
                                    i4 = i6;
                                    list2 = dataSets;
                                    mPPointF2 = instance;
                                    float f14 = i9 >= 0 ? fArr3[i7] + f11 : fArr3[i6 + 3] + f12;
                                    valueFormatter = valueFormatter2;
                                    drawValue(canvas, str, f8, f14, iBarDataSet.getValueTextColor(i8));
                                } else {
                                    barEntry3 = barEntry4;
                                    f8 = f13;
                                    i4 = i6;
                                    valueFormatter = valueFormatter2;
                                    list2 = dataSets;
                                    mPPointF2 = instance;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = barEntry3.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (f8 + mPPointF2.x), (int) ((y >= 0.0f ? barBuffer.buffer[i7] + f11 : barBuffer.buffer[i4 + 3] + f12) + mPPointF2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            }
                            i6 = i4 + 4;
                            instance = mPPointF2;
                            valueFormatter2 = valueFormatter;
                            dataSets = list2;
                        }
                        list = dataSets;
                        mPPointF = instance;
                    } else {
                        ValueFormatter valueFormatter3 = valueFormatter2;
                        list = dataSets;
                        mPPointF = instance;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i10 = 0;
                        int i11 = 0;
                        while (((float) i10) < ((float) iBarDataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry5 = (BarEntry) iBarDataSet.getEntryForIndex(i10);
                            float[] yVals = barEntry5.getYVals();
                            float f15 = (barBuffer.buffer[i11] + barBuffer.buffer[i11 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i10);
                            if (yVals != null) {
                                BarEntry barEntry6 = barEntry5;
                                i = i10;
                                f3 = f;
                                z3 = z;
                                fArr = yVals;
                                transformer = transformer2;
                                float f16 = f15;
                                int length = fArr.length * 2;
                                float[] fArr4 = new float[length];
                                float f17 = -barEntry6.getNegativeSum();
                                float f18 = 0.0f;
                                int i12 = 0;
                                int i13 = 0;
                                while (i12 < length) {
                                    float f19 = fArr[i13];
                                    int i14 = (f19 > 0.0f ? 1 : (f19 == 0.0f ? 0 : -1));
                                    if (i14 == 0 && (f18 == 0.0f || f17 == 0.0f)) {
                                        float f20 = f17;
                                        f17 = f19;
                                        f6 = f20;
                                    } else if (i14 >= 0) {
                                        f18 += f19;
                                        f6 = f17;
                                        f17 = f18;
                                    } else {
                                        f6 = f17 - f19;
                                    }
                                    fArr4[i12 + 1] = f17 * phaseY;
                                    i12 += 2;
                                    i13++;
                                    f17 = f6;
                                }
                                transformer.pointValuesToPixel(fArr4);
                                int i15 = 0;
                                while (i15 < length) {
                                    float f21 = fArr[i15 / 2];
                                    float f22 = fArr4[i15 + 1] + (((f21 > 0.0f ? 1 : (f21 == 0.0f ? 0 : -1)) == 0 && (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) == 0 && (f18 > 0.0f ? 1 : (f18 == 0.0f ? 0 : -1)) > 0) || (f21 > 0.0f ? 1 : (f21 == 0.0f ? 0 : -1)) < 0 ? f12 : f11);
                                    int i16 = i15;
                                    if (!this.mViewPortHandler.isInBoundsRight(f16)) {
                                        break;
                                    }
                                    if (!this.mViewPortHandler.isInBoundsY(f22) || !this.mViewPortHandler.isInBoundsLeft(f16)) {
                                        i2 = length;
                                        f4 = f16;
                                        i3 = i16;
                                        barEntry = barEntry6;
                                        fArr2 = fArr4;
                                    } else {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            BarEntry barEntry7 = barEntry6;
                                            f5 = f22;
                                            i3 = i16;
                                            barEntry = barEntry7;
                                            fArr2 = fArr4;
                                            i2 = length;
                                            f4 = f16;
                                            drawValue(canvas, valueFormatter3.getBarStackedLabel(f21, barEntry7), f16, f5, valueTextColor);
                                        } else {
                                            f5 = f22;
                                            i2 = length;
                                            f4 = f16;
                                            i3 = i16;
                                            barEntry = barEntry6;
                                            fArr2 = fArr4;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (f4 + mPPointF.x), (int) (f5 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    }
                                    i15 = i3 + 2;
                                    fArr4 = fArr2;
                                    barEntry6 = barEntry;
                                    length = i2;
                                    f16 = f4;
                                }
                            } else if (!this.mViewPortHandler.isInBoundsRight(f15)) {
                                break;
                            } else {
                                float[] fArr5 = yVals;
                                int i17 = i11 + 1;
                                if (!this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i17]) || !this.mViewPortHandler.isInBoundsLeft(f15)) {
                                    transformer2 = transformer2;
                                    z = z;
                                    f = f;
                                    i10 = i10;
                                } else {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f7 = f15;
                                        f3 = f;
                                        fArr = fArr5;
                                        barEntry2 = barEntry5;
                                        i = i10;
                                        z3 = z;
                                        transformer = transformer2;
                                        drawValue(canvas, valueFormatter3.getBarLabel(barEntry5), f7, barBuffer.buffer[i17] + (barEntry5.getY() >= 0.0f ? f11 : f12), valueTextColor);
                                    } else {
                                        f7 = f15;
                                        i = i10;
                                        f3 = f;
                                        z3 = z;
                                        fArr = fArr5;
                                        barEntry2 = barEntry5;
                                        transformer = transformer2;
                                    }
                                    if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon3 = barEntry2.getIcon();
                                        Utils.drawImage(canvas, icon3, (int) (mPPointF.x + f7), (int) (barBuffer.buffer[i17] + (barEntry2.getY() >= 0.0f ? f11 : f12) + mPPointF.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                    }
                                }
                            }
                            i11 = fArr == null ? i11 + 4 : i11 + (fArr.length * 4);
                            i10 = i + 1;
                            transformer2 = transformer;
                            z = z3;
                            f = f3;
                        }
                    }
                    f2 = f;
                    z2 = z;
                    MPPointF.recycleInstance(mPPointF);
                }
                i5++;
                isDrawValueAboveBarEnabled = z2;
                dataSets = list;
                convertDpToPixel = f2;
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float f;
        float f2;
        float f3;
        float f4;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (!(highlight.getStackIndex() >= 0 && barEntry.isStacked())) {
                        f4 = barEntry.getY();
                        f3 = 0.0f;
                    } else if (this.mChart.isHighlightFullBarEnabled()) {
                        f4 = barEntry.getPositiveSum();
                        f3 = -barEntry.getNegativeSum();
                    } else {
                        Range range = barEntry.getRanges()[highlight.getStackIndex()];
                        f2 = range.from;
                        f = range.to;
                        prepareBarHighlight(barEntry.getX(), f2, f, barData.getBarWidth() / 2.0f, transformer);
                        setHighlightDrawPos(highlight, this.mBarRect);
                        canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                    }
                    f = f3;
                    f2 = f4;
                    prepareBarHighlight(barEntry.getX(), f2, f, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }
}
