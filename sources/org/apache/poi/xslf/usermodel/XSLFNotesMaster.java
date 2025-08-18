package org.apache.poi.xslf.usermodel;

import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument;

public class XSLFNotesMaster extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private CTNotesMaster _slide;

    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRootElementName() {
        return "notesMaster";
    }

    /* access modifiers changed from: package-private */
    public boolean isSupportTheme() {
        return true;
    }

    public /* bridge */ /* synthetic */ SimpleShape getPlaceholder(Placeholder placeholder) {
        return super.getPlaceholder(placeholder);
    }

    XSLFNotesMaster() {
        this._slide = prototype();
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
    protected XSLFNotesMaster(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster r0 = r0.getNotesMaster()     // Catch:{ all -> 0x0021 }
            r2._slide = r0     // Catch:{ all -> 0x0021 }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFNotesMaster.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private static CTNotesMaster prototype() {
        InputStream resourceAsStream = XSLFNotesMaster.class.getResourceAsStream("notesMaster.xml");
        if (resourceAsStream != null) {
            try {
                CTNotesMaster notesMaster = NotesMasterDocument.Factory.parse(resourceAsStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getNotesMaster();
                resourceAsStream.close();
                return notesMaster;
            } catch (Exception e) {
                throw new POIXMLException("Can't initialize NotesMaster", e);
            } catch (Throwable th) {
                resourceAsStream.close();
                throw th;
            }
        } else {
            throw new POIXMLException("Missing resource 'notesMaster.xml'");
        }
    }

    public CTNotesMaster getXmlObject() {
        return this._slide;
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        String mapSchemeColor = mapSchemeColor(this._slide.getClrMap(), str);
        return mapSchemeColor == null ? str : mapSchemeColor;
    }
}
