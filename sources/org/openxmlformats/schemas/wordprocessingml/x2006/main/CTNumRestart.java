package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRestartNumber;

public interface CTNumRestart extends XmlObject {
    public static final DocumentFactory<CTNumRestart> Factory;
    public static final SchemaType type;

    STRestartNumber.Enum getVal();

    void setVal(STRestartNumber.Enum enumR);

    STRestartNumber xgetVal();

    void xsetVal(STRestartNumber sTRestartNumber);

    static {
        DocumentFactory<CTNumRestart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumrestart261ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
