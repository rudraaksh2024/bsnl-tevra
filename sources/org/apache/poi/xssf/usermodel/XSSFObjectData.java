package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.util.IOUtils;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;

public class XSSFObjectData extends XSSFSimpleShape implements ObjectData {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFObjectData.class);
    private static CTShape prototype;
    private CTOleObject oleObject;

    protected XSSFObjectData(XSSFDrawing xSSFDrawing, CTShape cTShape) {
        super(xSSFDrawing, cTShape);
    }

    /* JADX INFO: finally extract failed */
    protected static CTShape prototype() {
        if (prototype == null) {
            CTShape newInstance = CTShape.Factory.newInstance();
            CTShapeNonVisual addNewNvSpPr = newInstance.addNewNvSpPr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvSpPr.addNewCNvPr();
            addNewCNvPr.setId(1);
            addNewCNvPr.setName("Shape 1");
            CTOfficeArtExtension addNewExt = addNewCNvPr.addNewExtLst().addNewExt();
            addNewExt.setUri("{63B3BB69-23CF-44E3-9099-C40C66FF867C}");
            XmlCursor newCursor = addNewExt.newCursor();
            try {
                newCursor.toEndToken();
                newCursor.beginElement(new QName("http://schemas.microsoft.com/office/drawing/2010/main", "compatExt", "a14"));
                newCursor.insertNamespace("a14", "http://schemas.microsoft.com/office/drawing/2010/main");
                newCursor.insertAttributeWithValue("spid", "_x0000_s1");
                newCursor.dispose();
                addNewNvSpPr.addNewCNvSpPr();
                CTShapeProperties addNewSpPr = newInstance.addNewSpPr();
                CTTransform2D addNewXfrm = addNewSpPr.addNewXfrm();
                CTPositiveSize2D addNewExt2 = addNewXfrm.addNewExt();
                addNewExt2.setCx(0);
                addNewExt2.setCy(0);
                CTPoint2D addNewOff = addNewXfrm.addNewOff();
                addNewOff.setX(0);
                addNewOff.setY(0);
                CTPresetGeometry2D addNewPrstGeom = addNewSpPr.addNewPrstGeom();
                addNewPrstGeom.setPrst(STShapeType.RECT);
                addNewPrstGeom.addNewAvLst();
                prototype = newInstance;
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
        return prototype;
    }

    public String getOLE2ClassName() {
        return getOleObject().getProgId();
    }

    public CTOleObject getOleObject() {
        if (this.oleObject == null) {
            CTOleObject readOleObject = getSheet().readOleObject(getCTShape().getNvSpPr().getCNvPr().getId());
            this.oleObject = readOleObject;
            if (readOleObject == null) {
                throw new POIXMLException("Ole object not found in sheet container - it's probably a control element");
            }
        }
        return this.oleObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        if (r2 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getObjectData() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.getObjectPart()
            java.io.InputStream r2 = r2.getInputStream()
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArray(r2)     // Catch:{ all -> 0x0012 }
            if (r2 == 0) goto L_0x0011
            r2.close()
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r1 = move-exception
            if (r2 == 0) goto L_0x001f
            r2.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x001f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFObjectData.getObjectData():byte[]");
    }

    public PackagePart getObjectPart() {
        if (getOleObject().isSetId()) {
            POIXMLDocumentPart relationById = getSheet().getRelationById(getOleObject().getId());
            if (relationById == null) {
                return null;
            }
            return relationById.getPackagePart();
        }
        throw new POIXMLException("Invalid ole object found in sheet container");
    }

    public boolean hasDirectoryEntry() {
        boolean z = false;
        InputStream inputStream = null;
        try {
            inputStream = FileMagic.prepareToCheckMagic(getObjectPart().getInputStream());
            if (FileMagic.valueOf(inputStream) == FileMagic.OLE2) {
                z = true;
            }
            return z;
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("can't determine if directory entry exists");
            return false;
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r2 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.poifs.filesystem.DirectoryEntry getDirectory() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.getObjectPart()
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0017 }
            r0.<init>((java.io.InputStream) r2)     // Catch:{ all -> 0x0017 }
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = r0.getRoot()     // Catch:{ all -> 0x0017 }
            if (r2 == 0) goto L_0x0016
            r2.close()
        L_0x0016:
            return r0
        L_0x0017:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r1 = move-exception
            if (r2 == 0) goto L_0x0024
            r2.close()     // Catch:{ all -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0024:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFObjectData.getDirectory():org.apache.poi.poifs.filesystem.DirectoryEntry");
    }

    public String getFileName() {
        return getObjectPart().getPartName().getName();
    }

    /* access modifiers changed from: protected */
    public XSSFSheet getSheet() {
        return (XSSFSheet) getDrawing().getParent();
    }

    public XSSFPictureData getPictureData() {
        XmlCursor newCursor = getOleObject().newCursor();
        try {
            if (newCursor.toChild(XSSFRelation.NS_SPREADSHEETML, "objectPr")) {
                return (XSSFPictureData) getSheet().getRelationById(newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id")));
            }
            newCursor.dispose();
            return null;
        } finally {
            newCursor.dispose();
        }
    }

    public String getContentType() {
        return getObjectPart().getContentType();
    }
}
