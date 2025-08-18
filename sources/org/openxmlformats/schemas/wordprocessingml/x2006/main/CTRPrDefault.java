package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRPrDefault extends XmlObject {
    public static final DocumentFactory<CTRPrDefault> Factory;
    public static final SchemaType type;

    CTRPr addNewRPr();

    CTRPr getRPr();

    boolean isSetRPr();

    void setRPr(CTRPr cTRPr);

    void unsetRPr();

    static {
        DocumentFactory<CTRPrDefault> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrprdefault5ebbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
