package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPaperSource extends XmlObject {
    public static final DocumentFactory<CTPaperSource> Factory;
    public static final SchemaType type;

    BigInteger getFirst();

    BigInteger getOther();

    boolean isSetFirst();

    boolean isSetOther();

    void setFirst(BigInteger bigInteger);

    void setOther(BigInteger bigInteger);

    void unsetFirst();

    void unsetOther();

    STDecimalNumber xgetFirst();

    STDecimalNumber xgetOther();

    void xsetFirst(STDecimalNumber sTDecimalNumber);

    void xsetOther(STDecimalNumber sTDecimalNumber);

    static {
        DocumentFactory<CTPaperSource> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpapersource8aabtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
