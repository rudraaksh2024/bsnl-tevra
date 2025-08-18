package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface IdentifierType extends XmlAnyURI {
    public static final DocumentFactory<IdentifierType> Factory;
    public static final SchemaType type;

    QualifierType$Enum getQualifier();

    boolean isSetQualifier();

    void setQualifier(QualifierType$Enum qualifierType$Enum);

    void unsetQualifier();

    QualifierType xgetQualifier();

    void xsetQualifier(QualifierType qualifierType);

    static {
        DocumentFactory<IdentifierType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "identifiertype2cb7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
