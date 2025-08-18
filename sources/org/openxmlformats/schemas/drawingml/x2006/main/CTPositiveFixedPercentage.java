package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPositiveFixedPercentage extends XmlObject {
    public static final DocumentFactory<CTPositiveFixedPercentage> Factory;
    public static final SchemaType type;

    Object getVal();

    void setVal(Object obj);

    STPositiveFixedPercentage xgetVal();

    void xsetVal(STPositiveFixedPercentage sTPositiveFixedPercentage);

    static {
        DocumentFactory<CTPositiveFixedPercentage> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpositivefixedpercentage8966type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
