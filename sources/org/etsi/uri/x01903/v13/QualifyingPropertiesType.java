package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface QualifyingPropertiesType extends XmlObject {
    public static final DocumentFactory<QualifyingPropertiesType> Factory;
    public static final SchemaType type;

    SignedPropertiesType addNewSignedProperties();

    UnsignedPropertiesType addNewUnsignedProperties();

    String getId();

    SignedPropertiesType getSignedProperties();

    String getTarget();

    UnsignedPropertiesType getUnsignedProperties();

    boolean isSetId();

    boolean isSetSignedProperties();

    boolean isSetUnsignedProperties();

    void setId(String str);

    void setSignedProperties(SignedPropertiesType signedPropertiesType);

    void setTarget(String str);

    void setUnsignedProperties(UnsignedPropertiesType unsignedPropertiesType);

    void unsetId();

    void unsetSignedProperties();

    void unsetUnsignedProperties();

    XmlID xgetId();

    XmlAnyURI xgetTarget();

    void xsetId(XmlID xmlID);

    void xsetTarget(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<QualifyingPropertiesType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "qualifyingpropertiestype9e16type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
