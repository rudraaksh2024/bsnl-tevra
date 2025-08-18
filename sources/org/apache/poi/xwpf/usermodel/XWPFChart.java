package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

public class XWPFChart extends XDDFChart {
    public static final int DEFAULT_HEIGHT = 500000;
    public static final int DEFAULT_WIDTH = 500000;
    private Long checksum;
    private CTInline ctInline;

    public boolean equals(Object obj) {
        return obj == this;
    }

    protected XWPFChart() {
    }

    protected XWPFChart(PackagePart packagePart) throws IOException, XmlException {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartRelation() {
        return XWPFRelation.CHART;
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartWorkbookRelation() {
        return XWPFRelation.WORKBOOK;
    }

    /* access modifiers changed from: protected */
    public POIXMLFactory getChartFactory() {
        return XWPFFactory.getInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r0 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0029, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long getChecksum() {
        /*
            r3 = this;
            java.lang.Long r0 = r3.checksum
            if (r0 != 0) goto L_0x0031
            org.apache.poi.openxml4j.opc.PackagePart r0 = r3.getPackagePart()     // Catch:{ IOException -> 0x002a }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x002a }
            long r1 = org.apache.poi.util.IOUtils.calculateChecksum((java.io.InputStream) r0)     // Catch:{ all -> 0x001c }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x001c }
            r3.checksum = r1     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x0031
        L_0x001c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            if (r0 == 0) goto L_0x0029
            r0.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ IOException -> 0x002a }
        L_0x0029:
            throw r1     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r3)
            throw r0
        L_0x0031:
            java.lang.Long r3 = r3.checksum
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFChart.getChecksum():java.lang.Long");
    }

    public int hashCode() {
        return getChecksum().hashCode();
    }

    /* access modifiers changed from: protected */
    public void attach(String str, XWPFRun xWPFRun) throws InvalidFormatException, IOException {
        CTInline addChart = xWPFRun.addChart(str);
        this.ctInline = addChart;
        addChart.addNewExtent();
        setChartBoundingBox(500000, 500000);
    }

    public void setChartHeight(long j) {
        this.ctInline.getExtent().setCy(j);
    }

    public void setChartWidth(long j) {
        this.ctInline.getExtent().setCx(j);
    }

    public long getChartHeight() {
        return this.ctInline.getExtent().getCy();
    }

    public long getChartWidth() {
        return this.ctInline.getExtent().getCx();
    }

    public void setChartBoundingBox(long j, long j2) {
        setChartWidth(j);
        setChartHeight(j2);
    }

    public void setChartTopMargin(long j) {
        this.ctInline.setDistT(j);
    }

    public long getChartTopMargin(long j) {
        return this.ctInline.getDistT();
    }

    public void setChartBottomMargin(long j) {
        this.ctInline.setDistB(j);
    }

    public long getChartBottomMargin(long j) {
        return this.ctInline.getDistB();
    }

    public void setChartLeftMargin(long j) {
        this.ctInline.setDistL(j);
    }

    public long getChartLeftMargin(long j) {
        return this.ctInline.getDistL();
    }

    public void setChartRightMargin(long j) {
        this.ctInline.setDistR(j);
    }

    public long getChartRightMargin(long j) {
        return this.ctInline.getDistR();
    }

    public void setChartMargin(long j, long j2, long j3, long j4) {
        setChartBottomMargin(j3);
        setChartRightMargin(j2);
        setChartLeftMargin(j4);
        setChartRightMargin(j2);
    }
}
