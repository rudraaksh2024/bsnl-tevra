package org.apache.poi.xssf.usermodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public final class XSSFDrawing extends POIXMLDocumentPart implements Drawing<XSSFShape> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFDrawing.class);
    protected static final String NAMESPACE_A = "http://schemas.openxmlformats.org/drawingml/2006/main";
    protected static final String NAMESPACE_C = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    private CTDrawing drawing;
    private long numOfGraphicFrames;

    protected XSSFDrawing() {
        this.drawing = newDrawing();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        if (r3 != null) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSSFDrawing(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>((org.apache.poi.openxml4j.opc.PackagePart) r3)
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            r1 = 0
            r0.setLoadReplaceDocumentElement(r1)
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing> r1 = org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing.Factory     // Catch:{ all -> 0x0022 }
            java.lang.Object r0 = r1.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0022 }
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing r0 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing) r0     // Catch:{ all -> 0x0022 }
            r2.drawing = r0     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0021
            r3.close()
        L_0x0021:
            return
        L_0x0022:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r0 = move-exception
            if (r3 == 0) goto L_0x002f
            r3.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFDrawing.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private static CTDrawing newDrawing() {
        return CTDrawing.Factory.newInstance();
    }

    @Internal
    public CTDrawing getCTDrawing() {
        return this.drawing;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        if (r1 != null) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "wsDr"
            java.lang.String r4 = "xdr"
            r1.<init>(r2, r3, r4)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing r5 = r5.drawing     // Catch:{ all -> 0x0030 }
            r5.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return
        L_0x0030:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            r5.addSuppressed(r1)
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFDrawing.commit():void");
    }

    public XSSFClientAnchor createAnchor(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new XSSFClientAnchor(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public XSSFTextBox createTextbox(XSSFClientAnchor xSSFClientAnchor) {
        long newShapeId = newShapeId();
        CTShape addNewSp = createTwoCellAnchor(xSSFClientAnchor).addNewSp();
        addNewSp.set(XSSFSimpleShape.prototype());
        addNewSp.getNvSpPr().getCNvPr().setId(newShapeId);
        XSSFTextBox xSSFTextBox = new XSSFTextBox(this, addNewSp);
        xSSFTextBox.anchor = xSSFClientAnchor;
        return xSSFTextBox;
    }

    public XSSFPicture createPicture(XSSFClientAnchor xSSFClientAnchor, int i) {
        PackageRelationship addPictureReference = addPictureReference(i);
        long newShapeId = newShapeId();
        CTPicture addNewPic = createTwoCellAnchor(xSSFClientAnchor).addNewPic();
        addNewPic.set(XSSFPicture.prototype());
        addNewPic.getNvPicPr().getCNvPr().setId(newShapeId);
        XSSFPicture xSSFPicture = new XSSFPicture(this, addNewPic);
        xSSFPicture.anchor = xSSFClientAnchor;
        xSSFPicture.setPictureReference(addPictureReference);
        addNewPic.getSpPr().setXfrm(createXfrm(xSSFClientAnchor));
        return xSSFPicture;
    }

    public XSSFPicture createPicture(ClientAnchor clientAnchor, int i) {
        return createPicture((XSSFClientAnchor) clientAnchor, i);
    }

    public XSSFChart createChart(XSSFClientAnchor xSSFClientAnchor) {
        POIXMLDocumentPart.RelationPart createChartRelationPart = createChartRelationPart();
        XSSFChart xSSFChart = (XSSFChart) createChartRelationPart.getDocumentPart();
        String id = createChartRelationPart.getRelationship().getId();
        XSSFGraphicFrame createGraphicFrame = createGraphicFrame(xSSFClientAnchor);
        createGraphicFrame.setChart(xSSFChart, id);
        createGraphicFrame.getCTGraphicalObjectFrame().setXfrm(createXfrm(xSSFClientAnchor));
        return xSSFChart;
    }

    /* access modifiers changed from: protected */
    public POIXMLDocumentPart.RelationPart createChartRelationPart() {
        XSSFWorkbook workbook = getSheet().getWorkbook();
        XSSFFactory instance = workbook == null ? XSSFFactory.getInstance() : workbook.getXssfFactory();
        OPCPackage oPCPackage = getPackagePart().getPackage();
        int size = oPCPackage.getPartsByContentType(XSSFRelation.CHART.getContentType()).size();
        do {
            size++;
            try {
            } catch (InvalidFormatException e) {
                throw new IllegalStateException("Failed for " + size, e);
            }
        } while (oPCPackage.getPart(PackagingURIHelper.createPartName(XSSFRelation.CHART.getFileName(size))) != null);
        return createRelationship(XSSFRelation.CHART, instance, size, false);
    }

    public XSSFChart createChart(ClientAnchor clientAnchor) {
        return createChart((XSSFClientAnchor) clientAnchor);
    }

    public XSSFChart importChart(XSSFChart xSSFChart) {
        CTTwoCellAnchor twoCellAnchorArray = ((XSSFDrawing) xSSFChart.getParent()).getCTDrawing().getTwoCellAnchorArray(0);
        XSSFClientAnchor xSSFClientAnchor = new XSSFClientAnchor((CTMarker) twoCellAnchorArray.getFrom().copy(), (CTMarker) twoCellAnchorArray.getTo().copy());
        xSSFClientAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        XSSFChart createChart = createChart(xSSFClientAnchor);
        createChart.getCTChartSpace().set(xSSFChart.getCTChartSpace().copy());
        return createChart;
    }

    /* access modifiers changed from: protected */
    public PackageRelationship addPictureReference(int i) {
        return addRelation((String) null, XSSFRelation.IMAGES, new XSSFPictureData(getSheet().getWorkbook().getAllPictures().get(i).getPackagePart())).getRelationship();
    }

    public XSSFSimpleShape createSimpleShape(XSSFClientAnchor xSSFClientAnchor) {
        long newShapeId = newShapeId();
        CTShape addNewSp = createTwoCellAnchor(xSSFClientAnchor).addNewSp();
        addNewSp.set(XSSFSimpleShape.prototype());
        addNewSp.getNvSpPr().getCNvPr().setId(newShapeId);
        addNewSp.getSpPr().setXfrm(createXfrm(xSSFClientAnchor));
        XSSFSimpleShape xSSFSimpleShape = new XSSFSimpleShape(this, addNewSp);
        xSSFSimpleShape.anchor = xSSFClientAnchor;
        return xSSFSimpleShape;
    }

    public XSSFConnector createConnector(XSSFClientAnchor xSSFClientAnchor) {
        CTConnector addNewCxnSp = createTwoCellAnchor(xSSFClientAnchor).addNewCxnSp();
        addNewCxnSp.set(XSSFConnector.prototype());
        XSSFConnector xSSFConnector = new XSSFConnector(this, addNewCxnSp);
        xSSFConnector.anchor = xSSFClientAnchor;
        return xSSFConnector;
    }

    public XSSFShapeGroup createGroup(XSSFClientAnchor xSSFClientAnchor) {
        CTGroupShape addNewGrpSp = createTwoCellAnchor(xSSFClientAnchor).addNewGrpSp();
        addNewGrpSp.set(XSSFShapeGroup.prototype());
        CTTransform2D createXfrm = createXfrm(xSSFClientAnchor);
        CTGroupTransform2D xfrm = addNewGrpSp.getGrpSpPr().getXfrm();
        xfrm.setOff(createXfrm.getOff());
        xfrm.setExt(createXfrm.getExt());
        xfrm.setChExt(createXfrm.getExt());
        XSSFShapeGroup xSSFShapeGroup = new XSSFShapeGroup(this, addNewGrpSp);
        xSSFShapeGroup.anchor = xSSFClientAnchor;
        return xSSFShapeGroup;
    }

    public XSSFComment createCellComment(ClientAnchor clientAnchor) {
        return getSheet().getCommentsTable(true).createNewComment(clientAnchor);
    }

    private XSSFGraphicFrame createGraphicFrame(XSSFClientAnchor xSSFClientAnchor) {
        CTGraphicalObjectFrame addNewGraphicFrame = createTwoCellAnchor(xSSFClientAnchor).addNewGraphicFrame();
        addNewGraphicFrame.set(XSSFGraphicFrame.prototype());
        addNewGraphicFrame.setXfrm(createXfrm(xSSFClientAnchor));
        long j = this.numOfGraphicFrames;
        this.numOfGraphicFrames = 1 + j;
        XSSFGraphicFrame xSSFGraphicFrame = new XSSFGraphicFrame(this, addNewGraphicFrame);
        xSSFGraphicFrame.setAnchor(xSSFClientAnchor);
        xSSFGraphicFrame.setId(j);
        xSSFGraphicFrame.setName("Diagramm" + j);
        return xSSFGraphicFrame;
    }

    /* JADX INFO: finally extract failed */
    public XSSFObjectData createObjectData(ClientAnchor clientAnchor, int i, int i2) {
        XSSFSheet sheet = getSheet();
        PackagePart packagePart = sheet.getPackagePart();
        XSSFSheet sheet2 = getSheet();
        long sheetIndex = ((((long) sheet2.getWorkbook().getSheetIndex((Sheet) sheet2)) + 1) * 1024) + newShapeId();
        XSSFRelation xSSFRelation = XSSFRelation.OLEEMBEDDINGS;
        try {
            PackageRelationship addRelationship = packagePart.addRelationship(PackagingURIHelper.createPartName(xSSFRelation.getFileName(i)), TargetMode.INTERNAL, xSSFRelation.getRelation());
            PackagePartName partName = sheet.getWorkbook().getAllPictures().get(i2).getPackagePart().getPartName();
            PackageRelationship addRelationship2 = packagePart.addRelationship(partName, TargetMode.INTERNAL, PackageRelationshipTypes.IMAGE_PART);
            PackageRelationship addRelationship3 = getPackagePart().addRelationship(partName, TargetMode.INTERNAL, PackageRelationshipTypes.IMAGE_PART);
            CTWorksheet cTWorksheet = sheet.getCTWorksheet();
            CTOleObject addNewOleObject = (cTWorksheet.isSetOleObjects() ? cTWorksheet.getOleObjects() : cTWorksheet.addNewOleObjects()).addNewOleObject();
            addNewOleObject.setProgId(ExtractorFactory.OOXML_PACKAGE);
            addNewOleObject.setShapeId(sheetIndex);
            addNewOleObject.setId(addRelationship.getId());
            XmlCursor newCursor = addNewOleObject.newCursor();
            try {
                newCursor.toEndToken();
                newCursor.beginElement("objectPr", XSSFRelation.NS_SPREADSHEETML);
                newCursor.insertAttributeWithValue("id", PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, addRelationship2.getId());
                newCursor.insertAttributeWithValue("defaultSize", "0");
                newCursor.beginElement("anchor", XSSFRelation.NS_SPREADSHEETML);
                newCursor.insertAttributeWithValue("moveWithCells", "1");
                CTTwoCellAnchor createTwoCellAnchor = createTwoCellAnchor((XSSFClientAnchor) clientAnchor);
                newCursor = createTwoCellAnchor.newCursor();
                newCursor.copyXmlContents(newCursor);
                newCursor.dispose();
                newCursor.toParent();
                newCursor.toFirstChild();
                newCursor.setName(new QName(XSSFRelation.NS_SPREADSHEETML, "from"));
                newCursor.toNextSibling();
                newCursor.setName(new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.TransitionType.S_TO));
                newCursor.dispose();
                CTShape addNewSp = createTwoCellAnchor.addNewSp();
                addNewSp.set(XSSFObjectData.prototype());
                XSSFClientAnchor xSSFClientAnchor = (XSSFClientAnchor) clientAnchor;
                addNewSp.getSpPr().setXfrm(createXfrm(xSSFClientAnchor));
                CTBlipFillProperties addNewBlipFill = addNewSp.getSpPr().addNewBlipFill();
                addNewBlipFill.addNewBlip().setEmbed(addRelationship3.getId());
                addNewBlipFill.addNewStretch().addNewFillRect();
                CTNonVisualDrawingProps cNvPr = addNewSp.getNvSpPr().getCNvPr();
                cNvPr.setId(sheetIndex);
                cNvPr.setName("Object " + sheetIndex);
                newCursor = cNvPr.getExtLst().getExtArray(0).newCursor();
                try {
                    newCursor.toFirstChild();
                    newCursor.setAttributeText(new QName("spid"), "_x0000_s" + sheetIndex);
                    newCursor.dispose();
                    XSSFObjectData xSSFObjectData = new XSSFObjectData(this, addNewSp);
                    xSSFObjectData.anchor = xSSFClientAnchor;
                    return xSSFObjectData;
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            } finally {
                newCursor.dispose();
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public List<XSSFChart> getCharts() {
        ArrayList arrayList = new ArrayList();
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XSSFChart) {
                arrayList.add((XSSFChart) next);
            }
        }
        return arrayList;
    }

    private CTTwoCellAnchor createTwoCellAnchor(XSSFClientAnchor xSSFClientAnchor) {
        STEditAs.Enum enumR;
        CTTwoCellAnchor addNewTwoCellAnchor = this.drawing.addNewTwoCellAnchor();
        addNewTwoCellAnchor.setFrom(xSSFClientAnchor.getFrom());
        addNewTwoCellAnchor.setTo(xSSFClientAnchor.getTo());
        addNewTwoCellAnchor.addNewClientData();
        xSSFClientAnchor.setTo(addNewTwoCellAnchor.getTo());
        xSSFClientAnchor.setFrom(addNewTwoCellAnchor.getFrom());
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$ClientAnchor$AnchorType[xSSFClientAnchor.getAnchorType().ordinal()];
        if (i == 1) {
            enumR = STEditAs.ABSOLUTE;
        } else if (i == 2) {
            enumR = STEditAs.TWO_CELL;
        } else if (i != 3) {
            enumR = STEditAs.ONE_CELL;
        } else {
            enumR = STEditAs.ONE_CELL;
        }
        addNewTwoCellAnchor.setEditAs(enumR);
        return addNewTwoCellAnchor;
    }

    /* renamed from: org.apache.poi.xssf.usermodel.XSSFDrawing$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$ClientAnchor$AnchorType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ss.usermodel.ClientAnchor$AnchorType[] r0 = org.apache.poi.ss.usermodel.ClientAnchor.AnchorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$ClientAnchor$AnchorType = r0
                org.apache.poi.ss.usermodel.ClientAnchor$AnchorType r1 = org.apache.poi.ss.usermodel.ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ClientAnchor$AnchorType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.ClientAnchor$AnchorType r1 = org.apache.poi.ss.usermodel.ClientAnchor.AnchorType.MOVE_AND_RESIZE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ClientAnchor$AnchorType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.ClientAnchor$AnchorType r1 = org.apache.poi.ss.usermodel.ClientAnchor.AnchorType.MOVE_DONT_RESIZE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFDrawing.AnonymousClass1.<clinit>():void");
        }
    }

    private CTTransform2D createXfrm(XSSFClientAnchor xSSFClientAnchor) {
        CTTransform2D newInstance = CTTransform2D.Factory.newInstance();
        CTPoint2D addNewOff = newInstance.addNewOff();
        addNewOff.setX(Integer.valueOf(xSSFClientAnchor.getDx1()));
        addNewOff.setY(Integer.valueOf(xSSFClientAnchor.getDy1()));
        XSSFSheet sheet = getSheet();
        double d = 0.0d;
        double d2 = 0.0d;
        for (int col1 = xSSFClientAnchor.getCol1(); col1 < xSSFClientAnchor.getCol2(); col1++) {
            d2 += (double) sheet.getColumnWidthInPixels(col1);
        }
        for (int row1 = xSSFClientAnchor.getRow1(); row1 < xSSFClientAnchor.getRow2(); row1++) {
            d += ImageUtils.getRowHeightInPixels(sheet, row1);
        }
        long pixelToEMU = (long) Units.pixelToEMU((int) d2);
        long pixelToEMU2 = (long) Units.pixelToEMU((int) d);
        CTPositiveSize2D addNewExt = newInstance.addNewExt();
        addNewExt.setCx((pixelToEMU - ((long) xSSFClientAnchor.getDx1())) + ((long) xSSFClientAnchor.getDx2()));
        addNewExt.setCy((pixelToEMU2 - ((long) xSSFClientAnchor.getDy1())) + ((long) xSSFClientAnchor.getDy2()));
        return newInstance;
    }

    private long newShapeId() {
        return ((long) this.drawing.sizeOfAbsoluteAnchorArray()) + 1 + ((long) this.drawing.sizeOfOneCellAnchorArray()) + ((long) this.drawing.sizeOfTwoCellAnchorArray());
    }

    public List<XSSFShape> getShapes() {
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = this.drawing.newCursor();
        try {
            if (newCursor.toFirstChild()) {
                addShapes(newCursor, arrayList);
            }
            return arrayList;
        } finally {
            newCursor.dispose();
        }
    }

    public List<XSSFShape> getShapes(XSSFShapeGroup xSSFShapeGroup) {
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = xSSFShapeGroup.getCTGroupShape().newCursor();
        try {
            addShapes(newCursor, arrayList);
            return arrayList;
        } finally {
            newCursor.dispose();
        }
    }

    private void addShapes(XmlCursor xmlCursor, List<XSSFShape> list) {
        XSSFShape xSSFShape;
        do {
            try {
                xmlCursor.push();
                if (xmlCursor.toFirstChild()) {
                    do {
                        XmlObject object = xmlCursor.getObject();
                        if (!(object instanceof CTMarker)) {
                            if (object instanceof CTPicture) {
                                xSSFShape = new XSSFPicture(this, (CTPicture) object);
                            } else if (object instanceof CTConnector) {
                                xSSFShape = new XSSFConnector(this, (CTConnector) object);
                            } else if (object instanceof CTShape) {
                                xSSFShape = hasOleLink(object) ? new XSSFObjectData(this, (CTShape) object) : new XSSFSimpleShape(this, (CTShape) object);
                            } else if (object instanceof CTGraphicalObjectFrame) {
                                xSSFShape = new XSSFGraphicFrame(this, (CTGraphicalObjectFrame) object);
                            } else if (object instanceof CTGroupShape) {
                                xSSFShape = new XSSFShapeGroup(this, (CTGroupShape) object);
                            } else if (object instanceof XmlAnyTypeImpl) {
                                LOG.atWarn().log("trying to parse AlternateContent, this unlinks the returned Shapes from the underlying xml content, so those shapes can't be used to modify the drawing, i.e. modifications will be ignored!");
                                xmlCursor.push();
                                xmlCursor.toFirstChild();
                                xmlCursor = null;
                                try {
                                    XmlCursor newCursor = CTDrawing.Factory.parse(xmlCursor.newXMLStreamReader()).newCursor();
                                    if (newCursor.toFirstChild()) {
                                        addShapes(newCursor, list);
                                    }
                                    if (newCursor != null) {
                                        newCursor.dispose();
                                    }
                                } catch (XmlException e) {
                                    LOG.atWarn().withThrowable(e).log("unable to parse CTDrawing in alternate content.");
                                    if (xmlCursor != null) {
                                        xmlCursor.dispose();
                                    }
                                }
                                xmlCursor.pop();
                            }
                            xSSFShape.anchor = getAnchorFromParent(object);
                            list.add(xSSFShape);
                        }
                    } while (xmlCursor.toNextSibling());
                }
                xmlCursor.pop();
            } catch (Throwable th) {
                if (xmlCursor != null) {
                }
                xmlCursor.pop();
                throw th;
            } finally {
                xmlCursor.dispose();
            }
        } while (xmlCursor.toNextSibling());
    }

    /* JADX INFO: finally extract failed */
    private boolean hasOleLink(XmlObject xmlObject) {
        QName qName = new QName((String) null, "uri");
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            newCursor.selectPath("declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:extLst/a:ext");
            while (newCursor.toNextSelection()) {
                if ("{63B3BB69-23CF-44E3-9099-C40C66FF867C}".equals(newCursor.getAttributeText(qName))) {
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

    private XSSFAnchor getAnchorFromParent(XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            XmlObject object = newCursor.toParent() ? newCursor.getObject() : null;
            if (object == null) {
                return null;
            }
            if (object instanceof CTTwoCellAnchor) {
                CTTwoCellAnchor cTTwoCellAnchor = (CTTwoCellAnchor) object;
                return new XSSFClientAnchor(cTTwoCellAnchor.getFrom(), cTTwoCellAnchor.getTo());
            } else if (object instanceof CTOneCellAnchor) {
                CTOneCellAnchor cTOneCellAnchor = (CTOneCellAnchor) object;
                return new XSSFClientAnchor(getSheet(), cTOneCellAnchor.getFrom(), cTOneCellAnchor.getExt());
            } else if (!(object instanceof CTAbsoluteAnchor)) {
                return null;
            } else {
                CTAbsoluteAnchor cTAbsoluteAnchor = (CTAbsoluteAnchor) object;
                return new XSSFClientAnchor(getSheet(), cTAbsoluteAnchor.getPos(), cTAbsoluteAnchor.getExt());
            }
        } finally {
            newCursor.dispose();
        }
    }

    public Iterator<XSSFShape> iterator() {
        return getShapes().iterator();
    }

    public Spliterator<XSSFShape> spliterator() {
        return getShapes().spliterator();
    }

    public XSSFSheet getSheet() {
        return (XSSFSheet) getParent();
    }
}
