package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;

public interface CTXAlign extends XmlObject {
    public static final DocumentFactory<CTXAlign> Factory;
    public static final SchemaType type;

    STXAlign.Enum getVal();

    void setVal(STXAlign.Enum enumR);

    STXAlign xgetVal();

    void xsetVal(STXAlign sTXAlign);

    static {
        DocumentFactory<CTXAlign> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxalignd265type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
