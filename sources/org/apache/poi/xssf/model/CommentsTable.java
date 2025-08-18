package org.apache.poi.xssf.model;

import com.microsoft.schemas.vml.CTShape;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.OoxmlSheetExtensions;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFVMLDrawing;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CommentsDocument;

@Internal
public class CommentsTable extends POIXMLDocumentPart implements Comments {
    public static final String DEFAULT_AUTHOR = "";
    public static final int DEFAULT_AUTHOR_ID = 0;
    private Map<CellAddress, CTComment> commentRefs;
    private CTComments comments;
    private Sheet sheet;
    private XSSFVMLDrawing vmlDrawing;

    public void commentUpdated(XSSFComment xSSFComment) {
    }

    public CommentsTable() {
        CTComments newInstance = CTComments.Factory.newInstance();
        this.comments = newInstance;
        newInstance.addNewCommentList();
        this.comments.addNewAuthors().addAuthor("");
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
    public CommentsTable(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.CommentsTable.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void readFrom(InputStream inputStream) throws IOException {
        try {
            this.comments = CommentsDocument.Factory.parse(inputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getComments();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        CommentsDocument newInstance = CommentsDocument.Factory.newInstance();
        newInstance.setComments(this.comments);
        newInstance.save(outputStream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Internal
    public void setSheet(Sheet sheet2) {
        this.sheet = sheet2;
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.model.CommentsTable.commit():void");
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public void referenceUpdated(CellAddress cellAddress, CTComment cTComment) {
        Map<CellAddress, CTComment> map = this.commentRefs;
        if (map != null) {
            map.remove(cellAddress);
            this.commentRefs.put(new CellAddress(cTComment.getRef()), cTComment);
        }
    }

    public void referenceUpdated(CellAddress cellAddress, XSSFComment xSSFComment) {
        Map<CellAddress, CTComment> map = this.commentRefs;
        if (map != null) {
            map.remove(cellAddress);
            this.commentRefs.put(xSSFComment.getAddress(), xSSFComment.getCTComment());
        }
    }

    public int getNumberOfComments() {
        return this.comments.getCommentList().sizeOfCommentArray();
    }

    public int getNumberOfAuthors() {
        return this.comments.getAuthors().sizeOfAuthorArray();
    }

    public String getAuthor(long j) {
        return this.comments.getAuthors().getAuthorArray(Math.toIntExact(j));
    }

    public int findAuthor(String str) {
        String[] authorArray = this.comments.getAuthors().getAuthorArray();
        for (int i = 0; i < authorArray.length; i++) {
            if (authorArray[i].equals(str)) {
                return i;
            }
        }
        return addNewAuthor(str);
    }

    public XSSFComment findCellComment(CellAddress cellAddress) {
        CTComment cTComment = getCTComment(cellAddress);
        CTShape cTShape = null;
        if (cTComment == null) {
            return null;
        }
        XSSFVMLDrawing vMLDrawing = getVMLDrawing(this.sheet, false);
        if (vMLDrawing != null) {
            cTShape = vMLDrawing.findCommentShape(cellAddress.getRow(), cellAddress.getColumn());
        }
        return new XSSFComment(this, cTComment, cTShape);
    }

    /* access modifiers changed from: package-private */
    @Internal
    public CTComment getCTComment(CellAddress cellAddress) {
        prepareCTCommentCache();
        return this.commentRefs.get(cellAddress);
    }

    public Iterator<CellAddress> getCellAddresses() {
        prepareCTCommentCache();
        return this.commentRefs.keySet().iterator();
    }

    public XSSFComment createNewComment(ClientAnchor clientAnchor) {
        CTShape cTShape;
        XSSFVMLDrawing vMLDrawing = getVMLDrawing(this.sheet, true);
        if (vMLDrawing == null) {
            cTShape = null;
        } else {
            cTShape = vMLDrawing.newCommentShape();
        }
        if (cTShape != null && (clientAnchor instanceof XSSFClientAnchor) && ((XSSFClientAnchor) clientAnchor).isSet()) {
            cTShape.getClientDataArray(0).setAnchorArray(0, clientAnchor.getCol1() + ", " + (clientAnchor.getDx1() / 9525) + ", " + clientAnchor.getRow1() + ", " + (clientAnchor.getDy1() / 9525) + ", " + clientAnchor.getCol2() + ", " + (clientAnchor.getDx2() / 9525) + ", " + clientAnchor.getRow2() + ", " + (clientAnchor.getDy2() / 9525));
        }
        CellAddress cellAddress = new CellAddress(clientAnchor.getRow1(), clientAnchor.getCol1());
        if (findCellComment(cellAddress) == null) {
            return new XSSFComment(this, newComment(cellAddress), cTShape);
        }
        throw new IllegalArgumentException("Multiple cell comments in one cell are not allowed, cell: " + cellAddress);
    }

    @Internal
    public CTComment newComment(CellAddress cellAddress) {
        CTComment addNewComment = this.comments.getCommentList().addNewComment();
        addNewComment.setRef(cellAddress.formatAsString());
        addNewComment.setAuthorId(0);
        Map<CellAddress, CTComment> map = this.commentRefs;
        if (map != null) {
            map.put(cellAddress, addNewComment);
        }
        return addNewComment;
    }

    public boolean removeComment(CellAddress cellAddress) {
        String formatAsString = cellAddress.formatAsString();
        CTCommentList commentList = this.comments.getCommentList();
        if (commentList != null) {
            CTComment[] commentArray = commentList.getCommentArray();
            for (int i = 0; i < commentArray.length; i++) {
                if (formatAsString.equals(commentArray[i].getRef())) {
                    commentList.removeComment(i);
                    Map<CellAddress, CTComment> map = this.commentRefs;
                    if (map == null) {
                        return true;
                    }
                    map.remove(cellAddress);
                    return true;
                }
            }
        }
        return false;
    }

    @Internal
    public CTComments getCTComments() {
        return this.comments;
    }

    private void prepareCTCommentCache() {
        if (this.commentRefs == null) {
            this.commentRefs = new HashMap();
            for (CTComment cTComment : this.comments.getCommentList().getCommentArray()) {
                this.commentRefs.put(new CellAddress(cTComment.getRef()), cTComment);
            }
        }
    }

    private int addNewAuthor(String str) {
        int sizeOfAuthorArray = this.comments.getAuthors().sizeOfAuthorArray();
        this.comments.getAuthors().insertAuthor(sizeOfAuthorArray, str);
        return sizeOfAuthorArray;
    }

    private XSSFVMLDrawing getVMLDrawing(Sheet sheet2, boolean z) {
        if (this.vmlDrawing == null && (sheet2 instanceof OoxmlSheetExtensions)) {
            this.vmlDrawing = ((OoxmlSheetExtensions) sheet2).getVMLDrawing(z);
        }
        return this.vmlDrawing;
    }
}
