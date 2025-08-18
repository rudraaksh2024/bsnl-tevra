package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblOverlap;

public interface CTTblOverlap extends XmlObject {
    public static final DocumentFactory<CTTblOverlap> Factory;
    public static final SchemaType type;

    STTblOverlap.Enum getVal();

    void setVal(STTblOverlap.Enum enumR);

    STTblOverlap xgetVal();

    void xsetVal(STTblOverlap sTTblOverlap);

    static {
        DocumentFactory<CTTblOverlap> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttbloverlap231ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
