package org.apache.poi.xslf.usermodel;

import java.awt.geom.Point2D;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

public class XSLFComment implements Comment {
    final XSLFCommentAuthors authors;
    final CTComment comment;

    XSLFComment(CTComment cTComment, XSLFCommentAuthors xSLFCommentAuthors) {
        this.comment = cTComment;
        this.authors = xSLFCommentAuthors;
    }

    public String getAuthor() {
        return this.authors.getAuthorById(this.comment.getAuthorId()).getName();
    }

    public void setAuthor(String str) {
        if (str != null) {
            CTCommentAuthorList cTCommentAuthorsList = this.authors.getCTCommentAuthorsList();
            long j = -1;
            for (CTCommentAuthor cTCommentAuthor : cTCommentAuthorsList.getCmAuthorArray()) {
                j = Math.max(cTCommentAuthor.getId(), j);
                if (str.equals(cTCommentAuthor.getName())) {
                    this.comment.setAuthorId(cTCommentAuthor.getId());
                    return;
                }
            }
            CTCommentAuthor addNewCmAuthor = cTCommentAuthorsList.addNewCmAuthor();
            addNewCmAuthor.setName(str);
            long j2 = j + 1;
            addNewCmAuthor.setId(j2);
            addNewCmAuthor.setInitials(str.replaceAll("\\s*(\\w)\\S*", "$1").toUpperCase(LocaleUtil.getUserLocale()));
            this.comment.setAuthorId(j2);
            return;
        }
        throw new IllegalArgumentException("author must not be null");
    }

    public String getAuthorInitials() {
        CTCommentAuthor authorById = this.authors.getAuthorById(this.comment.getAuthorId());
        if (authorById == null) {
            return null;
        }
        return authorById.getInitials();
    }

    public void setAuthorInitials(String str) {
        CTCommentAuthor authorById = this.authors.getAuthorById(this.comment.getAuthorId());
        if (authorById != null) {
            authorById.setInitials(str);
        }
    }

    public String getText() {
        return this.comment.getText();
    }

    public void setText(String str) {
        this.comment.setText(str);
    }

    public Date getDate() {
        Calendar dt = this.comment.getDt();
        if (dt == null) {
            return null;
        }
        return dt.getTime();
    }

    public void setDate(Date date) {
        Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
        localeCalendar.setTime(date);
        this.comment.setDt(localeCalendar);
    }

    public Point2D getOffset() {
        CTPoint2D pos = this.comment.getPos();
        return new Point2D.Double(Units.toPoints(POIXMLUnits.parseLength(pos.xgetX())), Units.toPoints(POIXMLUnits.parseLength(pos.xgetY())));
    }

    public void setOffset(Point2D point2D) {
        CTPoint2D pos = this.comment.getPos();
        if (pos == null) {
            pos = this.comment.addNewPos();
        }
        pos.setX(Integer.valueOf(Units.toEMU(point2D.getX())));
        pos.setY(Integer.valueOf(Units.toEMU(point2D.getY())));
    }
}
