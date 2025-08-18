package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;

public interface CTVectorLpstr extends XmlObject {
    public static final DocumentFactory<CTVectorLpstr> Factory;
    public static final SchemaType type;

    CTVector addNewVector();

    CTVector getVector();

    void setVector(CTVector cTVector);

    static {
        DocumentFactory<CTVectorLpstr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctvectorlpstr9b1dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
