package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTZoom extends XmlObject {
    public static final DocumentFactory<CTZoom> Factory;
    public static final SchemaType type;

    Object getPercent();

    STZoom$Enum getVal();

    boolean isSetVal();

    void setPercent(Object obj);

    void setVal(STZoom$Enum sTZoom$Enum);

    void unsetVal();

    STDecimalNumberOrPercent xgetPercent();

    STZoom xgetVal();

    void xsetPercent(STDecimalNumberOrPercent sTDecimalNumberOrPercent);

    void xsetVal(STZoom sTZoom);

    static {
        DocumentFactory<CTZoom> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctzoomc275type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
