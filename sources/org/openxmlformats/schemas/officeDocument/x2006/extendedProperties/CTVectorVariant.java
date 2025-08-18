package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;

public interface CTVectorVariant extends XmlObject {
    public static final DocumentFactory<CTVectorVariant> Factory;
    public static final SchemaType type;

    CTVector addNewVector();

    CTVector getVector();

    void setVector(CTVector cTVector);

    static {
        DocumentFactory<CTVectorVariant> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctvectorvariant9d75type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
