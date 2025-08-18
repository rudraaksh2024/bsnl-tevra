package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STObjectType;
import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.schemas.vmldrawing.XmlDocument;
import org.apache.poi.util.Internal;
import org.apache.poi.util.ReplacingInputStream;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

public final class XSSFVMLDrawing extends POIXMLDocumentPart {
    private static final String COMMENT_SHAPE_TYPE_ID = "_x0000_t202";
    public static final QName QNAME_VMLDRAWING = new QName("urn:schemas-poi-apache-org:vmldrawing", "xml");
    private static final Pattern ptrn_shapeId = Pattern.compile("_x0000_s(\\d+)");
    private int _shapeId = 1024;
    private String _shapeTypeId;
    private XmlDocument root;

    protected XSSFVMLDrawing() {
        newDrawing();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected XSSFVMLDrawing(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            r2 = 1024(0x400, float:1.435E-42)
            r1._shapeId = r2
            org.apache.poi.openxml4j.opc.PackagePart r2 = r1.getPackagePart()
            java.io.InputStream r2 = r2.getInputStream()
            r1.read(r2)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0017
            r2.close()
        L_0x0017:
            return
        L_0x0018:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001a }
        L_0x001a:
            r0 = move-exception
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0025:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFVMLDrawing.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public XmlDocument getDocument() {
        return this.root;
    }

    /* access modifiers changed from: protected */
    public void read(InputStream inputStream) throws IOException, XmlException {
        String id;
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setLoadSubstituteNamespaces(Collections.singletonMap("", QNAME_VMLDRAWING.getNamespaceURI()));
        xmlOptions.setDocumentType(XmlDocument.type);
        XmlDocument parse = XmlDocument.Factory.parse((InputStream) new ReplacingInputStream((InputStream) new ReplacingInputStream(inputStream, "<br>", "<br/>"), " xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"", ""), xmlOptions);
        this.root = parse;
        XmlCursor newCursor = parse.getXml().newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTShapetype) {
                    this._shapeTypeId = ((CTShapetype) object).getId();
                } else if ((object instanceof CTShape) && (id = ((CTShape) object).getId()) != null) {
                    Matcher matcher = ptrn_shapeId.matcher(id);
                    if (matcher.find()) {
                        this._shapeId = Math.max(this._shapeId, Integer.parseInt(matcher.group(1)));
                    }
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public List<XmlObject> getItems() {
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = this.root.getXml().newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                arrayList.add(newCursor.getObject());
            }
            return arrayList;
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void write(OutputStream outputStream) throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveImplicitNamespaces(Collections.singletonMap("", QNAME_VMLDRAWING.getNamespaceURI()));
        this.root.save(outputStream, xmlOptions);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            r2.write(r0)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return
        L_0x0011:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFVMLDrawing.commit():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0091, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void newDrawing() {
        /*
            r4 = this;
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.apache.poi.schemas.vmldrawing.XmlDocument> r0 = org.apache.poi.schemas.vmldrawing.XmlDocument.Factory
            java.lang.Object r0 = r0.newInstance()
            org.apache.poi.schemas.vmldrawing.XmlDocument r0 = (org.apache.poi.schemas.vmldrawing.XmlDocument) r0
            r4.root = r0
            org.apache.poi.schemas.vmldrawing.CTXML r0 = r0.addNewXml()
            org.apache.xmlbeans.XmlCursor r0 = r0.newCursor()
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.office.ShapelayoutDocument> r1 = com.microsoft.schemas.office.office.ShapelayoutDocument.Factory     // Catch:{ all -> 0x0092 }
            java.lang.Object r1 = r1.newInstance()     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.office.office.ShapelayoutDocument r1 = (com.microsoft.schemas.office.office.ShapelayoutDocument) r1     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.office.office.CTShapeLayout r2 = r1.addNewShapelayout()     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.STExt$Enum r3 = com.microsoft.schemas.vml.STExt.EDIT     // Catch:{ all -> 0x0092 }
            r2.setExt(r3)     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.office.office.CTIdMap r2 = r2.addNewIdmap()     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.STExt$Enum r3 = com.microsoft.schemas.vml.STExt.EDIT     // Catch:{ all -> 0x0092 }
            r2.setExt(r3)     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = "1"
            r2.setData(r3)     // Catch:{ all -> 0x0092 }
            r0.toEndToken()     // Catch:{ all -> 0x0092 }
            org.apache.xmlbeans.XmlCursor r1 = r1.newCursor()     // Catch:{ all -> 0x0092 }
            r1.copyXmlContents(r0)     // Catch:{ all -> 0x008d }
            r1.dispose()     // Catch:{ all -> 0x0092 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.vml.CTGroup> r1 = com.microsoft.schemas.vml.CTGroup.Factory     // Catch:{ all -> 0x0092 }
            java.lang.Object r1 = r1.newInstance()     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.CTGroup r1 = (com.microsoft.schemas.vml.CTGroup) r1     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.CTShapetype r2 = r1.addNewShapetype()     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = "_x0000_t202"
            r4._shapeTypeId = r3     // Catch:{ all -> 0x0092 }
            r2.setId(r3)     // Catch:{ all -> 0x0092 }
            java.lang.String r4 = "21600,21600"
            r2.setCoordsize(r4)     // Catch:{ all -> 0x0092 }
            r4 = 1128923136(0x434a0000, float:202.0)
            r2.setSpt(r4)     // Catch:{ all -> 0x0092 }
            java.lang.String r4 = "m,l,21600r21600,l21600,xe"
            r2.setPath2(r4)     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.CTStroke r4 = r2.addNewStroke()     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.STStrokeJoinStyle$Enum r3 = com.microsoft.schemas.vml.STStrokeJoinStyle.MITER     // Catch:{ all -> 0x0092 }
            r4.setJoinstyle(r3)     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.vml.CTPath r4 = r2.addNewPath()     // Catch:{ all -> 0x0092 }
            org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse$Enum r2 = org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse.T     // Catch:{ all -> 0x0092 }
            r4.setGradientshapeok(r2)     // Catch:{ all -> 0x0092 }
            com.microsoft.schemas.office.office.STConnectType$Enum r2 = com.microsoft.schemas.office.office.STConnectType.RECT     // Catch:{ all -> 0x0092 }
            r4.setConnecttype(r2)     // Catch:{ all -> 0x0092 }
            r0.toEndToken()     // Catch:{ all -> 0x0092 }
            org.apache.xmlbeans.XmlCursor r4 = r1.newCursor()     // Catch:{ all -> 0x0092 }
            r4.copyXmlContents(r0)     // Catch:{ all -> 0x0088 }
            r4.dispose()     // Catch:{ all -> 0x0092 }
            r0.dispose()
            return
        L_0x0088:
            r1 = move-exception
            r4.dispose()     // Catch:{ all -> 0x0092 }
            throw r1     // Catch:{ all -> 0x0092 }
        L_0x008d:
            r4 = move-exception
            r1.dispose()     // Catch:{ all -> 0x0092 }
            throw r4     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r4 = move-exception
            r0.dispose()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFVMLDrawing.newDrawing():void");
    }

    @Internal
    public CTShape newCommentShape() {
        CTGroup newInstance = CTGroup.Factory.newInstance();
        CTShape addNewShape = newInstance.addNewShape();
        StringBuilder sb = new StringBuilder("_x0000_s");
        int i = this._shapeId + 1;
        this._shapeId = i;
        addNewShape.setId(sb.append(i).toString());
        addNewShape.setType("#" + this._shapeTypeId);
        addNewShape.setStyle("position:absolute; visibility:hidden");
        addNewShape.setFillcolor("#ffffe1");
        addNewShape.setInsetmode(STInsetMode.AUTO);
        addNewShape.addNewFill().setColor("#ffffe1");
        CTShadow addNewShadow = addNewShape.addNewShadow();
        addNewShadow.setOn(STTrueFalse.T);
        addNewShadow.setColor("black");
        addNewShadow.setObscured(STTrueFalse.T);
        addNewShape.addNewPath().setConnecttype(STConnectType.NONE);
        addNewShape.addNewTextbox().setStyle("mso-direction-alt:auto");
        CTClientData addNewClientData = addNewShape.addNewClientData();
        addNewClientData.setObjectType(STObjectType.NOTE);
        addNewClientData.addNewMoveWithCells();
        addNewClientData.addNewSizeWithCells();
        addNewClientData.addNewAnchor().setStringValue("1, 15, 0, 2, 3, 15, 3, 16");
        addNewClientData.addNewAutoFill().setStringValue("False");
        addNewClientData.addNewRow().setBigIntegerValue(BigInteger.valueOf(0));
        addNewClientData.addNewColumn().setBigIntegerValue(BigInteger.valueOf(0));
        XmlCursor newCursor = this.root.getXml().newCursor();
        try {
            newCursor.toEndToken();
            newCursor = newInstance.newCursor();
            newCursor.copyXmlContents(newCursor);
            newCursor.toPrevSibling();
            return (CTShape) newCursor.getObject();
        } catch (Throwable th) {
            throw th;
        } finally {
            newCursor.dispose();
        }
    }

    public CTShape findCommentShape(int i, int i2) {
        XmlCursor newCursor = this.root.getXml().newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                XmlObject object = newCursor.getObject();
                if (matchCommentShape(object, i, i2)) {
                    return (CTShape) object;
                }
            }
            newCursor.dispose();
            return null;
        } finally {
            newCursor.dispose();
        }
    }

    private boolean matchCommentShape(XmlObject xmlObject, int i, int i2) {
        if (!(xmlObject instanceof CTShape)) {
            return false;
        }
        CTShape cTShape = (CTShape) xmlObject;
        if (cTShape.sizeOfClientDataArray() == 0) {
            return false;
        }
        CTClientData clientDataArray = cTShape.getClientDataArray(0);
        if (clientDataArray.getObjectType() != STObjectType.NOTE) {
            return false;
        }
        int intValue = clientDataArray.getRowArray(0).intValue();
        int intValue2 = clientDataArray.getColumnArray(0).intValue();
        if (intValue == i && intValue2 == i2) {
            return true;
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public boolean removeCommentShape(int i, int i2) {
        XmlCursor newCursor = this.root.getXml().newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                if (matchCommentShape(newCursor.getObject(), i, i2)) {
                    newCursor.removeXml();
                    newCursor.dispose();
                    return true;
                }
            }
            newCursor.dispose();
            return false;
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }
}
