package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFtnEdn;

public class XWPFEndnotes extends XWPFAbstractFootnotesEndnotes {
    protected CTEndnotes ctEndnotes;

    public XWPFEndnotes() {
    }

    public XWPFEndnotes(PackagePart packagePart) {
        super(packagePart);
    }

    @Internal
    public void setEndnotes(CTEndnotes cTEndnotes) {
        this.ctEndnotes = cTEndnotes;
    }

    public XWPFEndnote createEndnote() {
        CTFtnEdn newInstance = CTFtnEdn.Factory.newInstance();
        newInstance.setType(STFtnEdn.NORMAL);
        XWPFEndnote addEndnote = addEndnote(newInstance);
        addEndnote.getCTFtnEdn().setId(getIdManager().nextId());
        return addEndnote;
    }

    public boolean removeFootnote(int i) {
        if (this.ctEndnotes.sizeOfEndnoteArray() < i - 1) {
            return false;
        }
        this.ctEndnotes.removeEndnote(i);
        this.listFootnote.remove(i);
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r0 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r4.getPackagePart()     // Catch:{ XmlException -> 0x004d }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x004d }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument> r1 = org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument.Factory     // Catch:{ all -> 0x003f }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x003f }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x003f }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument) r1     // Catch:{ all -> 0x003f }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes r1 = r1.getEndnotes()     // Catch:{ all -> 0x003f }
            r4.ctEndnotes = r1     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x001d
            r0.close()     // Catch:{ XmlException -> 0x004d }
        L_0x001d:
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes r0 = r4.ctEndnotes
            java.util.List r0 = r0.getEndnoteList()
            java.util.Iterator r0 = r0.iterator()
        L_0x0027:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x003e
            java.lang.Object r1 = r0.next()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn) r1
            java.util.List r2 = r4.listFootnote
            org.apache.poi.xwpf.usermodel.XWPFEndnote r3 = new org.apache.poi.xwpf.usermodel.XWPFEndnote
            r3.<init>((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn) r1, (org.apache.poi.xwpf.usermodel.XWPFAbstractFootnotesEndnotes) r4)
            r2.add(r3)
            goto L_0x0027
        L_0x003e:
            return
        L_0x003f:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r1 = move-exception
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ all -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r0 = move-exception
            r4.addSuppressed(r0)     // Catch:{ XmlException -> 0x004d }
        L_0x004c:
            throw r1     // Catch:{ XmlException -> 0x004d }
        L_0x004d:
            org.apache.poi.ooxml.POIXMLException r4 = new org.apache.poi.ooxml.POIXMLException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFEndnotes.onDocumentRead():void");
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
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "endnotes"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes r4 = r4.ctEndnotes     // Catch:{ all -> 0x002e }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFEndnotes.commit():void");
    }

    public void addEndnote(XWPFEndnote xWPFEndnote) {
        this.listFootnote.add(xWPFEndnote);
        this.ctEndnotes.addNewEndnote().set(xWPFEndnote.getCTFtnEdn());
    }

    @Internal
    public XWPFEndnote addEndnote(CTFtnEdn cTFtnEdn) {
        CTFtnEdn addNewEndnote = this.ctEndnotes.addNewEndnote();
        addNewEndnote.set(cTFtnEdn);
        XWPFEndnote xWPFEndnote = new XWPFEndnote(addNewEndnote, (XWPFAbstractFootnotesEndnotes) this);
        this.listFootnote.add(xWPFEndnote);
        return xWPFEndnote;
    }

    public XWPFEndnote getFootnoteById(int i) {
        return (XWPFEndnote) super.getFootnoteById(i);
    }

    public List<XWPFEndnote> getEndnotesList() {
        ArrayList arrayList = new ArrayList();
        for (XWPFAbstractFootnoteEndnote xWPFAbstractFootnoteEndnote : this.listFootnote) {
            arrayList.add((XWPFEndnote) xWPFAbstractFootnoteEndnote);
        }
        return arrayList;
    }

    public boolean removeEndnote(int i) {
        if (this.ctEndnotes.sizeOfEndnoteArray() < i - 1) {
            return false;
        }
        this.ctEndnotes.removeEndnote(i);
        this.listFootnote.remove(i);
        return true;
    }
}
