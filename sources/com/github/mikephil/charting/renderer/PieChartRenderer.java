package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;

public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds = new RectF();
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer = new Path();
    protected RectF mDrawHighlightedRectF = new RectF();
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath = new Path();
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer = new RectF();
    private Path mPathBuffer = new Path();
    private RectF[] mRectBuffer = {new RectF(), new RectF(), new RectF()};
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public void initBuffers() {
    }

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = pieChart;
        Paint paint = new Paint(1);
        this.mHolePaint = paint;
        paint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mTransparentCirclePaint = paint2;
        paint2.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        TextPaint textPaint = new TextPaint(1);
        this.mCenterTextPaint = textPaint;
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        Paint paint3 = new Paint(1);
        this.mEntryLabelsPaint = paint3;
        paint3.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        Paint paint4 = new Paint(1);
        this.mValueLinePaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        Bitmap bitmap = weakReference == null ? null : (Bitmap) weakReference.get();
        if (!(bitmap != null && bitmap.getWidth() == chartWidth && bitmap.getHeight() == chartHeight)) {
            if (chartWidth > 0 && chartHeight > 0) {
                bitmap = Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444);
                this.mDrawBitmap = new WeakReference<>(bitmap);
                this.mBitmapCanvas = new Canvas(bitmap);
            } else {
                return;
            }
        }
        bitmap.eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.mChart.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public float calculateMinimumRadiusForSpacedSlice(MPPointF mPPointF, float f, float f2, float f3, float f4, float f5, float f6) {
        MPPointF mPPointF2 = mPPointF;
        double d = (double) ((f5 + f6) * 0.017453292f);
        float cos = mPPointF2.x + (((float) Math.cos(d)) * f);
        float sin = mPPointF2.y + (((float) Math.sin(d)) * f);
        double d2 = (double) ((f5 + (f6 / 2.0f)) * 0.017453292f);
        return (float) (((double) (f - ((float) ((Math.sqrt(Math.pow((double) (cos - f3), 2.0d) + Math.pow((double) (sin - f4), 2.0d)) / 2.0d) * Math.tan(((180.0d - ((double) f2)) / 2.0d) * 0.017453292519943295d))))) - Math.sqrt(Math.pow((double) ((mPPointF2.x + (((float) Math.cos(d2)) * f)) - ((cos + f3) / 2.0f)), 2.0d) + Math.pow((double) ((mPPointF2.y + (((float) Math.sin(d2)) * f)) - ((sin + f4) / 2.0f)), 2.0d)));
    }

    /* access modifiers changed from: protected */
    public float getSliceSpace(IPieDataSet iPieDataSet) {
        if (!iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return iPieDataSet.getSliceSpace();
        }
        if (iPieDataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f) {
            return 0.0f;
        }
        return iPieDataSet.getSliceSpace();
    }

    /* access modifiers changed from: protected */
    public void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        float f;
        float[] fArr;
        float f2;
        float f3;
        int i;
        RectF rectF;
        int i2;
        float f4;
        int i3;
        MPPointF mPPointF;
        RectF rectF2;
        float f5;
        int i4;
        float f6;
        float f7;
        float f8;
        int i5;
        RectF rectF3;
        RectF rectF4;
        MPPointF mPPointF2;
        int i6;
        float f9;
        MPPointF mPPointF3;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = this.mChart.getRotationAngle();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        RectF circleBox = this.mChart.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = this.mChart.getDrawAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean z = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        float holeRadius = z ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        float holeRadius2 = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
        RectF rectF5 = new RectF();
        boolean z2 = z && this.mChart.isDrawRoundedSlicesEnabled();
        int i7 = 0;
        for (int i8 = 0; i8 < entryCount; i8++) {
            if (Math.abs(((PieEntry) iPieDataSet2.getEntryForIndex(i8)).getY()) > Utils.FLOAT_EPSILON) {
                i7++;
            }
        }
        if (i7 <= 1) {
            f = 0.0f;
        } else {
            f = getSliceSpace(iPieDataSet2);
        }
        int i9 = 0;
        float f10 = 0.0f;
        while (i9 < entryCount) {
            float f11 = drawAngles[i9];
            if (Math.abs(iPieDataSet2.getEntryForIndex(i9).getY()) > Utils.FLOAT_EPSILON && (!this.mChart.needsHighlight(i9) || z2)) {
                boolean z3 = f > 0.0f && f11 <= 180.0f;
                i2 = entryCount;
                this.mRenderPaint.setColor(iPieDataSet2.getColor(i9));
                float f12 = i7 == 1 ? 0.0f : f / (radius * 0.017453292f);
                float f13 = rotationAngle + ((f10 + (f12 / 2.0f)) * phaseY);
                float f14 = (f11 - f12) * phaseY;
                float f15 = f14 < 0.0f ? 0.0f : f14;
                this.mPathBuffer.reset();
                if (z2) {
                    float f16 = radius - holeRadius2;
                    i = i9;
                    i4 = i7;
                    double d = (double) (f13 * 0.017453292f);
                    f3 = rotationAngle;
                    f2 = phaseX;
                    float cos = centerCircleBox.x + (((float) Math.cos(d)) * f16);
                    float sin = centerCircleBox.y + (f16 * ((float) Math.sin(d)));
                    rectF5.set(cos - holeRadius2, sin - holeRadius2, cos + holeRadius2, sin + holeRadius2);
                } else {
                    i = i9;
                    i4 = i7;
                    f3 = rotationAngle;
                    f2 = phaseX;
                }
                double d2 = (double) (f13 * 0.017453292f);
                float f17 = holeRadius;
                float cos2 = centerCircleBox.x + (((float) Math.cos(d2)) * radius);
                float sin2 = centerCircleBox.y + (((float) Math.sin(d2)) * radius);
                int i10 = (f15 > 360.0f ? 1 : (f15 == 360.0f ? 0 : -1));
                if (i10 < 0 || f15 % 360.0f > Utils.FLOAT_EPSILON) {
                    fArr = drawAngles;
                    if (z2) {
                        this.mPathBuffer.arcTo(rectF5, f13 + 180.0f, -180.0f);
                    }
                    this.mPathBuffer.arcTo(circleBox, f13, f15);
                } else {
                    fArr = drawAngles;
                    this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Path.Direction.CW);
                }
                RectF rectF6 = rectF5;
                this.mInnerRectBuffer.set(centerCircleBox.x - f17, centerCircleBox.y - f17, centerCircleBox.x + f17, centerCircleBox.y + f17);
                if (!z) {
                    f7 = radius;
                    f8 = f17;
                    i5 = i4;
                    rectF3 = rectF6;
                    rectF4 = circleBox;
                    mPPointF = centerCircleBox;
                    f6 = 360.0f;
                } else if (f17 > 0.0f || z3) {
                    if (z3) {
                        int i11 = i;
                        i3 = i4;
                        float f18 = radius;
                        rectF = circleBox;
                        RectF rectF7 = rectF6;
                        f5 = f17;
                        i6 = 1;
                        f4 = radius;
                        float f19 = f13;
                        mPPointF2 = centerCircleBox;
                        float calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(centerCircleBox, f18, f11 * phaseY, cos2, sin2, f19, f15);
                        if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                            calculateMinimumRadiusForSpacedSlice = -calculateMinimumRadiusForSpacedSlice;
                        }
                        f9 = Math.max(f5, calculateMinimumRadiusForSpacedSlice);
                    } else {
                        f4 = radius;
                        mPPointF2 = centerCircleBox;
                        f5 = f17;
                        i3 = i4;
                        rectF = circleBox;
                        i6 = 1;
                        f9 = f5;
                    }
                    float f20 = (i3 == i6 || f9 == 0.0f) ? 0.0f : f / (f9 * 0.017453292f);
                    float f21 = f3 + ((f10 + (f20 / 2.0f)) * phaseY);
                    float f22 = (f11 - f20) * phaseY;
                    if (f22 < 0.0f) {
                        f22 = 0.0f;
                    }
                    float f23 = f21 + f22;
                    if (i10 < 0 || f15 % 360.0f > Utils.FLOAT_EPSILON) {
                        if (z2) {
                            float f24 = f4 - holeRadius2;
                            double d3 = (double) (0.017453292f * f23);
                            mPPointF3 = mPPointF2;
                            float cos3 = mPPointF2.x + (((float) Math.cos(d3)) * f24);
                            float sin3 = mPPointF3.y + (f24 * ((float) Math.sin(d3)));
                            rectF2 = rectF6;
                            rectF2.set(cos3 - holeRadius2, sin3 - holeRadius2, cos3 + holeRadius2, sin3 + holeRadius2);
                            this.mPathBuffer.arcTo(rectF2, f23, 180.0f);
                        } else {
                            mPPointF3 = mPPointF2;
                            rectF2 = rectF6;
                            double d4 = (double) (f23 * 0.017453292f);
                            this.mPathBuffer.lineTo(mPPointF3.x + (((float) Math.cos(d4)) * f9), mPPointF3.y + (f9 * ((float) Math.sin(d4))));
                        }
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, f23, -f22);
                    } else {
                        this.mPathBuffer.addCircle(mPPointF2.x, mPPointF2.y, f9, Path.Direction.CCW);
                        mPPointF3 = mPPointF2;
                        rectF2 = rectF6;
                    }
                    mPPointF = mPPointF3;
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                    f10 += f11 * f2;
                } else {
                    f7 = radius;
                    f8 = f17;
                    i5 = i4;
                    rectF3 = rectF6;
                    f6 = 360.0f;
                    rectF4 = circleBox;
                    mPPointF = centerCircleBox;
                }
                if (f15 % f6 > Utils.FLOAT_EPSILON) {
                    if (z3) {
                        float calculateMinimumRadiusForSpacedSlice2 = calculateMinimumRadiusForSpacedSlice(mPPointF, f4, f11 * phaseY, cos2, sin2, f13, f15);
                        double d5 = (double) (0.017453292f * (f13 + (f15 / 2.0f)));
                        this.mPathBuffer.lineTo(mPPointF.x + (((float) Math.cos(d5)) * calculateMinimumRadiusForSpacedSlice2), mPPointF.y + (calculateMinimumRadiusForSpacedSlice2 * ((float) Math.sin(d5))));
                    } else {
                        this.mPathBuffer.lineTo(mPPointF.x, mPPointF.y);
                    }
                }
                this.mPathBuffer.close();
                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                f10 += f11 * f2;
            } else {
                f10 += f11 * phaseX;
                i = i9;
                f4 = radius;
                f3 = rotationAngle;
                f2 = phaseX;
                rectF = circleBox;
                i2 = entryCount;
                fArr = drawAngles;
                i3 = i7;
                rectF2 = rectF5;
                f5 = holeRadius;
                mPPointF = centerCircleBox;
            }
            i9 = i + 1;
            iPieDataSet2 = iPieDataSet;
            holeRadius = f5;
            rectF5 = rectF2;
            centerCircleBox = mPPointF;
            i7 = i3;
            radius = f4;
            entryCount = i2;
            circleBox = rectF;
            rotationAngle = f3;
            phaseX = f2;
            drawAngles = fArr;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x039f  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x03ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawValues(android.graphics.Canvas r53) {
        /*
            r52 = this;
            r6 = r52
            r7 = r53
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            com.github.mikephil.charting.utils.MPPointF r8 = r0.getCenterCircleBox()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            float r9 = r0.getRadius()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            float r0 = r0.getRotationAngle()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float[] r10 = r1.getDrawAngles()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float[] r11 = r1.getAbsoluteAngles()
            com.github.mikephil.charting.animation.ChartAnimator r1 = r6.mAnimator
            float r12 = r1.getPhaseX()
            com.github.mikephil.charting.animation.ChartAnimator r1 = r6.mAnimator
            float r13 = r1.getPhaseY()
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            float r1 = r1.getHoleRadius()
            float r1 = r1 * r9
            r14 = 1120403456(0x42c80000, float:100.0)
            float r1 = r1 / r14
            float r1 = r9 - r1
            r15 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r15
            com.github.mikephil.charting.charts.PieChart r2 = r6.mChart
            float r2 = r2.getHoleRadius()
            float r16 = r2 / r14
            r2 = 1092616192(0x41200000, float:10.0)
            float r2 = r9 / r2
            r3 = 1080452710(0x40666666, float:3.6)
            float r2 = r2 * r3
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawHoleEnabled()
            if (r3 == 0) goto L_0x007a
            float r2 = r9 * r16
            float r2 = r9 - r2
            float r2 = r2 / r15
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawSlicesUnderHoleEnabled()
            if (r3 != 0) goto L_0x007a
            com.github.mikephil.charting.charts.PieChart r3 = r6.mChart
            boolean r3 = r3.isDrawRoundedSlicesEnabled()
            if (r3 == 0) goto L_0x007a
            double r3 = (double) r0
            r0 = 1135869952(0x43b40000, float:360.0)
            float r1 = r1 * r0
            double r0 = (double) r1
            r17 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r14 = (double) r9
            double r14 = r14 * r17
            double r0 = r0 / r14
            double r3 = r3 + r0
            float r0 = (float) r3
        L_0x007a:
            r14 = r0
            float r15 = r9 - r2
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            com.github.mikephil.charting.data.ChartData r0 = r0.getData()
            r17 = r0
            com.github.mikephil.charting.data.PieData r17 = (com.github.mikephil.charting.data.PieData) r17
            java.util.List r5 = r17.getDataSets()
            float r18 = r17.getYValueSum()
            com.github.mikephil.charting.charts.PieChart r0 = r6.mChart
            boolean r21 = r0.isDrawEntryLabelsEnabled()
            r53.save()
            r0 = 1084227584(0x40a00000, float:5.0)
            float r22 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r23 = 0
            r0 = r23
            r4 = r0
        L_0x00a3:
            int r1 = r5.size()
            if (r4 >= r1) goto L_0x0424
            java.lang.Object r1 = r5.get(r4)
            r3 = r1
            com.github.mikephil.charting.interfaces.datasets.IPieDataSet r3 = (com.github.mikephil.charting.interfaces.datasets.IPieDataSet) r3
            boolean r24 = r3.isDrawValuesEnabled()
            if (r24 != 0) goto L_0x00d2
            if (r21 != 0) goto L_0x00d2
            r26 = r4
            r28 = r5
            r44 = r9
            r34 = r10
            r36 = r11
            r37 = r12
            r38 = r13
            r40 = r14
            r35 = r15
            r14 = 1073741824(0x40000000, float:2.0)
            r19 = 1120403456(0x42c80000, float:100.0)
            r9 = r7
            r10 = r8
            goto L_0x040e
        L_0x00d2:
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r2 = r3.getXValuePosition()
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r1 = r3.getYValuePosition()
            r6.applyValueTextStyle(r3)
            r25 = r0
            android.graphics.Paint r0 = r6.mValuePaint
            r26 = r4
            java.lang.String r4 = "Q"
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r0, r4)
            float r0 = (float) r0
            r4 = 1082130432(0x40800000, float:4.0)
            float r4 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r4)
            float r27 = r0 + r4
            com.github.mikephil.charting.formatter.ValueFormatter r4 = r3.getValueFormatter()
            int r0 = r3.getEntryCount()
            r28 = r5
            android.graphics.Paint r5 = r6.mValueLinePaint
            int r7 = r3.getValueLineColor()
            r5.setColor(r7)
            android.graphics.Paint r5 = r6.mValueLinePaint
            float r7 = r3.getValueLineWidth()
            float r7 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r7)
            r5.setStrokeWidth(r7)
            float r7 = r6.getSliceSpace(r3)
            com.github.mikephil.charting.utils.MPPointF r5 = r3.getIconsOffset()
            com.github.mikephil.charting.utils.MPPointF r5 = com.github.mikephil.charting.utils.MPPointF.getInstance(r5)
            r29 = r8
            float r8 = r5.x
            float r8 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r8)
            r5.x = r8
            float r8 = r5.y
            float r8 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r8)
            r5.y = r8
            r8 = r23
        L_0x0132:
            if (r8 >= r0) goto L_0x03f2
            com.github.mikephil.charting.data.Entry r30 = r3.getEntryForIndex(r8)
            r31 = r5
            r5 = r30
            com.github.mikephil.charting.data.PieEntry r5 = (com.github.mikephil.charting.data.PieEntry) r5
            if (r25 != 0) goto L_0x0143
            r30 = 0
            goto L_0x0149
        L_0x0143:
            int r30 = r25 + -1
            r30 = r11[r30]
            float r30 = r30 * r12
        L_0x0149:
            r32 = r10[r25]
            r33 = 1016003125(0x3c8efa35, float:0.017453292)
            float r34 = r15 * r33
            float r34 = r7 / r34
            r20 = 1073741824(0x40000000, float:2.0)
            float r34 = r34 / r20
            float r32 = r32 - r34
            float r32 = r32 / r20
            float r30 = r30 + r32
            float r30 = r30 * r13
            r32 = r0
            float r0 = r14 + r30
            r30 = r7
            com.github.mikephil.charting.charts.PieChart r7 = r6.mChart
            boolean r7 = r7.isUsePercentValuesEnabled()
            if (r7 == 0) goto L_0x0177
            float r7 = r5.getY()
            float r7 = r7 / r18
            r19 = 1120403456(0x42c80000, float:100.0)
            float r7 = r7 * r19
            goto L_0x017b
        L_0x0177:
            float r7 = r5.getY()
        L_0x017b:
            java.lang.String r7 = r4.getPieLabel(r7, r5)
            r34 = r10
            java.lang.String r10 = r5.getLabel()
            r35 = r4
            float r4 = r0 * r33
            r33 = r5
            double r4 = (double) r4
            r36 = r11
            r37 = r12
            double r11 = java.lang.Math.cos(r4)
            float r11 = (float) r11
            r38 = r13
            double r12 = java.lang.Math.sin(r4)
            float r12 = (float) r12
            if (r21 == 0) goto L_0x01a4
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r13 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.OUTSIDE_SLICE
            if (r2 != r13) goto L_0x01a4
            r13 = 1
            goto L_0x01a6
        L_0x01a4:
            r13 = r23
        L_0x01a6:
            r40 = r14
            if (r24 == 0) goto L_0x01b0
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r14 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.OUTSIDE_SLICE
            if (r1 != r14) goto L_0x01b0
            r14 = 1
            goto L_0x01b2
        L_0x01b0:
            r14 = r23
        L_0x01b2:
            r41 = r10
            if (r21 == 0) goto L_0x01bc
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r10 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.INSIDE_SLICE
            if (r2 != r10) goto L_0x01bc
            r10 = 1
            goto L_0x01be
        L_0x01bc:
            r10 = r23
        L_0x01be:
            r42 = r2
            if (r24 == 0) goto L_0x01c9
            com.github.mikephil.charting.data.PieDataSet$ValuePosition r2 = com.github.mikephil.charting.data.PieDataSet.ValuePosition.INSIDE_SLICE
            if (r1 != r2) goto L_0x01c9
            r39 = 1
            goto L_0x01cb
        L_0x01c9:
            r39 = r23
        L_0x01cb:
            if (r13 != 0) goto L_0x01ed
            if (r14 == 0) goto L_0x01d0
            goto L_0x01ed
        L_0x01d0:
            r45 = r1
            r44 = r9
            r47 = r10
            r46 = r11
            r50 = r29
            r51 = r31
            r31 = r33
            r48 = r35
            r11 = r41
            r19 = 1120403456(0x42c80000, float:100.0)
            r9 = r53
            r29 = r12
            r35 = r15
            r15 = r3
            goto L_0x032c
        L_0x01ed:
            float r2 = r3.getValueLinePart1Length()
            float r43 = r3.getValueLinePart2Length()
            float r44 = r3.getValueLinePart1OffsetPercentage()
            r19 = 1120403456(0x42c80000, float:100.0)
            float r44 = r44 / r19
            r45 = r1
            com.github.mikephil.charting.charts.PieChart r1 = r6.mChart
            boolean r1 = r1.isDrawHoleEnabled()
            if (r1 == 0) goto L_0x0210
            float r1 = r9 * r16
            float r46 = r9 - r1
            float r46 = r46 * r44
            float r46 = r46 + r1
            goto L_0x0212
        L_0x0210:
            float r46 = r9 * r44
        L_0x0212:
            boolean r1 = r3.isValueLineVariableLength()
            if (r1 == 0) goto L_0x0226
            float r43 = r43 * r15
            double r4 = java.lang.Math.sin(r4)
            double r4 = java.lang.Math.abs(r4)
            float r1 = (float) r4
            float r43 = r43 * r1
            goto L_0x0228
        L_0x0226:
            float r43 = r43 * r15
        L_0x0228:
            float r1 = r46 * r11
            r5 = r29
            float r4 = r5.x
            float r1 = r1 + r4
            float r46 = r46 * r12
            float r4 = r5.y
            float r4 = r46 + r4
            r29 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 + r29
            float r2 = r2 * r15
            float r29 = r2 * r11
            r44 = r9
            float r9 = r5.x
            float r9 = r29 + r9
            float r2 = r2 * r12
            r29 = r12
            float r12 = r5.y
            float r12 = r12 + r2
            r47 = r10
            r46 = r11
            double r10 = (double) r0
            r48 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r10 = r10 % r48
            r48 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r0 = (r10 > r48 ? 1 : (r10 == r48 ? 0 : -1))
            if (r0 < 0) goto L_0x027e
            r48 = 4643457506423603200(0x4070e00000000000, double:270.0)
            int r0 = (r10 > r48 ? 1 : (r10 == r48 ? 0 : -1))
            if (r0 > 0) goto L_0x027e
            float r0 = r9 - r43
            android.graphics.Paint r2 = r6.mValuePaint
            android.graphics.Paint$Align r10 = android.graphics.Paint.Align.RIGHT
            r2.setTextAlign(r10)
            if (r13 == 0) goto L_0x0278
            android.graphics.Paint r2 = r6.mEntryLabelsPaint
            android.graphics.Paint$Align r10 = android.graphics.Paint.Align.RIGHT
            r2.setTextAlign(r10)
        L_0x0278:
            float r2 = r0 - r22
            r43 = r0
            r10 = r2
            goto L_0x0293
        L_0x027e:
            float r43 = r9 + r43
            android.graphics.Paint r0 = r6.mValuePaint
            android.graphics.Paint$Align r2 = android.graphics.Paint.Align.LEFT
            r0.setTextAlign(r2)
            if (r13 == 0) goto L_0x0290
            android.graphics.Paint r0 = r6.mEntryLabelsPaint
            android.graphics.Paint$Align r2 = android.graphics.Paint.Align.LEFT
            r0.setTextAlign(r2)
        L_0x0290:
            float r0 = r43 + r22
            r10 = r0
        L_0x0293:
            int r0 = r3.getValueLineColor()
            r2 = 1122867(0x112233, float:1.573472E-39)
            if (r0 == r2) goto L_0x02cc
            boolean r0 = r3.isUsingSliceColorAsValueLineColor()
            if (r0 == 0) goto L_0x02ab
            android.graphics.Paint r0 = r6.mValueLinePaint
            int r2 = r3.getColor(r8)
            r0.setColor(r2)
        L_0x02ab:
            android.graphics.Paint r11 = r6.mValueLinePaint
            r0 = r53
            r2 = r4
            r4 = r3
            r3 = r9
            r48 = r35
            r35 = r15
            r15 = r4
            r4 = r12
            r50 = r5
            r51 = r31
            r31 = r33
            r5 = r11
            r0.drawLine(r1, r2, r3, r4, r5)
            android.graphics.Paint r5 = r6.mValueLinePaint
            r1 = r9
            r2 = r12
            r3 = r43
            r0.drawLine(r1, r2, r3, r4, r5)
            goto L_0x02d7
        L_0x02cc:
            r50 = r5
            r51 = r31
            r31 = r33
            r48 = r35
            r35 = r15
            r15 = r3
        L_0x02d7:
            if (r13 == 0) goto L_0x0300
            if (r14 == 0) goto L_0x0300
            int r5 = r15.getValueTextColor(r8)
            r0 = r52
            r1 = r53
            r2 = r7
            r3 = r10
            r4 = r12
            r0.drawValue(r1, r2, r3, r4, r5)
            int r0 = r17.getEntryCount()
            if (r8 >= r0) goto L_0x02fb
            if (r41 == 0) goto L_0x02fb
            float r12 = r12 + r27
            r9 = r53
            r11 = r41
            r6.drawEntryLabel(r9, r11, r10, r12)
            goto L_0x032c
        L_0x02fb:
            r9 = r53
            r11 = r41
            goto L_0x032c
        L_0x0300:
            r9 = r53
            r11 = r41
            if (r13 == 0) goto L_0x0317
            int r0 = r17.getEntryCount()
            if (r8 >= r0) goto L_0x032c
            if (r11 == 0) goto L_0x032c
            r0 = 1073741824(0x40000000, float:2.0)
            float r1 = r27 / r0
            float r12 = r12 + r1
            r6.drawEntryLabel(r9, r11, r10, r12)
            goto L_0x032c
        L_0x0317:
            r0 = 1073741824(0x40000000, float:2.0)
            if (r14 == 0) goto L_0x032c
            float r1 = r27 / r0
            float r4 = r12 + r1
            int r5 = r15.getValueTextColor(r8)
            r0 = r52
            r1 = r53
            r2 = r7
            r3 = r10
            r0.drawValue(r1, r2, r3, r4, r5)
        L_0x032c:
            if (r47 != 0) goto L_0x0336
            if (r39 == 0) goto L_0x0331
            goto L_0x0336
        L_0x0331:
            r10 = r50
        L_0x0333:
            r14 = 1073741824(0x40000000, float:2.0)
            goto L_0x0393
        L_0x0336:
            float r0 = r35 * r46
            r10 = r50
            float r1 = r10.x
            float r12 = r0 + r1
            float r0 = r35 * r29
            float r1 = r10.y
            float r13 = r0 + r1
            android.graphics.Paint r0 = r6.mValuePaint
            android.graphics.Paint$Align r1 = android.graphics.Paint.Align.CENTER
            r0.setTextAlign(r1)
            if (r47 == 0) goto L_0x036b
            if (r39 == 0) goto L_0x036b
            int r5 = r15.getValueTextColor(r8)
            r0 = r52
            r1 = r53
            r2 = r7
            r3 = r12
            r4 = r13
            r0.drawValue(r1, r2, r3, r4, r5)
            int r0 = r17.getEntryCount()
            if (r8 >= r0) goto L_0x0333
            if (r11 == 0) goto L_0x0333
            float r13 = r13 + r27
            r6.drawEntryLabel(r9, r11, r12, r13)
            goto L_0x0333
        L_0x036b:
            if (r47 == 0) goto L_0x037e
            int r0 = r17.getEntryCount()
            if (r8 >= r0) goto L_0x0333
            if (r11 == 0) goto L_0x0333
            r14 = 1073741824(0x40000000, float:2.0)
            float r0 = r27 / r14
            float r13 = r13 + r0
            r6.drawEntryLabel(r9, r11, r12, r13)
            goto L_0x0393
        L_0x037e:
            r14 = 1073741824(0x40000000, float:2.0)
            if (r39 == 0) goto L_0x0393
            float r0 = r27 / r14
            float r4 = r13 + r0
            int r5 = r15.getValueTextColor(r8)
            r0 = r52
            r1 = r53
            r2 = r7
            r3 = r12
            r0.drawValue(r1, r2, r3, r4, r5)
        L_0x0393:
            android.graphics.drawable.Drawable r0 = r31.getIcon()
            if (r0 == 0) goto L_0x03ce
            boolean r0 = r15.isDrawIconsEnabled()
            if (r0 == 0) goto L_0x03ce
            android.graphics.drawable.Drawable r1 = r31.getIcon()
            r7 = r51
            float r0 = r7.y
            float r0 = r35 + r0
            float r0 = r0 * r46
            float r2 = r10.x
            float r0 = r0 + r2
            float r2 = r7.y
            float r2 = r35 + r2
            float r2 = r2 * r29
            float r3 = r10.y
            float r2 = r2 + r3
            float r3 = r7.x
            float r2 = r2 + r3
            int r3 = (int) r0
            int r4 = (int) r2
            int r5 = r1.getIntrinsicWidth()
            int r11 = r1.getIntrinsicHeight()
            r0 = r53
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r11
            com.github.mikephil.charting.utils.Utils.drawImage(r0, r1, r2, r3, r4, r5)
            goto L_0x03d0
        L_0x03ce:
            r7 = r51
        L_0x03d0:
            int r25 = r25 + 1
            int r8 = r8 + 1
            r5 = r7
            r29 = r10
            r3 = r15
            r7 = r30
            r0 = r32
            r10 = r34
            r15 = r35
            r11 = r36
            r12 = r37
            r13 = r38
            r14 = r40
            r2 = r42
            r9 = r44
            r1 = r45
            r4 = r48
            goto L_0x0132
        L_0x03f2:
            r7 = r5
            r44 = r9
            r34 = r10
            r36 = r11
            r37 = r12
            r38 = r13
            r40 = r14
            r35 = r15
            r10 = r29
            r14 = 1073741824(0x40000000, float:2.0)
            r19 = 1120403456(0x42c80000, float:100.0)
            r9 = r53
            com.github.mikephil.charting.utils.MPPointF.recycleInstance(r7)
            r0 = r25
        L_0x040e:
            int r4 = r26 + 1
            r7 = r9
            r8 = r10
            r5 = r28
            r10 = r34
            r15 = r35
            r11 = r36
            r12 = r37
            r13 = r38
            r14 = r40
            r9 = r44
            goto L_0x00a3
        L_0x0424:
            r9 = r7
            r10 = r8
            com.github.mikephil.charting.utils.MPPointF.recycleInstance(r10)
            r53.restore()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.PieChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    /* access modifiers changed from: protected */
    public void drawEntryLabel(Canvas canvas, String str, float f, float f2) {
        canvas.drawText(str, f, f2, this.mEntryLabelsPaint);
    }

    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap((Bitmap) this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawHole(Canvas canvas) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0) {
                this.mBitmapCanvas.drawCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, this.mHolePaint);
            }
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                float transparentCircleRadius = radius * (this.mChart.getTransparentCircleRadius() / 100.0f);
                this.mTransparentCirclePaint.setAlpha((int) (((float) alpha) * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, transparentCircleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(centerCircleBox);
        }
    }

    /* access modifiers changed from: protected */
    public void drawCenterText(Canvas canvas) {
        float f;
        MPPointF mPPointF;
        Canvas canvas2 = canvas;
        CharSequence centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            MPPointF centerTextOffset = this.mChart.getCenterTextOffset();
            float f2 = centerCircleBox.x + centerTextOffset.x;
            float f3 = centerCircleBox.y + centerTextOffset.y;
            if (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) {
                f = this.mChart.getRadius();
            } else {
                f = this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
            }
            RectF rectF = this.mRectBuffer[0];
            rectF.left = f2 - f;
            rectF.top = f3 - f;
            rectF.right = f2 + f;
            rectF.bottom = f3 + f;
            RectF rectF2 = this.mRectBuffer[1];
            rectF2.set(rectF);
            float centerTextRadiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
            if (((double) centerTextRadiusPercent) > 0.0d) {
                rectF2.inset((rectF2.width() - (rectF2.width() * centerTextRadiusPercent)) / 2.0f, (rectF2.height() - (rectF2.height() * centerTextRadiusPercent)) / 2.0f);
            }
            if (!centerText.equals(this.mCenterTextLastValue) || !rectF2.equals(this.mCenterTextLastBounds)) {
                this.mCenterTextLastBounds.set(rectF2);
                this.mCenterTextLastValue = centerText;
                mPPointF = centerTextOffset;
                StaticLayout staticLayout = r3;
                StaticLayout staticLayout2 = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil((double) this.mCenterTextLastBounds.width()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
                this.mCenterTextLayout = staticLayout;
            } else {
                mPPointF = centerTextOffset;
            }
            canvas.save();
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(rectF, Path.Direction.CW);
            canvas2.clipPath(path);
            canvas2.translate(rectF2.left, rectF2.top + ((rectF2.height() - ((float) this.mCenterTextLayout.getHeight())) / 2.0f));
            this.mCenterTextLayout.draw(canvas2);
            canvas.restore();
            MPPointF.recycleInstance(centerCircleBox);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        boolean z;
        float[] fArr;
        float f;
        MPPointF mPPointF;
        float f2;
        int i;
        RectF rectF;
        float f3;
        IPieDataSet dataSetByIndex;
        float f4;
        int i2;
        float f5;
        int i3;
        float f6;
        float[] fArr2;
        float f7;
        float f8;
        Highlight[] highlightArr2 = highlightArr;
        boolean z2 = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        if (!z2 || !this.mChart.isDrawRoundedSlicesEnabled()) {
            float phaseX = this.mAnimator.getPhaseX();
            float phaseY = this.mAnimator.getPhaseY();
            float rotationAngle = this.mChart.getRotationAngle();
            float[] drawAngles = this.mChart.getDrawAngles();
            float[] absoluteAngles = this.mChart.getAbsoluteAngles();
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            float radius = this.mChart.getRadius();
            float holeRadius = z2 ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
            RectF rectF2 = this.mDrawHighlightedRectF;
            rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
            int i4 = 0;
            while (i4 < highlightArr2.length) {
                int x = (int) highlightArr2[i4].getX();
                if (x < drawAngles.length && (dataSetByIndex = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr2[i4].getDataSetIndex())) != null && dataSetByIndex.isHighlightEnabled()) {
                    int entryCount = dataSetByIndex.getEntryCount();
                    int i5 = 0;
                    for (int i6 = 0; i6 < entryCount; i6++) {
                        if (Math.abs(((PieEntry) dataSetByIndex.getEntryForIndex(i6)).getY()) > Utils.FLOAT_EPSILON) {
                            i5++;
                        }
                    }
                    if (x == 0) {
                        i2 = 1;
                        f4 = 0.0f;
                    } else {
                        f4 = absoluteAngles[x - 1] * phaseX;
                        i2 = 1;
                    }
                    if (i5 <= i2) {
                        f5 = 0.0f;
                    } else {
                        f5 = dataSetByIndex.getSliceSpace();
                    }
                    float f9 = drawAngles[x];
                    float selectionShift = dataSetByIndex.getSelectionShift();
                    int i7 = i4;
                    float f10 = radius + selectionShift;
                    float f11 = holeRadius;
                    rectF2.set(this.mChart.getCircleBox());
                    float f12 = -selectionShift;
                    rectF2.inset(f12, f12);
                    boolean z3 = f5 > 0.0f && f9 <= 180.0f;
                    this.mRenderPaint.setColor(dataSetByIndex.getColor(x));
                    float f13 = i5 == 1 ? 0.0f : f5 / (radius * 0.017453292f);
                    float f14 = i5 == 1 ? 0.0f : f5 / (f10 * 0.017453292f);
                    float f15 = rotationAngle + (((f13 / 2.0f) + f4) * phaseY);
                    float f16 = (f9 - f13) * phaseY;
                    float f17 = f16 < 0.0f ? 0.0f : f16;
                    float f18 = (((f14 / 2.0f) + f4) * phaseY) + rotationAngle;
                    float f19 = (f9 - f14) * phaseY;
                    if (f19 < 0.0f) {
                        f19 = 0.0f;
                    }
                    this.mPathBuffer.reset();
                    int i8 = (f17 > 360.0f ? 1 : (f17 == 360.0f ? 0 : -1));
                    if (i8 < 0 || f17 % 360.0f > Utils.FLOAT_EPSILON) {
                        fArr2 = drawAngles;
                        f6 = f4;
                        double d = (double) (f18 * 0.017453292f);
                        i3 = i5;
                        z = z2;
                        this.mPathBuffer.moveTo(centerCircleBox.x + (((float) Math.cos(d)) * f10), centerCircleBox.y + (f10 * ((float) Math.sin(d))));
                        this.mPathBuffer.arcTo(rectF2, f18, f19);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f10, Path.Direction.CW);
                        fArr2 = drawAngles;
                        f6 = f4;
                        i3 = i5;
                        z = z2;
                    }
                    if (z3) {
                        double d2 = (double) (f15 * 0.017453292f);
                        i = i7;
                        rectF = rectF2;
                        f2 = f11;
                        mPPointF = centerCircleBox;
                        fArr = fArr2;
                        f7 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f9 * phaseY, (((float) Math.cos(d2)) * radius) + centerCircleBox.x, centerCircleBox.y + (((float) Math.sin(d2)) * radius), f15, f17);
                    } else {
                        rectF = rectF2;
                        mPPointF = centerCircleBox;
                        i = i7;
                        f2 = f11;
                        fArr = fArr2;
                        f7 = 0.0f;
                    }
                    this.mInnerRectBuffer.set(mPPointF.x - f2, mPPointF.y - f2, mPPointF.x + f2, mPPointF.y + f2);
                    if (!z || (f2 <= 0.0f && !z3)) {
                        f3 = phaseX;
                        f = phaseY;
                        if (f17 % 360.0f > Utils.FLOAT_EPSILON) {
                            if (z3) {
                                double d3 = (double) ((f15 + (f17 / 2.0f)) * 0.017453292f);
                                this.mPathBuffer.lineTo(mPPointF.x + (((float) Math.cos(d3)) * f7), mPPointF.y + (f7 * ((float) Math.sin(d3))));
                            } else {
                                this.mPathBuffer.lineTo(mPPointF.x, mPPointF.y);
                            }
                        }
                    } else {
                        if (z3) {
                            if (f7 < 0.0f) {
                                f7 = -f7;
                            }
                            f8 = Math.max(f2, f7);
                        } else {
                            f8 = f2;
                        }
                        float f20 = (i3 == 1 || f8 == 0.0f) ? 0.0f : f5 / (f8 * 0.017453292f);
                        float f21 = ((f6 + (f20 / 2.0f)) * phaseY) + rotationAngle;
                        float f22 = (f9 - f20) * phaseY;
                        if (f22 < 0.0f) {
                            f22 = 0.0f;
                        }
                        float f23 = f21 + f22;
                        if (i8 < 0 || f17 % 360.0f > Utils.FLOAT_EPSILON) {
                            double d4 = (double) (f23 * 0.017453292f);
                            f3 = phaseX;
                            f = phaseY;
                            this.mPathBuffer.lineTo(mPPointF.x + (((float) Math.cos(d4)) * f8), mPPointF.y + (f8 * ((float) Math.sin(d4))));
                            this.mPathBuffer.arcTo(this.mInnerRectBuffer, f23, -f22);
                        } else {
                            this.mPathBuffer.addCircle(mPPointF.x, mPPointF.y, f8, Path.Direction.CCW);
                            f3 = phaseX;
                            f = phaseY;
                        }
                    }
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                } else {
                    i = i4;
                    rectF = rectF2;
                    f2 = holeRadius;
                    fArr = drawAngles;
                    z = z2;
                    f3 = phaseX;
                    f = phaseY;
                    mPPointF = centerCircleBox;
                }
                i4 = i + 1;
                phaseX = f3;
                rectF2 = rectF;
                holeRadius = f2;
                centerCircleBox = mPPointF;
                phaseY = f;
                drawAngles = fArr;
                z2 = z;
                highlightArr2 = highlightArr;
            }
            MPPointF.recycleInstance(centerCircleBox);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRoundedSlices(Canvas canvas) {
        float f;
        float f2;
        float[] fArr;
        if (this.mChart.isDrawRoundedSlicesEnabled()) {
            IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
            if (dataSet.isVisible()) {
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
                float radius = this.mChart.getRadius();
                float holeRadius = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles();
                float rotationAngle = this.mChart.getRotationAngle();
                int i = 0;
                while (i < dataSet.getEntryCount()) {
                    float f3 = drawAngles[i];
                    if (Math.abs(dataSet.getEntryForIndex(i).getY()) > Utils.FLOAT_EPSILON) {
                        double d = (double) (radius - holeRadius);
                        double d2 = (double) ((rotationAngle + f3) * phaseY);
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                        float cos = (float) (((double) centerCircleBox.x) + (Math.cos(Math.toRadians(d2)) * d));
                        float sin = (float) ((d * Math.sin(Math.toRadians(d2))) + ((double) centerCircleBox.y));
                        this.mRenderPaint.setColor(dataSet.getColor(i));
                        this.mBitmapCanvas.drawCircle(cos, sin, holeRadius, this.mRenderPaint);
                    } else {
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                    }
                    rotationAngle = f2 + (f3 * phaseX);
                    i++;
                    phaseY = f;
                    drawAngles = fArr;
                }
                MPPointF.recycleInstance(centerCircleBox);
            }
        }
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (canvas != null) {
            canvas.setBitmap((Bitmap) null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (weakReference != null) {
            Bitmap bitmap = (Bitmap) weakReference.get();
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
