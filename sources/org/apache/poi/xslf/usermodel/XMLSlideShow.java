package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.extractor.POIXMLPropertiesTextExtractor;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;

public class XMLSlideShow extends POIXMLDocument implements SlideShow<XSLFShape, XSLFTextParagraph> {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) XMLSlideShow.class);
    private static int MAX_RECORD_LENGTH = 1000000;
    private final List<XSLFChart> _charts;
    private XSLFCommentAuthors _commentAuthors;
    private final List<XSLFSlideMaster> _masters;
    private XSLFNotesMaster _notesMaster;
    private final List<XSLFPictureData> _pictures;
    private CTPresentation _presentation;
    private final List<XSLFSlide> _slides;
    private XSLFTableStyles _tableStyles;

    public Object getPersistDocument() {
        return this;
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public XMLSlideShow() {
        this(empty());
    }

    public XMLSlideShow(OPCPackage oPCPackage) {
        super(oPCPackage);
        this._slides = new ArrayList();
        this._masters = new ArrayList();
        this._pictures = new ArrayList();
        this._charts = new ArrayList();
        try {
            if (getCorePart().getContentType().equals(XSLFRelation.THEME_MANAGER.getContentType())) {
                rebase(getPackage());
            }
            load(XSLFFactory.getInstance());
        } catch (Exception e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public XMLSlideShow(InputStream inputStream) throws IOException {
        this(PackageHelper.open(inputStream));
    }

    static OPCPackage empty() {
        InputStream resourceAsStream = XMLSlideShow.class.getResourceAsStream("empty.pptx");
        if (resourceAsStream != null) {
            try {
                OPCPackage open = OPCPackage.open(resourceAsStream);
                IOUtils.closeQuietly(resourceAsStream);
                return open;
            } catch (Exception e) {
                throw new POIXMLException((Throwable) e);
            } catch (Throwable th) {
                IOUtils.closeQuietly(resourceAsStream);
                throw th;
            }
        } else {
            throw new POIXMLException("Missing resource 'empty.pptx'");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f8, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f9, code lost:
        if (r0 != null) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0103, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r7 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r7.getCorePart()     // Catch:{ XmlException -> 0x0104 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x0104 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument> r1 = org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument.Factory     // Catch:{ all -> 0x00f6 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00f6 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x00f6 }
            org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument r1 = (org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument) r1     // Catch:{ all -> 0x00f6 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r1 = r1.getPresentation()     // Catch:{ all -> 0x00f6 }
            r7._presentation = r1     // Catch:{ all -> 0x00f6 }
            if (r0 == 0) goto L_0x001d
            r0.close()     // Catch:{ XmlException -> 0x0104 }
        L_0x001d:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ XmlException -> 0x0104 }
            r0.<init>()     // Catch:{ XmlException -> 0x0104 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ XmlException -> 0x0104 }
            r1.<init>()     // Catch:{ XmlException -> 0x0104 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ XmlException -> 0x0104 }
            r2.<init>()     // Catch:{ XmlException -> 0x0104 }
            java.util.List r3 = r7.getRelationParts()     // Catch:{ XmlException -> 0x0104 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ XmlException -> 0x0104 }
        L_0x0034:
            boolean r4 = r3.hasNext()     // Catch:{ XmlException -> 0x0104 }
            if (r4 == 0) goto L_0x00a9
            java.lang.Object r4 = r3.next()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r4 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r4     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r4.getDocumentPart()     // Catch:{ XmlException -> 0x0104 }
            boolean r6 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFSlide     // Catch:{ XmlException -> 0x0104 }
            if (r6 == 0) goto L_0x0080
            org.apache.poi.openxml4j.opc.PackageRelationship r4 = r4.getRelationship()     // Catch:{ XmlException -> 0x0104 }
            java.lang.String r4 = r4.getId()     // Catch:{ XmlException -> 0x0104 }
            r6 = r5
            org.apache.poi.xslf.usermodel.XSLFSlide r6 = (org.apache.poi.xslf.usermodel.XSLFSlide) r6     // Catch:{ XmlException -> 0x0104 }
            r1.put(r4, r6)     // Catch:{ XmlException -> 0x0104 }
            java.util.List r4 = r5.getRelations()     // Catch:{ XmlException -> 0x0104 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ XmlException -> 0x0104 }
        L_0x005e:
            boolean r5 = r4.hasNext()     // Catch:{ XmlException -> 0x0104 }
            if (r5 == 0) goto L_0x0034
            java.lang.Object r5 = r4.next()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = (org.apache.poi.ooxml.POIXMLDocumentPart) r5     // Catch:{ XmlException -> 0x0104 }
            boolean r6 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFChart     // Catch:{ XmlException -> 0x0104 }
            if (r6 == 0) goto L_0x005e
            org.apache.poi.openxml4j.opc.PackagePart r6 = r5.getPackagePart()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.openxml4j.opc.PackagePartName r6 = r6.getPartName()     // Catch:{ XmlException -> 0x0104 }
            java.lang.String r6 = r6.getName()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.xslf.usermodel.XSLFChart r5 = (org.apache.poi.xslf.usermodel.XSLFChart) r5     // Catch:{ XmlException -> 0x0104 }
            r2.put(r6, r5)     // Catch:{ XmlException -> 0x0104 }
            goto L_0x005e
        L_0x0080:
            boolean r4 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFSlideMaster     // Catch:{ XmlException -> 0x0104 }
            if (r4 == 0) goto L_0x008e
            java.lang.String r4 = r7.getRelationId(r5)     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.xslf.usermodel.XSLFSlideMaster r5 = (org.apache.poi.xslf.usermodel.XSLFSlideMaster) r5     // Catch:{ XmlException -> 0x0104 }
            r0.put(r4, r5)     // Catch:{ XmlException -> 0x0104 }
            goto L_0x0034
        L_0x008e:
            boolean r4 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFTableStyles     // Catch:{ XmlException -> 0x0104 }
            if (r4 == 0) goto L_0x0097
            org.apache.poi.xslf.usermodel.XSLFTableStyles r5 = (org.apache.poi.xslf.usermodel.XSLFTableStyles) r5     // Catch:{ XmlException -> 0x0104 }
            r7._tableStyles = r5     // Catch:{ XmlException -> 0x0104 }
            goto L_0x0034
        L_0x0097:
            boolean r4 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFNotesMaster     // Catch:{ XmlException -> 0x0104 }
            if (r4 == 0) goto L_0x00a0
            org.apache.poi.xslf.usermodel.XSLFNotesMaster r5 = (org.apache.poi.xslf.usermodel.XSLFNotesMaster) r5     // Catch:{ XmlException -> 0x0104 }
            r7._notesMaster = r5     // Catch:{ XmlException -> 0x0104 }
            goto L_0x0034
        L_0x00a0:
            boolean r4 = r5 instanceof org.apache.poi.xslf.usermodel.XSLFCommentAuthors     // Catch:{ XmlException -> 0x0104 }
            if (r4 == 0) goto L_0x0034
            org.apache.poi.xslf.usermodel.XSLFCommentAuthors r5 = (org.apache.poi.xslf.usermodel.XSLFCommentAuthors) r5     // Catch:{ XmlException -> 0x0104 }
            r7._commentAuthors = r5     // Catch:{ XmlException -> 0x0104 }
            goto L_0x0034
        L_0x00a9:
            java.util.List<org.apache.poi.xslf.usermodel.XSLFChart> r3 = r7._charts     // Catch:{ XmlException -> 0x0104 }
            r3.clear()     // Catch:{ XmlException -> 0x0104 }
            java.util.List<org.apache.poi.xslf.usermodel.XSLFChart> r3 = r7._charts     // Catch:{ XmlException -> 0x0104 }
            java.util.Collection r2 = r2.values()     // Catch:{ XmlException -> 0x0104 }
            r3.addAll(r2)     // Catch:{ XmlException -> 0x0104 }
            java.util.List<org.apache.poi.xslf.usermodel.XSLFSlideMaster> r2 = r7._masters     // Catch:{ XmlException -> 0x0104 }
            r2.clear()     // Catch:{ XmlException -> 0x0104 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r2 = r7._presentation     // Catch:{ XmlException -> 0x0104 }
            boolean r2 = r2.isSetSldMasterIdLst()     // Catch:{ XmlException -> 0x0104 }
            if (r2 == 0) goto L_0x00d6
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r2 = r7._presentation     // Catch:{ XmlException -> 0x0104 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList r2 = r2.getSldMasterIdLst()     // Catch:{ XmlException -> 0x0104 }
            java.util.List r2 = r2.getSldMasterIdList()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda0 r3 = new org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda0     // Catch:{ XmlException -> 0x0104 }
            r3.<init>(r7, r0)     // Catch:{ XmlException -> 0x0104 }
            r2.forEach(r3)     // Catch:{ XmlException -> 0x0104 }
        L_0x00d6:
            java.util.List<org.apache.poi.xslf.usermodel.XSLFSlide> r0 = r7._slides     // Catch:{ XmlException -> 0x0104 }
            r0.clear()     // Catch:{ XmlException -> 0x0104 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r0 = r7._presentation     // Catch:{ XmlException -> 0x0104 }
            boolean r0 = r0.isSetSldIdLst()     // Catch:{ XmlException -> 0x0104 }
            if (r0 == 0) goto L_0x00f5
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r0 = r7._presentation     // Catch:{ XmlException -> 0x0104 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList r0 = r0.getSldIdLst()     // Catch:{ XmlException -> 0x0104 }
            java.util.List r0 = r0.getSldIdList()     // Catch:{ XmlException -> 0x0104 }
            org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda1 r2 = new org.apache.poi.xslf.usermodel.XMLSlideShow$$ExternalSyntheticLambda1     // Catch:{ XmlException -> 0x0104 }
            r2.<init>(r7, r1)     // Catch:{ XmlException -> 0x0104 }
            r0.forEach(r2)     // Catch:{ XmlException -> 0x0104 }
        L_0x00f5:
            return
        L_0x00f6:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00f8 }
        L_0x00f8:
            r1 = move-exception
            if (r0 == 0) goto L_0x0103
            r0.close()     // Catch:{ all -> 0x00ff }
            goto L_0x0103
        L_0x00ff:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ XmlException -> 0x0104 }
        L_0x0103:
            throw r1     // Catch:{ XmlException -> 0x0104 }
        L_0x0104:
            r7 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XMLSlideShow.onDocumentRead():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onDocumentRead$0$org-apache-poi-xslf-usermodel-XMLSlideShow  reason: not valid java name */
    public /* synthetic */ void m2304lambda$onDocumentRead$0$orgapachepoixslfusermodelXMLSlideShow(Map map, CTSlideMasterIdListEntry cTSlideMasterIdListEntry) {
        this._masters.add(map.get(cTSlideMasterIdListEntry.getId2()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onDocumentRead$1$org-apache-poi-xslf-usermodel-XMLSlideShow  reason: not valid java name */
    public /* synthetic */ void m2305lambda$onDocumentRead$1$orgapachepoixslfusermodelXMLSlideShow(Map map, CTSlideIdListEntry cTSlideIdListEntry) {
        XSLFSlide xSLFSlide = (XSLFSlide) map.get(cTSlideIdListEntry.getId2());
        if (xSLFSlide == null) {
            LOG.atWarn().log("Slide with r:id {} was defined, but didn't exist in package, skipping", (Object) Unbox.box(cTSlideIdListEntry.getId()));
        } else {
            this._slides.add(xSLFSlide);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r0 != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r2.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()
            org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation r2 = r2._presentation     // Catch:{ all -> 0x0015 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0015 }
            r2.save((java.io.OutputStream) r0, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0014
            r0.close()
        L_0x0014:
            return
        L_0x0015:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r1 = move-exception
            if (r0 == 0) goto L_0x0022
            r0.close()     // Catch:{ all -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0022:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XMLSlideShow.commit():void");
    }

    public List<PackagePart> getAllEmbeddedParts() {
        return Collections.unmodifiableList(getPackage().getPartsByName(Pattern.compile("/ppt/embeddings/.*?")));
    }

    public List<XSLFPictureData> getPictureData() {
        if (this._pictures.isEmpty()) {
            getPackage().getPartsByName(Pattern.compile("/ppt/media/.*?")).forEach(new XMLSlideShow$$ExternalSyntheticLambda2(this));
        }
        return Collections.unmodifiableList(this._pictures);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPictureData$2$org-apache-poi-xslf-usermodel-XMLSlideShow  reason: not valid java name */
    public /* synthetic */ void m2303lambda$getPictureData$2$orgapachepoixslfusermodelXMLSlideShow(PackagePart packagePart) {
        XSLFPictureData xSLFPictureData = new XSLFPictureData(packagePart);
        xSLFPictureData.setIndex(this._pictures.size());
        this._pictures.add(xSLFPictureData);
    }

    public XSLFSlide createSlide(XSLFSlideLayout xSLFSlideLayout) {
        CTSlideIdList sldIdLst = this._presentation.isSetSldIdLst() ? this._presentation.getSldIdLst() : this._presentation.addNewSldIdLst();
        OptionalLong max = Stream.of(sldIdLst.getSldIdArray()).mapToLong(new XMLSlideShow$$ExternalSyntheticLambda3()).max();
        XSLFRelation xSLFRelation = XSLFRelation.SLIDE;
        int max2 = (int) (Math.max(max.orElse(0), 255) + 1);
        POIXMLDocumentPart.RelationPart createRelationship = createRelationship(xSLFRelation, XSLFFactory.getInstance(), findNextAvailableFileNameIndex(xSLFRelation), false);
        XSLFSlide xSLFSlide = (XSLFSlide) createRelationship.getDocumentPart();
        CTSlideIdListEntry addNewSldId = sldIdLst.addNewSldId();
        addNewSldId.setId((long) max2);
        addNewSldId.setId2(createRelationship.getRelationship().getId());
        xSLFSlideLayout.copyLayout(xSLFSlide);
        xSLFSlide.getPackagePart().clearRelationships();
        xSLFSlide.addRelation((String) null, XSLFRelation.SLIDE_LAYOUT, xSLFSlideLayout);
        this._slides.add(xSLFSlide);
        return xSLFSlide;
    }

    private int findNextAvailableFileNameIndex(XSLFRelation xSLFRelation) {
        try {
            return getPackage().getUnusedPartIndex(xSLFRelation.getDefaultFileName());
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public XSLFSlide createSlide() {
        XSLFSlideMaster xSLFSlideMaster = this._masters.get(0);
        XSLFSlideLayout layout = xSLFSlideMaster.getLayout(SlideLayout.BLANK);
        if (layout == null) {
            LOG.atWarn().log("Blank layout was not found - defaulting to first slide layout in master");
            XSLFSlideLayout[] slideLayouts = xSLFSlideMaster.getSlideLayouts();
            if (slideLayouts.length != 0) {
                layout = slideLayouts[0];
            } else {
                throw new POIXMLException("SlideMaster must contain a SlideLayout.");
            }
        }
        return createSlide(layout);
    }

    public XSLFChart createChart(XSLFSlide xSLFSlide) {
        XSLFChart createChart = createChart();
        xSLFSlide.addRelation((String) null, XSLFRelation.CHART, createChart);
        return createChart;
    }

    public XSLFChart createChart() {
        int findNextAvailableFileNameIndex = findNextAvailableFileNameIndex(XSLFRelation.CHART);
        XSLFChart xSLFChart = (XSLFChart) createRelationship(XSLFRelation.CHART, XSLFFactory.getInstance(), findNextAvailableFileNameIndex, true).getDocumentPart();
        xSLFChart.setChartIndex(findNextAvailableFileNameIndex);
        this._charts.add(xSLFChart);
        return xSLFChart;
    }

    public XSLFNotes getNotesSlide(XSLFSlide xSLFSlide) {
        XSLFNotes notes = xSLFSlide.getNotes();
        return notes == null ? createNotesSlide(xSLFSlide) : notes;
    }

    private XSLFNotes createNotesSlide(XSLFSlide xSLFSlide) {
        if (this._notesMaster == null) {
            createNotesMaster();
        }
        XSLFRelation xSLFRelation = XSLFRelation.NOTES;
        XSLFNotes xSLFNotes = (XSLFNotes) createRelationship(xSLFRelation, XSLFFactory.getInstance(), findNextAvailableFileNameIndex(xSLFRelation));
        xSLFSlide.addRelation((String) null, xSLFRelation, xSLFNotes);
        xSLFNotes.addRelation((String) null, XSLFRelation.NOTES_MASTER, this._notesMaster);
        xSLFNotes.addRelation((String) null, XSLFRelation.SLIDE, xSLFSlide);
        xSLFNotes.importContent(this._notesMaster);
        return xSLFNotes;
    }

    public void createNotesMaster() {
        int i = 1;
        POIXMLDocumentPart.RelationPart createRelationship = createRelationship(XSLFRelation.NOTES_MASTER, XSLFFactory.getInstance(), 1, false);
        this._notesMaster = (XSLFNotesMaster) createRelationship.getDocumentPart();
        this._presentation.addNewNotesMasterIdLst().addNewNotesMasterId().setId(createRelationship.getRelationship().getId());
        ArrayList arrayList = new ArrayList();
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XSLFTheme) {
                arrayList.add(XSLFRelation.THEME.getFileNameIndex(next));
            }
        }
        if (!arrayList.isEmpty()) {
            int i2 = 1;
            boolean z = false;
            for (int i3 = 1; i3 <= arrayList.size(); i3++) {
                if (!arrayList.contains(Integer.valueOf(i3))) {
                    i2 = i3;
                    z = true;
                }
            }
            i = !z ? 1 + arrayList.size() : i2;
        }
        XSLFTheme xSLFTheme = (XSLFTheme) createRelationship(XSLFRelation.THEME, XSLFFactory.getInstance(), i);
        xSLFTheme.importTheme(getSlides().get(0).getTheme());
        this._notesMaster.addRelation((String) null, XSLFRelation.THEME, xSLFTheme);
    }

    public XSLFNotesMaster getNotesMaster() {
        return this._notesMaster;
    }

    public List<XSLFSlideMaster> getSlideMasters() {
        return this._masters;
    }

    public List<XSLFSlide> getSlides() {
        return this._slides;
    }

    public List<XSLFChart> getCharts() {
        return Collections.unmodifiableList(this._charts);
    }

    public XSLFCommentAuthors getCommentAuthors() {
        return this._commentAuthors;
    }

    public void setSlideOrder(XSLFSlide xSLFSlide, int i) {
        int indexOf = this._slides.indexOf(xSLFSlide);
        if (indexOf == -1) {
            throw new IllegalArgumentException("Slide not found");
        } else if (indexOf != i) {
            List<XSLFSlide> list = this._slides;
            list.add(i, list.remove(indexOf));
            CTSlideIdList sldIdLst = this._presentation.getSldIdLst();
            CTSlideIdListEntry[] sldIdArray = sldIdLst.getSldIdArray();
            CTSlideIdListEntry cTSlideIdListEntry = sldIdArray[indexOf];
            if (indexOf < i) {
                System.arraycopy(sldIdArray, indexOf + 1, sldIdArray, indexOf, i - indexOf);
            } else {
                System.arraycopy(sldIdArray, i, sldIdArray, i + 1, indexOf - i);
            }
            sldIdArray[i] = cTSlideIdListEntry;
            sldIdLst.setSldIdArray(sldIdArray);
        }
    }

    public XSLFSlide removeSlide(int i) {
        XSLFSlide remove = this._slides.remove(i);
        removeRelation((POIXMLDocumentPart) remove);
        this._presentation.getSldIdLst().removeSldId(i);
        for (POIXMLDocumentPart next : remove.getRelations()) {
            if (next instanceof XSLFChart) {
                XSLFChart xSLFChart = (XSLFChart) next;
                remove.removeChartRelation(xSLFChart);
                this._charts.remove(xSLFChart);
            } else if (next instanceof XSLFSlideLayout) {
                remove.removeLayoutRelation((XSLFSlideLayout) next);
            }
        }
        return remove;
    }

    public Dimension getPageSize() {
        CTSlideSize sldSz = this._presentation.getSldSz();
        return new Dimension((int) Units.toPoints((long) sldSz.getCx()), (int) Units.toPoints((long) sldSz.getCy()));
    }

    public void setPageSize(Dimension dimension) {
        CTSlideSize newInstance = CTSlideSize.Factory.newInstance();
        newInstance.setCx(Units.toEMU(dimension.getWidth()));
        newInstance.setCy(Units.toEMU(dimension.getHeight()));
        this._presentation.setSldSz(newInstance);
    }

    @Internal
    public CTPresentation getCTPresentation() {
        return this._presentation;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        if (r3 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XSLFPictureData addPicture(byte[] r4, org.apache.poi.sl.usermodel.PictureData.PictureType r5) {
        /*
            r3 = this;
            org.apache.poi.xslf.usermodel.XSLFPictureData r0 = r3.findPictureData((byte[]) r4)
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            org.apache.poi.xslf.usermodel.XSLFRelation r0 = org.apache.poi.xslf.usermodel.XSLFPictureData.getRelationForType(r5)
            if (r0 == 0) goto L_0x0062
            r5 = 1
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r3.getPackage()     // Catch:{ InvalidFormatException -> 0x0019 }
            java.lang.String r2 = "/ppt/media/image#\\..+"
            int r1 = r1.getUnusedPartIndex(r2)     // Catch:{ InvalidFormatException -> 0x0019 }
            goto L_0x0020
        L_0x0019:
            java.util.List<org.apache.poi.xslf.usermodel.XSLFPictureData> r1 = r3._pictures
            int r1 = r1.size()
            int r1 = r1 + r5
        L_0x0020:
            org.apache.poi.xslf.usermodel.XSLFFactory r2 = org.apache.poi.xslf.usermodel.XSLFFactory.getInstance()
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r5 = r3.createRelationship(r0, r2, r1, r5)
            org.apache.poi.ooxml.POIXMLDocumentPart r5 = r5.getDocumentPart()
            org.apache.poi.xslf.usermodel.XSLFPictureData r5 = (org.apache.poi.xslf.usermodel.XSLFPictureData) r5
            java.util.List<org.apache.poi.xslf.usermodel.XSLFPictureData> r0 = r3._pictures
            int r0 = r0.size()
            r5.setIndex(r0)
            java.util.List<org.apache.poi.xslf.usermodel.XSLFPictureData> r3 = r3._pictures
            r3.add(r5)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r5.getPackagePart()     // Catch:{ IOException -> 0x005b }
            java.io.OutputStream r3 = r3.getOutputStream()     // Catch:{ IOException -> 0x005b }
            r3.write(r4)     // Catch:{ all -> 0x004d }
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ IOException -> 0x005b }
        L_0x004c:
            return r5
        L_0x004d:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004f }
        L_0x004f:
            r5 = move-exception
            if (r3 == 0) goto L_0x005a
            r3.close()     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ IOException -> 0x005b }
        L_0x005a:
            throw r5     // Catch:{ IOException -> 0x005b }
        L_0x005b:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r4 = new org.apache.poi.ooxml.POIXMLException
            r4.<init>((java.lang.Throwable) r3)
            throw r4
        L_0x0062:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "Picture type "
            r4.<init>(r0)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " is not supported."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XMLSlideShow.addPicture(byte[], org.apache.poi.sl.usermodel.PictureData$PictureType):org.apache.poi.xslf.usermodel.XSLFPictureData");
    }

    public XSLFPictureData addPicture(InputStream inputStream, PictureData.PictureType pictureType) throws IOException {
        return addPicture(IOUtils.toByteArrayWithMaxLength(inputStream, XSLFPictureData.getMaxImageSize()), pictureType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r4 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XSLFPictureData addPicture(java.io.File r4, org.apache.poi.sl.usermodel.PictureData.PictureType r5) throws java.io.IOException {
        /*
            r3 = this;
            long r0 = r4.length()
            int r2 = MAX_RECORD_LENGTH
            byte[] r0 = org.apache.poi.util.IOUtils.safelyAllocate(r0, r2)
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r4)
            org.apache.poi.util.IOUtils.readFully((java.io.InputStream) r1, (byte[]) r0)     // Catch:{ all -> 0x001a }
            r1.close()
            org.apache.poi.xslf.usermodel.XSLFPictureData r3 = r3.addPicture((byte[]) r0, (org.apache.poi.sl.usermodel.PictureData.PictureType) r5)
            return r3
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0025:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XMLSlideShow.addPicture(java.io.File, org.apache.poi.sl.usermodel.PictureData$PictureType):org.apache.poi.xslf.usermodel.XSLFPictureData");
    }

    public XSLFPictureData findPictureData(byte[] bArr) {
        long calculateChecksum = IOUtils.calculateChecksum(bArr);
        byte[] bArr2 = new byte[8];
        LittleEndian.putLong(bArr2, 0, calculateChecksum);
        for (XSLFPictureData next : getPictureData()) {
            if (Arrays.equals(next.getChecksum(), bArr2)) {
                return next;
            }
        }
        return null;
    }

    public XSLFSlideLayout findLayout(String str) {
        for (XSLFSlideMaster layout : getSlideMasters()) {
            XSLFSlideLayout layout2 = layout.getLayout(str);
            if (layout2 != null) {
                return layout2;
            }
        }
        return null;
    }

    public XSLFTableStyles getTableStyles() {
        return this._tableStyles;
    }

    public MasterSheet<XSLFShape, XSLFTextParagraph> createMasterSheet() throws IOException {
        throw new UnsupportedOperationException();
    }

    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return new POIXMLPropertiesTextExtractor((POIXMLDocument) this);
    }

    public XSLFFontInfo addFont(InputStream inputStream) throws IOException {
        return XSLFFontInfo.addFontToSlideShow(this, inputStream);
    }

    public List<XSLFFontInfo> getFonts() {
        return XSLFFontInfo.getFonts(this);
    }

    /* access modifiers changed from: package-private */
    public String importBlip(String str, POIXMLDocumentPart pOIXMLDocumentPart, POIXMLDocumentPart pOIXMLDocumentPart2) {
        OPCPackage oPCPackage = pOIXMLDocumentPart2.getPackagePart().getPackage();
        if (oPCPackage == getPackage()) {
            POIXMLDocumentPart documentPart = pOIXMLDocumentPart.getRelationPartById(str).getDocumentPart();
            if (documentPart instanceof XSLFPictureData) {
                XSLFPictureData xSLFPictureData = (XSLFPictureData) documentPart;
                if (oPCPackage != pOIXMLDocumentPart.getPackagePart().getPackage()) {
                    xSLFPictureData = addPicture(xSLFPictureData.getData(), xSLFPictureData.getType());
                }
                return pOIXMLDocumentPart2.addRelation((String) null, XSLFRelation.IMAGES, xSLFPictureData).getRelationship().getId();
            }
            throw new RuntimeException("cannot import blip " + str + " - its document part is not XSLFPictureData");
        }
        throw new RuntimeException("the target document part is not a child of this package");
    }
}
