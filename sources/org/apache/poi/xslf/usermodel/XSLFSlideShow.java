package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument;

public class XSLFSlideShow extends POIXMLDocument {
    private final List<PackagePart> embeddedParts;
    private final PresentationDocument presentationDoc;

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ab, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ac, code lost:
        if (r8 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b2, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b3, code lost:
        r7.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b6, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSLFSlideShow(org.apache.poi.openxml4j.opc.OPCPackage r8) throws org.apache.poi.openxml4j.exceptions.OpenXML4JException, java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r7 = this;
            r7.<init>(r8)
            org.apache.poi.openxml4j.opc.PackagePart r8 = r7.getCorePart()
            java.lang.String r8 = r8.getContentType()
            org.apache.poi.xslf.usermodel.XSLFRelation r0 = org.apache.poi.xslf.usermodel.XSLFRelation.THEME_MANAGER
            java.lang.String r0 = r0.getContentType()
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x001e
            org.apache.poi.openxml4j.opc.OPCPackage r8 = r7.getPackage()
            r7.rebase(r8)
        L_0x001e:
            org.apache.poi.openxml4j.opc.PackagePart r8 = r7.getCorePart()
            java.io.InputStream r8 = r8.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument.Factory     // Catch:{ all -> 0x00a9 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00a9 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r8, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x00a9 }
            org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument) r0     // Catch:{ all -> 0x00a9 }
            r7.presentationDoc = r0     // Catch:{ all -> 0x00a9 }
            if (r8 == 0) goto L_0x0037
            r8.close()
        L_0x0037:
            java.util.LinkedList r8 = new java.util.LinkedList
            r8.<init>()
            r7.embeddedParts = r8
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList r8 = r7.getSlideReferences()
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry[] r8 = r8.getSldIdArray()
            int r0 = r8.length
            r1 = 0
        L_0x0048:
            if (r1 >= r0) goto L_0x00a8
            r2 = r8[r1]
            org.apache.poi.openxml4j.opc.PackagePart r3 = r7.getCorePart()
            java.lang.String r2 = r2.getId2()
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r3.getRelationship(r2)
            org.apache.poi.openxml4j.opc.PackagePart r2 = r3.getRelatedPart(r2)
            java.lang.String r3 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject"
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r3 = r2.getRelationshipsByType(r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x0066:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r3.next()
            org.apache.poi.openxml4j.opc.PackageRelationship r4 = (org.apache.poi.openxml4j.opc.PackageRelationship) r4
            org.apache.poi.openxml4j.opc.TargetMode r5 = org.apache.poi.openxml4j.opc.TargetMode.EXTERNAL
            org.apache.poi.openxml4j.opc.TargetMode r6 = r4.getTargetMode()
            if (r5 != r6) goto L_0x007b
            goto L_0x0066
        L_0x007b:
            java.util.List<org.apache.poi.openxml4j.opc.PackagePart> r5 = r7.embeddedParts
            org.apache.poi.openxml4j.opc.PackagePart r4 = r2.getRelatedPart(r4)
            r5.add(r4)
            goto L_0x0066
        L_0x0085:
            java.lang.String r3 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/package"
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r3 = r2.getRelationshipsByType(r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x008f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00a5
            java.lang.Object r4 = r3.next()
            org.apache.poi.openxml4j.opc.PackageRelationship r4 = (org.apache.poi.openxml4j.opc.PackageRelationship) r4
            java.util.List<org.apache.poi.openxml4j.opc.PackagePart> r5 = r7.embeddedParts
            org.apache.poi.openxml4j.opc.PackagePart r4 = r2.getRelatedPart(r4)
            r5.add(r4)
            goto L_0x008f
        L_0x00a5:
            int r1 = r1 + 1
            goto L_0x0048
        L_0x00a8:
            return
        L_0x00a9:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x00ab }
        L_0x00ab:
            r0 = move-exception
            if (r8 == 0) goto L_0x00b6
            r8.close()     // Catch:{ all -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r8 = move-exception
            r7.addSuppressed(r8)
        L_0x00b6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShow.<init>(org.apache.poi.openxml4j.opc.OPCPackage):void");
    }

    public XSLFSlideShow(String str) throws OpenXML4JException, IOException, XmlException {
        this(openPackage(str));
    }

    @Internal
    public CTPresentation getPresentation() {
        return this.presentationDoc.getPresentation();
    }

    @Internal
    public CTSlideIdList getSlideReferences() {
        if (!getPresentation().isSetSldIdLst()) {
            getPresentation().setSldIdLst(CTSlideIdList.Factory.newInstance());
        }
        return getPresentation().getSldIdLst();
    }

    @Internal
    public CTSlideMasterIdList getSlideMasterReferences() {
        return getPresentation().getSldMasterIdLst();
    }

