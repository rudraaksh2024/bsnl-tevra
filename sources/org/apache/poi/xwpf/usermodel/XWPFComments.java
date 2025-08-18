package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments;

public class XWPFComments extends POIXMLDocumentPart {
    private final List<XWPFComment> comments;
    private CTComments ctComments;
    XWPFDocument document;
    private final List<XWPFPictureData> pictures;

    public XWPFComments(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        super(pOIXMLDocumentPart, packagePart);
        this.comments = new ArrayList();
        this.pictures = new ArrayList();
        XWPFDocument xWPFDocument = (XWPFDocument) getParent();
        this.document = xWPFDocument;
        xWPFDocument.getClass();
    }

    public XWPFComments() {
        this.comments = new ArrayList();
        this.pictures = new ArrayList();
        this.ctComments = CTComments.Factory.newInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0065, code lost:
        if (r0 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.getPackagePart()     // Catch:{ XmlException -> 0x0070 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x0070 }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument> r1 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument.Factory     // Catch:{ all -> 0x0062 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0062 }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x0062 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument) r1     // Catch:{ all -> 0x0062 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments r1 = r1.getComments()     // Catch:{ all -> 0x0062 }
            r5.ctComments = r1     // Catch:{ all -> 0x0062 }
            java.util.List r1 = r1.getCommentList()     // Catch:{ all -> 0x0062 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0062 }
        L_0x0020:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0062 }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment r2 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment) r2     // Catch:{ all -> 0x0062 }
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFComment> r3 = r5.comments     // Catch:{ all -> 0x0062 }
            org.apache.poi.xwpf.usermodel.XWPFComment r4 = new org.apache.poi.xwpf.usermodel.XWPFComment     // Catch:{ all -> 0x0062 }
            r4.<init>(r2, r5)     // Catch:{ all -> 0x0062 }
            r3.add(r4)     // Catch:{ all -> 0x0062 }
            goto L_0x0020
        L_0x0037:
            if (r0 == 0) goto L_0x003c
            r0.close()     // Catch:{ XmlException -> 0x0070 }
        L_0x003c:
            java.util.List r0 = r5.getRelations()
            java.util.Iterator r0 = r0.iterator()
        L_0x0044:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0061
            java.lang.Object r1 = r0.next()
            org.apache.poi.ooxml.POIXMLDocumentPart r1 = (org.apache.poi.ooxml.POIXMLDocumentPart) r1
            boolean r2 = r1 instanceof org.apache.poi.xwpf.usermodel.XWPFPictureData
            if (r2 == 0) goto L_0x0044
            org.apache.poi.xwpf.usermodel.XWPFPictureData r1 = (org.apache.poi.xwpf.usermodel.XWPFPictureData) r1
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r2 = r5.pictures
            r2.add(r1)
            org.apache.poi.xwpf.usermodel.XWPFDocument r2 = r5.document
            r2.registerPackagePictureData(r1)
            goto L_0x0044
        L_0x0061:
            return
        L_0x0062:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r1 = move-exception
            if (r0 == 0) goto L_0x006f
            r0.close()     // Catch:{ all -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ XmlException -> 0x0070 }
        L_0x006f:
            throw r1     // Catch:{ XmlException -> 0x0070 }
        L_0x0070:
            r5 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            java.lang.String r1 = "Unable to read comments"
            r0.<init>(r1, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFComments.onDocumentRead():void");
    }

