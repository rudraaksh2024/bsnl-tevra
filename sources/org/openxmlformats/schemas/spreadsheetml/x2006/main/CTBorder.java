package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBorder extends XmlObject {
    public static final DocumentFactory<CTBorder> Factory;
    public static final SchemaType type;

    CTBorderPr addNewBottom();

    CTBorderPr addNewDiagonal();

    CTBorderPr addNewEnd();

    CTBorderPr addNewHorizontal();

    CTBorderPr addNewLeft();

    CTBorderPr addNewRight();

    CTBorderPr addNewStart();

    CTBorderPr addNewTop();

    CTBorderPr addNewVertical();

    CTBorderPr getBottom();

    CTBorderPr getDiagonal();

    boolean getDiagonalDown();

    boolean getDiagonalUp();

    CTBorderPr getEnd();

    CTBorderPr getHorizontal();

    CTBorderPr getLeft();

    boolean getOutline();

    CTBorderPr getRight();

    CTBorderPr getStart();

    CTBorderPr getTop();

    CTBorderPr getVertical();

    boolean isSetBottom();

    boolean isSetDiagonal();

    boolean isSetDiagonalDown();

    boolean isSetDiagonalUp();

    boolean isSetEnd();

    boolean isSetHorizontal();

    boolean isSetLeft();

    boolean isSetOutline();

    boolean isSetRight();

    boolean isSetStart();

    boolean isSetTop();

    boolean isSetVertical();

    void setBottom(CTBorderPr cTBorderPr);

    void setDiagonal(CTBorderPr cTBorderPr);

    void setDiagonalDown(boolean z);

    void setDiagonalUp(boolean z);

    void setEnd(CTBorderPr cTBorderPr);

    void setHorizontal(CTBorderPr cTBorderPr);

    void setLeft(CTBorderPr cTBorderPr);

    void setOutline(boolean z);

    void setRight(CTBorderPr cTBorderPr);

    void setStart(CTBorderPr cTBorderPr);

    void setTop(CTBorderPr cTBorderPr);

    void setVertical(CTBorderPr cTBorderPr);

    void unsetBottom();

    void unsetDiagonal();

    void unsetDiagonalDown();

    void unsetDiagonalUp();

    void unsetEnd();

    void unsetHorizontal();

    void unsetLeft();

    void unsetOutline();

    void unsetRight();

    void unsetStart();

    void unsetTop();

    void unsetVertical();

    XmlBoolean xgetDiagonalDown();

    XmlBoolean xgetDiagonalUp();

    XmlBoolean xgetOutline();

    void xsetDiagonalDown(XmlBoolean xmlBoolean);

    void xsetDiagonalUp(XmlBoolean xmlBoolean);

    void xsetOutline(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTBorder> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctborderf935type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
