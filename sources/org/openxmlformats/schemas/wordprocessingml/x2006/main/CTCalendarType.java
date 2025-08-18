package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STCalendarType;

public interface CTCalendarType extends XmlObject {
    public static final DocumentFactory<CTCalendarType> Factory;
    public static final SchemaType type;

    STCalendarType.Enum getVal();

    boolean isSetVal();

    void setVal(STCalendarType.Enum enumR);

    void unsetVal();

    STCalendarType xgetVal();

    void xsetVal(STCalendarType sTCalendarType);

    static {
        DocumentFactory<CTCalendarType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcalendartyped1d0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
