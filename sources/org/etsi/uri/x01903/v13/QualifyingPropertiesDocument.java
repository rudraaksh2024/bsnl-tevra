package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface QualifyingPropertiesDocument extends XmlObject {
    public static final DocumentFactory<QualifyingPropertiesDocument> Factory;
    public static final SchemaType type;

    QualifyingPropertiesType addNewQualifyingProperties();

    QualifyingPropertiesType getQualifyingProperties();

    void setQualifyingProperties(QualifyingPropertiesType qualifyingPropertiesType);

    static {
        DocumentFactory<QualifyingPropertiesDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "qualifyingproperties53ccdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
