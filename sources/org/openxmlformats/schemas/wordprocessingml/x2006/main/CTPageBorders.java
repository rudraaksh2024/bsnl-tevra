package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderDisplay;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderOffset;

public interface CTPageBorders extends XmlObject {
    public static final DocumentFactory<CTPageBorders> Factory;
    public static final SchemaType type;

    CTBottomPageBorder addNewBottom();

    CTPageBorder addNewLeft();

    CTPageBorder addNewRight();

    CTTopPageBorder addNewTop();

    CTBottomPageBorder getBottom();

    STPageBorderDisplay.Enum getDisplay();

    CTPageBorder getLeft();

    STPageBorderOffset.Enum getOffsetFrom();

    CTPageBorder getRight();

    CTTopPageBorder getTop();

    STPageBorderZOrder$Enum getZOrder();

    boolean isSetBottom();

    boolean isSetDisplay();

    boolean isSetLeft();

    boolean isSetOffsetFrom();

    boolean isSetRight();

    boolean isSetTop();

    boolean isSetZOrder();

    void setBottom(CTBottomPageBorder cTBottomPageBorder);

    void setDisplay(STPageBorderDisplay.Enum enumR);

    void setLeft(CTPageBorder cTPageBorder);

    void setOffsetFrom(STPageBorderOffset.Enum enumR);

    void setRight(CTPageBorder cTPageBorder);

    void setTop(CTTopPageBorder cTTopPageBorder);

    void setZOrder(STPageBorderZOrder$Enum sTPageBorderZOrder$Enum);

    void unsetBottom();

    void unsetDisplay();

    void unsetLeft();

    void unsetOffsetFrom();

    void unsetRight();

    void unsetTop();

    void unsetZOrder();

    STPageBorderDisplay xgetDisplay();

    STPageBorderOffset xgetOffsetFrom();

    STPageBorderZOrder xgetZOrder();

    void xsetDisplay(STPageBorderDisplay sTPageBorderDisplay);

    void xsetOffsetFrom(STPageBorderOffset sTPageBorderOffset);

    void xsetZOrder(STPageBorderZOrder sTPageBorderZOrder);

    static {
        DocumentFactory<CTPageBorders> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagebordersa4datype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
