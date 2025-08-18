package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument;

public class SingleXmlCells extends POIXMLDocumentPart {
    private CTSingleXmlCells singleXMLCells;

    public SingleXmlCells() {
        this.singleXMLCells = CTSingleXmlCells.Factory.newInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SingleXmlCells(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
        /*
            r1 = this;
            r1.<init>((org.apache.poi.openxml4j.opc.PackagePart) r2)
            java.io.InputStream r2 = r2.getInputStream()
            r1.readFrom(r2)     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x000f
            r2.close()
        L_0x000f:
            return
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.SingleXmlCells.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.singleXMLCells = SingleXmlCellsDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getSingleXmlCells();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFSheet getXSSFSheet() {
        return (XSSFSheet) getParent();
    }

    /* access modifiers changed from: protected */
    public void writeTo(OutputStream outputStream) throws IOException {
        SingleXmlCellsDocument newInstance = SingleXmlCellsDocument.Factory.newInstance();
        newInstance.setSingleXmlCells(this.singleXMLCells);
        newInstance.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
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
            r2.writeTo(r0)     // Catch:{ all -> 0x0011 }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.SingleXmlCells.commit():void");
    }

    public CTSingleXmlCells getCTSingleXMLCells() {
        return this.singleXMLCells;
    }

    public List<XSSFSingleXmlCell> getAllSimpleXmlCell() {
        Vector vector = new Vector();
        for (CTSingleXmlCell xSSFSingleXmlCell : this.singleXMLCells.getSingleXmlCellArray()) {
            vector.add(new XSSFSingleXmlCell(xSSFSingleXmlCell, this));
        }
        return vector;
    }
}
