package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFixedPercentage extends XmlObject {
    public static final DocumentFactory<CTFixedPercentage> Factory;
    public static final SchemaType type;

    Object getVal();

    void setVal(Object obj);

    STFixedPercentage xgetVal();

    void xsetVal(STFixedPercentage sTFixedPercentage);

    static {
        DocumentFactory<CTFixedPercentage> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfixedpercentagea2dftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
