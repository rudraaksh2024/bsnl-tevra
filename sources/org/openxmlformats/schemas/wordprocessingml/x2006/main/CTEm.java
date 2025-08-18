package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;

public interface CTEm extends XmlObject {
    public static final DocumentFactory<CTEm> Factory;
    public static final SchemaType type;

    STEm.Enum getVal();

    void setVal(STEm.Enum enumR);

    STEm xgetVal();

    void xsetVal(STEm sTEm);

    static {
        DocumentFactory<CTEm> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctemdc80type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
