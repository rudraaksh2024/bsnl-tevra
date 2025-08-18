package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;

public interface DigestAlgAndValueType extends XmlObject {
    public static final DocumentFactory<DigestAlgAndValueType> Factory;
    public static final SchemaType type;

    DigestMethodType addNewDigestMethod();

    DigestMethodType getDigestMethod();

    byte[] getDigestValue();

    void setDigestMethod(DigestMethodType digestMethodType);

    void setDigestValue(byte[] bArr);

    DigestValueType xgetDigestValue();

    void xsetDigestValue(DigestValueType digestValueType);

    static {
        DocumentFactory<DigestAlgAndValueType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "digestalgandvaluetype234etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
