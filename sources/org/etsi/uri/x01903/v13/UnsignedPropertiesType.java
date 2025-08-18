package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface UnsignedPropertiesType extends XmlObject {
    public static final DocumentFactory<UnsignedPropertiesType> Factory;
    public static final SchemaType type;

    UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties();

    UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties();

    String getId();

    UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties();

    UnsignedSignaturePropertiesType getUnsignedSignatureProperties();

    boolean isSetId();

    boolean isSetUnsignedDataObjectProperties();

    boolean isSetUnsignedSignatureProperties();

    void setId(String str);

    void setUnsignedDataObjectProperties(UnsignedDataObjectPropertiesType unsignedDataObjectPropertiesType);

    void setUnsignedSignatureProperties(UnsignedSignaturePropertiesType unsignedSignaturePropertiesType);

    void unsetId();

    void unsetUnsignedDataObjectProperties();

    void unsetUnsignedSignatureProperties();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<UnsignedPropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "unsignedpropertiestype49d6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
