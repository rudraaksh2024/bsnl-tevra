package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSdtDateMappingType;

public interface CTSdtDateMappingType extends XmlObject {
    public static final DocumentFactory<CTSdtDateMappingType> Factory;
    public static final SchemaType type;

    STSdtDateMappingType.Enum getVal();

    boolean isSetVal();

    void setVal(STSdtDateMappingType.Enum enumR);

    void unsetVal();

    STSdtDateMappingType xgetVal();

    void xsetVal(STSdtDateMappingType sTSdtDateMappingType);

    static {
        DocumentFactory<CTSdtDateMappingType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtdatemappingtype5fb1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
