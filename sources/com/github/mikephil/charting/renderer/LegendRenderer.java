package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries = new ArrayList(16);
    protected Paint.FontMetrics legendFontMetrics = new Paint.FontMetrics();
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath = new Path();

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.mLegend = legend;
        Paint paint = new Paint(1);
        this.mLegendLabelPaint = paint;
        paint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(1);
        this.mLegendFormPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: com.github.mikephil.charting.data.ChartData<?>} */
    /* JADX WARNING: type inference failed for: r7v2, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void computeLegend(com.github.mikephil.charting.data.ChartData<?> r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            boolean r2 = r2.isLegendCustom()
            if (r2 != 0) goto L_0x01c1
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r2.clear()
            r3 = 0
        L_0x0012:
            int r4 = r19.getDataSetCount()
            if (r3 >= r4) goto L_0x01a7
            com.github.mikephil.charting.interfaces.datasets.IDataSet r4 = r1.getDataSetByIndex(r3)
            java.util.List r5 = r4.getColors()
            int r6 = r4.getEntryCount()
            boolean r7 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IBarDataSet
            if (r7 == 0) goto L_0x0096
            r7 = r4
            com.github.mikephil.charting.interfaces.datasets.IBarDataSet r7 = (com.github.mikephil.charting.interfaces.datasets.IBarDataSet) r7
            boolean r8 = r7.isStacked()
            if (r8 == 0) goto L_0x0096
            java.lang.String[] r6 = r7.getStackLabels()
            r8 = 0
        L_0x0036:
            int r9 = r5.size()
            if (r8 >= r9) goto L_0x0074
            int r9 = r7.getStackSize()
            if (r8 >= r9) goto L_0x0074
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r9 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            int r10 = r6.length
            int r10 = r8 % r10
            r11 = r6[r10]
            com.github.mikephil.charting.components.Legend$LegendForm r12 = r4.getForm()
            float r13 = r4.getFormSize()
            float r14 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r16 = r4.getFormLineDashEffect()
            java.lang.Object r10 = r5.get(r8)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r17 = r10.intValue()
            r10 = r15
            r2 = r15
            r15 = r16
            r16 = r17
            r10.<init>(r11, r12, r13, r14, r15, r16)
            r9.add(r2)
            int r8 = r8 + 1
            goto L_0x0036
        L_0x0074:
            java.lang.String r2 = r7.getLabel()
            if (r2 == 0) goto L_0x0093
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
        L_0x0093:
            r2 = r1
            goto L_0x01a2
        L_0x0096:
            boolean r2 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.IPieDataSet
            if (r2 == 0) goto L_0x00fe
            r2 = r4
            com.github.mikephil.charting.interfaces.datasets.IPieDataSet r2 = (com.github.mikephil.charting.interfaces.datasets.IPieDataSet) r2
            r7 = 0
        L_0x009e:
            int r8 = r5.size()
            if (r7 >= r8) goto L_0x00dd
            if (r7 >= r6) goto L_0x00dd
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r8 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.data.Entry r9 = r2.getEntryForIndex(r7)
            com.github.mikephil.charting.data.PieEntry r9 = (com.github.mikephil.charting.data.PieEntry) r9
            java.lang.String r10 = r9.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r11 = r4.getForm()
            float r12 = r4.getFormSize()
            float r13 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r14 = r4.getFormLineDashEffect()
            java.lang.Object r9 = r5.get(r7)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r16 = r9.intValue()
            r9 = r15
            r1 = r15
            r15 = r16
            r9.<init>(r10, r11, r12, r13, r14, r15)
            r8.add(r1)
            int r7 = r7 + 1
            r1 = r19
            goto L_0x009e
        L_0x00dd:
            java.lang.String r1 = r2.getLabel()
            if (r1 == 0) goto L_0x01a0
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r2 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            r8 = 2143289344(0x7fc00000, float:NaN)
            r9 = 2143289344(0x7fc00000, float:NaN)
            r10 = 0
            r11 = 1122867(0x112233, float:1.573472E-39)
            r5 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r1.add(r2)
            goto L_0x01a0
        L_0x00fe:
            boolean r1 = r4 instanceof com.github.mikephil.charting.interfaces.datasets.ICandleDataSet
            if (r1 == 0) goto L_0x0153
            r1 = r4
            com.github.mikephil.charting.interfaces.datasets.ICandleDataSet r1 = (com.github.mikephil.charting.interfaces.datasets.ICandleDataSet) r1
            int r2 = r1.getDecreasingColor()
            r7 = 1122867(0x112233, float:1.573472E-39)
            if (r2 == r7) goto L_0x0153
            int r14 = r1.getDecreasingColor()
            int r1 = r1.getIncreasingColor()
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r5 = new com.github.mikephil.charting.components.LegendEntry
            r9 = 0
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            r8 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r2.add(r5)
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r12 = new com.github.mikephil.charting.components.LegendEntry
            java.lang.String r6 = r4.getLabel()
            com.github.mikephil.charting.components.Legend$LegendForm r7 = r4.getForm()
            float r8 = r4.getFormSize()
            float r9 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r10 = r4.getFormLineDashEffect()
            r5 = r12
            r11 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2.add(r12)
            goto L_0x01a0
        L_0x0153:
            r1 = 0
        L_0x0154:
            int r2 = r5.size()
            if (r1 >= r2) goto L_0x01a0
            if (r1 >= r6) goto L_0x01a0
            int r2 = r5.size()
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x016d
            int r2 = r6 + -1
            if (r1 >= r2) goto L_0x016d
            r2 = 0
            r9 = r2
            r2 = r19
            goto L_0x0178
        L_0x016d:
            r2 = r19
            com.github.mikephil.charting.interfaces.datasets.IDataSet r7 = r2.getDataSetByIndex(r3)
            java.lang.String r7 = r7.getLabel()
            r9 = r7
        L_0x0178:
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r7 = r0.computedEntries
            com.github.mikephil.charting.components.LegendEntry r15 = new com.github.mikephil.charting.components.LegendEntry
            com.github.mikephil.charting.components.Legend$LegendForm r10 = r4.getForm()
            float r11 = r4.getFormSize()
            float r12 = r4.getFormLineWidth()
            android.graphics.DashPathEffect r13 = r4.getFormLineDashEffect()
            java.lang.Object r8 = r5.get(r1)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r14 = r8.intValue()
            r8 = r15
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r7.add(r15)
            int r1 = r1 + 1
            goto L_0x0154
        L_0x01a0:
            r2 = r19
        L_0x01a2:
            int r3 = r3 + 1
            r1 = r2
            goto L_0x0012
        L_0x01a7:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r1 = r1.getExtraEntries()
            if (r1 == 0) goto L_0x01ba
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r1 = r0.computedEntries
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r2 = r2.getExtraEntries()
            java.util.Collections.addAll(r1, r2)
        L_0x01ba:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            java.util.List<com.github.mikephil.charting.components.LegendEntry> r2 = r0.computedEntries
            r1.setEntries(r2)
        L_0x01c1:
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Typeface r1 = r1.getTypeface()
            if (r1 == 0) goto L_0x01ce
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            r2.setTypeface(r1)
        L_0x01ce:
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            float r2 = r2.getTextSize()
            r1.setTextSize(r2)
            android.graphics.Paint r1 = r0.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r2 = r0.mLegend
            int r2 = r2.getTextColor()
            r1.setColor(r2)
            com.github.mikephil.charting.components.Legend r1 = r0.mLegend
            android.graphics.Paint r2 = r0.mLegendLabelPaint
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r0.mViewPortHandler
            r1.calculateDimensions(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.computeLegend(com.github.mikephil.charting.data.ChartData):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x0269  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0160  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderLegend(android.graphics.Canvas r35) {
        /*
            r34 = this;
            r6 = r34
            r7 = r35
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            android.graphics.Typeface r0 = r0.getTypeface()
            if (r0 == 0) goto L_0x001a
            android.graphics.Paint r1 = r6.mLegendLabelPaint
            r1.setTypeface(r0)
        L_0x001a:
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getTextSize()
            r0.setTextSize(r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            int r1 = r1.getTextColor()
            r0.setColor(r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            android.graphics.Paint$FontMetrics r1 = r6.legendFontMetrics
            float r8 = com.github.mikephil.charting.utils.Utils.getLineHeight(r0, r1)
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            android.graphics.Paint$FontMetrics r1 = r6.legendFontMetrics
            float r0 = com.github.mikephil.charting.utils.Utils.getLineSpacing(r0, r1)
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getYEntrySpace()
            float r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1)
            float r9 = r0 + r1
            android.graphics.Paint r0 = r6.mLegendLabelPaint
            java.lang.String r1 = "ABC"
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r0, r1)
            float r0 = (float) r0
            r10 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r10
            float r11 = r8 - r0
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            com.github.mikephil.charting.components.LegendEntry[] r12 = r0.getEntries()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            float r0 = r0.getFormToTextSpace()
            float r13 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            float r0 = r0.getXEntrySpace()
            float r14 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendOrientation r0 = r0.getOrientation()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r15 = r1.getHorizontalAlignment()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r1 = r1.getVerticalAlignment()
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            com.github.mikephil.charting.components.Legend$LegendDirection r5 = r2.getDirection()
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getFormSize()
            float r16 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2)
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getStackSpace()
            float r4 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r2)
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.getYOffset()
            com.github.mikephil.charting.components.Legend r3 = r6.mLegend
            float r3 = r3.getXOffset()
            int[] r17 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment
            int r18 = r15.ordinal()
            r10 = r17[r18]
            r17 = r4
            r4 = 2
            r20 = 0
            r21 = r14
            r14 = 1
            if (r10 == r14) goto L_0x013b
            if (r10 == r4) goto L_0x011b
            r4 = 3
            if (r10 == r4) goto L_0x00ca
            r26 = r8
            r14 = r9
            r7 = r20
            goto L_0x0155
        L_0x00ca:
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x00d8
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.getChartWidth()
            r10 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r10
            goto L_0x00e8
        L_0x00d8:
            r10 = 1073741824(0x40000000, float:2.0)
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentLeft()
            com.github.mikephil.charting.utils.ViewPortHandler r14 = r6.mViewPortHandler
            float r14 = r14.contentWidth()
            float r14 = r14 / r10
            float r4 = r4 + r14
        L_0x00e8:
            com.github.mikephil.charting.components.Legend$LegendDirection r10 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r10) goto L_0x00ee
            r10 = r3
            goto L_0x00ef
        L_0x00ee:
            float r10 = -r3
        L_0x00ef:
            float r4 = r4 + r10
            com.github.mikephil.charting.components.Legend$LegendOrientation r10 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r10) goto L_0x0117
            r14 = r9
            double r9 = (double) r4
            com.github.mikephil.charting.components.Legend$LegendDirection r4 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            r24 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r5 != r4) goto L_0x0109
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            float r4 = -r4
            r26 = r8
            double r7 = (double) r4
            double r7 = r7 / r24
            double r3 = (double) r3
            double r7 = r7 + r3
            goto L_0x0114
        L_0x0109:
            r26 = r8
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            double r7 = (double) r4
            double r7 = r7 / r24
            double r3 = (double) r3
            double r7 = r7 - r3
        L_0x0114:
            double r9 = r9 + r7
            float r4 = (float) r9
            goto L_0x0139
        L_0x0117:
            r26 = r8
            r14 = r9
            goto L_0x0139
        L_0x011b:
            r26 = r8
            r14 = r9
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x0129
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.getChartWidth()
            goto L_0x012f
        L_0x0129:
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentRight()
        L_0x012f:
            float r4 = r4 - r3
            com.github.mikephil.charting.components.Legend$LegendDirection r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r3) goto L_0x0139
            com.github.mikephil.charting.components.Legend r3 = r6.mLegend
            float r3 = r3.mNeededWidth
            float r4 = r4 - r3
        L_0x0139:
            r7 = r4
            goto L_0x0155
        L_0x013b:
            r26 = r8
            r14 = r9
            com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL
            if (r0 != r4) goto L_0x0143
            goto L_0x014a
        L_0x0143:
            com.github.mikephil.charting.utils.ViewPortHandler r4 = r6.mViewPortHandler
            float r4 = r4.contentLeft()
            float r3 = r3 + r4
        L_0x014a:
            com.github.mikephil.charting.components.Legend$LegendDirection r4 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r5 != r4) goto L_0x0154
            com.github.mikephil.charting.components.Legend r4 = r6.mLegend
            float r4 = r4.mNeededWidth
            float r4 = r4 + r3
            goto L_0x0139
        L_0x0154:
            r7 = r3
        L_0x0155:
            int[] r3 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation
            int r0 = r0.ordinal()
            r0 = r3[r0]
            r3 = 1
            if (r0 == r3) goto L_0x0269
            r4 = 2
            if (r0 == r4) goto L_0x0165
            goto L_0x03ce
        L_0x0165:
            int[] r0 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment
            int r1 = r1.ordinal()
            r0 = r0[r1]
            if (r0 == r3) goto L_0x01a6
            if (r0 == r4) goto L_0x018e
            r1 = 3
            if (r0 == r1) goto L_0x0177
            r0 = r20
            goto L_0x01b4
        L_0x0177:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            float r2 = r2.mNeededHeight
            float r2 = r2 / r1
            float r0 = r0 - r2
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.getYOffset()
            float r0 = r0 + r1
            goto L_0x01b4
        L_0x018e:
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r0 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r0) goto L_0x0199
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            goto L_0x019f
        L_0x0199:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.contentBottom()
        L_0x019f:
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r1 = r1 + r2
            float r0 = r0 - r1
            goto L_0x01b4
        L_0x01a6:
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r0 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r0) goto L_0x01ad
            r0 = r20
            goto L_0x01b3
        L_0x01ad:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.contentTop()
        L_0x01b3:
            float r0 = r0 + r2
        L_0x01b4:
            r9 = r0
            r18 = r20
            r10 = 0
            r15 = 0
        L_0x01b9:
            int r0 = r12.length
            if (r15 >= r0) goto L_0x03ce
            r4 = r12[r15]
            com.github.mikephil.charting.components.Legend$LegendForm r0 = r4.form
            com.github.mikephil.charting.components.Legend$LegendForm r1 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r0 == r1) goto L_0x01c7
            r19 = 1
            goto L_0x01c9
        L_0x01c7:
            r19 = 0
        L_0x01c9:
            float r0 = r4.formSize
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 == 0) goto L_0x01d4
            r21 = r16
            goto L_0x01dc
        L_0x01d4:
            float r0 = r4.formSize
            float r0 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r21 = r0
        L_0x01dc:
            if (r19 == 0) goto L_0x020c
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r5 != r0) goto L_0x01e5
            float r0 = r7 + r18
            goto L_0x01e9
        L_0x01e5:
            float r0 = r21 - r18
            float r0 = r7 - r0
        L_0x01e9:
            r22 = r0
            float r3 = r9 + r11
            com.github.mikephil.charting.components.Legend r2 = r6.mLegend
            r0 = r34
            r1 = r35
            r24 = r2
            r2 = r22
            r8 = r17
            r17 = r4
            r27 = r11
            r11 = r5
            r5 = r24
            r0.drawForm(r1, r2, r3, r4, r5)
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r0) goto L_0x0209
            float r22 = r22 + r21
        L_0x0209:
            r0 = r17
            goto L_0x0214
        L_0x020c:
            r27 = r11
            r8 = r17
            r11 = r5
            r0 = r4
            r22 = r7
        L_0x0214:
            java.lang.String r1 = r0.label
            if (r1 == 0) goto L_0x0259
            if (r19 == 0) goto L_0x0226
            if (r10 != 0) goto L_0x0226
            com.github.mikephil.charting.components.Legend$LegendDirection r1 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r1) goto L_0x0222
            r1 = r13
            goto L_0x0223
        L_0x0222:
            float r1 = -r13
        L_0x0223:
            float r22 = r22 + r1
            goto L_0x022a
        L_0x0226:
            if (r10 == 0) goto L_0x022a
            r22 = r7
        L_0x022a:
            com.github.mikephil.charting.components.Legend$LegendDirection r1 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r1) goto L_0x0239
            android.graphics.Paint r1 = r6.mLegendLabelPaint
            java.lang.String r2 = r0.label
            int r1 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r1, r2)
            float r1 = (float) r1
            float r22 = r22 - r1
        L_0x0239:
            r1 = r22
            if (r10 != 0) goto L_0x0247
            float r2 = r9 + r26
            java.lang.String r0 = r0.label
            r5 = r35
            r6.drawLabel(r5, r1, r2, r0)
            goto L_0x0253
        L_0x0247:
            r5 = r35
            float r2 = r26 + r14
            float r9 = r9 + r2
            float r2 = r9 + r26
            java.lang.String r0 = r0.label
            r6.drawLabel(r5, r1, r2, r0)
        L_0x0253:
            float r0 = r26 + r14
            float r9 = r9 + r0
            r18 = r20
            goto L_0x0260
        L_0x0259:
            r5 = r35
            float r21 = r21 + r8
            float r18 = r18 + r21
            r10 = 1
        L_0x0260:
            int r15 = r15 + 1
            r17 = r8
            r5 = r11
            r11 = r27
            goto L_0x01b9
        L_0x0269:
            r27 = r11
            r8 = r17
            r11 = r5
            r5 = r35
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r9 = r0.getCalculatedLineSizes()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r10 = r0.getCalculatedLabelSizes()
            com.github.mikephil.charting.components.Legend r0 = r6.mLegend
            java.util.List r4 = r0.getCalculatedLabelBreakPoints()
            int[] r0 = com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r3 = 1
            if (r0 == r3) goto L_0x02b3
            r1 = 2
            if (r0 == r1) goto L_0x02a6
            r1 = 3
            if (r0 == r1) goto L_0x0296
            r2 = r20
            goto L_0x02b3
        L_0x0296:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r0 = r0 - r1
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            float r2 = r2 + r0
            goto L_0x02b3
        L_0x02a6:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float r0 = r0.getChartHeight()
            float r0 = r0 - r2
            com.github.mikephil.charting.components.Legend r1 = r6.mLegend
            float r1 = r1.mNeededHeight
            float r2 = r0 - r1
        L_0x02b3:
            int r1 = r12.length
            r17 = r7
            r0 = 0
            r3 = 0
        L_0x02b8:
            if (r0 >= r1) goto L_0x03ce
            r18 = r8
            r8 = r12[r0]
            r20 = r1
            com.github.mikephil.charting.components.Legend$LegendForm r1 = r8.form
            com.github.mikephil.charting.components.Legend$LegendForm r5 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r1 == r5) goto L_0x02c9
            r22 = 1
            goto L_0x02cb
        L_0x02c9:
            r22 = 0
        L_0x02cb:
            float r1 = r8.formSize
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 == 0) goto L_0x02d6
            r24 = r16
            goto L_0x02de
        L_0x02d6:
            float r1 = r8.formSize
            float r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1)
            r24 = r1
        L_0x02de:
            int r1 = r4.size()
            if (r0 >= r1) goto L_0x02f7
            java.lang.Object r1 = r4.get(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x02f7
            float r1 = r26 + r14
            float r2 = r2 + r1
            r17 = r2
            r1 = r7
            goto L_0x02fb
        L_0x02f7:
            r1 = r17
            r17 = r2
        L_0x02fb:
            int r2 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0327
            com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER
            if (r15 != r2) goto L_0x0327
            int r2 = r9.size()
            if (r3 >= r2) goto L_0x0327
            com.github.mikephil.charting.components.Legend$LegendDirection r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r2) goto L_0x0316
            java.lang.Object r2 = r9.get(r3)
            com.github.mikephil.charting.utils.FSize r2 = (com.github.mikephil.charting.utils.FSize) r2
            float r2 = r2.width
            goto L_0x031f
        L_0x0316:
            java.lang.Object r2 = r9.get(r3)
            com.github.mikephil.charting.utils.FSize r2 = (com.github.mikephil.charting.utils.FSize) r2
            float r2 = r2.width
            float r2 = -r2
        L_0x031f:
            r19 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r19
            float r1 = r1 + r2
            int r3 = r3 + 1
            goto L_0x0329
        L_0x0327:
            r19 = 1073741824(0x40000000, float:2.0)
        L_0x0329:
            r28 = r3
            java.lang.String r2 = r8.label
            if (r2 != 0) goto L_0x0332
            r29 = 1
            goto L_0x0334
        L_0x0332:
            r29 = 0
        L_0x0334:
            if (r22 == 0) goto L_0x0362
            com.github.mikephil.charting.components.Legend$LegendDirection r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r2) goto L_0x033c
            float r1 = r1 - r24
        L_0x033c:
            r30 = r1
            float r3 = r17 + r27
            com.github.mikephil.charting.components.Legend r5 = r6.mLegend
            r2 = r0
            r0 = r34
            r1 = r35
            r31 = r7
            r7 = r2
            r2 = r30
            r23 = 1
            r32 = r4
            r4 = r8
            r33 = r9
            r9 = r35
            r0.drawForm(r1, r2, r3, r4, r5)
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r0) goto L_0x035f
            float r1 = r30 + r24
            goto L_0x036d
        L_0x035f:
            r1 = r30
            goto L_0x036d
        L_0x0362:
            r32 = r4
            r31 = r7
            r33 = r9
            r23 = 1
            r9 = r35
            r7 = r0
        L_0x036d:
            if (r29 != 0) goto L_0x03a9
            if (r22 == 0) goto L_0x0379
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x0377
            float r0 = -r13
            goto L_0x0378
        L_0x0377:
            r0 = r13
        L_0x0378:
            float r1 = r1 + r0
        L_0x0379:
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x0386
            java.lang.Object r0 = r10.get(r7)
            com.github.mikephil.charting.utils.FSize r0 = (com.github.mikephil.charting.utils.FSize) r0
            float r0 = r0.width
            float r1 = r1 - r0
        L_0x0386:
            float r0 = r17 + r26
            java.lang.String r2 = r8.label
            r6.drawLabel(r9, r1, r0, r2)
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT
            if (r11 != r0) goto L_0x039a
            java.lang.Object r0 = r10.get(r7)
            com.github.mikephil.charting.utils.FSize r0 = (com.github.mikephil.charting.utils.FSize) r0
            float r0 = r0.width
            float r1 = r1 + r0
        L_0x039a:
            com.github.mikephil.charting.components.Legend$LegendDirection r0 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r0) goto L_0x03a2
            r0 = r21
            float r2 = -r0
            goto L_0x03a5
        L_0x03a2:
            r0 = r21
            r2 = r0
        L_0x03a5:
            float r1 = r1 + r2
            r2 = r18
            goto L_0x03b7
        L_0x03a9:
            r0 = r21
            com.github.mikephil.charting.components.Legend$LegendDirection r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT
            if (r11 != r2) goto L_0x03b3
            r2 = r18
            float r4 = -r2
            goto L_0x03b6
        L_0x03b3:
            r2 = r18
            r4 = r2
        L_0x03b6:
            float r1 = r1 + r4
        L_0x03b7:
            int r3 = r7 + 1
            r21 = r0
            r8 = r2
            r0 = r3
            r5 = r9
            r2 = r17
            r3 = r28
            r7 = r31
            r4 = r32
            r9 = r33
            r17 = r1
            r1 = r20
            goto L_0x02b8
        L_0x03ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.renderLegend(android.graphics.Canvas):void");
    }

    /* access modifiers changed from: protected */
    public void drawForm(Canvas canvas, float f, float f2, LegendEntry legendEntry, Legend legend) {
        if (legendEntry.formColor != 1122868 && legendEntry.formColor != 1122867 && legendEntry.formColor != 0) {
            int save = canvas.save();
            Legend.LegendForm legendForm = legendEntry.form;
            if (legendForm == Legend.LegendForm.DEFAULT) {
                legendForm = legend.getForm();
            }
            this.mLegendFormPaint.setColor(legendEntry.formColor);
            float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
            float f3 = convertDpToPixel / 2.0f;
            int i = AnonymousClass1.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm[legendForm.ordinal()];
            if (i == 3 || i == 4) {
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(f + f3, f2, f3, this.mLegendFormPaint);
            } else if (i == 5) {
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(f, f2 - f3, f + convertDpToPixel, f2 + f3, this.mLegendFormPaint);
            } else if (i == 6) {
                float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
                DashPathEffect formLineDashEffect = legendEntry.formLineDashEffect == null ? legend.getFormLineDashEffect() : legendEntry.formLineDashEffect;
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                this.mLegendFormPaint.setStrokeWidth(convertDpToPixel2);
                this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                this.mLineFormPath.reset();
                this.mLineFormPath.moveTo(f, f2);
                this.mLineFormPath.lineTo(f + convertDpToPixel, f2);
                canvas.drawPath(this.mLineFormPath, this.mLegendFormPaint);
            }
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: com.github.mikephil.charting.renderer.LegendRenderer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|(2:21|22)|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Can't wrap try/catch for region: R(35:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|25|26|27|28|29|30|31|33|34|35|36|37|38|40) */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x009a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00a4 */
        static {
            /*
                com.github.mikephil.charting.components.Legend$LegendForm[] r0 = com.github.mikephil.charting.components.Legend.LegendForm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm = r0
                r1 = 1
                com.github.mikephil.charting.components.Legend$LegendForm r2 = com.github.mikephil.charting.components.Legend.LegendForm.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.github.mikephil.charting.components.Legend$LegendForm r3 = com.github.mikephil.charting.components.Legend.LegendForm.EMPTY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x003e }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.SQUARE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendForm     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.github.mikephil.charting.components.Legend$LegendForm r4 = com.github.mikephil.charting.components.Legend.LegendForm.LINE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.github.mikephil.charting.components.Legend$LegendOrientation[] r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation = r3
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x005a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.github.mikephil.charting.components.Legend$LegendOrientation r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment = r3
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment     // Catch:{ NoSuchFieldError -> 0x007f }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x007f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r3 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.github.mikephil.charting.components.Legend$LegendVerticalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment[] r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment = r3
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r4 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT     // Catch:{ NoSuchFieldError -> 0x009a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x009a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x009a }
            L_0x009a:
                int[] r1 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x00a4 }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x00a4 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a4 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x00a4 }
            L_0x00a4:
                int[] r0 = $SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment     // Catch:{ NoSuchFieldError -> 0x00ae }
                com.github.mikephil.charting.components.Legend$LegendHorizontalAlignment r1 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x00ae }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ae }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ae }
            L_0x00ae:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.LegendRenderer.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.mLegendLabelPaint);
    }
}
