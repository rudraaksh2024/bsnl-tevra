package org.etsi.uri.x01903.v14;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TimeStampValidationDataDocument extends XmlObject {
    public static final DocumentFactory<TimeStampValidationDataDocument> Factory;
    public static final SchemaType type;

    ValidationDataType addNewTimeStampValidationData();

    ValidationDataType getTimeStampValidationData();

    void setTimeStampValidationData(ValidationDataType validationDataType);

    static {
        DocumentFactory<TimeStampValidationDataDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "timestampvalidationdataeb4bdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
