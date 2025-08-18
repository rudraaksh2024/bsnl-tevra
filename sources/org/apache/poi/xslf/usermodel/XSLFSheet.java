package org.apache.poi.xslf.usermodel;

import com.zaxxer.sparsebits.SparseBitSet;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public abstract class XSLFSheet extends POIXMLDocumentPart implements XSLFShapeContainer, Sheet<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFSheet.class);
    private XSLFDrawing _drawing;
    private Map<Integer, XSLFSimpleShape> _placeholderByIdMap;
    private Map<Integer, XSLFSimpleShape> _placeholderByTypeMap;
    private List<XSLFTextShape> _placeholders;
    private List<XSLFShape> _shapes;
    private CTGroupShape _spTree;
    private XSLFTheme _theme;
    private final SparseBitSet shapeIds = new SparseBitSet();

    public XSLFBackground getBackground() {
        return null;
    }

    public boolean getFollowMasterGraphics() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract String getRootElementName();

    public abstract XmlObject getXmlObject();

    /* access modifiers changed from: package-private */
    public boolean isSupportTheme() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        return null;
    }

    protected XSLFSheet() {
    }

    protected XSLFSheet(PackagePart packagePart) {
        super(packagePart);
    }

    public XMLSlideShow getSlideShow() {
        for (POIXMLDocumentPart parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof XMLSlideShow) {
                return (XMLSlideShow) parent;
            }
        }
        throw new IllegalStateException("SlideShow was not found");
    }

    /* access modifiers changed from: protected */
    public int allocateShapeId() {
        int nextClearBit = this.shapeIds.nextClearBit(1);
        this.shapeIds.set(nextClearBit);
        return nextClearBit;
    }

    /* access modifiers changed from: protected */
    public void registerShapeId(int i) {
        if (this.shapeIds.get(i)) {
            LOG.atWarn().log("shape id {} has been already used.", (Object) Unbox.box(i));
        }
        this.shapeIds.set(i);
    }

    /* access modifiers changed from: protected */
    public void deregisterShapeId(int i) {
        if (!this.shapeIds.get(i)) {
            LOG.atWarn().log("shape id {} hasn't been registered.", (Object) Unbox.box(i));
        }
        this.shapeIds.clear(i);
    }

    protected static List<XSLFShape> buildShapes(CTGroupShape cTGroupShape, XSLFShapeContainer xSLFShapeContainer) {
        XSLFSheet sheet = xSLFShapeContainer instanceof XSLFSheet ? (XSLFSheet) xSLFShapeContainer : ((XSLFShape) xSLFShapeContainer).getSheet();
        ArrayList<XSLFShape> arrayList = new ArrayList<>();
        XmlCursor newCursor = cTGroupShape.newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTShape) {
                    arrayList.add(XSLFAutoShape.create((CTShape) object, sheet));
                } else if (object instanceof CTGroupShape) {
                    arrayList.add(new XSLFGroupShape((CTGroupShape) object, sheet));
                } else if (object instanceof CTConnector) {
                    arrayList.add(new XSLFConnectorShape((CTConnector) object, sheet));
                } else if (object instanceof CTPicture) {
                    arrayList.add(new XSLFPictureShape((CTPicture) object, sheet));
                } else if (object instanceof CTGraphicalObjectFrame) {
                    arrayList.add(XSLFGraphicFrame.create((CTGraphicalObjectFrame) object, sheet));
                } else if (object instanceof XmlAnyTypeImpl) {
                    newCursor.push();
                    if (newCursor.toChild(PackageNamespaces.MARKUP_COMPATIBILITY, "Choice") && newCursor.toFirstChild()) {
                        arrayList.addAll(buildShapes(CTGroupShape.Factory.parse(newCursor.newXMLStreamReader()), xSLFShapeContainer));
                    }
                    newCursor.pop();
                }
            }
            newCursor.dispose();
            for (XSLFShape parent : arrayList) {
                parent.setParent(xSLFShapeContainer);
            }
            return arrayList;
        } catch (XmlException e) {
            LOG.atDebug().withThrowable(e).log("unparsable alternate content");
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    private XSLFDrawing getDrawing() {
        initDrawingAndShapes();
        return this._drawing;
    }

    public List<XSLFShape> getShapes() {
        initDrawingAndShapes();
        return this._shapes;
    }

    private void initDrawingAndShapes() {
        CTGroupShape spTree = getSpTree();
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(this, spTree);
        }
        if (this._shapes == null) {
            this._shapes = buildShapes(spTree, this);
        }
    }

    public XSLFAutoShape createAutoShape() {
        XSLFAutoShape createAutoShape = getDrawing().createAutoShape();
        getShapes().add(createAutoShape);
        createAutoShape.setParent(this);
        return createAutoShape;
    }

    public XSLFFreeformShape createFreeform() {
        XSLFFreeformShape createFreeform = getDrawing().createFreeform();
        getShapes().add(createFreeform);
        createFreeform.setParent(this);
        return createFreeform;
    }

    public XSLFTextBox createTextBox() {
        XSLFTextBox createTextBox = getDrawing().createTextBox();
        getShapes().add(createTextBox);
        createTextBox.setParent(this);
        return createTextBox;
    }

    public XSLFConnectorShape createConnector() {
        XSLFConnectorShape createConnector = getDrawing().createConnector();
        getShapes().add(createConnector);
        createConnector.setParent(this);
        return createConnector;
    }

    public XSLFGroupShape createGroup() {
        XSLFGroupShape createGroup = getDrawing().createGroup();
        getShapes().add(createGroup);
        createGroup.setParent(this);
        return createGroup;
    }

    public XSLFPictureShape createPicture(PictureData pictureData) {
        if (pictureData instanceof XSLFPictureData) {
            XSLFPictureShape createPicture = getDrawing().createPicture(addRelation((String) null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData).getRelationship().getId());
            new DrawPictureShape(createPicture).resize();
            getShapes().add(createPicture);
            createPicture.setParent(this);
            return createPicture;
        }
        throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
    }

    public XSLFTable createTable() {
        XSLFTable createTable = getDrawing().createTable();
        getShapes().add(createTable);
        createTable.setParent(this);
        return createTable;
    }

    public XSLFTable createTable(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable createTable = getDrawing().createTable();
        getShapes().add(createTable);
        createTable.setParent(this);
        for (int i3 = 0; i3 < i; i3++) {
            XSLFTableRow addRow = createTable.addRow();
            for (int i4 = 0; i4 < i2; i4++) {
                addRow.addCell();
            }
        }
        return createTable;
    }

    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (pictureData instanceof XSLFPictureData) {
            XSLFObjectShape createOleShape = getDrawing().createOleShape(addRelation((String) null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData).getRelationship().getId());
            CTOleObject cTOleObject = createOleShape.getCTOleObject();
            Dimension imageDimension = pictureData.getImageDimension();
            cTOleObject.setImgW(Units.toEMU(imageDimension.getWidth()));
            cTOleObject.setImgH(Units.toEMU(imageDimension.getHeight()));
            getShapes().add(createOleShape);
            createOleShape.setParent(this);
            return createOleShape;
        }
        throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
    }

    public Iterator<XSLFShape> iterator() {
        return getShapes().iterator();
    }

    public void addShape(XSLFShape xSLFShape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch witht XSLFSheet.create* methods");
    }

    public boolean removeShape(XSLFShape xSLFShape) {
        XmlObject xmlObject = xSLFShape.getXmlObject();
        CTGroupShape spTree = getSpTree();
        deregisterShapeId(xSLFShape.getShapeId());
        if (xmlObject instanceof CTShape) {
            spTree.getSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTGroupShape) {
            XSLFGroupShape xSLFGroupShape = (XSLFGroupShape) xSLFShape;
            ArrayList arrayList = new ArrayList(xSLFGroupShape.getShapes());
            xSLFGroupShape.getClass();
            arrayList.forEach(new XSLFGroupShape$$ExternalSyntheticLambda0(xSLFGroupShape));
            spTree.getGrpSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTConnector) {
            spTree.getCxnSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTGraphicalObjectFrame) {
            spTree.getGraphicFrameList().remove(xmlObject);
        } else if (xmlObject instanceof CTPicture) {
            removePictureRelation((XSLFPictureShape) xSLFShape);
            spTree.getPicList().remove(xmlObject);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xSLFShape);
        }
        return getShapes().remove(xSLFShape);
    }

    public void clear() {
        for (XSLFShape removeShape : new ArrayList(getShapes())) {
            removeShape(removeShape);
        }
    }

    /* access modifiers changed from: protected */
    public CTGroupShape getSpTree() {
        if (this._spTree == null) {
            XmlObject[] selectPath = getXmlObject().selectPath("declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main' .//*/p:spTree");
            if (selectPath.length != 0) {
                this._spTree = (CTGroupShape) selectPath[0];
            } else {
                throw new IllegalStateException("CTGroupShape was not found");
            }
        }
        return this._spTree;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r1 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            java.lang.String r1 = r4.getRootElementName()
            if (r1 == 0) goto L_0x0017
            javax.xml.namespace.QName r2 = new javax.xml.namespace.QName
            java.lang.String r3 = "http://schemas.openxmlformats.org/presentationml/2006/main"
            r2.<init>(r3, r1)
            r0.setSaveSyntheticDocumentElement(r2)
        L_0x0017:
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.apache.xmlbeans.XmlObject r4 = r4.getXmlObject()     // Catch:{ all -> 0x002c }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x002b
            r1.close()
        L_0x002b:
            return
        L_0x002c:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x002e }
        L_0x002e:
            r0 = move-exception
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x0039:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSheet.commit():void");
    }

    public XSLFSheet importContent(XSLFSheet xSLFSheet) {
        this._spTree = null;
        getSpTree().set(xSLFSheet.getSpTree().copy());
        wipeAndReinitialize(xSLFSheet, 0);
        return this;
    }

    private void wipeAndReinitialize(XSLFSheet xSLFSheet, int i) {
        this._shapes = null;
        this._drawing = null;
        initDrawingAndShapes();
        this._placeholders = null;
        List<XSLFShape> shapes = getShapes();
        List<XSLFShape> shapes2 = xSLFSheet.getShapes();
        for (int i2 = 0; i2 < shapes2.size(); i2++) {
            shapes.get(i + i2).copy(shapes2.get(i2));
        }
    }

    public XSLFSheet appendContent(XSLFSheet xSLFSheet) {
        int size = getShapes().size();
        CTGroupShape spTree = getSpTree();
        for (XmlObject xmlObject : xSLFSheet.getSpTree().selectPath("*")) {
            if (xmlObject instanceof CTShape) {
                spTree.addNewSp().set(xmlObject.copy());
            } else if (xmlObject instanceof CTGroupShape) {
                spTree.addNewGrpSp().set(xmlObject.copy());
            } else if (xmlObject instanceof CTConnector) {
                spTree.addNewCxnSp().set(xmlObject.copy());
            } else if (xmlObject instanceof CTPicture) {
                spTree.addNewPic().set(xmlObject.copy());
            } else if (xmlObject instanceof CTGraphicalObjectFrame) {
                spTree.addNewGraphicFrame().set(xmlObject.copy());
            }
        }
        wipeAndReinitialize(xSLFSheet, size);
        return this;
    }

    public XSLFTheme getTheme() {
        if (this._theme != null || !isSupportTheme()) {
            return this._theme;
        }
        getRelations().stream().filter(new XSLFSheet$$ExternalSyntheticLambda0()).findAny().ifPresent(new XSLFSheet$$ExternalSyntheticLambda1(this));
        return this._theme;
    }

    static /* synthetic */ boolean lambda$getTheme$0(POIXMLDocumentPart pOIXMLDocumentPart) {
        return pOIXMLDocumentPart instanceof XSLFTheme;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getTheme$1$org-apache-poi-xslf-usermodel-XSLFSheet  reason: not valid java name */
    public /* synthetic */ void m2307lambda$getTheme$1$orgapachepoixslfusermodelXSLFSheet(POIXMLDocumentPart pOIXMLDocumentPart) {
        this._theme = (XSLFTheme) pOIXMLDocumentPart;
    }

    /* access modifiers changed from: protected */
    public XSLFTextShape getTextShapeByType(Placeholder placeholder) {
        for (XSLFShape next : getShapes()) {
            if (next instanceof XSLFTextShape) {
                XSLFTextShape xSLFTextShape = (XSLFTextShape) next;
                if (xSLFTextShape.getTextType() == placeholder) {
                    return xSLFTextShape;
                }
            }
        }
        return null;
    }

    public XSLFSimpleShape getPlaceholder(Placeholder placeholder) {
        return getPlaceholderByType(placeholder.ooxmlId);
    }

    @Internal
    public XSLFSimpleShape getPlaceholder(CTPlaceholder cTPlaceholder) {
        XSLFSimpleShape placeholderById = cTPlaceholder.isSetIdx() ? getPlaceholderById((int) cTPlaceholder.getIdx()) : null;
        return (placeholderById != null || !cTPlaceholder.isSetType()) ? placeholderById : getPlaceholderByType(cTPlaceholder.getType().intValue());
    }

    private void initPlaceholders() {
        if (this._placeholders == null) {
            this._placeholders = new ArrayList();
            this._placeholderByIdMap = new HashMap();
            this._placeholderByTypeMap = new HashMap();
            for (XSLFShape next : getShapes()) {
                if (next instanceof XSLFTextShape) {
                    XSLFTextShape xSLFTextShape = (XSLFTextShape) next;
                    CTPlaceholder cTPlaceholder = xSLFTextShape.getPlaceholderDetails().getCTPlaceholder(false);
                    if (cTPlaceholder != null) {
                        this._placeholders.add(xSLFTextShape);
                        if (cTPlaceholder.isSetIdx()) {
                            this._placeholderByIdMap.put(Integer.valueOf((int) cTPlaceholder.getIdx()), xSLFTextShape);
                        }
                        if (cTPlaceholder.isSetType()) {
                            this._placeholderByTypeMap.put(Integer.valueOf(cTPlaceholder.getType().intValue()), xSLFTextShape);
                        }
                    }
                }
            }
        }
    }

    private XSLFSimpleShape getPlaceholderById(int i) {
        initPlaceholders();
        return this._placeholderByIdMap.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    public XSLFSimpleShape getPlaceholderByType(int i) {
        initPlaceholders();
        return this._placeholderByTypeMap.get(Integer.valueOf(i));
    }

    public XSLFTextShape getPlaceholder(int i) {
        initPlaceholders();
        return this._placeholders.get(i);
    }

    public XSLFTextShape[] getPlaceholders() {
        initPlaceholders();
        return (XSLFTextShape[]) this._placeholders.toArray(new XSLFTextShape[0]);
    }

    public void draw(Graphics2D graphics2D) {
        DrawFactory.getInstance(graphics2D).getDrawable((Sheet<?, ?>) this).draw(graphics2D);
    }

    /* access modifiers changed from: package-private */
    public String importBlip(String str, POIXMLDocumentPart pOIXMLDocumentPart) {
        return getSlideShow().importBlip(str, pOIXMLDocumentPart, this);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r4 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0043, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004b, code lost:
        if (r3 != null) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0055, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void importPart(org.apache.poi.openxml4j.opc.PackageRelationship r4, org.apache.poi.openxml4j.opc.PackagePart r5) {
        /*
            r3 = this;
            org.apache.poi.openxml4j.opc.PackagePart r3 = r3.getPackagePart()
            org.apache.poi.openxml4j.opc.PackagePartName r0 = r5.getPartName()
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r3.getPackage()
            boolean r2 = r1.containPart(r0)
            if (r2 == 0) goto L_0x0013
            return
        L_0x0013:
            org.apache.poi.openxml4j.opc.TargetMode r2 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL
            java.lang.String r4 = r4.getRelationshipType()
            r3.addRelationship((org.apache.poi.openxml4j.opc.PackagePartName) r0, (org.apache.poi.openxml4j.opc.TargetMode) r2, (java.lang.String) r4)
            java.lang.String r3 = r5.getContentType()
            org.apache.poi.openxml4j.opc.PackagePart r3 = r1.createPart(r0, r3)
            java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ IOException -> 0x0056 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ all -> 0x0048 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r4, (java.io.OutputStream) r3)     // Catch:{ all -> 0x003a }
            if (r4 == 0) goto L_0x0034
            r4.close()     // Catch:{ all -> 0x0048 }
        L_0x0034:
            if (r3 == 0) goto L_0x0039
            r3.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0039:
            return
        L_0x003a:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003c }
        L_0x003c:
            r0 = move-exception
            if (r4 == 0) goto L_0x0047
            r4.close()     // Catch:{ all -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ all -> 0x0048 }
        L_0x0047:
            throw r0     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004a }
        L_0x004a:
            r5 = move-exception
            if (r3 == 0) goto L_0x0055
            r3.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ IOException -> 0x0056 }
        L_0x0055:
            throw r5     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r4 = new org.apache.poi.ooxml.POIXMLException
            r4.<init>((java.lang.Throwable) r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSheet.importPart(org.apache.poi.openxml4j.opc.PackageRelationship, org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    /* access modifiers changed from: package-private */
    public void removePictureRelation(XSLFPictureShape xSLFPictureShape) {
        String blipId;
        String blipId2 = xSLFPictureShape.getBlipId();
        int i = 0;
        for (XSLFShape next : xSLFPictureShape.getSheet().getShapes()) {
            if ((next instanceof XSLFPictureShape) && (blipId = ((XSLFPictureShape) next).getBlipId()) != null && blipId.equals(blipId2)) {
                i++;
            }
        }
        if (i <= 1) {
            removeRelation(xSLFPictureShape.getBlipId());
        }
    }

    public XSLFPlaceholderDetails getPlaceholderDetails(Placeholder placeholder) {
        XSLFSimpleShape placeholder2 = getPlaceholder(placeholder);
        if (placeholder2 == null) {
            return null;
        }
        return new XSLFPlaceholderDetails(placeholder2);
    }

    public void addChart(XSLFChart xSLFChart) {
        addChart(xSLFChart, new Rectangle(10, 10, 500000, 500000));
    }

    public void addChart(XSLFChart xSLFChart, Rectangle2D rectangle2D) {
        getDrawing().addChart(addRelation((String) null, XSLFRelation.CHART, xSLFChart).getRelationship().getId(), rectangle2D);
    }

    /* access modifiers changed from: protected */
    public String mapSchemeColor(CTColorMappingOverride cTColorMappingOverride, String str) {
        String str2 = null;
        String mapSchemeColor = mapSchemeColor(cTColorMappingOverride == null ? null : cTColorMappingOverride.getOverrideClrMapping(), str);
        if (mapSchemeColor != null) {
            return mapSchemeColor;
        }
        XSLFSheet xSLFSheet = (XSLFSheet) getMasterSheet();
        if (xSLFSheet != null) {
            str2 = xSLFSheet.mapSchemeColor(str);
        }
        return str2 == null ? str : str2;
    }

    /* access modifiers changed from: protected */
    public String mapSchemeColor(CTColorMapping cTColorMapping, String str) {
        STColorSchemeIndex.Enum enumR;
        if (!(cTColorMapping == null || str == null)) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1177623385:
                    if (str.equals("accent1")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1177623384:
                    if (str.equals("accent2")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1177623383:
                    if (str.equals("accent3")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1177623382:
                    if (str.equals("accent4")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1177623381:
                    if (str.equals("accent5")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1177623380:
                    if (str.equals("accent6")) {
                        c = 5;
                        break;
                    }
                    break;
                case 97420:
                    if (str.equals("bg1")) {
                        c = 6;
                        break;
                    }
                    break;
                case 97421:
                    if (str.equals("bg2")) {
                        c = 7;
                        break;
                    }
                    break;
                case 115245:
                    if (str.equals("tx1")) {
                        c = 8;
                        break;
                    }
                    break;
                case 115246:
                    if (str.equals("tx2")) {
                        c = 9;
                        break;
                    }
                    break;
                case 99368034:
                    if (str.equals("hlink")) {
                        c = 10;
                        break;
                    }
                    break;
                case 268452191:
                    if (str.equals("folHlink")) {
                        c = 11;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    enumR = cTColorMapping.getAccent1();
                    break;
                case 1:
                    enumR = cTColorMapping.getAccent2();
                    break;
                case 2:
                    enumR = cTColorMapping.getAccent3();
                    break;
                case 3:
                    enumR = cTColorMapping.getAccent4();
                    break;
                case 4:
                    enumR = cTColorMapping.getAccent5();
                    break;
                case 5:
                    enumR = cTColorMapping.getAccent6();
                    break;
                case 6:
                    enumR = cTColorMapping.getBg1();
                    break;
                case 7:
                    enumR = cTColorMapping.getBg2();
                    break;
                case 8:
                    enumR = cTColorMapping.getTx1();
                    break;
                case 9:
                    enumR = cTColorMapping.getTx2();
                    break;
                case 10:
                    enumR = cTColorMapping.getHlink();
                    break;
                case 11:
                    enumR = cTColorMapping.getFolHlink();
                    break;
            }
        }
        enumR = null;
        if (enumR == null) {
            return null;
        }
        return enumR.toString();
    }
}
