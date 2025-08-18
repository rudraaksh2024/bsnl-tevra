package org.apache.poi.xslf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.usermodel.Notes;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;

public final class XSLFNotes extends XSLFSheet implements Notes<XSLFShape, XSLFTextParagraph> {
    private CTNotesSlide _notes;

    /* access modifiers changed from: protected */
    public String getRootElementName() {
        return "notes";
    }

    XSLFNotes() {
        this._notes = prototype();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r3 != null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XSLFNotes(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide r0 = r0.getNotes()     // Catch:{ all -> 0x0021 }
            r2._notes = r0     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x0020
            r3.close()
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r0 = move-exception
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFNotes.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private static CTNotesSlide prototype() {
        CTNotesSlide newInstance = CTNotesSlide.Factory.newInstance();
        newInstance.addNewCSld().addNewSpTree();
        return newInstance;
    }

    public CTNotesSlide getXmlObject() {
        return this._notes;
    }

    public XSLFTheme getTheme() {
        XSLFNotesMaster masterSheet = getMasterSheet();
        if (masterSheet != null) {
            return masterSheet.getTheme();
        }
        return null;
    }

    public XSLFNotesMaster getMasterSheet() {
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XSLFNotesMaster) {
                return (XSLFNotesMaster) next;
            }
        }
        return null;
    }

    public List<List<XSLFTextParagraph>> getTextParagraphs() {
        ArrayList arrayList = new ArrayList();
        for (XSLFShape next : super.getShapes()) {
            if (next instanceof XSLFTextShape) {
                arrayList.add(((XSLFTextShape) next).getTextParagraphs());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        return mapSchemeColor(this._notes.getClrMapOvr(), str);
    }
}
