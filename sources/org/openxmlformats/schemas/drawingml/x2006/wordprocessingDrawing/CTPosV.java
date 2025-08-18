package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromV;

public interface CTPosV extends XmlObject {
    public static final DocumentFactory<CTPosV> Factory;
    public static final SchemaType type;

    STAlignV$Enum getAlign();

    int getPosOffset();

    STRelFromV.Enum getRelativeFrom();

    boolean isSetAlign();

    boolean isSetPosOffset();

    void setAlign(STAlignV$Enum sTAlignV$Enum);

    void setPosOffset(int i);

    void setRelativeFrom(STRelFromV.Enum enumR);

    void unsetAlign();

    void unsetPosOffset();

    STAlignV xgetAlign();

    STPositionOffset xgetPosOffset();

    STRelFromV xgetRelativeFrom();

    void xsetAlign(STAlignV sTAlignV);

    void xsetPosOffset(STPositionOffset sTPositionOffset);

    void xsetRelativeFrom(STRelFromV sTRelFromV);

    static {
        DocumentFactory<CTPosV> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctposv63ddtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
