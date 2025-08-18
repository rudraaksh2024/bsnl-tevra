package org.apache.poi.xdgf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.PackageHelper;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XmlVisioDocument extends POIXMLDocument {
    protected XDGFDocument _document;
    protected XDGFMasters _masters;
    protected XDGFPages _pages;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        if (r2 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XmlVisioDocument(org.apache.poi.openxml4j.opc.OPCPackage r2) throws java.io.IOException {
        /*
            r1 = this;
            java.lang.String r0 = "http://schemas.microsoft.com/visio/2010/relationships/document"
            r1.<init>(r2, r0)
            org.apache.poi.openxml4j.opc.PackagePart r2 = r1.getPackagePart()     // Catch:{ IOException | XmlException -> 0x003e }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException | XmlException -> 0x003e }
            org.apache.xmlbeans.impl.schema.DocumentFactory<com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1> r0 = com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1.Factory     // Catch:{ all -> 0x0030 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r2)     // Catch:{ all -> 0x0030 }
            com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1 r0 = (com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1) r0     // Catch:{ all -> 0x0030 }
            com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType r0 = r0.getVisioDocument()     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x001e
            r2.close()     // Catch:{ IOException | XmlException -> 0x003e }
        L_0x001e:
            org.apache.poi.xdgf.usermodel.XDGFDocument r2 = new org.apache.poi.xdgf.usermodel.XDGFDocument
            r2.<init>(r0)
            r1._document = r2
            org.apache.poi.xdgf.usermodel.XDGFFactory r2 = new org.apache.poi.xdgf.usermodel.XDGFFactory
            org.apache.poi.xdgf.usermodel.XDGFDocument r0 = r1._document
            r2.<init>(r0)
            r1.load(r2)
            return
        L_0x0030:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            if (r2 == 0) goto L_0x003d
            r2.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch:{ IOException | XmlException -> 0x003e }
        L_0x003d:
            throw r0     // Catch:{ IOException | XmlException -> 0x003e }
        L_0x003e:
            r1 = move-exception
            org.apache.poi.ooxml.POIXMLException r2 = new org.apache.poi.ooxml.POIXMLException
            r2.<init>((java.lang.Throwable) r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XmlVisioDocument.<init>(org.apache.poi.openxml4j.opc.OPCPackage):void");
    }

    public XmlVisioDocument(InputStream inputStream) throws IOException {
        this(PackageHelper.open(inputStream));
    }

    /* access modifiers changed from: protected */
    public void onDocumentRead() {
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XDGFPages) {
                this._pages = (XDGFPages) next;
            } else if (next instanceof XDGFMasters) {
                this._masters = (XDGFMasters) next;
            }
        }
        XDGFMasters xDGFMasters = this._masters;
        if (xDGFMasters != null) {
            xDGFMasters.onDocumentRead();
        }
        XDGFPages xDGFPages = this._pages;
        if (xDGFPages != null) {
            xDGFPages.onDocumentRead();
        }
    }

    public List<PackagePart> getAllEmbeddedParts() {
        return new ArrayList();
    }

    public Collection<XDGFPage> getPages() {
        return this._pages.getPageList();
    }

    public XDGFStyleSheet getStyleById(long j) {
        return this._document.getStyleById(j);
    }
}
