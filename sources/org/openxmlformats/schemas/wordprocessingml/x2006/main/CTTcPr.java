package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTcPr extends CTTcPrInner {
    public static final DocumentFactory<CTTcPr> Factory;
    public static final SchemaType type;

    CTTcPrChange addNewTcPrChange();

    CTTcPrChange getTcPrChange();

    boolean isSetTcPrChange();

    void setTcPrChange(CTTcPrChange cTTcPrChange);

    void unsetTcPrChange();

    static {
        DocumentFactory<CTTcPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcpree37type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
