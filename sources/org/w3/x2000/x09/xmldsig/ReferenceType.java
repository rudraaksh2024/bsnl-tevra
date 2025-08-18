package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ReferenceType extends XmlObject {
    public static final DocumentFactory<ReferenceType> Factory;
    public static final SchemaType type;

    DigestMethodType addNewDigestMethod();

    TransformsType addNewTransforms();

    DigestMethodType getDigestMethod();

    byte[] getDigestValue();

    String getId();

    TransformsType getTransforms();

    String getType();

    String getURI();

    boolean isSetId();

    boolean isSetTransforms();

    boolean isSetType();

    boolean isSetURI();

    void setDigestMethod(DigestMethodType digestMethodType);

    void setDigestValue(byte[] bArr);

    void setId(String str);

    void setTransforms(TransformsType transformsType);

    void setType(String str);

    void setURI(String str);

    void unsetId();

    void unsetTransforms();

    void unsetType();

    void unsetURI();

    DigestValueType xgetDigestValue();

    XmlID xgetId();

    XmlAnyURI xgetType();

    XmlAnyURI xgetURI();

    void xsetDigestValue(DigestValueType digestValueType);

    void xsetId(XmlID xmlID);

    void xsetType(XmlAnyURI xmlAnyURI);

    void xsetURI(XmlAnyURI xmlAnyURI);

    static {
        DocumentFactory<ReferenceType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "referencetypef44ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
