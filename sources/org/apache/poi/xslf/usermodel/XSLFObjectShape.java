package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class XSLFObjectShape extends XSLFGraphicFrame implements ObjectShape<XSLFShape, XSLFTextParagraph> {
    private static final QName[] CT_PICTURE = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "pic")};
    private static final QName[] GRAPHIC = {new QName(XSSFRelation.NS_DRAWINGML, "graphic")};
    private static final QName[] GRAPHIC_DATA = {new QName(XSSFRelation.NS_DRAWINGML, "graphicData")};
    private static final QName[] OLE_OBJ = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "oleObj")};
    static final String OLE_URI = "http://schemas.openxmlformats.org/presentationml/2006/ole";
    private XSLFPictureData _data;
    private final CTOleObject _oleObject;

    XSLFObjectShape(CTGraphicalObjectFrame cTGraphicalObjectFrame, XSLFSheet xSLFSheet) {
        super(cTGraphicalObjectFrame, xSLFSheet);
        try {
            this._oleObject = (CTOleObject) XPathHelper.selectProperty(getXmlObject(), CTOleObject.class, (XSLFShape.ReparseFactory) null, GRAPHIC, GRAPHIC_DATA, OLE_OBJ);
        } catch (XmlException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTOleObject getCTOleObject() {
        return this._oleObject;
    }

    public XSLFObjectData getObjectData() {
        return (XSLFObjectData) getSheet().getRelationPartById(getCTOleObject().getId()).getDocumentPart();
    }

    public String getProgId() {
        CTOleObject cTOleObject = this._oleObject;
        if (cTOleObject == null) {
            return null;
        }
        return cTOleObject.getProgId();
    }

    public String getFullName() {
        CTOleObject cTOleObject = this._oleObject;
        if (cTOleObject == null) {
            return null;
        }
        return cTOleObject.getName();
    }

    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = getBlipId();
            if (blipId == null) {
                return null;
            }
            PackagePart packagePart = getSheet().getPackagePart();
            PackageRelationship relationship = packagePart.getRelationship(blipId);
            if (relationship != null) {
                try {
                    this._data = new XSLFPictureData(packagePart.getRelatedPart(relationship));
                } catch (Exception e) {
                    throw new POIXMLException((Throwable) e);
                }
            }
        }
        return this._data;
    }

    /* access modifiers changed from: protected */
    public CTBlip getBlip() {
        return getBlipFill().getBlip();
    }

    /* access modifiers changed from: protected */
    public String getBlipId() {
        String embed = getBlip().getEmbed();
        if (embed.isEmpty()) {
            return null;
        }
        return embed;
    }

    /* access modifiers changed from: protected */
    public CTBlipFillProperties getBlipFill() {
        try {
            CTPicture cTPicture = (CTPicture) XPathHelper.selectProperty(getXmlObject(), CTPicture.class, new XSLFObjectShape$$ExternalSyntheticLambda0(), GRAPHIC, GRAPHIC_DATA, OLE_OBJ, CT_PICTURE);
            if (cTPicture != null) {
                return cTPicture.getBlipFill();
            }
            return null;
        } catch (XmlException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static CTPicture parse(XMLStreamReader xMLStreamReader) throws XmlException {
        CTGroupShape parse = CTGroupShape.Factory.parse(xMLStreamReader);
        if (parse.sizeOfPicArray() > 0) {
            return parse.getPicArray(0);
        }
        return null;
    }

    public OutputStream updateObjectData(ObjectMetaData.Application application, final ObjectMetaData objectMetaData) throws IOException {
        final POIXMLDocumentPart.RelationPart relationPart;
        if (application != null) {
            objectMetaData = application.getMetaData();
        }
        if (objectMetaData == null || objectMetaData.getClassID() == null) {
            throw new IllegalArgumentException("either application and/or metaData needs to be set.");
        }
        XSLFSheet sheet = getSheet();
        if (this._oleObject.isSetId()) {
            relationPart = sheet.getRelationPartById(this._oleObject.getId());
        } else {
            try {
                XSLFRelation xSLFRelation = XSLFRelation.OLE_OBJECT;
                relationPart = sheet.createRelationship(xSLFRelation, XSLFFactory.getInstance(), sheet.getPackagePart().getPackage().getUnusedPartIndex(xSLFRelation.getDefaultFileName()), false);
                this._oleObject.setId(relationPart.getRelationship().getId());
            } catch (InvalidFormatException e) {
                throw new IOException("Unable to add new ole embedding", e);
            }
        }
        this._oleObject.setProgId(objectMetaData.getProgId());
        this._oleObject.setName(objectMetaData.getObjectName());
        return new ByteArrayOutputStream() {
            public void close() throws IOException {
                XSLFObjectShape.this.addUpdatedData(relationPart.getDocumentPart().getPackagePart(), objectMetaData, this);
            }
        };
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0073, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0078, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x007f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0080, code lost:
        if (r3 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0086, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r4.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x008a, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x008d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x008e, code lost:
        if (r2 != null) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0094, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0095, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0098, code lost:
        throw r4;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:15:0x0031, B:41:0x0074] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addUpdatedData(org.apache.poi.openxml4j.opc.PackagePart r3, org.apache.poi.sl.usermodel.ObjectMetaData r4, org.apache.commons.io.output.ByteArrayOutputStream r5) throws java.io.IOException {
        /*
            r2 = this;
            r3.clear()
            java.io.InputStream r2 = r5.toInputStream()
            java.io.InputStream r2 = org.apache.poi.poifs.filesystem.FileMagic.prepareToCheckMagic(r2)
            java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ all -> 0x008b }
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.InputStream) r2)     // Catch:{ all -> 0x007d }
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2     // Catch:{ all -> 0x007d }
            if (r0 != r1) goto L_0x003a
            org.apache.poi.poifs.filesystem.POIFSFileSystem r5 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x007d }
            r5.<init>((java.io.InputStream) r2)     // Catch:{ all -> 0x007d }
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = r5.getRoot()     // Catch:{ all -> 0x002e }
            org.apache.poi.hpsf.ClassID r4 = r4.getClassID()     // Catch:{ all -> 0x002e }
            r0.setStorageClsid(r4)     // Catch:{ all -> 0x002e }
            r5.writeFilesystem(r3)     // Catch:{ all -> 0x002e }
            r5.close()     // Catch:{ all -> 0x007d }
            goto L_0x0066
        L_0x002e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch:{ all -> 0x007d }
        L_0x0039:
            throw r0     // Catch:{ all -> 0x007d }
        L_0x003a:
            java.lang.String r0 = r4.getOleEntry()     // Catch:{ all -> 0x007d }
            if (r0 != 0) goto L_0x0044
            r5.writeTo(r3)     // Catch:{ all -> 0x007d }
            goto L_0x0066
        L_0x0044:
            org.apache.poi.poifs.filesystem.POIFSFileSystem r5 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x007d }
            r5.<init>()     // Catch:{ all -> 0x007d }
            org.apache.poi.hpsf.ClassID r0 = r4.getClassID()     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x0056
            org.apache.poi.poifs.filesystem.DirectoryNode r1 = r5.getRoot()     // Catch:{ all -> 0x0071 }
            r1.setStorageClsid(r0)     // Catch:{ all -> 0x0071 }
        L_0x0056:
            java.lang.String r4 = r4.getOleEntry()     // Catch:{ all -> 0x0071 }
            r5.createDocument(r2, r4)     // Catch:{ all -> 0x0071 }
            org.apache.poi.poifs.filesystem.Ole10Native.createOleMarkerEntry((org.apache.poi.poifs.filesystem.POIFSFileSystem) r5)     // Catch:{ all -> 0x0071 }
            r5.writeFilesystem(r3)     // Catch:{ all -> 0x0071 }
            r5.close()     // Catch:{ all -> 0x007d }
        L_0x0066:
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch:{ all -> 0x008b }
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()
        L_0x0070:
            return
        L_0x0071:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch:{ all -> 0x007d }
        L_0x007c:
            throw r0     // Catch:{ all -> 0x007d }
        L_0x007d:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x007f }
        L_0x007f:
            r5 = move-exception
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch:{ all -> 0x0086 }
            goto L_0x008a
        L_0x0086:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ all -> 0x008b }
        L_0x008a:
            throw r5     // Catch:{ all -> 0x008b }
        L_0x008b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x008d }
        L_0x008d:
            r4 = move-exception
            if (r2 == 0) goto L_0x0098
            r2.close()     // Catch:{ all -> 0x0094 }
            goto L_0x0098
        L_0x0094:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0098:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFObjectShape.addUpdatedData(org.apache.poi.openxml4j.opc.PackagePart, org.apache.poi.sl.usermodel.ObjectMetaData, org.apache.commons.io.output.ByteArrayOutputStream):void");
    }

    static CTGraphicalObjectFrame prototype(int i, String str) {
        CTGraphicalObjectFrame newInstance = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr = newInstance.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvGraphicFramePr.addNewCNvPr();
        addNewCNvPr.setName("Object " + i);
        addNewCNvPr.setId((long) i);
        addNewNvGraphicFramePr.addNewCNvGraphicFramePr();
        addNewNvGraphicFramePr.addNewNvPr();
        newInstance.addNewXfrm();
        CTGraphicalObjectData addNewGraphicData = newInstance.addNewGraphic().addNewGraphicData();
        addNewGraphicData.setUri(OLE_URI);
        XmlCursor newCursor = addNewGraphicData.newCursor();
        try {
            newCursor.toEndToken();
            newCursor.beginElement(new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "oleObj"));
            newCursor.insertElement(new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "embed"));
            CTGroupShape newInstance2 = CTGroupShape.Factory.newInstance();
            CTPicture addNewPic = newInstance2.addNewPic();
            CTPictureNonVisual addNewNvPicPr = addNewPic.addNewNvPicPr();
            CTNonVisualDrawingProps addNewCNvPr2 = addNewNvPicPr.addNewCNvPr();
            addNewCNvPr2.setName("");
            addNewCNvPr2.setId(0);
            addNewNvPicPr.addNewCNvPicPr();
            addNewNvPicPr.addNewNvPr();
            CTBlipFillProperties addNewBlipFill = addNewPic.addNewBlipFill();
            addNewBlipFill.addNewBlip().setEmbed(str);
            addNewBlipFill.addNewStretch().addNewFillRect();
            CTShapeProperties addNewSpPr = addNewPic.addNewSpPr();
            CTTransform2D addNewXfrm = addNewSpPr.addNewXfrm();
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewOff.setX(1270000);
            addNewOff.setY(1270000);
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            addNewExt.setCx(1270000);
            addNewExt.setCy(1270000);
            addNewSpPr.addNewPrstGeom().setPrst(STShapeType.RECT);
            newCursor = newInstance2.newCursor();
            newCursor.toStartDoc();
            newCursor.moveXmlContents(newCursor);
            return newInstance;
        } catch (Throwable th) {
            throw th;
        } finally {
            newCursor.dispose();
        }
    }
}
