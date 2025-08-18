package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSignatureTime extends XmlObject {
    public static final DocumentFactory<CTSignatureTime> Factory;
    public static final SchemaType type;

    String getFormat();

    String getValue();

    void setFormat(String str);

    void setValue(String str);

    STFormat xgetFormat();

    STValue xgetValue();

    void xsetFormat(STFormat sTFormat);

    void xsetValue(STValue sTValue);

    static {
        DocumentFactory<CTSignatureTime> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsignaturetime461dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
