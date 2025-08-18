package org.apache.poi.xslf.usermodel;

import java.awt.Insets;
import java.net.URI;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class XSLFPictureShape extends XSLFSimpleShape implements PictureShape<XSLFShape, XSLFTextParagraph> {
    private static final String BITMAP_URI = "{28A0092B-C50C-407E-A947-70E740481C1C}";
    private static final QName[] BLIP_FILL = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "blipFill")};
    private static final QName EMBED_TAG = new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "embed", "rel");
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFPictureShape.class);
    private static final String MS_DML_NS = "http://schemas.microsoft.com/office/drawing/2010/main";
    private static final String MS_SVG_NS = "http://schemas.microsoft.com/office/drawing/2016/SVG/main";
    private static final String SVG_URI = "{96DAC541-7B7A-43D3-8B79-37D633B846F1}";
    private XSLFPictureData _data;

    XSLFPictureShape(CTPicture cTPicture, XSLFSheet xSLFSheet) {
        super(cTPicture, xSLFSheet);
    }

    static CTPicture prototype(int i, String str) {
        CTPicture newInstance = CTPicture.Factory.newInstance();
        CTPictureNonVisual addNewNvPicPr = newInstance.addNewNvPicPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvPicPr.addNewCNvPr();
        addNewCNvPr.setName("Picture " + i);
        addNewCNvPr.setId((long) i);
        addNewNvPicPr.addNewCNvPicPr().addNewPicLocks().setNoChangeAspect(true);
        addNewNvPicPr.addNewNvPr();
        CTBlipFillProperties addNewBlipFill = newInstance.addNewBlipFill();
        addNewBlipFill.addNewBlip().setEmbed(str);
        addNewBlipFill.addNewStretch().addNewFillRect();
        CTPresetGeometry2D addNewPrstGeom = newInstance.addNewSpPr().addNewPrstGeom();
        addNewPrstGeom.setPrst(STShapeType.RECT);
        addNewPrstGeom.addNewAvLst();
        return newInstance;
    }

    public boolean isExternalLinkedPicture() {
        return getBlipId() == null && getBlipLink() != null;
    }

    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = getBlipId();
            if (blipId == null) {
                return null;
            }
            this._data = (XSLFPictureData) getSheet().getRelationById(blipId);
        }
        return this._data;
    }

    public void setPlaceholder(Placeholder placeholder) {
        super.setPlaceholder(placeholder);
    }

    public URI getPictureLink() {
        String blipLink;
        PackageRelationship relationship;
        if (getBlipId() != null || (blipLink = getBlipLink()) == null || (relationship = getSheet().getPackagePart().getRelationship(blipLink)) == null) {
            return null;
        }
        return relationship.getTargetURI();
    }

    /* access modifiers changed from: protected */
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties blipFill = ((CTPicture) getXmlObject()).getBlipFill();
        if (blipFill != null) {
            return blipFill;
        }
        try {
            return (CTBlipFillProperties) XPathHelper.selectProperty(getXmlObject(), CTBlipFillProperties.class, new XSLFPictureShape$$ExternalSyntheticLambda0(), BLIP_FILL);
        } catch (XmlException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static CTBlipFillProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
        CTPicture parse = CTPicture.Factory.parse(xMLStreamReader);
        if (parse != null) {
            return parse.getBlipFill();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public CTBlip getBlip() {
        return getBlipFill().getBlip();
    }

    /* access modifiers changed from: protected */
    public String getBlipLink() {
        CTBlip blip = getBlip();
        if (blip == null) {
            return null;
        }
        String link = blip.getLink();
        if (link.isEmpty()) {
            return null;
        }
        return link;
    }

    /* access modifiers changed from: protected */
    public String getBlipId() {
        CTBlip blip = getBlip();
        if (blip == null) {
            return null;
        }
        String embed = blip.getEmbed();
        if (embed.isEmpty()) {
            return null;
        }
        return embed;
    }

    public Insets getClipping() {
        CTRelativeRect srcRect = getBlipFill().getSrcRect();
        if (srcRect == null) {
            return null;
        }
        return new Insets(POIXMLUnits.parsePercent(srcRect.xgetT()), POIXMLUnits.parsePercent(srcRect.xgetL()), POIXMLUnits.parsePercent(srcRect.xgetB()), POIXMLUnits.parsePercent(srcRect.xgetR()));
    }

    public void setSvgImage(XSLFPictureData xSLFPictureData) {
        CTBlip blip = getBlip();
        CTOfficeArtExtensionList extLst = blip.isSetExtLst() ? blip.getExtLst() : blip.addNewExtLst();
        if (getExt(extLst, BITMAP_URI) == -1) {
            CTOfficeArtExtension addNewExt = extLst.addNewExt();
            addNewExt.setUri(BITMAP_URI);
            XmlCursor newCursor = addNewExt.newCursor();
            try {
                newCursor.toEndToken();
                newCursor.beginElement(new QName(MS_DML_NS, "useLocalDpi", "a14"));
                newCursor.insertNamespace("a14", MS_DML_NS);
                newCursor.insertAttributeWithValue("val", "0");
            } finally {
                newCursor.dispose();
            }
        }
        int ext = getExt(extLst, SVG_URI);
        if (ext != -1) {
            extLst.removeExt(ext);
        }
        String relationId = getSheet().getRelationId(xSLFPictureData);
        if (relationId == null) {
            relationId = getSheet().addRelation((String) null, XSLFRelation.IMAGE_SVG, xSLFPictureData).getRelationship().getId();
        }
        CTOfficeArtExtension addNewExt2 = extLst.addNewExt();
        addNewExt2.setUri(SVG_URI);
        XmlCursor newCursor2 = addNewExt2.newCursor();
        try {
            newCursor2.toEndToken();
            newCursor2.beginElement(new QName(MS_SVG_NS, "svgBlip", "asvg"));
            newCursor2.insertNamespace("asvg", MS_SVG_NS);
            newCursor2.insertAttributeWithValue(EMBED_TAG, relationId);
        } finally {
            newCursor2.dispose();
        }
    }

    public PictureData getAlternativePictureData() {
        return getSvgImage();
    }

    public String getName() {
        CTNonVisualDrawingProps cNvPr;
        CTPictureNonVisual cTPictureNonVisual = getCTPictureNonVisual();
        if (cTPictureNonVisual == null || (cNvPr = cTPictureNonVisual.getCNvPr()) == null) {
            return null;
        }
        return cNvPr.getName();
    }

    public boolean setName(String str) {
        XmlObject xmlObject = getXmlObject();
        if (!(xmlObject instanceof CTPicture)) {
            return false;
        }
        CTPicture cTPicture = (CTPicture) xmlObject;
        CTPictureNonVisual nvPicPr = cTPicture.getNvPicPr();
        if (nvPicPr == null) {
            nvPicPr = cTPicture.addNewNvPicPr();
        }
        if (nvPicPr == null) {
            return false;
        }
        CTNonVisualDrawingProps cNvPr = nvPicPr.getCNvPr();
        if (cNvPr == null) {
            cNvPr = nvPicPr.addNewCNvPr();
        }
        if (cNvPr == null) {
            return false;
        }
        cNvPr.setName(str);
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r7v3, types: [org.apache.poi.ooxml.POIXMLDocumentPart] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XSLFPictureData getSvgImage() {
        /*
            r7 = this;
            org.openxmlformats.schemas.drawingml.x2006.main.CTBlip r0 = r7.getBlip()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList r0 = r0.getExtLst()
            if (r0 != 0) goto L_0x000f
            return r1
        L_0x000f:
            int r2 = r0.sizeOfExtArray()
            r3 = 0
        L_0x0014:
            if (r3 >= r2) goto L_0x004a
            org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension r4 = r0.getExtArray(r3)
            org.apache.xmlbeans.XmlCursor r4 = r4.newCursor()
            java.lang.String r5 = "http://schemas.microsoft.com/office/drawing/2016/SVG/main"
            java.lang.String r6 = "svgBlip"
            boolean r5 = r4.toChild((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ all -> 0x0045 }
            if (r5 == 0) goto L_0x003f
            javax.xml.namespace.QName r0 = EMBED_TAG     // Catch:{ all -> 0x0045 }
            java.lang.String r0 = r4.getAttributeText(r0)     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x003b
            org.apache.poi.xslf.usermodel.XSLFSheet r7 = r7.getSheet()     // Catch:{ all -> 0x0045 }
            org.apache.poi.ooxml.POIXMLDocumentPart r7 = r7.getRelationById(r0)     // Catch:{ all -> 0x0045 }
            r1 = r7
            org.apache.poi.xslf.usermodel.XSLFPictureData r1 = (org.apache.poi.xslf.usermodel.XSLFPictureData) r1     // Catch:{ all -> 0x0045 }
        L_0x003b:
            r4.dispose()
            return r1
        L_0x003f:
            r4.dispose()
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0045:
            r7 = move-exception
            r4.dispose()
            throw r7
        L_0x004a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPictureShape.getSvgImage():org.apache.poi.xslf.usermodel.XSLFPictureData");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007e, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007f, code lost:
        if (r1 != null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0085, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        r11.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.xslf.usermodel.XSLFPictureShape addSvgImage(org.apache.poi.xslf.usermodel.XSLFSheet r11, org.apache.poi.xslf.usermodel.XSLFPictureData r12, org.apache.poi.sl.usermodel.PictureData.PictureType r13, java.awt.geom.Rectangle2D.Double r14) throws java.io.IOException {
        /*
            org.apache.poi.xslf.draw.SVGImageRenderer r0 = new org.apache.poi.xslf.draw.SVGImageRenderer
            r0.<init>()
            java.io.InputStream r1 = r12.getInputStream()
            org.apache.poi.sl.usermodel.PictureData$PictureType r2 = r12.getType()     // Catch:{ all -> 0x007c }
            java.lang.String r2 = r2.contentType     // Catch:{ all -> 0x007c }
            r0.loadImage((java.io.InputStream) r1, (java.lang.String) r2)     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x0017
            r1.close()
        L_0x0017:
            java.awt.geom.Dimension2D r1 = r0.getDimension()
            if (r14 == 0) goto L_0x001e
            goto L_0x003c
        L_0x001e:
            java.awt.geom.Rectangle2D$Double r14 = new java.awt.geom.Rectangle2D$Double
            r3 = 0
            r5 = 0
            double r7 = r1.getWidth()
            int r2 = (int) r7
            double r7 = (double) r2
            double r7 = org.apache.poi.util.Units.pixelToPoints((double) r7)
            double r9 = r1.getHeight()
            int r2 = (int) r9
            double r9 = (double) r2
            double r9 = org.apache.poi.util.Units.pixelToPoints((double) r9)
            r2 = r14
            r2.<init>(r3, r5, r7, r9)
        L_0x003c:
            if (r13 == 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            org.apache.poi.sl.usermodel.PictureData$PictureType r13 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG
        L_0x0041:
            org.apache.poi.sl.usermodel.PictureData$PictureType r2 = org.apache.poi.sl.usermodel.PictureData.PictureType.JPEG
            if (r13 == r2) goto L_0x004f
            org.apache.poi.sl.usermodel.PictureData$PictureType r2 = org.apache.poi.sl.usermodel.PictureData.PictureType.GIF
            if (r13 == r2) goto L_0x004f
            org.apache.poi.sl.usermodel.PictureData$PictureType r2 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG
            if (r13 == r2) goto L_0x004f
            org.apache.poi.sl.usermodel.PictureData$PictureType r13 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG
        L_0x004f:
            java.awt.image.BufferedImage r0 = r0.getImage(r1)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r2 = 100000(0x186a0, float:1.4013E-40)
            r1.<init>(r2)
            java.lang.String r2 = r13.extension
            r3 = 1
            java.lang.String r2 = r2.substring(r3)
            javax.imageio.ImageIO.write(r0, r2, r1)
            org.apache.poi.xslf.usermodel.XMLSlideShow r0 = r11.getSlideShow()
            java.io.InputStream r1 = r1.toInputStream()
            org.apache.poi.xslf.usermodel.XSLFPictureData r13 = r0.addPicture((java.io.InputStream) r1, (org.apache.poi.sl.usermodel.PictureData.PictureType) r13)
            org.apache.poi.xslf.usermodel.XSLFPictureShape r11 = r11.createPicture((org.apache.poi.sl.usermodel.PictureData) r13)
            r11.setAnchor(r14)
            r11.setSvgImage(r12)
            return r11
        L_0x007c:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x007e }
        L_0x007e:
            r12 = move-exception
            if (r1 == 0) goto L_0x0089
            r1.close()     // Catch:{ all -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r13 = move-exception
            r11.addSuppressed(r13)
        L_0x0089:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPictureShape.addSvgImage(org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.xslf.usermodel.XSLFPictureData, org.apache.poi.sl.usermodel.PictureData$PictureType, java.awt.geom.Rectangle2D):org.apache.poi.xslf.usermodel.XSLFPictureShape");
    }

    private int getExt(CTOfficeArtExtensionList cTOfficeArtExtensionList, String str) {
        int sizeOfExtArray = cTOfficeArtExtensionList.sizeOfExtArray();
        for (int i = 0; i < sizeOfExtArray; i++) {
            if (str.equals(cTOfficeArtExtensionList.getExtArray(i).getUri())) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFShape xSLFShape) {
        CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps;
        super.copy(xSLFShape);
        XSLFPictureShape xSLFPictureShape = (XSLFPictureShape) xSLFShape;
        String blipId = xSLFPictureShape.getBlipId();
        if (blipId == null) {
            LOG.atWarn().log("unable to copy invalid picture shape");
            return;
        }
        String importBlip = getSheet().importBlip(blipId, xSLFPictureShape.getSheet());
        CTBlip blip = getBlipFill().getBlip();
        blip.setEmbed(importBlip);
        CTPictureNonVisual cTPictureNonVisual = getCTPictureNonVisual();
        if (cTPictureNonVisual == null) {
            cTApplicationNonVisualDrawingProps = null;
        } else {
            cTApplicationNonVisualDrawingProps = cTPictureNonVisual.getNvPr();
        }
        if (cTApplicationNonVisualDrawingProps != null && cTApplicationNonVisualDrawingProps.isSetCustDataLst()) {
            cTApplicationNonVisualDrawingProps.unsetCustDataLst();
        }
        if (blip.isSetExtLst()) {
            for (CTOfficeArtExtension selectPath : blip.getExtLst().getExtArray()) {
                XmlObject[] selectPath2 = selectPath.selectPath("declare namespace a14='http://schemas.microsoft.com/office/drawing/2010/main' $this//a14:imgProps/a14:imgLayer");
                if (selectPath2 != null && selectPath2.length == 1) {
                    XmlCursor newCursor = selectPath2[0].newCursor();
                    try {
                        QName qName = EMBED_TAG;
                        newCursor.setAttributeText(qName, getSheet().importBlip(newCursor.getAttributeText(qName), xSLFPictureShape.getSheet()));
                    } finally {
                        newCursor.dispose();
                    }
                }
            }
        }
    }

    public boolean isVideoFile() {
        CTApplicationNonVisualDrawingProps nvPr;
        CTPictureNonVisual cTPictureNonVisual = getCTPictureNonVisual();
        if (cTPictureNonVisual == null || (nvPr = cTPictureNonVisual.getNvPr()) == null) {
            return false;
        }
        return nvPr.isSetVideoFile();
    }

    public String getVideoFileLink() {
        CTPictureNonVisual cTPictureNonVisual;
        CTApplicationNonVisualDrawingProps nvPr;
        if (!isVideoFile() || (cTPictureNonVisual = getCTPictureNonVisual()) == null || (nvPr = cTPictureNonVisual.getNvPr()) == null || nvPr.getVideoFile() == null) {
            return null;
        }
        return nvPr.getVideoFile().getLink();
    }

    private CTPictureNonVisual getCTPictureNonVisual() {
        XmlObject xmlObject = getXmlObject();
        if (xmlObject instanceof CTPicture) {
            return ((CTPicture) xmlObject).getNvPicPr();
        }
        return null;
    }
}
