package org.apache.poi.sl.usermodel;

import java.awt.geom.Point2D;
import java.util.Date;

public interface Comment {
    String getAuthor();

    String getAuthorInitials();

    Date getDate();

    Point2D getOffset();

    String getText();

    void setAuthor(String str);

    void setAuthorInitials(String str);

    void setDate(Date date);

    void setOffset(Point2D point2D);

    void setText(String str);
}
