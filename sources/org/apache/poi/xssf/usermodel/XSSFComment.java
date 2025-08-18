package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.vml.CTShape;
import java.math.BigInteger;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.CommentsTable;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;

public class XSSFComment implements Comment {
    private final CTComment _comment;
    private final Comments _comments;
    private XSSFRichTextString _str;
    private final CTShape _vmlShape;

    public XSSFComment(CommentsTable commentsTable, CTComment cTComment, CTShape cTShape) {
        this((Comments) commentsTable, cTComment, cTShape);
    }

    public XSSFComment(Comments comments, CTComment cTComment, CTShape cTShape) {
        this._comment = cTComment;
        this._comments = comments;
        this._vmlShape = cTShape;
        if (cTComment != null && cTShape != null && cTShape.sizeOfClientDataArray() > 0) {
            CellReference cellReference = new CellReference(cTComment.getRef());
            CTClientData clientDataArray = cTShape.getClientDataArray(0);
            clientDataArray.setRowArray(0, new BigInteger(String.valueOf(cellReference.getRow())));
            clientDataArray.setColumnArray(0, new BigInteger(String.valueOf(cellReference.getCol())));
            avoidXmlbeansCorruptPointer(cTShape);
        }
    }

    public String getAuthor() {
        return this._comments.getAuthor(this._comment.getAuthorId());
    }

    public void setAuthor(String str) {
        this._comment.setAuthorId((long) this._comments.findAuthor(str));
        this._comments.commentUpdated(this);
    }

    public int getColumn() {
        return getAddress().getColumn();
    }

    public int getRow() {
        return getAddress().getRow();
    }

    public boolean isVisible() {
        CTShape cTShape = this._vmlShape;
        if (cTShape == null) {
            return false;
        }
        if (cTShape.sizeOfClientDataArray() > 0) {
            CTClientData clientDataArray = this._vmlShape.getClientDataArray(0);
            if (clientDataArray == null || clientDataArray.sizeOfVisibleArray() <= 0) {
                return false;
            }
        } else {
            String style = this._vmlShape.getStyle();
            if (style == null || !style.contains("visibility:visible")) {
                return false;
            }
        }
        return true;
    }

    public void setVisible(boolean z) {
        CTShape cTShape = this._vmlShape;
        if (cTShape != null) {
            if (z) {
                cTShape.setStyle("position:absolute");
                CTClientData clientDataArray = this._vmlShape.getClientDataArray(0);
                if (clientDataArray != null && clientDataArray.sizeOfVisibleArray() == 0) {
                    clientDataArray.addVisible(STTrueFalseBlank.X);
                }
            } else {
                cTShape.setStyle("position:absolute;visibility:hidden");
                CTClientData clientDataArray2 = this._vmlShape.getClientDataArray(0);
                if (clientDataArray2 != null && clientDataArray2.sizeOfVisibleArray() > 0) {
                    clientDataArray2.removeVisible(0);
                }
            }
        }
        this._comments.commentUpdated(this);
    }

    public CellAddress getAddress() {
        return new CellAddress(this._comment.getRef());
    }

    public void setAddress(int i, int i2) {
        setAddress(new CellAddress(i, i2));
    }

    public void setAddress(CellAddress cellAddress) {
        CellAddress cellAddress2 = new CellAddress(this._comment.getRef());
        if (!cellAddress.equals(cellAddress2)) {
            this._comment.setRef(cellAddress.formatAsString());
            this._comments.referenceUpdated(cellAddress2, this);
            CTShape cTShape = this._vmlShape;
            if (cTShape != null) {
                CTClientData clientDataArray = cTShape.getClientDataArray(0);
                clientDataArray.setRowArray(0, new BigInteger(String.valueOf(cellAddress.getRow())));
                clientDataArray.setColumnArray(0, new BigInteger(String.valueOf(cellAddress.getColumn())));
                avoidXmlbeansCorruptPointer(this._vmlShape);
            }
            this._comments.commentUpdated(this);
        }
    }

    public void setColumn(int i) {
        setAddress(getRow(), i);
    }

    public void setRow(int i) {
        setAddress(i, getColumn());
    }

    public XSSFRichTextString getString() {
        if (this._str == null && this._comment.getText() != null) {
            this._str = new XSSFRichTextString(this._comment.getText());
        }
        return this._str;
    }

    public void setString(RichTextString richTextString) {
        if (richTextString instanceof XSSFRichTextString) {
            XSSFRichTextString xSSFRichTextString = (XSSFRichTextString) richTextString;
            this._str = xSSFRichTextString;
            this._comment.setText(xSSFRichTextString.getCTRst());
            this._comments.commentUpdated(this);
            return;
        }
        throw new IllegalArgumentException("Only XSSFRichTextString argument is supported");
    }

    public void setString(String str) {
        setString((RichTextString) new XSSFRichTextString(str));
    }

    public ClientAnchor getClientAnchor() {
        CTShape cTShape = this._vmlShape;
        if (cTShape == null) {
            return null;
        }
        int[] iArr = new int[8];
        String[] split = cTShape.getClientDataArray(0).getAnchorArray(0).split(",");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            iArr[i2] = Integer.parseInt(split[i].trim());
            i++;
            i2++;
        }
        return new XSSFClientAnchor(iArr[1] * 9525, iArr[3] * 9525, iArr[5] * 9525, iArr[7] * 9525, iArr[0], iArr[2], iArr[4], iArr[6]);
    }

    public CTComment getCTComment() {
        return this._comment;
    }

    /* access modifiers changed from: protected */
    public CTShape getCTShape() {
        return this._vmlShape;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFComment)) {
            return false;
        }
        XSSFComment xSSFComment = (XSSFComment) obj;
        if (getCTComment() == xSSFComment.getCTComment() && getCTShape() == xSSFComment.getCTShape()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((getRow() * 17) + getColumn()) * 31;
    }

    private static void avoidXmlbeansCorruptPointer(CTShape cTShape) {
        cTShape.getClientDataList().toString();
    }
}
