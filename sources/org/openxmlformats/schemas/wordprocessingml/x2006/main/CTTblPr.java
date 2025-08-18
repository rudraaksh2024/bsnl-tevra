package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTblPr extends CTTblPrBase {
    public static final DocumentFactory<CTTblPr> Factory;
    public static final SchemaType type;

    CTTblPrChange addNewTblPrChange();

    CTTblPrChange getTblPrChange();

    boolean isSetTblPrChange();

    void setTblPrChange(CTTblPrChange cTTblPrChange);

    void unsetTblPrChange();

    static {
        DocumentFactory<CTTblPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblpr5b72type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
