package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;

public class XWPFNumbering extends POIXMLDocumentPart {
    protected List<XWPFAbstractNum> abstractNums;
    private CTNumbering ctNumbering;
    boolean isNew;
    protected List<XWPFNum> nums;

    public XWPFNumbering(PackagePart packagePart) {
        super(packagePart);
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.isNew = true;
    }

    public XWPFNumbering() {
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.isNew = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004f, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
        throw new org.apache.poi.ooxml.POIXMLException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005a, code lost:
        throw r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r8 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r8.getPackagePart()
            java.io.InputStream r0 = r0.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument> r1 = org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument.Factory     // Catch:{ XmlException -> 0x0051 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ XmlException -> 0x0051 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ XmlException -> 0x0051 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument) r1     // Catch:{ XmlException -> 0x0051 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering r1 = r1.getNumbering()     // Catch:{ XmlException -> 0x0051 }
            r8.ctNumbering = r1     // Catch:{ XmlException -> 0x0051 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum[] r1 = r1.getNumArray()     // Catch:{ XmlException -> 0x0051 }
            int r2 = r1.length     // Catch:{ XmlException -> 0x0051 }
            r3 = 0
            r4 = r3
        L_0x001f:
            if (r4 >= r2) goto L_0x0030
            r5 = r1[r4]     // Catch:{ XmlException -> 0x0051 }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFNum> r6 = r8.nums     // Catch:{ XmlException -> 0x0051 }
            org.apache.poi.xwpf.usermodel.XWPFNum r7 = new org.apache.poi.xwpf.usermodel.XWPFNum     // Catch:{ XmlException -> 0x0051 }
            r7.<init>(r5, r8)     // Catch:{ XmlException -> 0x0051 }
            r6.add(r7)     // Catch:{ XmlException -> 0x0051 }
            int r4 = r4 + 1
            goto L_0x001f
        L_0x0030:
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering r1 = r8.ctNumbering     // Catch:{ XmlException -> 0x0051 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum[] r1 = r1.getAbstractNumArray()     // Catch:{ XmlException -> 0x0051 }
            int r2 = r1.length     // Catch:{ XmlException -> 0x0051 }
            r4 = r3
        L_0x0038:
            if (r4 >= r2) goto L_0x0049
            r5 = r1[r4]     // Catch:{ XmlException -> 0x0051 }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFAbstractNum> r6 = r8.abstractNums     // Catch:{ XmlException -> 0x0051 }
            org.apache.poi.xwpf.usermodel.XWPFAbstractNum r7 = new org.apache.poi.xwpf.usermodel.XWPFAbstractNum     // Catch:{ XmlException -> 0x0051 }
            r7.<init>(r5, r8)     // Catch:{ XmlException -> 0x0051 }
            r6.add(r7)     // Catch:{ XmlException -> 0x0051 }
            int r4 = r4 + 1
            goto L_0x0038
        L_0x0049:
            r8.isNew = r3     // Catch:{ XmlException -> 0x0051 }
            r0.close()
            return
        L_0x004f:
            r8 = move-exception
            goto L_0x0057
        L_0x0051:
            org.apache.poi.ooxml.POIXMLException r8 = new org.apache.poi.ooxml.POIXMLException     // Catch:{ all -> 0x004f }
            r8.<init>()     // Catch:{ all -> 0x004f }
            throw r8     // Catch:{ all -> 0x004f }
        L_0x0057:
            r0.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFNumbering.onDocumentRead():void");
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
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "numbering"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering r4 = r4.ctNumbering     // Catch:{ all -> 0x002e }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFNumbering.commit():void");
    }

    public void setNumbering(CTNumbering cTNumbering) {
        this.ctNumbering = cTNumbering;
    }

    public boolean numExist(BigInteger bigInteger) {
        for (XWPFNum cTNum : this.nums) {
            if (cTNum.getCTNum().getNumId().equals(bigInteger)) {
                return true;
            }
        }
        return false;
    }

