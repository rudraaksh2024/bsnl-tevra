package org.openxmlformats.schemas.officeDocument.x2006.customProperties;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTProperties extends XmlObject {
    public static final DocumentFactory<CTProperties> Factory;
    public static final SchemaType type;

    CTProperty addNewProperty();

    CTProperty getPropertyArray(int i);

    CTProperty[] getPropertyArray();

    List<CTProperty> getPropertyList();

    CTProperty insertNewProperty(int i);

    void removeProperty(int i);

    void setPropertyArray(int i, CTProperty cTProperty);

    void setPropertyArray(CTProperty[] cTPropertyArr);

    int sizeOfPropertyArray();

    static {
        DocumentFactory<CTProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctproperties2c18type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
