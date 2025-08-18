package org.openxmlformats.schemas.officeDocument.x2006.customProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface PropertiesDocument extends XmlObject {
    public static final DocumentFactory<PropertiesDocument> Factory;
    public static final SchemaType type;

    CTProperties addNewProperties();

    CTProperties getProperties();

    void setProperties(CTProperties cTProperties);

    static {
        DocumentFactory<PropertiesDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "properties288cdoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
