package org.apache.poi.xssf.binary;

import com.microsoft.schemas.vml.CTShape;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;

@Internal
class XSSFBComment extends XSSFComment {
    private final String author;
    private final CellAddress cellAddress;
    private final XSSFBRichTextString comment;
    private boolean visible = true;

    public ClientAnchor getClientAnchor() {
        return null;
    }

    XSSFBComment(CellAddress cellAddress2, String str, String str2) {
        super((CommentsTable) null, (CTComment) null, (CTShape) null);
        this.cellAddress = cellAddress2;
        this.author = str;
        this.comment = new XSSFBRichTextString(str2);
    }

    public void setVisible(boolean z) {
        throw new IllegalArgumentException("XSSFBComment is read only.");
    }

    public boolean isVisible() {
        return this.visible;
    }

    public CellAddress getAddress() {
        return this.cellAddress;
    }

    public void setAddress(CellAddress cellAddress2) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    public void setAddress(int i, int i2) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    public int getRow() {
        return this.cellAddress.getRow();
    }

    public void setRow(int i) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    public int getColumn() {
        return this.cellAddress.getColumn();
    }

    public void setColumn(int i) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }

    public XSSFBRichTextString getString() {
        return this.comment;
    }

    public void setString(RichTextString richTextString) {
        throw new IllegalArgumentException("XSSFBComment is read only");
    }
}
