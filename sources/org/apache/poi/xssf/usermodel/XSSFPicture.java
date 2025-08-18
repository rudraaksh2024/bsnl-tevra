package org.apache.poi.xssf.usermodel;

import java.awt.Dimension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPictureNonVisual;

public final class XSSFPicture extends XSSFShape implements Picture {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFPicture.class);
    private static CTPicture prototype;
    private final CTPicture ctPicture;

    protected XSSFPicture(XSSFDrawing xSSFDrawing, CTPicture cTPicture) {
        this.drawing = xSSFDrawing;
        this.ctPicture = cTPicture;
    }

    protected static CTPicture prototype() {
        if (prototype == null) {
            CTPicture newInstance = CTPicture.Factory.newInstance();
            CTPictureNonVisual addNewNvPicPr = newInstance.addNewNvPicPr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvPicPr.addNewCNvPr();
            addNewCNvPr.setId(1);
            addNewCNvPr.setName("Picture 1");
            addNewCNvPr.setDescr("Picture");
            addNewNvPicPr.addNewCNvPicPr().addNewPicLocks().setNoChangeAspect(true);
            CTBlipFillProperties addNewBlipFill = newInstance.addNewBlipFill();
            addNewBlipFill.addNewBlip().setEmbed("");
            addNewBlipFill.addNewStretch().addNewFillRect();
            CTShapeProperties addNewSpPr = newInstance.addNewSpPr();
            CTTransform2D addNewXfrm = addNewSpPr.addNewXfrm();
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            addNewExt.setCx(0);
            addNewExt.setCy(0);
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewOff.setX(0);
            addNewOff.setY(0);
            CTPresetGeometry2D addNewPrstGeom = addNewSpPr.addNewPrstGeom();
            addNewPrstGeom.setPrst(STShapeType.RECT);
            addNewPrstGeom.addNewAvLst();
            prototype = newInstance;
        }
        return prototype;
    }

    /* access modifiers changed from: protected */
    public void setPictureReference(PackageRelationship packageRelationship) {
        this.ctPicture.getBlipFill().getBlip().setEmbed(packageRelationship.getId());
    }

    @Internal
    public CTPicture getCTPicture() {
        return this.ctPicture;
    }

    public void resize() {
        resize(Double.MAX_VALUE);
    }

    public void resize(double d) {
        resize(d, d);
    }

    public void resize(double d, double d2) {
        XSSFClientAnchor clientAnchor = getClientAnchor();
        XSSFClientAnchor preferredSize = getPreferredSize(d, d2);
        if (clientAnchor == null || preferredSize == null) {
            LOG.atWarn().log("picture is not anchored via client anchor - ignoring resize call");
            return;
        }
        int row1 = clientAnchor.getRow1() + (preferredSize.getRow2() - preferredSize.getRow1());
        clientAnchor.setCol2(clientAnchor.getCol1() + (preferredSize.getCol2() - preferredSize.getCol1()));
        clientAnchor.setDx2(preferredSize.getDx2());
        clientAnchor.setRow2(row1);
        clientAnchor.setDy2(preferredSize.getDy2());
    }

    public XSSFClientAnchor getPreferredSize() {
        return getPreferredSize(1.0d);
    }

    public XSSFClientAnchor getPreferredSize(double d) {
        return getPreferredSize(d, d);
    }

    public XSSFClientAnchor getPreferredSize(double d, double d2) {
        Dimension preferredSize = ImageUtils.setPreferredSize(this, d, d2);
        CTPositiveSize2D ext = this.ctPicture.getSpPr().getXfrm().getExt();
        ext.setCx((long) ((int) preferredSize.getWidth()));
        ext.setCy((long) ((int) preferredSize.getHeight()));
        return getClientAnchor();
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFPicture.getImageDimension(org.apache.poi.openxml4j.opc.PackagePart, int):java.awt.Dimension");
    }

    public Dimension getImageDimension() {
        XSSFPictureData pictureData = getPictureData();
        return getImageDimension(pictureData.getPackagePart(), pictureData.getPictureType());
    }

    public XSSFPictureData getPictureData() {
        return (XSSFPictureData) getDrawing().getRelationById(this.ctPicture.getBlipFill().getBlip().getEmbed());
    }

    /* access modifiers changed from: protected */
    public CTShapeProperties getShapeProperties() {
        return this.ctPicture.getSpPr();
    }

    public XSSFClientAnchor getClientAnchor() {
        XSSFAnchor anchor = getAnchor();
        if (anchor instanceof XSSFClientAnchor) {
            return (XSSFClientAnchor) anchor;
        }
        return null;
    }

    public XSSFSheet getSheet() {
        return (XSSFSheet) getDrawing().getParent();
    }

    public String getShapeName() {
        return this.ctPicture.getNvPicPr().getCNvPr().getName();
    }
}
