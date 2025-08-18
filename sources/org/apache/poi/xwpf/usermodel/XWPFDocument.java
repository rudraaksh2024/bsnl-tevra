package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.IdentifierManager;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

public class XWPFDocument extends POIXMLDocument implements Document, IBody {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XWPFDocument.class);
    protected List<IBodyElement> bodyElements = new ArrayList();
    protected final List<XWPFChart> charts = new ArrayList();
    private XWPFComments comments;
    protected List<XWPFSDT> contentControls = new ArrayList();
    private CTDocument1 ctDocument;
    private final IdentifierManager drawingIdManager = new IdentifierManager(0, 4294967295L);
    protected XWPFEndnotes endnotes;
    protected List<XWPFFooter> footers = new ArrayList();
    private final FootnoteEndnoteIdManager footnoteIdManager = new FootnoteEndnoteIdManager(this);
    protected XWPFFootnotes footnotes;
    private XWPFHeaderFooterPolicy headerFooterPolicy;
    protected List<XWPFHeader> headers = new ArrayList();
    protected List<XWPFHyperlink> hyperlinks = new ArrayList();
    protected XWPFNumbering numbering;
    protected Map<Long, List<XWPFPictureData>> packagePictures = new HashMap();
    protected List<XWPFParagraph> paragraphs = new ArrayList();
    protected List<XWPFPictureData> pictures = new ArrayList();
    private XWPFSettings settings;
    protected XWPFStyles styles;
    protected List<XWPFTable> tables = new ArrayList();

    public POIXMLDocumentPart getPart() {
        return this;
    }

    public XWPFDocument getXWPFDocument() {
        return this;
    }

    public XWPFDocument(OPCPackage oPCPackage) throws IOException {
        super(oPCPackage);
        load(XWPFFactory.getInstance());
    }

    public XWPFDocument(InputStream inputStream) throws IOException {
        super(PackageHelper.open(inputStream));
        load(XWPFFactory.getInstance());
    }

    public XWPFDocument() {
        super(newPackage());
        onDocumentCreate();
    }

    protected static OPCPackage newPackage() {
        OPCPackage oPCPackage = null;
        try {
            oPCPackage = OPCPackage.create((OutputStream) new UnsynchronizedByteArrayOutputStream());
            PackagePartName createPartName = PackagingURIHelper.createPartName(XWPFRelation.DOCUMENT.getDefaultFileName());
            oPCPackage.addRelationship(createPartName, TargetMode.INTERNAL, PackageRelationshipTypes.CORE_DOCUMENT);
            oPCPackage.createPart(createPartName, XWPFRelation.DOCUMENT.getContentType());
            oPCPackage.getPackageProperties().setCreatorProperty(POIXMLDocument.DOCUMENT_CREATOR);
            return oPCPackage;
        } catch (Exception e) {
            IOUtils.closeQuietly(oPCPackage);
            throw new POIXMLException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01a3, code lost:
        if (r1 != null) goto L_0x01a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01ad, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "./*"
            org.apache.poi.openxml4j.opc.PackagePart r1 = r6.getPackagePart()     // Catch:{ XmlException -> 0x01ae }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ XmlException -> 0x01ae }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument> r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument.Factory     // Catch:{ all -> 0x01a0 }
            org.apache.xmlbeans.XmlOptions r3 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x01a0 }
            java.lang.Object r2 = r2.parse((java.io.InputStream) r1, (org.apache.xmlbeans.XmlOptions) r3)     // Catch:{ all -> 0x01a0 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument r2 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument) r2     // Catch:{ all -> 0x01a0 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1 r3 = r2.getDocument()     // Catch:{ all -> 0x01a0 }
            r6.ctDocument = r3     // Catch:{ all -> 0x01a0 }
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ XmlException -> 0x01ae }
        L_0x001f:
            r6.initFootnotes()     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1 r1 = r6.ctDocument     // Catch:{ XmlException -> 0x01ae }
            org.apache.xmlbeans.XmlCursor r1 = r1.newCursor()     // Catch:{ XmlException -> 0x01ae }
            r1.selectPath(r0)     // Catch:{ XmlException -> 0x01ae }
        L_0x002b:
            boolean r3 = r1.toNextSelection()     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0090
            org.apache.xmlbeans.XmlObject r3 = r1.getObject()     // Catch:{ XmlException -> 0x01ae }
            boolean r4 = r3 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody     // Catch:{ XmlException -> 0x01ae }
            if (r4 == 0) goto L_0x002b
            org.apache.xmlbeans.XmlCursor r3 = r3.newCursor()     // Catch:{ XmlException -> 0x01ae }
            r3.selectPath(r0)     // Catch:{ XmlException -> 0x01ae }
        L_0x0040:
            boolean r4 = r3.toNextSelection()     // Catch:{ XmlException -> 0x01ae }
            if (r4 == 0) goto L_0x008c
            org.apache.xmlbeans.XmlObject r4 = r3.getObject()     // Catch:{ XmlException -> 0x01ae }
            boolean r5 = r4 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP     // Catch:{ XmlException -> 0x01ae }
            if (r5 == 0) goto L_0x0060
            org.apache.poi.xwpf.usermodel.XWPFParagraph r5 = new org.apache.poi.xwpf.usermodel.XWPFParagraph     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP r4 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP) r4     // Catch:{ XmlException -> 0x01ae }
            r5.<init>(r4, r6)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.IBodyElement> r4 = r6.bodyElements     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFParagraph> r4 = r6.paragraphs     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x0040
        L_0x0060:
            boolean r5 = r4 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl     // Catch:{ XmlException -> 0x01ae }
            if (r5 == 0) goto L_0x0076
            org.apache.poi.xwpf.usermodel.XWPFTable r5 = new org.apache.poi.xwpf.usermodel.XWPFTable     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl r4 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl) r4     // Catch:{ XmlException -> 0x01ae }
            r5.<init>(r4, r6)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.IBodyElement> r4 = r6.bodyElements     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFTable> r4 = r6.tables     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x0040
        L_0x0076:
            boolean r5 = r4 instanceof org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock     // Catch:{ XmlException -> 0x01ae }
            if (r5 == 0) goto L_0x0040
            org.apache.poi.xwpf.usermodel.XWPFSDT r5 = new org.apache.poi.xwpf.usermodel.XWPFSDT     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock r4 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock) r4     // Catch:{ XmlException -> 0x01ae }
            r5.<init>((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock) r4, (org.apache.poi.xwpf.usermodel.IBody) r6)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.IBodyElement> r4 = r6.bodyElements     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFSDT> r4 = r6.contentControls     // Catch:{ XmlException -> 0x01ae }
            r4.add(r5)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x0040
        L_0x008c:
            r3.dispose()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x002b
        L_0x0090:
            r1.dispose()     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1 r0 = r2.getDocument()     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody r0 = r0.getBody()     // Catch:{ XmlException -> 0x01ae }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr r0 = r0.getSectPr()     // Catch:{ XmlException -> 0x01ae }
            if (r0 == 0) goto L_0x00a8
            org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy r0 = new org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy     // Catch:{ XmlException -> 0x01ae }
            r0.<init>(r6)     // Catch:{ XmlException -> 0x01ae }
            r6.headerFooterPolicy = r0     // Catch:{ XmlException -> 0x01ae }
        L_0x00a8:
            java.util.List r0 = r6.getRelationParts()     // Catch:{ XmlException -> 0x01ae }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ XmlException -> 0x01ae }
        L_0x00b0:
            boolean r1 = r0.hasNext()     // Catch:{ XmlException -> 0x01ae }
            if (r1 == 0) goto L_0x019c
            java.lang.Object r1 = r0.next()     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r1 = (org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart) r1     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.ooxml.POIXMLDocumentPart r2 = r1.getDocumentPart()     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.openxml4j.opc.PackageRelationship r1 = r1.getRelationship()     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r1 = r1.getRelationshipType()     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.STYLES     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x00dc
            org.apache.poi.xwpf.usermodel.XWPFStyles r2 = (org.apache.poi.xwpf.usermodel.XWPFStyles) r2     // Catch:{ XmlException -> 0x01ae }
            r6.styles = r2     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x00dc:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.NUMBERING     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x00f0
            org.apache.poi.xwpf.usermodel.XWPFNumbering r2 = (org.apache.poi.xwpf.usermodel.XWPFNumbering) r2     // Catch:{ XmlException -> 0x01ae }
            r6.numbering = r2     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x00f0:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.FOOTER     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0107
            org.apache.poi.xwpf.usermodel.XWPFFooter r2 = (org.apache.poi.xwpf.usermodel.XWPFFooter) r2     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFFooter> r1 = r6.footers     // Catch:{ XmlException -> 0x01ae }
            r1.add(r2)     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x0107:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.HEADER     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x011e
            org.apache.poi.xwpf.usermodel.XWPFHeader r2 = (org.apache.poi.xwpf.usermodel.XWPFHeader) r2     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFHeader> r1 = r6.headers     // Catch:{ XmlException -> 0x01ae }
            r1.add(r2)     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x011e:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.COMMENT     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0133
            org.apache.poi.xwpf.usermodel.XWPFComments r2 = (org.apache.poi.xwpf.usermodel.XWPFComments) r2     // Catch:{ XmlException -> 0x01ae }
            r6.comments = r2     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x0133:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.SETTINGS     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0148
            org.apache.poi.xwpf.usermodel.XWPFSettings r2 = (org.apache.poi.xwpf.usermodel.XWPFSettings) r2     // Catch:{ XmlException -> 0x01ae }
            r6.settings = r2     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x0148:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.IMAGES     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0163
            org.apache.poi.xwpf.usermodel.XWPFPictureData r2 = (org.apache.poi.xwpf.usermodel.XWPFPictureData) r2     // Catch:{ XmlException -> 0x01ae }
            r2.onDocumentRead()     // Catch:{ XmlException -> 0x01ae }
            r6.registerPackagePictureData(r2)     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r1 = r6.pictures     // Catch:{ XmlException -> 0x01ae }
            r1.add(r2)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x0163:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.CHART     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r3 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r3 == 0) goto L_0x0178
            org.apache.poi.xwpf.usermodel.XWPFChart r2 = (org.apache.poi.xwpf.usermodel.XWPFChart) r2     // Catch:{ XmlException -> 0x01ae }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFChart> r1 = r6.charts     // Catch:{ XmlException -> 0x01ae }
            r1.add(r2)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x00b0
        L_0x0178:
            org.apache.poi.xwpf.usermodel.XWPFRelation r3 = org.apache.poi.xwpf.usermodel.XWPFRelation.GLOSSARY_DOCUMENT     // Catch:{ XmlException -> 0x01ae }
            java.lang.String r3 = r3.getRelation()     // Catch:{ XmlException -> 0x01ae }
            boolean r1 = r1.equals(r3)     // Catch:{ XmlException -> 0x01ae }
            if (r1 == 0) goto L_0x00b0
            java.util.List r1 = r2.getRelations()     // Catch:{ XmlException -> 0x01ae }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ XmlException -> 0x01ae }
        L_0x018c:
            boolean r2 = r1.hasNext()     // Catch:{ XmlException -> 0x01ae }
            if (r2 == 0) goto L_0x00b0
            java.lang.Object r2 = r1.next()     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.ooxml.POIXMLDocumentPart r2 = (org.apache.poi.ooxml.POIXMLDocumentPart) r2     // Catch:{ XmlException -> 0x01ae }
            org.apache.poi.ooxml.POIXMLDocumentPart._invokeOnDocumentRead(r2)     // Catch:{ XmlException -> 0x01ae }
            goto L_0x018c
        L_0x019c:
            r6.initHyperlinks()     // Catch:{ XmlException -> 0x01ae }
            return
        L_0x01a0:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x01a2 }
        L_0x01a2:
            r0 = move-exception
            if (r1 == 0) goto L_0x01ad
            r1.close()     // Catch:{ all -> 0x01a9 }
            goto L_0x01ad
        L_0x01a9:
            r1 = move-exception
            r6.addSuppressed(r1)     // Catch:{ XmlException -> 0x01ae }
        L_0x01ad:
            throw r0     // Catch:{ XmlException -> 0x01ae }
        L_0x01ae:
            r6 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFDocument.onDocumentRead():void");
    }

    private void initHyperlinks() {
        try {
            this.hyperlinks = new ArrayList();
            Iterator<PackageRelationship> it = getPackagePart().getRelationshipsByType(XWPFRelation.HYPERLINK.getRelation()).iterator();
            while (it.hasNext()) {
                PackageRelationship next = it.next();
                this.hyperlinks.add(new XWPFHyperlink(next.getId(), next.getTargetURI().toString()));
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    private void initFootnotes() throws XmlException, IOException {
        for (POIXMLDocumentPart.RelationPart next : getRelationParts()) {
            POIXMLDocumentPart documentPart = next.getDocumentPart();
            String relationshipType = next.getRelationship().getRelationshipType();
            if (relationshipType.equals(XWPFRelation.FOOTNOTE.getRelation())) {
                XWPFFootnotes xWPFFootnotes = (XWPFFootnotes) documentPart;
                this.footnotes = xWPFFootnotes;
                xWPFFootnotes.onDocumentRead();
                this.footnotes.setIdManager(this.footnoteIdManager);
            } else if (relationshipType.equals(XWPFRelation.ENDNOTE.getRelation())) {
                XWPFEndnotes xWPFEndnotes = (XWPFEndnotes) documentPart;
                this.endnotes = xWPFEndnotes;
                xWPFEndnotes.onDocumentRead();
                this.endnotes.setIdManager(this.footnoteIdManager);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDocumentCreate() {
        CTDocument1 newInstance = CTDocument1.Factory.newInstance();
        this.ctDocument = newInstance;
        newInstance.addNewBody();
        this.settings = (XWPFSettings) createRelationship(XWPFRelation.SETTINGS, XWPFFactory.getInstance());
        getProperties().getExtendedProperties().getUnderlyingProperties().setApplication(POIXMLDocument.DOCUMENT_CREATOR);
    }

    @Internal
    public CTDocument1 getDocument() {
        return this.ctDocument;
    }

    /* access modifiers changed from: package-private */
    public IdentifierManager getDrawingIdManager() {
        return this.drawingIdManager;
    }

    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public Iterator<IBodyElement> getBodyElementsIterator() {
        return this.bodyElements.iterator();
    }

    public Spliterator<IBodyElement> getBodyElementsSpliterator() {
        return this.bodyElements.spliterator();
    }

    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public List<XWPFChart> getCharts() {
        return Collections.unmodifiableList(this.charts);
    }

    public XWPFTable getTableArray(int i) {
        if (i < 0 || i >= this.tables.size()) {
            return null;
        }
        return this.tables.get(i);
    }

    public List<XWPFFooter> getFooterList() {
        return Collections.unmodifiableList(this.footers);
    }

    public XWPFFooter getFooterArray(int i) {
        if (i < 0 || i >= this.footers.size()) {
            return null;
        }
        return this.footers.get(i);
    }

    public List<XWPFHeader> getHeaderList() {
        return Collections.unmodifiableList(this.headers);
    }

    public XWPFHeader getHeaderArray(int i) {
        if (i < 0 || i >= this.headers.size()) {
            return null;
        }
        return this.headers.get(i);
    }

    public String getTblStyle(XWPFTable xWPFTable) {
        return xWPFTable.getStyleID();
    }

    public XWPFHyperlink getHyperlinkByID(String str) {
        for (XWPFHyperlink next : this.hyperlinks) {
            if (next.getId().equals(str)) {
                return next;
            }
        }
        initHyperlinks();
        for (XWPFHyperlink next2 : this.hyperlinks) {
            if (next2.getId().equals(str)) {
                return next2;
            }
        }
        return null;
    }

    public XWPFFootnote getFootnoteByID(int i) {
        XWPFFootnotes xWPFFootnotes = this.footnotes;
        if (xWPFFootnotes == null) {
            return null;
        }
        return (XWPFFootnote) xWPFFootnotes.getFootnoteById(i);
    }

    public XWPFEndnote getEndnoteByID(int i) {
        XWPFEndnotes xWPFEndnotes = this.endnotes;
        if (xWPFEndnotes == null) {
            return null;
        }
        return xWPFEndnotes.getFootnoteById(i);
    }

    public List<XWPFFootnote> getFootnotes() {
        XWPFFootnotes xWPFFootnotes = this.footnotes;
        if (xWPFFootnotes == null) {
            return Collections.emptyList();
        }
        return xWPFFootnotes.getFootnotesList();
    }

    public XWPFHyperlink[] getHyperlinks() {
        return (XWPFHyperlink[]) this.hyperlinks.toArray(new XWPFHyperlink[0]);
    }

    public XWPFComments getDocComments() {
        return this.comments;
    }

    public XWPFComment getCommentByID(String str) {
        XWPFComments xWPFComments = this.comments;
        if (xWPFComments == null) {
            return null;
        }
        return xWPFComments.getCommentByID(str);
    }

    public XWPFComment[] getComments() {
        XWPFComments xWPFComments = this.comments;
        if (xWPFComments == null) {
            return null;
        }
        return (XWPFComment[]) xWPFComments.getComments().toArray(new XWPFComment[0]);
    }

    public PackagePart getPartById(String str) {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(str));
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public XWPFHeaderFooterPolicy getHeaderFooterPolicy() {
        return this.headerFooterPolicy;
    }

    public XWPFHeaderFooterPolicy createHeaderFooterPolicy() {
        if (this.headerFooterPolicy == null) {
            this.headerFooterPolicy = new XWPFHeaderFooterPolicy(this);
        }
        return this.headerFooterPolicy;
    }

    public XWPFHeader createHeader(HeaderFooterType headerFooterType) {
        XWPFHeaderFooterPolicy createHeaderFooterPolicy = createHeaderFooterPolicy();
        if (headerFooterType == HeaderFooterType.FIRST) {
            CTSectPr section = getSection();
            if (!section.isSetTitlePg()) {
                section.addNewTitlePg().setVal(STOnOff1.ON);
            }
        }
        return createHeaderFooterPolicy.createHeader(STHdrFtr.Enum.forInt(headerFooterType.toInt()));
    }

    public XWPFFooter createFooter(HeaderFooterType headerFooterType) {
        XWPFHeaderFooterPolicy createHeaderFooterPolicy = createHeaderFooterPolicy();
        if (headerFooterType == HeaderFooterType.FIRST) {
            CTSectPr section = getSection();
            if (!section.isSetTitlePg()) {
                section.addNewTitlePg().setVal(STOnOff1.ON);
            }
        }
        return createHeaderFooterPolicy.createFooter(STHdrFtr.Enum.forInt(headerFooterType.toInt()));
    }

    private CTSectPr getSection() {
        CTBody body = getDocument().getBody();
        if (body.isSetSectPr()) {
            return body.getSectPr();
        }
        return body.addNewSectPr();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r3 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        throw r1;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles getStyle() throws org.apache.xmlbeans.XmlException, java.io.IOException {
        /*
            r3 = this;
            org.apache.poi.xwpf.usermodel.XWPFRelation r0 = org.apache.poi.xwpf.usermodel.XWPFRelation.STYLES     // Catch:{ InvalidFormatException -> 0x004d }
            java.lang.String r0 = r0.getRelation()     // Catch:{ InvalidFormatException -> 0x004d }
            org.apache.poi.openxml4j.opc.PackagePart[] r3 = r3.getRelatedByType(r0)     // Catch:{ InvalidFormatException -> 0x004d }
            int r0 = r3.length
            r1 = 1
            if (r0 != r1) goto L_0x0037
            r0 = 0
            r3 = r3[r0]
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument> r0 = org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument.Factory     // Catch:{ all -> 0x0029 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0029 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0029 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument r0 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument) r0     // Catch:{ all -> 0x0029 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles r0 = r0.getStyles()     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0028
            r3.close()
        L_0x0028:
            return r0
        L_0x0029:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002b }
        L_0x002b:
            r1 = move-exception
            if (r3 == 0) goto L_0x0036
            r3.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x0036:
            throw r1
        L_0x0037:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expecting one Styles document part, but found "
            r1.<init>(r2)
            int r3 = r3.length
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        L_0x004d:
            r3 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFDocument.getStyle():org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles");
    }

    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        LinkedList linkedList = new LinkedList();
        PackagePart packagePart = getPackagePart();
        Iterator<PackageRelationship> it = getPackagePart().getRelationshipsByType(POIXMLDocument.OLE_OBJECT_REL_TYPE).iterator();
        while (it.hasNext()) {
            linkedList.add(packagePart.getRelatedPart(it.next()));
        }
        Iterator<PackageRelationship> it2 = getPackagePart().getRelationshipsByType(POIXMLDocument.PACK_OBJECT_REL_TYPE).iterator();
        while (it2.hasNext()) {
            linkedList.add(packagePart.getRelatedPart(it2.next()));
        }
        return linkedList;
    }

    private int getBodyElementSpecificPos(int i, List<? extends IBodyElement> list) {
        if (!list.isEmpty() && i >= 0 && i < this.bodyElements.size()) {
            IBodyElement iBodyElement = this.bodyElements.get(i);
            if (iBodyElement.getElementType() != ((IBodyElement) list.get(0)).getElementType()) {
                return -1;
            }
            for (int min = Math.min(i, list.size() - 1); min >= 0; min--) {
                if (list.get(min) == iBodyElement) {
                    return min;
                }
            }
        }
        return -1;
    }

    public int getParagraphPos(int i) {
        return getBodyElementSpecificPos(i, this.paragraphs);
    }

    public int getTablePos(int i) {
        return getBodyElementSpecificPos(i, this.tables);
    }

    public XWPFParagraph insertNewParagraph(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInBody(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("p", CTP.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTP ctp = (CTP) xmlCursor.getObject();
        XWPFParagraph xWPFParagraph = new XWPFParagraph(ctp, this);
        while (true) {
            z = xmlObject instanceof CTP;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z || xmlObject == ctp) {
            this.paragraphs.add(0, xWPFParagraph);
        } else {
            this.paragraphs.add(this.paragraphs.indexOf(getParagraph((CTP) xmlObject)) + 1, xWPFParagraph);
        }
        XmlCursor newCursor = ctp.newCursor();
        try {
            xmlCursor.toCursor(newCursor);
            while (xmlCursor.toPrevSibling()) {
                XmlObject object = xmlCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            }
            this.bodyElements.add(i2, xWPFParagraph);
            xmlCursor.toCursor(newCursor);
            xmlCursor.toEndToken();
            return xWPFParagraph;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFTable insertNewTbl(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInBody(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("tbl", CTTbl.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTTbl cTTbl = (CTTbl) xmlCursor.getObject();
        XWPFTable xWPFTable = new XWPFTable(cTTbl, this);
        while (true) {
            z = xmlObject instanceof CTTbl;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z) {
            this.tables.add(0, xWPFTable);
        } else {
            this.tables.add(this.tables.indexOf(getTable((CTTbl) xmlObject)) + 1, xWPFTable);
        }
        XmlCursor newCursor = cTTbl.newCursor();
        try {
            xmlCursor.toCursor(newCursor);
            while (xmlCursor.toPrevSibling()) {
                XmlObject object = xmlCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            }
            this.bodyElements.add(i2, xWPFTable);
            xmlCursor.toCursor(newCursor);
            xmlCursor.toEndToken();
            return xWPFTable;
        } finally {
            newCursor.dispose();
        }
    }

    private boolean isCursorInBody(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        newCursor.toParent();
        boolean z = newCursor.getObject() == this.ctDocument.getBody();
        newCursor.dispose();
        return z;
    }

    private int getPosOfBodyElement(IBodyElement iBodyElement) {
        BodyElementType elementType = iBodyElement.getElementType();
        for (int i = 0; i < this.bodyElements.size(); i++) {
            IBodyElement iBodyElement2 = this.bodyElements.get(i);
            if (iBodyElement2.getElementType() == elementType && iBodyElement2.equals(iBodyElement)) {
                return i;
            }
        }
        return -1;
    }

    public int getPosOfParagraph(XWPFParagraph xWPFParagraph) {
        return getPosOfBodyElement(xWPFParagraph);
    }

    public int getPosOfTable(XWPFTable xWPFTable) {
        return getPosOfBodyElement(xWPFTable);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r1 != null) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "document"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1 r4 = r4.ctDocument     // Catch:{ all -> 0x002e }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            return
        L_0x002e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x003b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFDocument.commit():void");
    }

    private int getRelationIndex(XWPFRelation xWPFRelation) {
        int i = 1;
        for (POIXMLDocumentPart.RelationPart relationship : getRelationParts()) {
            if (relationship.getRelationship().getRelationshipType().equals(xWPFRelation.getRelation())) {
                i++;
            }
        }
        return i;
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph xWPFParagraph = new XWPFParagraph(this.ctDocument.getBody().addNewP(), this);
        this.bodyElements.add(xWPFParagraph);
        this.paragraphs.add(xWPFParagraph);
        return xWPFParagraph;
    }

    public XWPFComments createComments() {
        if (this.comments == null) {
            XWPFRelation xWPFRelation = XWPFRelation.COMMENT;
            XWPFComments xWPFComments = (XWPFComments) createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
            xWPFComments.setCtComments(CommentsDocument.Factory.newInstance().addNewComments());
            xWPFComments.setXWPFDocument(getXWPFDocument());
            this.comments = xWPFComments;
        }
        return this.comments;
    }

    public XWPFNumbering createNumbering() {
        if (this.numbering == null) {
            XWPFRelation xWPFRelation = XWPFRelation.NUMBERING;
            XWPFNumbering xWPFNumbering = (XWPFNumbering) createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
            xWPFNumbering.setNumbering(NumberingDocument.Factory.newInstance().addNewNumbering());
            this.numbering = xWPFNumbering;
        }
        return this.numbering;
    }

    public XWPFStyles createStyles() {
        if (this.styles == null) {
            XWPFRelation xWPFRelation = XWPFRelation.STYLES;
            XWPFStyles xWPFStyles = (XWPFStyles) createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
            xWPFStyles.setStyles(StylesDocument.Factory.newInstance().addNewStyles());
            this.styles = xWPFStyles;
        }
        return this.styles;
    }

    public XWPFFootnotes createFootnotes() {
        if (this.footnotes == null) {
            XWPFRelation xWPFRelation = XWPFRelation.FOOTNOTE;
            XWPFFootnotes xWPFFootnotes = (XWPFFootnotes) createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
            xWPFFootnotes.setFootnotes(FootnotesDocument.Factory.newInstance().addNewFootnotes());
            xWPFFootnotes.setIdManager(this.footnoteIdManager);
            this.footnotes = xWPFFootnotes;
        }
        return this.footnotes;
    }

    @Internal
    public XWPFFootnote addFootnote(CTFtnEdn cTFtnEdn) {
        return this.footnotes.addFootnote(cTFtnEdn);
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn cTFtnEdn) {
        XWPFEndnote xWPFEndnote = new XWPFEndnote(this, cTFtnEdn);
        this.endnotes.addEndnote(cTFtnEdn);
        return xWPFEndnote;
    }

    public boolean removeBodyElement(int i) {
        if (i < 0 || i >= this.bodyElements.size()) {
            return false;
        }
        BodyElementType elementType = this.bodyElements.get(i).getElementType();
        if (elementType == BodyElementType.TABLE) {
            int tablePos = getTablePos(i);
            this.tables.remove(tablePos);
            this.ctDocument.getBody().removeTbl(tablePos);
        }
        if (elementType == BodyElementType.PARAGRAPH) {
            int paragraphPos = getParagraphPos(i);
            this.paragraphs.remove(paragraphPos);
            this.ctDocument.getBody().removeP(paragraphPos);
        }
        this.bodyElements.remove(i);
        return true;
    }

    public void setParagraph(XWPFParagraph xWPFParagraph, int i) {
        this.paragraphs.set(i, xWPFParagraph);
        this.ctDocument.getBody().setPArray(i, xWPFParagraph.getCTP());
    }

    public XWPFParagraph getLastParagraph() {
        return this.paragraphs.get(this.paragraphs.toArray().length - 1);
    }

    public XWPFTable createTable() {
        XWPFTable xWPFTable = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this);
        this.bodyElements.add(xWPFTable);
        this.tables.add(xWPFTable);
        return xWPFTable;
    }

    public XWPFTable createTable(int i, int i2) {
        XWPFTable xWPFTable = new XWPFTable(this.ctDocument.getBody().addNewTbl(), this, i, i2);
        this.bodyElements.add(xWPFTable);
        this.tables.add(xWPFTable);
        return xWPFTable;
    }

    public void createTOC() {
        TOC toc = new TOC(getDocument().getBody().addNewSdt());
        for (XWPFParagraph next : this.paragraphs) {
            String style = next.getStyle();
            if (style != null && style.startsWith("Heading")) {
                try {
                    toc.addRow(Integer.parseInt(style.substring(7)), next.getText(), 1, "112723803");
                } catch (NumberFormatException e) {
                    LOG.atError().withThrowable(e).log("can't format number in TOC heading");
                }
            }
        }
    }

    public void setTable(int i, XWPFTable xWPFTable) {
        this.tables.set(i, xWPFTable);
        this.ctDocument.getBody().setTblArray(i, xWPFTable.getCTTbl());
    }

    public boolean isEnforcedProtection() {
        return this.settings.isEnforcedWith();
    }

    public boolean isEnforcedReadonlyProtection() {
        return this.settings.isEnforcedWith(STDocProtect.READ_ONLY);
    }

    public boolean isEnforcedFillingFormsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.FORMS);
    }

    public boolean isEnforcedCommentsProtection() {
        return this.settings.isEnforcedWith(STDocProtect.COMMENTS);
    }

    public boolean isEnforcedTrackedChangesProtection() {
        return this.settings.isEnforcedWith(STDocProtect.TRACKED_CHANGES);
    }

    public boolean isEnforcedUpdateFields() {
        return this.settings.isUpdateFields();
    }

    public void enforceReadonlyProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY);
    }

    public void enforceReadonlyProtection(String str, HashAlgorithm hashAlgorithm) {
        this.settings.setEnforcementEditValue(STDocProtect.READ_ONLY, str, hashAlgorithm);
    }

    public void enforceFillingFormsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS);
    }

    public void enforceFillingFormsProtection(String str, HashAlgorithm hashAlgorithm) {
        this.settings.setEnforcementEditValue(STDocProtect.FORMS, str, hashAlgorithm);
    }

    public void enforceCommentsProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS);
    }

    public void enforceCommentsProtection(String str, HashAlgorithm hashAlgorithm) {
        this.settings.setEnforcementEditValue(STDocProtect.COMMENTS, str, hashAlgorithm);
    }

    public void enforceTrackedChangesProtection() {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES);
    }

    public void enforceTrackedChangesProtection(String str, HashAlgorithm hashAlgorithm) {
        this.settings.setEnforcementEditValue(STDocProtect.TRACKED_CHANGES, str, hashAlgorithm);
    }

    public boolean validateProtectionPassword(String str) {
        return this.settings.validateProtectionPassword(str);
    }

    public void removeProtectionEnforcement() {
        this.settings.removeEnforcement();
    }

    public void enforceUpdateFields() {
        this.settings.setUpdateFields();
    }

    public boolean isTrackRevisions() {
        return this.settings.isTrackRevisions();
    }

    public void setTrackRevisions(boolean z) {
        this.settings.setTrackRevisions(z);
    }

    public long getZoomPercent() {
        return this.settings.getZoomPercent();
    }

    public void setZoomPercent(long j) {
        this.settings.setZoomPercent(j);
    }

    public boolean getEvenAndOddHeadings() {
        return this.settings.getEvenAndOddHeadings();
    }

    public void setEvenAndOddHeadings(boolean z) {
        this.settings.setEvenAndOddHeadings(z);
    }

    public boolean getMirrorMargins() {
        return this.settings.getMirrorMargins();
    }

    public void setMirrorMargins(boolean z) {
        this.settings.setMirrorMargins(z);
    }

    public void insertTable(int i, XWPFTable xWPFTable) {
        this.bodyElements.add(i, xWPFTable);
        CTTbl[] tblArray = this.ctDocument.getBody().getTblArray();
        int length = tblArray.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length && tblArray[i2] != xWPFTable.getCTTbl()) {
            i3++;
            i2++;
        }
        this.tables.add(i3, xWPFTable);
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public List<XWPFPictureData> getAllPackagePictures() {
        ArrayList arrayList = new ArrayList();
        for (List<XWPFPictureData> addAll : this.packagePictures.values()) {
            arrayList.addAll(addAll);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public XWPFSettings getSettings() {
        return this.settings;
    }

    static /* synthetic */ List lambda$registerPackagePictureData$0(Long l) {
        return new ArrayList(1);
    }

    /* access modifiers changed from: package-private */
    public void registerPackagePictureData(XWPFPictureData xWPFPictureData) {
        List computeIfAbsent = this.packagePictures.computeIfAbsent(xWPFPictureData.getChecksum(), new XWPFDocument$$ExternalSyntheticLambda0());
        if (!computeIfAbsent.contains(xWPFPictureData)) {
            computeIfAbsent.add(xWPFPictureData);
        }
    }

    /* access modifiers changed from: package-private */
    public XWPFPictureData findPackagePictureData(byte[] bArr, int i) {
        List list = this.packagePictures.get(Long.valueOf(IOUtils.calculateChecksum(bArr)));
        XWPFPictureData xWPFPictureData = null;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext() && xWPFPictureData == null) {
                XWPFPictureData xWPFPictureData2 = (XWPFPictureData) it.next();
                if (Arrays.equals(bArr, xWPFPictureData2.getData())) {
                    xWPFPictureData = xWPFPictureData2;
                }
            }
        }
        return xWPFPictureData;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (r0 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String addPictureData(byte[] r3, int r4) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r2 = this;
            org.apache.poi.xwpf.usermodel.XWPFPictureData r0 = r2.findPackagePictureData(r3, r4)
            org.apache.poi.ooxml.POIXMLRelation[] r1 = org.apache.poi.xwpf.usermodel.XWPFPictureData.RELATIONS
            r1 = r1[r4]
            if (r0 != 0) goto L_0x004a
            int r4 = r2.getNextPicNameNumber(r4)
            org.apache.poi.xwpf.usermodel.XWPFFactory r0 = org.apache.poi.xwpf.usermodel.XWPFFactory.getInstance()
            org.apache.poi.ooxml.POIXMLDocumentPart r4 = r2.createRelationship(r1, r0, r4)
            org.apache.poi.xwpf.usermodel.XWPFPictureData r4 = (org.apache.poi.xwpf.usermodel.XWPFPictureData) r4
            org.apache.poi.openxml4j.opc.PackagePart r0 = r4.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ IOException -> 0x0043 }
            r0.write(r3)     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0028
            r0.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0028:
            r2.registerPackagePictureData(r4)
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r3 = r2.pictures
            r3.add(r4)
            java.lang.String r2 = r2.getRelationId(r4)
            return r2
        L_0x0035:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r3 = move-exception
            if (r0 == 0) goto L_0x0042
            r0.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x0043 }
        L_0x0042:
            throw r3     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
            r3.<init>((java.lang.Throwable) r2)
            throw r3
        L_0x004a:
            java.util.List r3 = r2.getRelations()
            boolean r3 = r3.contains(r0)
            if (r3 != 0) goto L_0x0064
            r3 = 0
            org.apache.poi.xwpf.usermodel.XWPFRelation r4 = org.apache.poi.xwpf.usermodel.XWPFRelation.IMAGES
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r2 = r2.addRelation(r3, r4, r0)
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r2.getRelationship()
            java.lang.String r2 = r2.getId()
            return r2
        L_0x0064:
            java.lang.String r2 = r2.getRelationId(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFDocument.addPictureData(byte[], int):java.lang.String");
    }

    public String addPictureData(InputStream inputStream, int i) throws InvalidFormatException {
        try {
            return addPictureData(IOUtils.toByteArrayWithMaxLength(inputStream, XWPFPictureData.getMaxImageSize()), i);
        } catch (IOException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public int getNextPicNameNumber(int i) throws InvalidFormatException {
        int size = getAllPackagePictures().size() + 1;
        PackagePartName createPartName = PackagingURIHelper.createPartName(XWPFPictureData.RELATIONS[i].getFileName(size));
        while (getPackage().getPart(createPartName) != null) {
            size++;
            createPartName = PackagingURIHelper.createPartName(XWPFPictureData.RELATIONS[i].getFileName(size));
        }
        return size;
    }

    public XWPFPictureData getPictureDataByID(String str) {
        POIXMLDocumentPart relationById = getRelationById(str);
        if (relationById instanceof XWPFPictureData) {
            return (XWPFPictureData) relationById;
        }
        return null;
    }

    public XWPFNumbering getNumbering() {
        return this.numbering;
    }

    public XWPFStyles getStyles() {
        return this.styles;
    }

    public XWPFParagraph getParagraph(CTP ctp) {
        for (XWPFParagraph next : this.paragraphs) {
            if (next.getCTP() == ctp) {
                return next;
            }
        }
        return null;
    }

    public XWPFTable getTable(CTTbl cTTbl) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (getTables().get(i).getCTTbl() == cTTbl) {
                return getTables().get(i);
            }
        }
        return null;
    }

    public Iterator<XWPFTable> getTablesIterator() {
        return this.tables.iterator();
    }

    public Spliterator<XWPFTable> getTablesSpliterator() {
        return this.tables.spliterator();
    }

    public Iterator<XWPFParagraph> getParagraphsIterator() {
        return this.paragraphs.iterator();
    }

    public Spliterator<XWPFParagraph> getParagraphsSpliterator() {
        return this.paragraphs.spliterator();
    }

    public XWPFParagraph getParagraphArray(int i) {
        if (i < 0 || i >= this.paragraphs.size()) {
            return null;
        }
        return this.paragraphs.get(i);
    }

    public BodyType getPartType() {
        return BodyType.DOCUMENT;
    }

    public XWPFTableCell getTableCell(CTTc cTTc) {
        XWPFTable table;
        XWPFTableRow row;
        XmlCursor newCursor = cTTc.newCursor();
        try {
            newCursor.toParent();
            XmlObject object = newCursor.getObject();
            if (!(object instanceof CTRow)) {
                return null;
            }
            CTRow cTRow = (CTRow) object;
            newCursor.toParent();
            XmlObject object2 = newCursor.getObject();
            newCursor.dispose();
            if (!(object2 instanceof CTTbl) || (table = getTable((CTTbl) object2)) == null || (row = table.getRow(cTRow)) == null) {
                return null;
            }
            return row.getTableCell(cTTc);
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFChart createChart() throws InvalidFormatException, IOException {
        return createChart(500000, 500000);
    }

    public XWPFChart createChart(int i, int i2) throws InvalidFormatException, IOException {
        return createChart(createParagraph().createRun(), i, i2);
    }

    public XWPFChart createChart(XWPFRun xWPFRun, int i, int i2) throws InvalidFormatException, IOException {
        int nextPartNumber = getNextPartNumber(XWPFRelation.CHART, this.charts.size() + 1);
        POIXMLDocumentPart.RelationPart createRelationship = createRelationship(XWPFRelation.CHART, XWPFFactory.getInstance(), nextPartNumber, false);
        XWPFChart xWPFChart = (XWPFChart) createRelationship.getDocumentPart();
        xWPFChart.setChartIndex(nextPartNumber);
        xWPFChart.attach(createRelationship.getRelationship().getId(), xWPFRun);
        xWPFChart.setChartBoundingBox((long) i, (long) i2);
        this.charts.add(xWPFChart);
        return xWPFChart;
    }

    public XWPFFootnote createFootnote() {
        return createFootnotes().createFootnote();
    }

    public boolean removeFootnote(int i) {
        XWPFFootnotes xWPFFootnotes = this.footnotes;
        if (xWPFFootnotes != null) {
            return xWPFFootnotes.removeFootnote(i);
        }
        return false;
    }

    public XWPFEndnote createEndnote() {
        return createEndnotes().createEndnote();
    }

    public XWPFEndnotes createEndnotes() {
        if (this.endnotes == null) {
            XWPFRelation xWPFRelation = XWPFRelation.ENDNOTE;
            XWPFEndnotes xWPFEndnotes = (XWPFEndnotes) createRelationship(xWPFRelation, XWPFFactory.getInstance(), getRelationIndex(xWPFRelation));
            xWPFEndnotes.setEndnotes(EndnotesDocument.Factory.newInstance().addNewEndnotes());
            xWPFEndnotes.setIdManager(this.footnoteIdManager);
            this.endnotes = xWPFEndnotes;
        }
        return this.endnotes;
    }

    public List<XWPFEndnote> getEndnotes() {
        XWPFEndnotes xWPFEndnotes = this.endnotes;
        if (xWPFEndnotes == null) {
            return Collections.emptyList();
        }
        return xWPFEndnotes.getEndnotesList();
    }

    public boolean removeEndnote(int i) {
        XWPFEndnotes xWPFEndnotes = this.endnotes;
        if (xWPFEndnotes != null) {
            return xWPFEndnotes.removeEndnote(i);
        }
        return false;
    }
}
