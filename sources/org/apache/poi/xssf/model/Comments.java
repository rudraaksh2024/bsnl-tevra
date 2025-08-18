package org.apache.poi.xssf.model;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFComment;

public interface Comments {
    void commentUpdated(XSSFComment xSSFComment);

    XSSFComment createNewComment(ClientAnchor clientAnchor);

    int findAuthor(String str);

    XSSFComment findCellComment(CellAddress cellAddress);

    String getAuthor(long j);

    Iterator<CellAddress> getCellAddresses();

    int getNumberOfAuthors();

    int getNumberOfComments();

    void referenceUpdated(CellAddress cellAddress, XSSFComment xSSFComment);

    boolean removeComment(CellAddress cellAddress);

    @Internal
    void setSheet(Sheet sheet);
}
