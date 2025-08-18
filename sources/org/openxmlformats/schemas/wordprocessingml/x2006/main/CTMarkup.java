package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTMarkup extends XmlObject {
    public static final DocumentFactory<CTMarkup> Factory;
    public static final SchemaType type;

    BigInteger getId();

    void setId(BigInteger bigInteger);

    STDecimalNumber xgetId();

    void xsetId(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTMarkup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkup2d80type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