    public String addPictureData(InputStream inputStream, int i) throws InvalidFormatException, IOException {
        return addPictureData(IOUtils.toByteArrayWithMaxLength(inputStream, XWPFPictureData.getMaxImageSize()), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r0 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String addPictureData(byte[] r3, int r4) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r2 = this;
            org.apache.poi.xwpf.usermodel.XWPFDocument r0 = r2.document
            org.apache.poi.xwpf.usermodel.XWPFPictureData r0 = r0.findPackagePictureData(r3, r4)
            org.apache.poi.ooxml.POIXMLRelation[] r1 = org.apache.poi.xwpf.usermodel.XWPFPictureData.RELATIONS
            r1 = r1[r4]
            if (r0 != 0) goto L_0x0052
            org.apache.poi.xwpf.usermodel.XWPFDocument r0 = r2.getXWPFDocument()
            int r4 = r0.getNextPicNameNumber(r4)
            org.apache.poi.xwpf.usermodel.XWPFFactory r0 = org.apache.poi.xwpf.usermodel.XWPFFactory.getInstance()
            org.apache.poi.ooxml.POIXMLDocumentPart r4 = r2.createRelationship(r1, r0, r4)
            org.apache.poi.xwpf.usermodel.XWPFPictureData r4 = (org.apache.poi.xwpf.usermodel.XWPFPictureData) r4
            org.apache.poi.openxml4j.opc.PackagePart r0 = r4.getPackagePart()
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ IOException -> 0x004b }
            r0.write(r3)     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x002e
            r0.close()     // Catch:{ IOException -> 0x004b }
        L_0x002e:
            org.apache.poi.xwpf.usermodel.XWPFDocument r3 = r2.document
            r3.registerPackagePictureData(r4)
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r3 = r2.pictures
            r3.add(r4)
            java.lang.String r2 = r2.getRelationId(r4)
            return r2
        L_0x003d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x003f }
        L_0x003f:
            r3 = move-exception
            if (r0 == 0) goto L_0x004a
            r0.close()     // Catch:{ all -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x004b }
        L_0x004a:
            throw r3     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            r2 = move-exception
            org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
            r3.<init>((java.lang.Throwable) r2)
            throw r3
        L_0x0052:
            java.util.List r3 = r2.getRelations()
            boolean r3 = r3.contains(r0)
            if (r3 != 0) goto L_0x0071
            r3 = 0
            org.apache.poi.xwpf.usermodel.XWPFRelation r4 = org.apache.poi.xwpf.usermodel.XWPFRelation.IMAGES
            org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r3 = r2.addRelation(r3, r4, r0)
            java.util.List<org.apache.poi.xwpf.usermodel.XWPFPictureData> r2 = r2.pictures
            r2.add(r0)
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = r3.getRelationship()
            java.lang.String r2 = r2.getId()
            return r2
        L_0x0071:
            java.lang.String r2 = r2.getRelationId(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFComments.addPictureData(byte[], int):java.lang.String");
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
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "comments"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments r4 = r4.ctComments     // Catch:{ all -> 0x002e }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFComments.commit():void");
    }

    public List<XWPFPictureData> getAllPictures() {
        return Collections.unmodifiableList(this.pictures);
    }

    public CTComments getCtComments() {
        return this.ctComments;
    }

    @Internal
    public void setCtComments(CTComments cTComments) {
        this.ctComments = cTComments;
    }

    public List<XWPFComment> getComments() {
        return this.comments;
    }

    public XWPFComment getComment(int i) {
        if (i < 0 || i >= this.ctComments.sizeOfCommentArray()) {
            return null;
        }
        return getComments().get(i);
    }

    public XWPFComment getCommentByID(String str) {
        for (XWPFComment next : this.comments) {
            if (next.getId().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public XWPFComment getComment(CTComment cTComment) {
        for (XWPFComment next : this.comments) {
            if (next.getCtComment() == cTComment) {
                return next;
            }
        }
        return null;
    }

    public XWPFComment createComment(BigInteger bigInteger) {
        CTComment addNewComment = this.ctComments.addNewComment();
        addNewComment.setId(bigInteger);
        XWPFComment xWPFComment = new XWPFComment(addNewComment, this);
        this.comments.add(xWPFComment);
        return xWPFComment;
    }

    public boolean removeComment(int i) {
        if (i < 0 || i >= this.ctComments.sizeOfCommentArray()) {
            return false;
        }
        this.comments.remove(i);
        this.ctComments.removeComment(i);
        return true;
    }

    public XWPFDocument getXWPFDocument() {
        XWPFDocument xWPFDocument = this.document;
        if (xWPFDocument != null) {
            return xWPFDocument;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument xWPFDocument) {
        this.document = xWPFDocument;
    }
}
