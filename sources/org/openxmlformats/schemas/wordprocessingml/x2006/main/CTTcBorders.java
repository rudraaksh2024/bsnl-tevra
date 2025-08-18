package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTcBorders extends XmlObject {
    public static final DocumentFactory<CTTcBorders> Factory;
    public static final SchemaType type;

    CTBorder addNewBottom();

    CTBorder addNewEnd();

    CTBorder addNewInsideH();

    CTBorder addNewInsideV();

    CTBorder addNewLeft();

    CTBorder addNewRight();

    CTBorder addNewStart();

    CTBorder addNewTl2Br();

    CTBorder addNewTop();

    CTBorder addNewTr2Bl();

    CTBorder getBottom();

    CTBorder getEnd();

    CTBorder getInsideH();

    CTBorder getInsideV();

    CTBorder getLeft();

    CTBorder getRight();

    CTBorder getStart();

    CTBorder getTl2Br();

    CTBorder getTop();

    CTBorder getTr2Bl();

    boolean isSetBottom();

    boolean isSetEnd();

    boolean isSetInsideH();

    boolean isSetInsideV();

    boolean isSetLeft();

    boolean isSetRight();

    boolean isSetStart();

    boolean isSetTl2Br();

    boolean isSetTop();

    boolean isSetTr2Bl();

    void setBottom(CTBorder cTBorder);

    void setEnd(CTBorder cTBorder);

    void setInsideH(CTBorder cTBorder);

    void setInsideV(CTBorder cTBorder);

    void setLeft(CTBorder cTBorder);

    void setRight(CTBorder cTBorder);

    void setStart(CTBorder cTBorder);

    void setTl2Br(CTBorder cTBorder);

    void setTop(CTBorder cTBorder);

    void setTr2Bl(CTBorder cTBorder);

    void unsetBottom();

    void unsetEnd();

    void unsetInsideH();

    void unsetInsideV();

    void unsetLeft();

    void unsetRight();

    void unsetStart();

    void unsetTl2Br();

    void unsetTop();

    void unsetTr2Bl();

    static {
        DocumentFactory<CTTcBorders> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcbordersa5fatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
