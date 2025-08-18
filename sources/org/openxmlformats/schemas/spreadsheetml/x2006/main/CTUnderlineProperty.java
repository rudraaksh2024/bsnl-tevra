package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

public interface CTUnderlineProperty extends XmlObject {
    public static final DocumentFactory<CTUnderlineProperty> Factory;
    public static final SchemaType type;

    STUnderlineValues.Enum getVal();

    boolean isSetVal();

    void setVal(STUnderlineValues.Enum enumR);

    void unsetVal();

    STUnderlineValues xgetVal();

    void xsetVal(STUnderlineValues sTUnderlineValues);

    static {
        DocumentFactory<CTUnderlineProperty> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctunderlineproperty8e20type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
