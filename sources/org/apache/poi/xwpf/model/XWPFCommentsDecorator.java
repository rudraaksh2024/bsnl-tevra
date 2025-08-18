package org.apache.poi.xwpf.model;

import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;

public class XWPFCommentsDecorator extends XWPFParagraphDecorator {
    private StringBuilder commentText;

    public XWPFCommentsDecorator(XWPFParagraphDecorator xWPFParagraphDecorator) {
        this(xWPFParagraphDecorator.paragraph, xWPFParagraphDecorator);
    }

    public XWPFCommentsDecorator(XWPFParagraph xWPFParagraph, XWPFParagraphDecorator xWPFParagraphDecorator) {
        super(xWPFParagraph, xWPFParagraphDecorator);
        this.commentText = new StringBuilder(64);
        for (CTMarkupRange id : xWPFParagraph.getCTP().getCommentRangeStartArray()) {
            XWPFComment commentByID = xWPFParagraph.getDocument().getCommentByID(id.getId().toString());
            if (commentByID != null) {
                this.commentText.append("\tComment by ").append(commentByID.getAuthor()).append(": ").append(commentByID.getText());
            }
        }
    }

    public String getCommentText() {
        return this.commentText.toString();
    }

    public String getText() {
        return super.getText() + this.commentText;
    }
}
