package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromH;

public interface CTPosH extends XmlObject {
    public static final DocumentFactory<CTPosH> Factory;
    public static final SchemaType type;

    STAlignH$Enum getAlign();

    int getPosOffset();

    STRelFromH.Enum getRelativeFrom();

    boolean isSetAlign();

    boolean isSetPosOffset();

    void setAlign(STAlignH$Enum sTAlignH$Enum);

    void setPosOffset(int i);

    void setRelativeFrom(STRelFromH.Enum enumR);

    void unsetAlign();

    void unsetPosOffset();

    STAlignH xgetAlign();

    STPositionOffset xgetPosOffset();

    STRelFromH xgetRelativeFrom();

    void xsetAlign(STAlignH sTAlignH);

    void xsetPosOffset(STPositionOffset sTPositionOffset);

    void xsetRelativeFrom(STRelFromH sTRelFromH);

    static {
        DocumentFactory<CTPosH> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctposh7fabtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