    public BigInteger addNum(XWPFNum xWPFNum) {
        this.ctNumbering.addNewNum();
        this.ctNumbering.setNumArray(this.ctNumbering.sizeOfNumArray() - 1, xWPFNum.getCTNum());
        this.nums.add(xWPFNum);
        return xWPFNum.getCTNum().getNumId();
    }

    public BigInteger addNum(BigInteger bigInteger) {
        CTNum addNewNum = this.ctNumbering.addNewNum();
        addNewNum.addNewAbstractNumId();
        addNewNum.getAbstractNumId().setVal(bigInteger);
        addNewNum.setNumId(BigInteger.valueOf(((long) this.nums.size()) + 1));
        this.nums.add(new XWPFNum(addNewNum, this));
        return addNewNum.getNumId();
    }

    public void addNum(BigInteger bigInteger, BigInteger bigInteger2) {
        CTNum addNewNum = this.ctNumbering.addNewNum();
        addNewNum.addNewAbstractNumId();
        addNewNum.getAbstractNumId().setVal(bigInteger);
        addNewNum.setNumId(bigInteger2);
        this.nums.add(new XWPFNum(addNewNum, this));
    }

    public XWPFNum getNum(BigInteger bigInteger) {
        for (XWPFNum next : this.nums) {
            if (next.getCTNum().getNumId().equals(bigInteger)) {
                return next;
            }
        }
        return null;
    }

    public XWPFAbstractNum getAbstractNum(BigInteger bigInteger) {
        for (XWPFAbstractNum next : this.abstractNums) {
            if (next.getAbstractNum().getAbstractNumId().equals(bigInteger)) {
                return next;
            }
        }
        return null;
    }

    public BigInteger getIdOfAbstractNum(XWPFAbstractNum xWPFAbstractNum) {
        XWPFAbstractNum xWPFAbstractNum2 = new XWPFAbstractNum((CTAbstractNum) xWPFAbstractNum.getCTAbstractNum().copy(), this);
        for (int i = 0; i < this.abstractNums.size(); i++) {
            xWPFAbstractNum2.getCTAbstractNum().setAbstractNumId(BigInteger.valueOf((long) i));
            xWPFAbstractNum2.setNumbering(this);
            if (xWPFAbstractNum2.getCTAbstractNum().valueEquals(this.abstractNums.get(i).getCTAbstractNum())) {
                return xWPFAbstractNum2.getCTAbstractNum().getAbstractNumId();
            }
        }
        return null;
    }

    public BigInteger addAbstractNum(XWPFAbstractNum xWPFAbstractNum) {
        int size = this.abstractNums.size();
        if (xWPFAbstractNum.getAbstractNum() != null) {
            this.ctNumbering.addNewAbstractNum().set(xWPFAbstractNum.getAbstractNum());
        } else {
            xWPFAbstractNum.setCtAbstractNum(this.ctNumbering.addNewAbstractNum());
            xWPFAbstractNum.getAbstractNum().setAbstractNumId(BigInteger.valueOf((long) size));
            this.ctNumbering.setAbstractNumArray(size, xWPFAbstractNum.getAbstractNum());
        }
        this.abstractNums.add(xWPFAbstractNum);
        return xWPFAbstractNum.getCTAbstractNum().getAbstractNumId();
    }

    public boolean removeAbstractNum(BigInteger bigInteger) {
        for (XWPFAbstractNum next : this.abstractNums) {
            BigInteger abstractNumId = next.getAbstractNum().getAbstractNumId();
            if (bigInteger.equals(abstractNumId)) {
                this.ctNumbering.removeAbstractNum(abstractNumId.byteValue());
                this.abstractNums.remove(next);
                return true;
            }
        }
        return false;
    }

    public BigInteger getAbstractNumID(BigInteger bigInteger) {
        XWPFNum num = getNum(bigInteger);
        if (num == null || num.getCTNum() == null || num.getCTNum().getAbstractNumId() == null) {
            return null;
        }
        return num.getCTNum().getAbstractNumId().getVal();
    }

    public List<XWPFAbstractNum> getAbstractNums() {
        return Collections.unmodifiableList(this.abstractNums);
    }

    public List<XWPFNum> getNums() {
        return Collections.unmodifiableList(this.nums);
    }
}