    public PackagePart getSlideMasterPart(CTSlideMasterIdListEntry cTSlideMasterIdListEntry) throws IOException, XmlException {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(cTSlideMasterIdListEntry.getId2()));
        } catch (InvalidFormatException e) {
            throw new XmlException((Throwable) e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r0 = move-exception;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster getSlideMaster(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry r2) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r1 = this;
            org.apache.poi.openxml4j.opc.PackagePart r1 = r1.getSlideMasterPart(r2)
            java.io.InputStream r1 = r1.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument> r2 = org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument.Factory     // Catch:{ all -> 0x001c }
            org.apache.xmlbeans.XmlOptions r0 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x001c }
            java.lang.Object r2 = r2.parse((java.io.InputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x001c }
            org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument r2 = (org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument) r2     // Catch:{ all -> 0x001c }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster r2 = r2.getSldMaster()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001b
            r1.close()
        L_0x001b:
            return r2
        L_0x001c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001e }
        L_0x001e:
            r0 = move-exception
            if (r1 == 0) goto L_0x0029
            r1.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0029:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShow.getSlideMaster(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry):org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster");
    }

    public PackagePart getSlidePart(CTSlideIdListEntry cTSlideIdListEntry) throws IOException, XmlException {
        try {
            PackagePart corePart = getCorePart();
            return corePart.getRelatedPart(corePart.getRelationship(cTSlideIdListEntry.getId2()));
        } catch (InvalidFormatException e) {
            throw new XmlException((Throwable) e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r0 = move-exception;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.presentationml.x2006.main.CTSlide getSlide(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry r2) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r1 = this;
            org.apache.poi.openxml4j.opc.PackagePart r1 = r1.getSlidePart(r2)
            java.io.InputStream r1 = r1.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.SldDocument> r2 = org.openxmlformats.schemas.presentationml.x2006.main.SldDocument.Factory     // Catch:{ all -> 0x001c }
            org.apache.xmlbeans.XmlOptions r0 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x001c }
            java.lang.Object r2 = r2.parse((java.io.InputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x001c }
            org.openxmlformats.schemas.presentationml.x2006.main.SldDocument r2 = (org.openxmlformats.schemas.presentationml.x2006.main.SldDocument) r2     // Catch:{ all -> 0x001c }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlide r2 = r2.getSld()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001b
            r1.close()
        L_0x001b:
            return r2
        L_0x001c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001e }
        L_0x001e:
            r0 = move-exception
            if (r1 == 0) goto L_0x0029
            r1.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0029:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShow.getSlide(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry):org.openxmlformats.schemas.presentationml.x2006.main.CTSlide");
    }

    public PackagePart getNodesPart(CTSlideIdListEntry cTSlideIdListEntry) throws IOException, XmlException {
        PackagePart slidePart = getSlidePart(cTSlideIdListEntry);
        try {
            PackageRelationshipCollection relationshipsByType = slidePart.getRelationshipsByType(XSLFRelation.NOTES.getRelation());
            if (relationshipsByType.isEmpty()) {
                return null;
            }
            if (relationshipsByType.size() <= 1) {
                try {
                    return slidePart.getRelatedPart(relationshipsByType.getRelationship(0));
                } catch (InvalidFormatException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalStateException("Expecting 0 or 1 notes for a slide, but found " + relationshipsByType.size());
            }
        } catch (InvalidFormatException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        if (r1 != null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        throw r0;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide getNotes(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry r2) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r1 = this;
            org.apache.poi.openxml4j.opc.PackagePart r1 = r1.getNodesPart(r2)
            if (r1 != 0) goto L_0x0008
            r1 = 0
            return r1
        L_0x0008:
            java.io.InputStream r1 = r1.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument> r2 = org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument.Factory     // Catch:{ all -> 0x0020 }
            org.apache.xmlbeans.XmlOptions r0 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0020 }
            java.lang.Object r2 = r2.parse((java.io.InputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0020 }
            org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument r2 = (org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument) r2     // Catch:{ all -> 0x0020 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide r2 = r2.getNotes()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001f
            r1.close()
        L_0x001f:
            return r2
        L_0x0020:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r0 = move-exception
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ all -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x002d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShow.getNotes(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry):org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        if (r2 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        throw r0;
     */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList getSlideComments(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.getSlidePart(r3)
            org.apache.poi.xslf.usermodel.XSLFRelation r3 = org.apache.poi.xslf.usermodel.XSLFRelation.COMMENTS     // Catch:{ InvalidFormatException -> 0x006c }
            java.lang.String r3 = r3.getRelation()     // Catch:{ InvalidFormatException -> 0x006c }
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r3 = r2.getRelationshipsByType(r3)     // Catch:{ InvalidFormatException -> 0x006c }
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0016
            r2 = 0
            return r2
        L_0x0016:
            int r0 = r3.size()
            r1 = 1
            if (r0 > r1) goto L_0x0053
            r0 = 0
            org.apache.poi.openxml4j.opc.PackageRelationship r3 = r3.getRelationship(r0)     // Catch:{ InvalidFormatException -> 0x004c }
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.getRelatedPart(r3)     // Catch:{ InvalidFormatException -> 0x004c }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ InvalidFormatException -> 0x004c }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument> r3 = org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument.Factory     // Catch:{ all -> 0x003e }
            org.apache.xmlbeans.XmlOptions r0 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x003e }
            java.lang.Object r3 = r3.parse((java.io.InputStream) r2, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x003e }
            org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument r3 = (org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument) r3     // Catch:{ all -> 0x003e }
            org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList r3 = r3.getCmLst()     // Catch:{ all -> 0x003e }
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ InvalidFormatException -> 0x004c }
        L_0x003d:
            return r3
        L_0x003e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r0 = move-exception
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ InvalidFormatException -> 0x004c }
        L_0x004b:
            throw r0     // Catch:{ InvalidFormatException -> 0x004c }
        L_0x004c:
            r2 = move-exception
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r2)
            throw r3
        L_0x0053:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Expecting 0 or 1 comments for a slide, but found "
            r0.<init>(r1)
            int r3 = r3.size()
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x006c:
            r2 = move-exception
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShow.getSlideComments(org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry):org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList");
    }

    public List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException {
        return this.embeddedParts;
    }
}
