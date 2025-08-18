package org.apache.poi.xssf.streaming;

import java.awt.Dimension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

public final class SXSSFPicture implements Picture {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final float DEFAULT_COLUMN_WIDTH = 9.140625f;
    private static final Logger LOG = LogManager.getLogger((Class<?>) SXSSFPicture.class);
    private final XSSFPicture _picture;
    private final SXSSFWorkbook _wb;

    SXSSFPicture(SXSSFWorkbook sXSSFWorkbook, XSSFPicture xSSFPicture) {
        this._wb = sXSSFWorkbook;
        this._picture = xSSFPicture;
    }

    @Internal
    public CTPicture getCTPicture() {
        return this._picture.getCTPicture();
    }

    public void resize() {
        resize(1.0d);
    }

    public void resize(double d) {
        XSSFClientAnchor clientAnchor = getClientAnchor();
        XSSFClientAnchor preferredSize = getPreferredSize(d);
        if (clientAnchor == null || preferredSize == null) {
            LOG.atWarn().log("picture is not anchored via client anchor - ignoring resize call");
            return;
        }
        int row1 = clientAnchor.getRow1() + (preferredSize.getRow2() - preferredSize.getRow1());
        clientAnchor.setCol2(clientAnchor.getCol1() + (preferredSize.getCol2() - preferredSize.getCol1()));
        clientAnchor.setDx1(0);
        clientAnchor.setDx2(preferredSize.getDx2());
        clientAnchor.setRow2(row1);
        clientAnchor.setDy1(0);
        clientAnchor.setDy2(preferredSize.getDy2());
    }

    public XSSFClientAnchor getPreferredSize() {
        return getPreferredSize(1.0d);
    }

    public XSSFClientAnchor getPreferredSize(double d) {
        double d2;
        XSSFClientAnchor clientAnchor = getClientAnchor();
        if (clientAnchor == null) {
            LOG.atWarn().log("picture is not anchored via client anchor - ignoring resize call");
            return null;
        }
        XSSFPictureData pictureData = getPictureData();
        Dimension imageDimension = getImageDimension(pictureData.getPackagePart(), pictureData.getPictureType());
        double width = imageDimension.getWidth() * d;
        double height = imageDimension.getHeight() * d;
        int col1 = clientAnchor.getCol1() - 1;
        float f = 0.0f;
        while (true) {
            d2 = (double) f;
            if (d2 > width) {
                break;
            }
            col1++;
            f += getColumnWidthInPixels(col1);
        }
        clientAnchor.setCol2(col1);
        clientAnchor.setDx2((int) ((((double) getColumnWidthInPixels(col1)) - (d2 - width)) * 9525.0d));
        int row1 = clientAnchor.getRow1() - 1;
        double d3 = 0.0d;
        while (d3 <= height) {
            row1++;
            d3 += (double) getRowHeightInPixels(row1);
        }
        clientAnchor.setRow2(row1);
        clientAnchor.setDy2((int) ((((double) getRowHeightInPixels(row1)) - (d3 - height)) * 9525.0d));
        CTPositiveSize2D ext = getCTPicture().getSpPr().getXfrm().getExt();
        ext.setCx((long) (width * 9525.0d));
        ext.setCy((long) (height * 9525.0d));
        return clientAnchor;
    }

    private float getColumnWidthInPixels(int i) {
        CTCol column = getSheet().getColumnHelper().getColumn((long) i, false);
        return ((float) ((column == null || !column.isSetWidth()) ? 9.140625d : column.getWidth())) * 7.0017f;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [org.apache.poi.xssf.streaming.SXSSFSheet] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float getRowHeightInPixels(int r2) {
        /*
            r1 = this;
            org.apache.poi.xssf.usermodel.XSSFSheet r0 = r1.getSheet()
            org.apache.poi.xssf.streaming.SXSSFWorkbook r1 = r1._wb
            org.apache.poi.xssf.streaming.SXSSFSheet r1 = r1.getSXSSFSheet(r0)
            if (r1 != 0) goto L_0x000d
            goto L_0x000e
        L_0x000d:
            r0 = r1
        L_0x000e:
            org.apache.poi.ss.usermodel.Row r1 = r0.getRow(r2)
            if (r1 == 0) goto L_0x0019
            float r1 = r1.getHeightInPoints()
            goto L_0x001d
        L_0x0019:
            float r1 = r0.getDefaultRowHeightInPoints()
        L_0x001d:
            r2 = 1119879168(0x42c00000, float:96.0)
            float r1 = r1 * r2
            r2 = 1116733440(0x42900000, float:72.0)
            float r1 = r1 / r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFPicture.getRowHeightInPixels(int):float");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0011, code lost:
        if (r1 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.awt.Dimension getImageDimension(org.apache.poi.openxml4j.opc.PackagePart r1, int r2) {
        /*
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x001c }
            java.awt.Dimension r2 = org.apache.poi.ss.util.ImageUtils.getImageDimension(r1, r2)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x000d
            r1.close()     // Catch:{ IOException -> 0x001c }
        L_0x000d:
            return r2
        L_0x000e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r0 = move-exception
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ IOException -> 0x001c }
        L_0x001b:
            throw r0     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            r1 = move-exception
            org.apache.logging.log4j.Logger r2 = LOG
            org.apache.logging.log4j.LogBuilder r2 = r2.atWarn()
            org.apache.logging.log4j.LogBuilder r1 = r2.withThrowable(r1)
            java.lang.String r2 = "Failed to read image"
            r1.log((java.lang.String) r2)
            java.awt.Dimension r1 = new java.awt.Dimension
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFPicture.getImageDimension(org.apache.poi.openxml4j.opc.PackagePart, int):java.awt.Dimension");
    }

    public XSSFPictureData getPictureData() {
        return this._picture.getPictureData();
    }

    /* access modifiers changed from: protected */
    public CTShapeProperties getShapeProperties() {
        return getCTPicture().getSpPr();
    }

    public XSSFAnchor getAnchor() {
        return this._picture.getAnchor();
    }

    public void resize(double d, double d2) {
        this._picture.resize(d, d2);
    }

    public XSSFClientAnchor getPreferredSize(double d, double d2) {
        return this._picture.getPreferredSize(d, d2);
    }

    public Dimension getImageDimension() {
        return this._picture.getImageDimension();
    }

    public XSSFClientAnchor getClientAnchor() {
        XSSFAnchor anchor = getAnchor();
        if (anchor instanceof XSSFClientAnchor) {
            return (XSSFClientAnchor) anchor;
        }
        return null;
    }

    public XSSFDrawing getDrawing() {
        return this._picture.getDrawing();
    }

    public XSSFSheet getSheet() {
        return this._picture.getSheet();
    }

    public String getShapeName() {
        return this._picture.getShapeName();
    }

    public Shape getParent() {
        return this._picture.getParent();
    }

    public boolean isNoFill() {
        return this._picture.isNoFill();
    }

    public void setNoFill(boolean z) {
        this._picture.setNoFill(z);
    }

    public void setFillColor(int i, int i2, int i3) {
        this._picture.setFillColor(i, i2, i3);
    }

    public void setLineStyleColor(int i, int i2, int i3) {
        this._picture.setLineStyleColor(i, i2, i3);
    }
}
