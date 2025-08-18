package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CalcChainDocument extends XmlObject {
    public static final DocumentFactory<CalcChainDocument> Factory;
    public static final SchemaType type;

    CTCalcChain addNewCalcChain();

    CTCalcChain getCalcChain();

    void setCalcChain(CTCalcChain cTCalcChain);

    static {
        DocumentFactory<CalcChainDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "calcchainfc37doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
